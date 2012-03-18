// Compiled from CoreArrayOfFloatsEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfFloatsEnhancement extends Object{

  public static Float sum(Float; $that$) {
    sum = (float) 0.0;
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
          *temp3 = ((Float) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Float.class), Float.valueOf(sum), elt, TypeSystem.get(float.class), TypeSystem.get(java.lang.Float.class), true, false, true));
          (*temp3 == null ? 0.0 : *temp3.floatValue())
        ]];
      }
    }
    return ((Float) TypeAsTransformer.coerceValue(Float.valueOf(sum), TypeSystem.get(java.lang.Float.class), FloatHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}