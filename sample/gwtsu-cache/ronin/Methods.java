// Compiled from Methods.gs
public ronin.Methods extends Object implements IAnnotation, IGosuClassObject {
  public List _methods;

  public void <init>(List methods) {
    this.<init>();

    this._methods = methods;
    return;
  }

  public List getPermittedMethods() {
    return this._methods;
  }

  public void setPermittedMethods(List __value_) {
    this._methods = __value_;
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("class");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.annotation.AnnotationUsage.class));
    builder.withArg("target", UsageTarget.MethodTarget);
    builder.withArg("usageModifier", UsageModifier.One);
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}