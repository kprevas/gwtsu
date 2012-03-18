// Compiled from JSONP.gs
public ronin.JSONP extends Object implements IAnnotation, IGosuClassObject {
  public String _callback;

  public void <init>(String callbackParameter) {
    this.<init>();

    this._callback = callbackParameter;
    return;
  }

  public String getCallback() {
    return this._callback;
  }

  public void setCallback(String __value_) {
    this._callback = __value_;
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