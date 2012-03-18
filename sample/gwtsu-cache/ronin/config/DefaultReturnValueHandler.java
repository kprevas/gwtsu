// Compiled from DefaultReturnValueHandler.gs
public ronin.config.DefaultReturnValueHandler extends Object implements IReturnValueHandler, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public void handleReturnValue(Object rtn, PrintWriter writer) {
    if ([[
      *temp0 = rtn;
      (*temp0 == null ? false : TypeSystem.get(java.lang.String.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      writer.write(((String) TypeAsTransformer.coerceValue(rtn, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
    }
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}