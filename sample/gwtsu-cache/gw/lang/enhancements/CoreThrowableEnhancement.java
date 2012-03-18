// Compiled from CoreThrowableEnhancement.gsx
public gw.lang.enhancements.CoreThrowableEnhancement extends Object{

  public static Boolean isChecked(Throwable $that$) {
    return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf(!(([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.get(java.lang.Error.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]] || [[
      *temp1 = $that$;
      (*temp1 == null ? false : TypeSystem.get(java.lang.RuntimeException.class).isAssignableFrom(TypeSystem.getFromObject(*temp1)))
    ]]))), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()));
  }

  public static Throwable getCauseOfType(Throwable $that$, IType typeparam$T, IType causeType) {
    visited = new IdentityHashMap();
    cause = $that$;
    while ((cause != null && !(causeType.isAssignableFrom([[
      *temp0 = cause;
      (*temp0 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp0))
    ]])))) {
      if ([[
        *temp1 = ((Boolean) visited.put(cause, ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf(true), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()))));
        (*temp1 == null ? false : *temp1.booleanValue())
      ]]) {
        return null;
      }
      cause = cause.getCause();
    }
    return ((Throwable) TypeAsTransformer.coerceValue(cause, typeparam$T, TypeVariableCoercer.instance()));
  }

  public static String getStackTraceAsString(Throwable $that$) {
    buf = new StringWriter();
    $that$.printStackTrace(new PrintWriter(buf));
    return buf.toString();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}