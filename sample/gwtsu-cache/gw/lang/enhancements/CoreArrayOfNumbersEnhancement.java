// Compiled from CoreArrayOfNumbersEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfNumbersEnhancement extends Object{
  internal final synthetic IType typeparam$N;

  public static BigDecimal average(Number; $that$, IType typeparam$N) {
    return [[
      *temp3 = [[
        *temp0 = $that$;
        *temp1 = typeparam$N;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
      *temp4 = typeparam$N;
      *temp5 = [[
        *temp2 = new block_0_($that$);
        *temp2._returnType = typeparam$N;
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.average(*temp3, *temp4, *temp5)
    ]];
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}