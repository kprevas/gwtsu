// Compiled from CoreIterableOfFloatsEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfFloatsEnhancement extends Object{

  public static Float sum(Iterable $that$) {
    sum = (float) 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Float) *temp0.next());

        sum = [[
          *temp1 = ((Float) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Float.class), Float.valueOf(sum), elt, TypeSystem.get(float.class), TypeSystem.get(java.lang.Float.class), true, false, true));
          (*temp1 == null ? 0.0 : *temp1.floatValue())
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