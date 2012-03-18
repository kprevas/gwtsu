// Compiled from CoreArrayPLongSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayPLongSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static long sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = (long) 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = sum + ((Long) mapper.invoke(elt)).longValue();
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}