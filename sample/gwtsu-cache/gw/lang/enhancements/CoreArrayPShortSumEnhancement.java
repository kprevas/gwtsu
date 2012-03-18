// Compiled from CoreArrayPShortSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayPShortSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static int sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = sum + (int) ((Short) mapper.invoke(elt)).shortValue();
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}