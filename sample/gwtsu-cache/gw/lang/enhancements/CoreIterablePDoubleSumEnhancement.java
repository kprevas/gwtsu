// Compiled from CoreIterablePDoubleSumEnhancement.gsx
public gw.lang.enhancements.CoreIterablePDoubleSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static double sum(Iterable $that$, IType typeparam$T, IFunction1 mapper) {
    sum = 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = sum + ((Double) mapper.invoke(elt)).doubleValue();
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}