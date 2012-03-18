// Compiled from CorePathEnhancement.gsx
public gw.vark.CorePathEnhancement extends Object{

  public static Path withFile(Path $that$, File file) {
    $that$.setLocation(file);
    return $that$;
  }

  public static Path withFileset(Path $that$, FileSet fs) {
    $that$.addFileset(fs);
    return $that$;
  }

  public static Path withPath(Path $that$, Path p) {
    $that$.addExisting(p);
    return $that$;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}