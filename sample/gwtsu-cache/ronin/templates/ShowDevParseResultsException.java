// Compiled from ShowDevParseResultsException.gst
public final ronin.templates.ShowDevParseResultsException extends Object implements IGosuClassObject {

  internal static void <clinit>() {
    $symbols$ = null;
    return;
  }

  public void <init>() {
    this.<init>();
    return;
  }

  public static void render(Writer p0, ErrantGosuClassException p1) {
    _forwarding_var_args_ = new Object[1];
    _forwarding_var_args_[0] = p1;
    TemplateRenderer.render(((ITemplateType) TypeSystem.getByFullName("ronin.templates.ShowDevParseResultsException", "_default_")), p0, _forwarding_var_args_);
    return;
  }

  public static String renderToString(ErrantGosuClassException p0) {
    _forwarding_var_args_ = new Object[1];
    _forwarding_var_args_[0] = p0;
    return ((String) GosuRuntimeMethods.invokeMethod(gw.internal.gosu.template.TemplateRenderer.class, "renderToString", [[
      *temp0 = new Class[2];
      *temp0[0] = gw.lang.reflect.gs.ITemplateType.class;
      *temp0[1] = [Ljava.lang.Object;.class;
      *temp0
    ]], null, [[
      *temp1 = new Object[2];
      *temp1[0] = ((ITemplateType) TypeSystem.getByFullName("ronin.templates.ShowDevParseResultsException", "_default_"));
      *temp1[1] = _forwarding_var_args_;
      *temp1
    ]]));
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}