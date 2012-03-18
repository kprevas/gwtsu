// Compiled from CoreIteratorOfIteratorsEnhancement.gsx
public gw.lang.enhancements.CoreIteratorOfIteratorsEnhancement extends Object{
  internal final synthetic IType typeparam$E;
  internal final synthetic IType typeparam$T;

  public static Iterator flatten(Iterator $that$, IType typeparam$E, IType typeparam$T) {
    outerIter = [[
      *temp0 = new Iterator[1];
      *temp0[0] = $that$;
      *temp0
    ]];
    return new AnonymouS__0($that$, outerIter, typeparam$E, typeparam$T);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}