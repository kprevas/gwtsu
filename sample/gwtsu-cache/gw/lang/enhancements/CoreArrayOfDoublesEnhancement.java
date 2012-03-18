// Compiled from CoreArrayOfDoublesEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfDoublesEnhancement extends Object{

  public static Double sum(Double; $that$) {
    sum = 0.0;
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = null;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        sum = [[
          *temp3 = ((Double) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Double.class), Double.valueOf(sum), elt, TypeSystem.get(double.class), TypeSystem.get(java.lang.Double.class), true, false, true));
          (*temp3 == null ? 0.0 : *temp3.doubleValue())
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