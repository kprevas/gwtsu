// Compiled from CoreArrayOfPDoublesEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfPDoublesEnhancement extends Object{

  public static int getCount([D $that$) {
    return $that$.length;
  }

  public static List toList([D $that$) {
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

        lst.add(((Double) TypeAsTransformer.coerceValue(Double.valueOf(elt), TypeSystem.get(java.lang.Double.class), DoubleHighPriorityCoercer.instance())));
      }
    }
    return lst;
  }

  public static double sum([D $that$) {
    sum = 0.0;
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

  public static BigDecimal average([D $that$) {
    return ((BigDecimal) MultiplicativeExpression.evaluate(TypeSystem.get(java.math.BigDecimal.class), ((BigDecimal) TypeAsTransformer.coerceValue(Double.valueOf([[
      *temp0 = $that$;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreArrayOfPDoublesEnhancement.sum(*temp0)
    ]]), TypeSystem.get(java.math.BigDecimal.class), BigDecimalCoercer.instance())), ((BigDecimal) TypeAsTransformer.coerceValue(Integer.valueOf([[
      *temp1 = $that$;
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreArrayOfPDoublesEnhancement.getCount(*temp1)
    ]]), TypeSystem.get(java.math.BigDecimal.class), BigDecimalCoercer.instance())), TypeSystem.get(java.math.BigDecimal.class), TypeSystem.get(java.math.BigDecimal.class), '/', false));
  }

  public static String join([D $that$, String delimiter) {
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
        retVal.append(((String) TypeAsTransformer.coerceValue(Double.valueOf(elt), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
      }
    }
    return retVal.toString();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}