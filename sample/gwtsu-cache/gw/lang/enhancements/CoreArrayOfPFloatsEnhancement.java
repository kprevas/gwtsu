// Compiled from CoreArrayOfPFloatsEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfPFloatsEnhancement extends Object{

  public static int getCount([F $that$) {
    return $that$.length;
  }

  public static List toList([F $that$) {
    lst = new ArrayList();
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = 0.0;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        lst.add(((Float) TypeAsTransformer.coerceValue(Float.valueOf(elt), TypeSystem.get(java.lang.Float.class), FloatHighPriorityCoercer.instance())));
      }
    }
    return lst;
  }

  public static float sum([F $that$) {
    sum = (float) 0.0;
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = 0.0;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        sum = sum + elt;
      }
    }
    return sum;
  }

  public static BigDecimal average([F $that$) {
    return ((BigDecimal) MultiplicativeExpression.evaluate(TypeSystem.get(java.math.BigDecimal.class), ((BigDecimal) TypeAsTransformer.coerceValue(Float.valueOf([[
      *temp0 = $that$;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreArrayOfPFloatsEnhancement.sum(*temp0)
    ]]), TypeSystem.get(java.math.BigDecimal.class), BigDecimalCoercer.instance())), ((BigDecimal) TypeAsTransformer.coerceValue(Integer.valueOf([[
      *temp1 = $that$;
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreArrayOfPFloatsEnhancement.getCount(*temp1)
    ]]), TypeSystem.get(java.math.BigDecimal.class), BigDecimalCoercer.instance())), TypeSystem.get(java.math.BigDecimal.class), TypeSystem.get(java.math.BigDecimal.class), '/', false));
  }

  public static String join([F $that$, String delimiter) {
    retVal = new StringBuilder();
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = 0.0;

        i = -1;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        i = i + 1;

        if (i > 0) {
          retVal.append(delimiter);
        }
        retVal.append(((String) TypeAsTransformer.coerceValue(Float.valueOf(elt), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
      }
    }
    return retVal.toString();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}