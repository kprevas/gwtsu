// Compiled from OSType.gs
public final enum gw.util.OSType extends Enum implements IEnumValue, IGosuClassObject {
  public final static OSType Undefined;
  public final static OSType Windows;
  public final static OSType Macintosh;
  public final static OSType Solaris;
  public final static OSType Linux;
  public final static OSType OS2;
  public final static OSType HPUX;
  public final static OSType AIX;
  public final static OSType Netware;
  private final static synthetic OSType[] ENUM$VALUES;

  internal static void <clinit>() {
    OSType.Undefined = new OSType("Undefined", 0);
    OSType.Windows = new OSType("Windows", 1);
    OSType.Macintosh = new OSType("Macintosh", 2);
    OSType.Solaris = new OSType("Solaris", 3);
    OSType.Linux = new OSType("Linux", 4);
    OSType.OS2 = new OSType("OS2", 5);
    OSType.HPUX = new OSType("HPUX", 6);
    OSType.AIX = new OSType("AIX", 7);
    OSType.Netware = new OSType("Netware", 8);
    OSType.ENUM$VALUES = [[
      *temp0 = new OSType[9];
      *temp0[0] = OSType.Undefined;
      *temp0[1] = OSType.Windows;
      *temp0[2] = OSType.Macintosh;
      *temp0[3] = OSType.Solaris;
      *temp0[4] = OSType.Linux;
      *temp0[5] = OSType.OS2;
      *temp0[6] = OSType.HPUX;
      *temp0[7] = OSType.AIX;
      *temp0[8] = OSType.Netware;
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

  public static OSType[] values() {
    *temp0 = new OSType[9];
    System.arraycopy(OSType.ENUM$VALUES, 0, *temp0, 0, 9);
    return *temp0;
  }

  public static List getAllValues() {
    return Arrays.asList(OSType.values());
  }

  public static OSType valueOf(String arg) {
    return ((OSType) Enum.valueOf(gw.util.OSType.class, arg));
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