// Compiled from IRoninUtilsEnhancement.gsx
public ronin.IRoninUtilsEnhancement extends Object{

  public static void log(Object msg, LogLevel level, String component, Throwable exception) {
    Ronin.log(msg, level, component, exception);
    return;
  }

  public static TraceElement trace(Object msg, boolean printTiming) {
    return [[
      *temp0 = Ronin.getCurrentTrace();
      (*temp0 == null ? ((TraceElement) null) : *temp0.withMessage(msg, printTiming))
    ]];
  }

  public static Object cache(IType typeparam$T, IFunction0 value, String name, CacheStore store) {
    return Ronin.cache(typeparam$T, value, name, store);
  }

  public static void invalidate(String name, CacheStore store) {
    Ronin.invalidate(TypeSystem.get(java.lang.Object.class), name, store);
    return;
  }

  public static RoninRequest getRoninRequest() {
    return Ronin.getCurrentRequest();
  }

  public static HttpServletRequest getRequest() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((HttpServletRequest) null) : *temp0.getHttpRequest())
    ]];
  }

  public static HttpServletResponse getResponse() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((HttpServletResponse) null) : *temp0.getHttpResponse())
    ]];
  }

  public static Writer getWriter() {
    return [[
      *temp0 = IRoninUtilsEnhancement.getResponse();
      (*temp0 == null ? ((PrintWriter) null) : *temp0.getWriter())
    ]];
  }

  public static HttpMethod getMethod() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((HttpMethod) null) : *temp0.getHttpMethod())
    ]];
  }

  public static Map getSession() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((Map) null) : *temp0.getHttpSession())
    ]];
  }

  public static String getReferrer() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((String) null) : *temp0.getReferrer())
    ]];
  }

  public static String getXSRFTokenName() {
    return "__ronin_XSRF";
  }

  public static String getXSRFTokenValue() {
    if (((Object) IRoninUtilsEnhancement.getSession().get(IRoninUtilsEnhancement.getXSRFTokenName())) != null) {
      return URLEncoder.encode(((String) TypeAsTransformer.coerceValue(((Object) IRoninUtilsEnhancement.getSession().get(IRoninUtilsEnhancement.getXSRFTokenName())), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
    }
    token = new String(MessageDigest.getInstance("SHA1").digest(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), IRoninUtilsEnhancement.getRequest().getSession().getId(), Long.valueOf(System.currentTimeMillis()), TypeSystem.get(java.lang.String.class), TypeSystem.get(long.class), true, false, false)).getBytes()));
    IRoninUtilsEnhancement.getSession().put(IRoninUtilsEnhancement.getXSRFTokenName(), token);
    return URLEncoder.encode(token);
  }

  public static IAuthManager getAuthManager() {
    return [[
      *temp0 = Ronin.getConfig();
      (*temp0 == null ? ((IAuthManager) null) : *temp0.getAuthManager())
    ]];
  }

  internal static String getPostLoginRedirect() {
    return ((String) TypeAsTransformer.coerceValue(((Object) IRoninUtilsEnhancement.getSession().get("__ronin_postLogin")), TypeSystem.get(java.lang.String.class), StringCoercer.instance()));
  }

  internal static void setPostLoginRedirect(String s) {
    IRoninUtilsEnhancement.getSession().put("__ronin_postLogin", s);
    return;
  }

  public static String urlFor(MethodReference target) {
    return URLUtil.urlFor(target);
  }

  public static String postUrlFor(MethodReference target) {
    return URLUtil.baseUrlFor(target);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("log(java.lang.Object, ronin.config.LogLevel, java.lang.String, java.lang.Throwable)");
    builder.addGosuAnnotation(new Param("msg", "The text of the message to log, or a block which returns said text."));
    builder.addGosuAnnotation(new Param("level", "(Optional) The level at which to log the message."));
    builder.addGosuAnnotation(new Param("component", "(Optional) The logical component from whence the message originated."));
    builder.addGosuAnnotation(new Param("exception", "(Optional) An exception to associate with the message."));
    builder.startAnnotationInfoForFeature("trace(java.lang.Object, boolean)");
    builder.addGosuAnnotation(new Param("msg", "The text of the trace message, or a block which returns said text."));
    builder.addGosuAnnotation(new Param("printTiming", "(Optional) Whether to print the time elapsed within the traced block.  Defaults to true."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "An object to be passed to a using() statement.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("cache(block():T, java.lang.String, ronin.Ronin.CacheStore)");
    builder.addGosuAnnotation(new Param("value", "A block which will compute the desired value."));
    builder.addGosuAnnotation(new Param("name", "(Optional) A unique identifier for the value.  Default is null, which means one will be
generated from the type of the value."));
    builder.addGosuAnnotation(new Param("store", "(Optional) The cache store used to retrieve or store the value.  Default is the request cache."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The retrieved or computed value.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("invalidate(java.lang.String, ronin.Ronin.CacheStore)");
    builder.addGosuAnnotation(new Param("name", "The unique identifier for the value."));
    builder.addGosuAnnotation(new Param("store", "The cache store in which to invalidate the value."));
    builder.startAnnotationInfoForFeature("urlFor(gw.lang.reflect.features.MethodReference<java.lang.Object, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("target", "The desired method invocation.  Arguments must be bound."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The URL as a String.");
    builder.finishJavaAnnotation();
    builder.addGosuAnnotation(new URLMethodValidator());
    builder.startAnnotationInfoForFeature("postUrlFor(gw.lang.reflect.features.MethodReference<java.lang.Object, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("target", "The desired method invocation.  Arguments should not be bound."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The URL as a String.");
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}