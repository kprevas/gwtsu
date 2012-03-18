// Compiled from CoreIterableOfLongsEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfLongsEnhancement extends Object{

  public static Long sum(Iterable $that$) {
    sum = ((Long) TypeAsTransformer.coerceValue(Integer.valueOf(0), TypeSystem.get(java.lang.Long.class), LongCoercer.instance()));
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Long) *temp0.next());

        sum = ((Long) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Long.class), sum, elt, TypeSystem.get(java.lang.Long.class), TypeSystem.get(java.lang.Long.class), true, false, true));
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}