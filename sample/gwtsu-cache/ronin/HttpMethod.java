// Compiled from HttpMethod.gs
public final enum ronin.HttpMethod extends Enum implements IEnumValue, IGosuClassObject {
  public final static HttpMethod GET;
  public final static HttpMethod POST;
  public final static HttpMethod PUT;
  public final static HttpMethod DELETE;
  private final static synthetic HttpMethod[] ENUM$VALUES;

  internal static void <clinit>() {
    HttpMethod.GET = new HttpMethod("GET", 0);
    HttpMethod.POST = new HttpMethod("POST", 1);
    HttpMethod.PUT = new HttpMethod("PUT", 2);
    HttpMethod.DELETE = new HttpMethod("DELETE", 3);
    HttpMethod.ENUM$VALUES = [[
      *temp0 = new HttpMethod[4];
      *temp0[0] = HttpMethod.GET;
      *temp0[1] = HttpMethod.POST;
      *temp0[2] = HttpMethod.PUT;
      *temp0[3] = HttpMethod.DELETE;
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

  public static HttpMethod[] values() {
    *temp0 = new HttpMethod[4];
    System.arraycopy(HttpMethod.ENUM$VALUES, 0, *temp0, 0, 4);
    return *temp0;
  }

  public static List getAllValues() {
    return Arrays.asList(HttpMethod.values());
  }

  public static HttpMethod valueOf(String arg) {
    return ((HttpMethod) Enum.valueOf(ronin.HttpMethod.class, arg));
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