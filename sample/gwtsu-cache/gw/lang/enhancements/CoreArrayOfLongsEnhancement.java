// Compiled from CoreArrayOfLongsEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfLongsEnhancement extends Object{

  public static Long sum(Long; $that$) {
    sum = (long) 0;
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
          *temp3 = ((Long) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Long.class), Long.valueOf(sum), elt, TypeSystem.get(long.class), TypeSystem.get(java.lang.Long.class), true, false, true));
          (*temp3 == null ? 0 : *temp3.longValue())
        ]];
      }
    }
    return ((Long) TypeAsTransformer.coerceValue(Long.valueOf(sum), TypeSystem.get(java.lang.Long.class), LongHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}