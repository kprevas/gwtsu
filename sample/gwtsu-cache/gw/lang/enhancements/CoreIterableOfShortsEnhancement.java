// Compiled from CoreIterableOfShortsEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfShortsEnhancement extends Object{

  public static int sum(Iterable $that$) {
    sum = 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Short) *temp0.next());

        sum = [[
          *temp1 = ((Integer) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Integer.class), Integer.valueOf(sum), elt, TypeSystem.get(int.class), TypeSystem.get(java.lang.Short.class), true, false, true));
          (*temp1 == null ? 0 : *temp1.intValue())
        ]];
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}