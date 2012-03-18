// Compiled from Main.gs
public controller.Main extends RoninController implements IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public void index() {
    Main.render(IRoninUtilsEnhancement.getWriter());
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