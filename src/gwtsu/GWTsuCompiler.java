package gwtsu;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.dev.cfg.ModuleDef;
import com.google.gwt.dev.cfg.ModuleDefLoader;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Sets;
import com.google.gwt.thirdparty.guava.common.io.Files;
import com.google.gwt.thirdparty.guava.common.io.InputSupplier;
import gw.lang.Gosu;
import gw.lang.ir.IRClass;
import gw.lang.parser.expressions.ITypeLiteralExpression;
import gw.lang.parser.statements.IClassFileStatement;
import gw.lang.reflect.IType;
import gw.lang.reflect.TypeSystem;
import gw.lang.reflect.gs.IGosuClass;
import gw.util.GosuClassUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author kprevas
 */
public class GWTsuCompiler {

  public static void main(String[] args) throws Exception {
    System.out.println("Pre-compiling Gosu classes...");
    URL[] gwtsuCacheUrls = new URL[1];
    File gwtsuCache = new File("gwtsu-cache");
    Gosu.init(Arrays.asList(new File("src")));

    List<String> modules = Lists.newArrayList();
    Set<String> entryPoints = Sets.newHashSet();
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.startsWith("-")) {
        i++;
      } else {
        modules.add(arg);
      }
    }
    for (String module : modules) {
      ModuleDef moduleDef = ModuleDefLoader.loadFromClassPath(TreeLogger.NULL, module);
      entryPoints.addAll(Arrays.asList(moduleDef.getEntryPointTypeNames()));
    }

    Set<IGosuClass> gosuClasses = Sets.newHashSet();
    for (String entryPoint : entryPoints) {
      IType type = TypeSystem.getByFullNameIfValid(entryPoint);
      if (type instanceof IGosuClass) {
        findReferencedTypes((IGosuClass) type, gosuClasses);
      }
    }
    Class<?> transformerClass = Class.forName("gw.internal.gosu.ir.transform.GosuClassTransformer");
    Class<?> classClass = Class.forName("gw.internal.gosu.parser.IGosuClassInternal");
    Method compile = transformerClass.getMethod("compile", classClass);
    for (IGosuClass type : gosuClasses) {
      if (type instanceof IGosuClass) {
        try {
          String packageName = GosuClassUtil.getPackage(type.getName());
          File dir = new File(gwtsuCache, packageName.replace('.', File.separatorChar));
          File javaFile = new File(dir, GosuClassUtil.getNameNoPackage(type.getName()) + ".java");
          if (!javaFile.exists() ||
                  javaFile.lastModified() < ((IGosuClass) type).getSourceFileHandle().getFileTimestamp()) {
            IRClass irClass = (IRClass) compile.invoke(null, type);
            String javaSource = new GWTSuIRClassCompiler(irClass).compileToJava();
            dir.mkdirs();
            FileUtils.writeStringToFile(javaFile, javaSource);
          }
        } catch (Throwable t) {
          t.printStackTrace();
        }
      }
    }
    File utilFile = new File(gwtsuCache, "Util.java");
    Files.copy(new InputSupplier<InputStream>() {
      @Override
      public InputStream getInput() throws IOException {
        return GWTsuCompiler.class.getClassLoader().getResourceAsStream("Util.java");
      }
    }, utilFile);
    gwtsuCacheUrls[0] = gwtsuCache.toURI().toURL();
    Thread.currentThread().setContextClassLoader(
            new URLClassLoader(gwtsuCacheUrls, Thread.currentThread().getContextClassLoader()));
    com.google.gwt.dev.Compiler.main(args);
    System.exit(0);
  }

  private static void findReferencedTypes(IGosuClass gsClass, Set<IGosuClass> gosuClasses) {
    gosuClasses.add(gsClass);
    gsClass.getBackingClass();
    gsClass = TypeSystem.getPureGenericType(gsClass);
    if (!gsClass.isValid()) {
      System.err.println(gsClass.getName() + " has errors.");
      return;
    }

    IType supertype = gsClass.getSupertype();
    if (supertype instanceof IGosuClass) {
      findReferencedTypes((IGosuClass) supertype, gosuClasses);
    }
    for (IType iface : gsClass.getInterfaces()) {
      if (iface instanceof IGosuClass) {
        findReferencedTypes((IGosuClass) iface, gosuClasses);
      }
    }
    for (IType innerClass : gsClass.getInnerClasses()) {
      if (innerClass instanceof IGosuClass) {
        findReferencedTypes((IGosuClass) innerClass, gosuClasses);
      }
    }
    List<ITypeLiteralExpression> results = new ArrayList<ITypeLiteralExpression>();
    IClassFileStatement classFileStatement = gsClass.getClassStatement().getClassFileStatement();
    if (classFileStatement != null) {
      classFileStatement.getContainedParsedElementsByType(ITypeLiteralExpression.class, results);
      for (ITypeLiteralExpression tl : results) {
        IType type = tl.getType().getType();
        if (type instanceof IGosuClass) {
          findReferencedTypes((IGosuClass) (type.isParameterizedType() ? type.getGenericType() : type),
                  gosuClasses);
        }
      }
    }

  }

}
