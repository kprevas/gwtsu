// Compiled from CoreArrayOfIntegersEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfIntegersEnhancement extends Object{

  public static Integer sum(Integer; $that$) {
    sum = 0;
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
          *temp3 = ((Integer) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Integer.class), Integer.valueOf(sum), elt, TypeSystem.get(int.class), TypeSystem.get(java.lang.Integer.class), true, false, true));
          (*temp3 == null ? 0 : *temp3.intValue())
        ]];
      }
    }
    return ((Integer) TypeAsTransformer.coerceValue(Integer.valueOf(sum), TypeSystem.get(java.lang.Integer.class), IntHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}