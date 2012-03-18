// Compiled from CoreIterableOfIterablesEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfIterablesEnhancement extends Object{
  internal final synthetic IType typeparam$E;
  internal final synthetic IType typeparam$T;

  public static Iterable flatten(Iterable $that$, IType typeparam$E, IType typeparam$T) {
    outerIterable = [[
      *temp0 = new Iterable[1];
      *temp0[0] = $that$;
      *temp0
    ]];
    return new AnonymouS__0($that$, outerIterable, typeparam$E, typeparam$T);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}