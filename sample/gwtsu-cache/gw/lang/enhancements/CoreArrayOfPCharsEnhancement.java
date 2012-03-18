// Compiled from CoreArrayOfPCharsEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfPCharsEnhancement extends Object{

  public static int getCount([C $that$) {
    return $that$.length;
  }

  public static List toList([C $that$) {
    lst = new ArrayList();
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = 0;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        lst.add(((Character) TypeAsTransformer.coerceValue(AbstractElementTransformer.valueOf(elt), TypeSystem.get(java.lang.Character.class), CharHighPriorityCoercer.instance())));
      }
    }
    return lst;
  }

  public static String join([C $that$, String delimiter) {
    retVal = new StringBuilder();
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = 0;

        i = -1;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

        i = i + 1;

        if (i > 0) {
          retVal.append(delimiter);
        }
        retVal.append(((String) TypeAsTransformer.coerceValue(AbstractElementTransformer.valueOf(elt), TypeSystem.get(java.lang.String.class), NonWarningStringCoercer.instance())));
      }
    }
    return retVal.toString();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}