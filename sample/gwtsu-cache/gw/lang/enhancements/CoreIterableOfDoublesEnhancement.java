// Compiled from CoreIterableOfDoublesEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfDoublesEnhancement extends Object{

  public static Double sum(Iterable $that$) {
    sum = 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Double) *temp0.next());

        sum = [[
          *temp1 = ((Double) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Double.class), Double.valueOf(sum), elt, TypeSystem.get(double.class), TypeSystem.get(java.lang.Double.class), true, false, true));
          (*temp1 == null ? 0.0 : *temp1.doubleValue())
        ]];
      }
    }
    return ((Double) TypeAsTransformer.coerceValue(Double.valueOf(sum), TypeSystem.get(java.lang.Double.class), DoubleHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}