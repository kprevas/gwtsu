// Compiled from HttpServletRequestEnhancement.gsx
public ronin.HttpServletRequestEnhancement extends Object{

  public static String getRootURL(HttpServletRequest $that$) {
    if (($that$.getServerPort() == 80 || $that$.getServerPort() == 443)) {
      return [[
        *temp0 = new StringBuilder();
        *temp0.append($that$.getScheme());
        *temp0.append("://");
        *temp0.append($that$.getServerName());
        *temp0.append($that$.getContextPath());
        *temp0.toString()
      ]];
    } else {
      return [[
        *temp1 = new StringBuilder();
        *temp1.append($that$.getScheme());
        *temp1.append("://");
        *temp1.append($that$.getServerName());
        *temp1.append(":");
        *temp1.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf($that$.getServerPort()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
        *temp1.append($that$.getContextPath());
        *temp1.toString()
      ]];
    }
  }

  public static String getFullURL(HttpServletRequest $that$) {
    return $that$.getRequestURL().append(($that$.getQueryString() == null ? "" : [[
      *temp0 = new StringBuilder();
      *temp0.append("?");
      *temp0.append($that$.getQueryString());
      *temp0.toString()
    ]])).toString();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}