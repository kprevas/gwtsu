// Compiled from CoreArrayOfComparablesEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfComparablesEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Comparable min(Comparable; $that$, IType typeparam$T) {
    return [[
      *temp3 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
      *temp4 = typeparam$T;
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = new block_0_($that$, typeparam$T);
        *temp2._returnType = typeparam$T;
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.min(*temp3, *temp4, *temp5, *temp6)
    ]];
  }

  public static Comparable max(Comparable; $that$, IType typeparam$T) {
    return [[
      *temp3 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
      *temp4 = typeparam$T;
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = new block_1_($that$, typeparam$T);
        *temp2._returnType = typeparam$T;
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.max(*temp3, *temp4, *temp5, *temp6)
    ]];
  }

  public static Comparable; sort(Comparable; $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.get([Ljava.lang.Object;.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      Arrays.sort($that$);
    } else {
      *temp1 = "This array is not a java-based non-primitive array, and thus is not sortable";
      if ((*temp1 instanceof Throwable)) {
        throw ((Throwable) *temp1);
      } else {
        throw new EvaluationException(((String) *temp1));
      }
    }
    return $that$;
  }

  public static Comparable; sortDescending(Comparable; $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.get([Ljava.lang.Object;.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      Arrays.sort($that$, Collections.reverseOrder());
    } else {
      *temp1 = "This array is not a java-based non-primitive array, and thus is not sortable";
      if ((*temp1 instanceof Throwable)) {
        throw ((Throwable) *temp1);
      } else {
        throw new EvaluationException(((String) *temp1));
      }
    }
    return $that$;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}