// Compiled from Trace.gs
public ronin.Trace extends Object implements IGosuClassObject {
  public TraceElement _currentElement;
  internal final static DecimalFormat DECIMAL;

  internal static void <clinit>() {
    Trace.DECIMAL = new DecimalFormat("0.000");
    return;
  }

  public void <init>() {
    this.<init>();
    this._currentElement = [[
      *temp0 = new TraceElement(this);
      *temp0.setMsg("TRACE");
      *temp0.setPrintTiming(true);
      *temp0
    ]];
    return;
  }

  public TraceElement withMessage(Object msg, boolean printTiming) {
    return [[
      *temp0 = new TraceElement(this);
      *temp0.setMsg(msg);
      *temp0.setPrintTiming(printTiming);
      *temp0
    ]];
  }

  public void addMessage(Object msg) {
    *temp1 = [[
      *temp0 = new TraceElement(this);
      *temp0.setMsg(msg);
      *temp0
    ]];
    if (*temp1 != null) {
      *temp1.enter();
    }
    try {
    }
    finally {
      if (*temp1 != null) {
        *temp1.exit();
      }
    }
    return;
  }

  public String toString() {
    sb = new StringBuilder();
    this._currentElement.write(sb);
    return sb.toString();
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("withMessage(java.lang.Object, boolean)");
    builder.addGosuAnnotation(new Param("msg", "The text of the message, or a block which returns said text."));
    builder.addGosuAnnotation(new Param("printTiming", "(Optional) Whether to print the elapsed time with the trace message.  Defaults to true."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "An object which can be passed to a using() statement surrounding the code to be traced.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addMessage(java.lang.Object)");
    builder.addGosuAnnotation(new Param("msg", "The text of the message, or a block which returns said text."));
    return builder.getAnnotations();
  }

}