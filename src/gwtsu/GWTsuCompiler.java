package gwtsu;

import com.google.gwt.thirdparty.guava.common.io.Files;
import com.google.gwt.thirdparty.guava.common.io.InputSupplier;
import gw.lang.Gosu;
import gw.lang.ir.IRClass;
import gw.lang.reflect.IType;
import gw.lang.reflect.TypeSystem;
import gw.lang.reflect.gs.GosuClassTypeLoader;
import gw.lang.reflect.gs.IGosuClass;
import gw.util.GosuClassUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
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
    GWTSuIRClassCompiler compiler = new GWTSuIRClassCompiler();
    GosuClassTypeLoader gosuClassTypeLoader = TypeSystem.getTypeLoader(GosuClassTypeLoader.class);
    Set<? extends CharSequence> typeNames = gosuClassTypeLoader.getRepository().getAllTypeNames(
            new String[]{"gs", "gsx", "gst"});
    Class<?> transformerClass = Class.forName("gw.internal.gosu.ir.transform.GosuClassTransformer");
    Class<?> classClass = Class.forName("gw.internal.gosu.parser.IGosuClassInternal");
    Method compile = transformerClass.getMethod("compile", classClass);
    for (CharSequence typeName : typeNames) {
      if (typeName.toString().startsWith("gw.vark.") || typeName.toString().startsWith("gwtsu.")) {
        // oh god how did this get here I am not good with computers
        continue;
      }
      IType type = TypeSystem.getByFullNameIfValid(typeName.toString());
      if (type instanceof IGosuClass) {
        try {
          String packageName = GosuClassUtil.getPackage(type.getName());
          File dir = new File(gwtsuCache, packageName.replace('.', File.separatorChar));
          File javaFile = new File(dir, GosuClassUtil.getNameNoPackage(type.getName()) + ".java");
          if (!javaFile.exists() ||
                  javaFile.lastModified() < ((IGosuClass) type).getSourceFileHandle().getFileTimestamp()) {
            IRClass irClass = (IRClass) compile.invoke(null, type);
            String javaSource = compiler.compileToJava(irClass);
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

}
