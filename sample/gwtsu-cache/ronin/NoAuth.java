// Compiled from NoAuth.gs
public ronin.NoAuth extends Object implements IAnnotation, IGosuClassObject {

  public void <init>() {
    this.<init>();
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
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.annotation.AnnotationUsage.class));
    builder.withArg("target", UsageTarget.TypeTarget);
    builder.withArg("usageModifier", UsageModifier.One);
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}