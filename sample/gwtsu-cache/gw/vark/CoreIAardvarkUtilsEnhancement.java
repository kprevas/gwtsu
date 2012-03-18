// Compiled from CoreIAardvarkUtilsEnhancement.gsx
public gw.vark.CoreIAardvarkUtilsEnhancement extends Object{

  public static void setProjectName(String name) {
    Aardvark.getProject().setName(name);
    return;
  }

  public static void setDefaultTarget(String target) {
    Aardvark.getProject().setDefaultTarget(target);
    return;
  }

  public static void setBaseDir(File dir) {
    Aardvark.getProject().setBaseDir(dir);
    return;
  }

  public static void log(Object o) {
    CoreIAardvarkUtilsEnhancement.logInfo(o);
    return;
  }

  public static void logError(Object o) {
    Aardvark.getProject().log(((String) TypeAsTransformer.coerceValue(o, TypeSystem.get(java.lang.String.class), StringCoercer.instance())), Project.MSG_ERR);
    return;
  }

  public static void logWarn(Object o) {
    Aardvark.getProject().log(((String) TypeAsTransformer.coerceValue(o, TypeSystem.get(java.lang.String.class), StringCoercer.instance())), Project.MSG_WARN);
    return;
  }

  public static void logInfo(Object o) {
    Aardvark.getProject().log(((String) TypeAsTransformer.coerceValue(o, TypeSystem.get(java.lang.String.class), StringCoercer.instance())), Project.MSG_INFO);
    return;
  }

  public static void logVerbose(Object o) {
    Aardvark.getProject().log(((String) TypeAsTransformer.coerceValue(o, TypeSystem.get(java.lang.String.class), StringCoercer.instance())), Project.MSG_VERBOSE);
    return;
  }

  public static void logDebug(Object o) {
    Aardvark.getProject().log(((String) TypeAsTransformer.coerceValue(o, TypeSystem.get(java.lang.String.class), StringCoercer.instance())), Project.MSG_DEBUG);
    return;
  }

  public static Path path() {
    return new Path(Aardvark.getProject());
  }

  public static Path path(File file) {
    return [[
      *temp0 = CoreIAardvarkUtilsEnhancement.path();
      *temp1 = file;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CorePathEnhancement.withFile(*temp0, *temp1)
    ]];
  }

  public static Path path(FileSet files) {
    return [[
      *temp0 = CoreIAardvarkUtilsEnhancement.path();
      *temp1 = files;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CorePathEnhancement.withFileset(*temp0, *temp1)
    ]];
  }

  public static Path classpath() {
    return CoreIAardvarkUtilsEnhancement.path();
  }

  public static Path classpath(File file) {
    return CoreIAardvarkUtilsEnhancement.path(file);
  }

  public static Path classpath(FileSet fs) {
    return CoreIAardvarkUtilsEnhancement.path(fs);
  }

  public static File file(String s) {
    f = new File(s);
    if ((f.isAbsolute() || s.startsWith("/"))) {
      return f;
    } else {
      return new File(Aardvark.getProject().getBaseDir(), s);
    }
  }

  public static PomHelper pom(String pomFile) {
    return PomHelper.load(CoreIAardvarkUtilsEnhancement.file(pomFile));
  }

  public static String getProperty(String propName) {
    return Aardvark.getProject().getProperty(propName);
  }

  public static String getRequiredProperty(String propName) {
    prop = CoreIAardvarkUtilsEnhancement.getProperty(propName);
    if ((prop == null || prop.length() == 0)) {
      throw CoreIAardvarkUtilsEnhancement.buildException([[
        *temp0 = new StringBuilder();
        *temp0.append("property ");
        *temp0.append(propName);
        *temp0.append(" needs to be set");
        *temp0.toString()
      ]]);
    }
    return prop;
  }

  public static BuildException buildException(String message) {
    throw new BuildException(message);
  }

  public static String getRawVarkFilePath() {
    return Aardvark.getRawVarkFilePath();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}