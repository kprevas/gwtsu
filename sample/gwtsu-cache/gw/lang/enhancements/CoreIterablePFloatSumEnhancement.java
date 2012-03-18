// Compiled from CoreIterablePFloatSumEnhancement.gsx
public gw.lang.enhancements.CoreIterablePFloatSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static float sum(Iterable $that$, IType typeparam$T, IFunction1 mapper) {
    sum = (float) 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = sum + ((Float) mapper.invoke(elt)).floatValue();
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}