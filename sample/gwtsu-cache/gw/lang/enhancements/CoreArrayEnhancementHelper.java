// Compiled from CoreArrayEnhancementHelper.gsx
public gw.lang.enhancements.CoreArrayEnhancementHelper extends Object{
  internal final synthetic IType typeparam$T;

  public static Object; flatMap(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 mapper$$unboxedParam) {
    mapper = [[
      *temp0 = new IFunction1[1];
      *temp0[0] = mapper$$unboxedParam;
      *temp0
    ]];
    return [[
      *temp9 = [[
        *temp5 = [[
          *temp1 = $that$;
          *temp2 = typeparam$T;
          if (*temp1 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp1, *temp2)
        ]];
        *temp6 = typeparam$T;
        *temp7 = typeparam$R;
        *temp8 = [[
          *temp3 = new block_0_($that$, mapper, typeparam$T, typeparam$R);
          *temp3._returnType = TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
            *temp4 = new IType[1];
            *temp4[0] = typeparam$R;
            *temp4
          ]]);
          *temp3
        ]];
        if (*temp5 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.flatMap(*temp5, *temp6, *temp7, *temp8)
      ]];
      *temp10 = typeparam$R;
      if (*temp9 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp9, *temp10)
    ]];
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}