// Compiled from CoreTimeZoneEnhancement.gsx
public gw.lang.enhancements.CoreTimeZoneEnhancement extends Object{

  public static TimeZone getGMT() {
    return TimeZone.getTimeZone("GMT");
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}