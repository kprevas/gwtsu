// Compiled from CoreIterableOfComparablesEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfComparablesEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Comparable min(Iterable $that$, IType typeparam$T) {
    return [[
      *temp1 = $that$;
      *temp2 = typeparam$T;
      *temp3 = typeparam$T;
      *temp4 = [[
        *temp0 = new block_0_($that$, typeparam$T);
        *temp0._returnType = typeparam$T;
        *temp0
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.min(*temp1, *temp2, *temp3, *temp4)
    ]];
  }

  public static Comparable max(Iterable $that$, IType typeparam$T) {
    return [[
      *temp1 = $that$;
      *temp2 = typeparam$T;
      *temp3 = typeparam$T;
      *temp4 = [[
        *temp0 = new block_1_($that$, typeparam$T);
        *temp0._returnType = typeparam$T;
        *temp0
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.max(*temp1, *temp2, *temp3, *temp4)
    ]];
  }

  public static IOrderedList order(Iterable $that$, IType typeparam$T) {
    return [[
      *temp3 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toList(*temp0, *temp1)
      ]];
      *temp4 = typeparam$T;
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = new block_2_($that$, typeparam$T);
        *temp2._returnType = typeparam$T;
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreCollectionEnhancement.orderBy(*temp3, *temp4, *temp5, *temp6)
    ]];
  }

  public static IOrderedList orderDescending(Iterable $that$, IType typeparam$T) {
    return [[
      *temp3 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toList(*temp0, *temp1)
      ]];
      *temp4 = typeparam$T;
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = new block_3_($that$, typeparam$T);
        *temp2._returnType = typeparam$T;
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreCollectionEnhancement.orderByDescending(*temp3, *temp4, *temp5, *temp6)
    ]];
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}