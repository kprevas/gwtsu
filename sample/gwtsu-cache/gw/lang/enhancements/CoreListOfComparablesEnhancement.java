// Compiled from CoreListOfComparablesEnhancement.gsx
public gw.lang.enhancements.CoreListOfComparablesEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static List sort(List $that$, IType typeparam$T) {
    Collections.sort($that$);
    return $that$;
  }

  public static List sortDescending(List $that$, IType typeparam$T) {
    Collections.sort($that$, Collections.reverseOrder());
    return $that$;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}