// Compiled from Autocreate.gs
public gw.lang.Autocreate extends Object implements IAnnotation, IInherited, IAutocreate, IGosuClassObject {
  public IFunction0 _block;

  public void <init>() {
    this.<init>();

    return;
  }

  public void <init>(IFunction0 b) {
    this.<init>();

    this._block = b;
    return;
  }

  public IFunction0 getBlock() {
    return this._block;
  }

  public volatile bridge synthetic Object getBlock() {
    return this.getBlock();
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