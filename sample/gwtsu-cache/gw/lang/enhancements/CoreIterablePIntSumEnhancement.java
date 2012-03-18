// Compiled from CoreIterablePIntSumEnhancement.gsx
public gw.lang.enhancements.CoreIterablePIntSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static int sum(Iterable $that$, IType typeparam$T, IFunction1 mapper) {
    sum = 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = sum + ((Integer) mapper.invoke(elt)).intValue();
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}