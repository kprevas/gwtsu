// Compiled from LogLevel.gs
public final enum ronin.config.LogLevel extends Enum implements IEnumValue, IGosuClassObject {
  public final static LogLevel TRACE;
  public final static LogLevel DEBUG;
  public final static LogLevel INFO;
  public final static LogLevel WARN;
  public final static LogLevel ERROR;
  public final static LogLevel FATAL;
  private final static synthetic LogLevel[] ENUM$VALUES;

  internal static void <clinit>() {
    LogLevel.TRACE = new LogLevel("TRACE", 0);
    LogLevel.DEBUG = new LogLevel("DEBUG", 1);
    LogLevel.INFO = new LogLevel("INFO", 2);
    LogLevel.WARN = new LogLevel("WARN", 3);
    LogLevel.ERROR = new LogLevel("ERROR", 4);
    LogLevel.FATAL = new LogLevel("FATAL", 5);
    LogLevel.ENUM$VALUES = [[
      *temp0 = new LogLevel[6];
      *temp0[0] = LogLevel.TRACE;
      *temp0[1] = LogLevel.DEBUG;
      *temp0[2] = LogLevel.INFO;
      *temp0[3] = LogLevel.WARN;
      *temp0[4] = LogLevel.ERROR;
      *temp0[5] = LogLevel.FATAL;
      *temp0
    ]];
    return;
  }

  internal void <init>(String enum$name, int enum$ordinal) {
    this.<init>(enum$name, enum$ordinal);
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static LogLevel[] values() {
    *temp0 = new LogLevel[6];
    System.arraycopy(LogLevel.ENUM$VALUES, 0, *temp0, 0, 6);
    return *temp0;
  }

  public static List getAllValues() {
    return Arrays.asList(LogLevel.values());
  }

  public static LogLevel valueOf(String arg) {
    return ((LogLevel) Enum.valueOf(ronin.config.LogLevel.class, arg));
  }

  public final Object getValue() {
    return this;
  }

  public final String getCode() {
    return this.name();
  }

  public final int getOrdinal() {
    return this.ordinal();
  }

  public final String getDisplayName() {
    return this.toString();
  }

  public final String getName() {
    return this.name();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}