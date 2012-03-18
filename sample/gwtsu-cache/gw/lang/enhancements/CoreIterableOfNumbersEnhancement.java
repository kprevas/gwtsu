// Compiled from CoreIterableOfNumbersEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfNumbersEnhancement extends Object{
  internal final synthetic IType typeparam$N;

  public static BigDecimal average(Iterable $that$, IType typeparam$N) {
    return [[
      *temp1 = $that$;
      *temp2 = typeparam$N;
      *temp3 = [[
        *temp0 = new block_0_($that$, typeparam$N);
        *temp0._returnType = typeparam$N;
        *temp0
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.average(*temp1, *temp2, *temp3)
    ]];
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}