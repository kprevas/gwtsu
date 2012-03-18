// Compiled from CoreEnumerationEnhancement.gsx
public gw.lang.enhancements.CoreEnumerationEnhancement extends Object{
  internal final synthetic IType typeparam$E;

  public static List toList(Enumeration $that$, IType typeparam$E) {
    return Collections.list($that$);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}