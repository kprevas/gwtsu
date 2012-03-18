// Compiled from RoninTemplateObserver.gs
public ronin.RoninTemplateObserver extends Object implements IReentrant, ITemplateObserver, IGosuClassObject {
  internal static ThreadLocal ESCAPER_STACK;
  internal static AnonymouS__0 _ESCAPER;

  internal static void <clinit>() {
    RoninTemplateObserver.ESCAPER_STACK = new ThreadLocal();
    RoninTemplateObserver._ESCAPER = new AnonymouS__0();
    return;
  }

  public void <init>() {
    this.<init>();
    return;
  }

  public static void pushRoninEscaper(StringEscaper esc) {
    stack = ((Stack) RoninTemplateObserver.ESCAPER_STACK.get());
    if (stack == null) {
      stack = new Stack();
      RoninTemplateObserver.ESCAPER_STACK.set(stack);
    }
    stack.push(esc);
    return;
  }

  public static void popRoninEscaper() {
    ((Stack) RoninTemplateObserver.ESCAPER_STACK.get()).pop();
    return;
  }

  public void enter() {
    ITemplateObserver.MANAGER.pushTemplateObserver(this);
    return;
  }

  public void exit() {
    ITemplateObserver.MANAGER.popTemplateObserver();
    return;
  }

  public boolean beforeTemplateRender(IType template, Writer w) {
    [[
      *temp0 = Ronin.getCurrentRequest();
      if (*temp0 != null) {
        *temp0.beforeRenderTemplate(template);
      }

    ]];
    return true;
  }

  public StringEscaper getEscaper() {
    return RoninTemplateObserver._ESCAPER;
  }

  public void afterTemplateRender(IType template, Writer w) {
    [[
      *temp0 = Ronin.getCurrentRequest();
      if (*temp0 != null) {
        *temp0.afterRenderTemplate(template);
      }

    ]];
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