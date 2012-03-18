// Compiled from Restricted.gs
public ronin.Restricted extends Object implements IAnnotation, IGosuClassObject {

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
    builder.withArg("target", UsageTarget.PropertyTarget);
    builder.withArg("usageModifier", UsageModifier.One);
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}