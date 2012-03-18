// Compiled from ApplicationMode.gs
public final enum ronin.config.ApplicationMode extends Enum implements IEnumValue, IGosuClassObject {
  public String _short;
  public final static ApplicationMode DEVELOPMENT;
  public final static ApplicationMode TESTING;
  public final static ApplicationMode STAGING;
  public final static ApplicationMode PRODUCTION;
  private final static synthetic ApplicationMode[] ENUM$VALUES;

  internal static void <clinit>() {
    ApplicationMode.DEVELOPMENT = new ApplicationMode("DEVELOPMENT", 0, "dev");
    ApplicationMode.TESTING = new ApplicationMode("TESTING", 1, "test");
    ApplicationMode.STAGING = new ApplicationMode("STAGING", 2, "staging");
    ApplicationMode.PRODUCTION = new ApplicationMode("PRODUCTION", 3, "prod");
    ApplicationMode.ENUM$VALUES = [[
      *temp0 = new ApplicationMode[4];
      *temp0[0] = ApplicationMode.DEVELOPMENT;
      *temp0[1] = ApplicationMode.TESTING;
      *temp0[2] = ApplicationMode.STAGING;
      *temp0[3] = ApplicationMode.PRODUCTION;
      *temp0
    ]];
    return;
  }

  internal void <init>(String enum$name, int enum$ordinal, String s) {
    this.<init>(enum$name, enum$ordinal);

    this._short = s;
    return;
  }

  public static ApplicationMode fromShortName(String s$$unboxedParam) {
    s = [[
      *temp0 = new String[1];
      *temp0[0] = s$$unboxedParam;
      *temp0
    ]];
    return (((ApplicationMode) [[
      *temp2 = ApplicationMode.getAllValues();
      *temp3 = TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_");
      *temp4 = [[
        *temp1 = new block_0_(s);
        *temp1._returnType = TypeSystem.get(boolean.class);
        *temp1
      ]];
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.firstWhere(*temp2, *temp3, *temp4)
    ]]) != null ? ((ApplicationMode) [[
      *temp6 = ApplicationMode.getAllValues();
      *temp7 = TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_");
      *temp8 = [[
        *temp5 = new block_0_(s);
        *temp5._returnType = TypeSystem.get(boolean.class);
        *temp5
      ]];
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.firstWhere(*temp6, *temp7, *temp8)
    ]]) : ApplicationMode.PRODUCTION);
  }

  public String getShortName() {
    return this._short;
  }

  public void setShortName(String __value_) {
    this._short = __value_;
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static ApplicationMode[] values() {
    *temp0 = new ApplicationMode[4];
    System.arraycopy(ApplicationMode.ENUM$VALUES, 0, *temp0, 0, 4);
    return *temp0;
  }

  public static List getAllValues() {
    return Arrays.asList(ApplicationMode.values());
  }

  public static ApplicationMode valueOf(String arg) {
    return ((ApplicationMode) Enum.valueOf(ronin.config.ApplicationMode.class, arg));
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