// Compiled from DefaultRoninConfig.gs
public ronin.config.DefaultRoninConfig extends Object implements IRoninConfig, IGosuClassObject {
  internal RoninServlet _servlet;
  public Cache _requestCache;
  public Cache _sessionCache;
  public Cache _applicationCache;
  public ApplicationMode _mode;
  public LogLevel _logLevel;
  public boolean _traceEnabled;
  public String _defaultAction;
  public IType _defaultController;
  public List _xsrfLevel;
  public ServletFileUpload _servletFileUpload;
  public List _filters;
  public IErrorHandler _errorHandler;
  public ILogHandler _logHandler;
  public IURLHandler _urlHandler;
  public IReturnValueHandler _returnValueHandler;
  public IParamConverter _paramConverter;
  public IAuthManager _authManager;
  public Set _restrictedProperties;
  public MethodReference _loginRedirect;

  public void <init>(ApplicationMode m, RoninServlet an) {
    this.<init>();




















    this.setRoninServlet(an);
    this.setRequestCache(new Cache(new DefaultRequestCacheStore()));
    this.setSessionCache(new Cache(new DefaultSessionCacheStore()));
    this.setApplicationCache(new Cache([[
      *temp0 = new DefaultApplicationCacheStore();
      *temp0.setServlet(an);
      *temp0
    ]]));
    this.setMode(m);
    this.setLogLevel(((EqualityExpressionTransformer.evaluate(this.getMode(), TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.DEVELOPMENT, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_")) || EqualityExpressionTransformer.evaluate(this.getMode(), TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.TESTING, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) ? LogLevel.DEBUG : LogLevel.WARN));
    this.setTraceEnabled((EqualityExpressionTransformer.evaluate(this.getMode(), TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.DEVELOPMENT, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_")) || EqualityExpressionTransformer.evaluate(System.getProperty("ronin.trace"), TypeSystem.get(java.lang.String.class), true, "true", TypeSystem.get(java.lang.String.class))));
    this.setDefaultController(TypeSystem.getByFullNameIfValid("controller.Main"));
    this.setDefaultAction("index");
    this.setXSRFLevel([[
      *temp1 = new ArrayList();
      *temp1.add(HttpMethod.POST);
      *temp1.add(HttpMethod.PUT);
      *temp1.add(HttpMethod.DELETE);
      *temp1
    ]]);
    this.setServletFileUpload(new ServletFileUpload(new DiskFileItemFactory()));
    this.setFilters(new ArrayList());
    this.setRestrictedProperties(new HashSet());
    this.setErrorHandler(new DefaultErrorHandler(this));
    this.setURLHandler(new DefaultURLHandler());
    this.setReturnValueHandler(new DefaultReturnValueHandler());
    this.setParamConverter(new DefaultParamConverter());
    return;
  }

  public RoninServlet getRoninServlet() {
    return this._servlet;
  }

  public void setRoninServlet(RoninServlet __value_) {
    this._servlet = __value_;
    return;
  }

  public Cache getRequestCache() {
    return this._requestCache;
  }

  public void setRequestCache(Cache __value_) {
    this._requestCache = __value_;
    return;
  }

  public Cache getSessionCache() {
    return this._sessionCache;
  }

  public void setSessionCache(Cache __value_) {
    this._sessionCache = __value_;
    return;
  }

  public Cache getApplicationCache() {
    return this._applicationCache;
  }

  public void setApplicationCache(Cache __value_) {
    this._applicationCache = __value_;
    return;
  }

  public ApplicationMode getMode() {
    return this._mode;
  }

  public void setMode(ApplicationMode __value_) {
    this._mode = __value_;
    return;
  }

  public LogLevel getLogLevel() {
    return this._logLevel;
  }

  public void setLogLevel(LogLevel __value_) {
    this._logLevel = __value_;
    return;
  }

  public boolean isTraceEnabled() {
    return this._traceEnabled;
  }

  public void setTraceEnabled(boolean __value_) {
    this._traceEnabled = __value_;
    return;
  }

  public String getDefaultAction() {
    return this._defaultAction;
  }

  public void setDefaultAction(String __value_) {
    this._defaultAction = __value_;
    return;
  }

  public IType getDefaultController() {
    return this._defaultController;
  }

  public void setDefaultController(IType __value_) {
    this._defaultController = __value_;
    return;
  }

  public List getXSRFLevel() {
    return this._xsrfLevel;
  }

  public void setXSRFLevel(List __value_) {
    this._xsrfLevel = __value_;
    return;
  }

  public ServletFileUpload getServletFileUpload() {
    return this._servletFileUpload;
  }

  public void setServletFileUpload(ServletFileUpload __value_) {
    this._servletFileUpload = __value_;
    return;
  }

  public List getFilters() {
    return this._filters;
  }

  public void setFilters(List __value_) {
    this._filters = __value_;
    return;
  }

  public IErrorHandler getErrorHandler() {
    return this._errorHandler;
  }

  public void setErrorHandler(IErrorHandler __value_) {
    this._errorHandler = __value_;
    return;
  }

  public ILogHandler getLogHandler() {
    return this._logHandler;
  }

  public void setLogHandler(ILogHandler __value_) {
    this._logHandler = __value_;
    return;
  }

  public IURLHandler getURLHandler() {
    return this._urlHandler;
  }

  public void setURLHandler(IURLHandler __value_) {
    this._urlHandler = __value_;
    return;
  }

  public IReturnValueHandler getReturnValueHandler() {
    return this._returnValueHandler;
  }

  public void setReturnValueHandler(IReturnValueHandler __value_) {
    this._returnValueHandler = __value_;
    return;
  }

  public IParamConverter getParamConverter() {
    return this._paramConverter;
  }

  public void setParamConverter(IParamConverter __value_) {
    this._paramConverter = __value_;
    return;
  }

  public IAuthManager getAuthManager() {
    return this._authManager;
  }

  public void setAuthManager(IAuthManager __value_) {
    this._authManager = __value_;
    return;
  }

  public Set getRestrictedProperties() {
    return this._restrictedProperties;
  }

  public void setRestrictedProperties(Set __value_) {
    this._restrictedProperties = __value_;
    return;
  }

  public MethodReference getLoginRedirect() {
    return this._loginRedirect;
  }

  public void setLoginRedirect(MethodReference methodRef) {
    if (methodRef != null) {
      noAuthMethodAnnotation = [[
        *temp0 = methodRef.getMethodInfo().getAnnotation(TypeSystem.getByFullName("ronin.NoAuth", "_default_"));
        (*temp0 == null ? ((Object) null) : *temp0.getInstance())
      ]];
      if (noAuthMethodAnnotation == null) {
        noAuthTypeAnnotation = [[
          *temp1 = methodRef.getRootType().getTypeInfo().getAnnotation(TypeSystem.getByFullName("ronin.NoAuth", "_default_"));
          (*temp1 == null ? ((Object) null) : *temp1.getInstance())
        ]];
        if (noAuthTypeAnnotation == null) {
          *temp2 = "LoginRedirect must be a method annotated with @NoAuth, or on a class annotated with @NoAuth.";
          if ((*temp2 instanceof Throwable)) {
            throw ((Throwable) *temp2);
          } else {
            throw new EvaluationException(((String) *temp2));
          }
        }
      }
    }
    this._loginRedirect = methodRef;
    return;
  }

  public final IAuthManager createDefaultAuthManager(IType typeparam$U, IFunction1 getUser, IFunction3 getOrCreateUserByOpenID, PropertyReference userName, PropertyReference userPassword, PropertyReference userSalt, PropertyReference userRoles, String hashAlgorithm, int hashIterations) {
    realm = new ShiroRealm(getUser, getOrCreateUserByOpenID, userName, userPassword, userSalt, userRoles, hashAlgorithm, hashIterations);
    return new ShiroAuthManager(realm, hashAlgorithm, hashIterations, this);
  }

  public IAuthManager createLDAPAuthManager(String url, String userDnTemplate, String authMechanism) {
    realm = [[
      *temp0 = new JndiLdapRealm();
      *temp0.setUserDnTemplate(userDnTemplate);
      *temp0
    ]];
    contextFactory = [[
      *temp1 = realm.getContextFactory();
      ((*temp1 instanceof JndiLdapContextFactory) ? ((JndiLdapContextFactory) *temp1) : ((JndiLdapContextFactory) TypeAsTransformer.coerceValue(*temp1, TypeSystem.get(org.apache.shiro.realm.ldap.JndiLdapContextFactory.class), RuntimeCoercer.instance())))
    ]];
    contextFactory.setUrl(url);
    contextFactory.setAuthenticationMechanism(authMechanism);
    return new ShiroAuthManager(realm, ((String) null), 0, this);
  }

  public final Filter initFilter(Filter filter) {
    filter.init(new AnonymouS__0(this));
    return filter;
  }

  public void schedule(IFunction0 task, Integer atSecond, Iterable atSeconds, Integer atMinute, Iterable atMinutes, Integer atHour, Iterable atHours, Integer onDayOfMonth, Iterable onDaysOfMonth, Day onDay, Iterable onDays, Month inMonth, Iterable inMonths, String cronString, String jobName) {
    Quartz.schedule(task, atSecond, atSeconds, atMinute, atMinutes, atHour, atHours, onDayOfMonth, onDaysOfMonth, onDay, onDays, inMonth, inMonths, cronString, jobName);
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("createDefaultAuthManager(block(java.lang.String):U, block(java.lang.String, java.lang.String, java.lang.String):U, gw.lang.reflect.features.PropertyReference<U, java.lang.String>, gw.lang.reflect.features.PropertyReference<U, java.lang.String>, gw.lang.reflect.features.PropertyReference<U, java.lang.String>, gw.lang.reflect.features.PropertyReference<U, java.lang.Iterable<java.lang.String>>, java.lang.String, int)");
    builder.addGosuAnnotation(new Param("getUser", "A block which can fetch the user object for a given username."));
    builder.addGosuAnnotation(new Param("getOrCreateUserByOpenID", "A block which can fetch the user object for a given identity and/or e-mail address provided by an
OpenID provider, or optionally create a new user for the identity/address.  May be null if you don't intend to support OpenID."));
    builder.addGosuAnnotation(new Param("userName", "The property on the user object which returns the user's username."));
    builder.addGosuAnnotation(new Param("userPassword", "The property on the user object which returns a hash of the user's password."));
    builder.addGosuAnnotation(new Param("userSalt", "The property on the user object which returns the salt used to hash the user's password."));
    builder.addGosuAnnotation(new Param("userRoles", "(Optional) The property on the user object which returns a list of the user's roles.  Default is null."));
    builder.addGosuAnnotation(new Param("hashAlgorithm", "(Optional) The name of the algorithm used to hash passwords.  Default is "SHA-256"."));
    builder.addGosuAnnotation(new Param("hashIterations", "(Optional) The number of iterations in the hashing process.  Default is 1024."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "An IAuthManager, which should be assigned to {@link ronin.config.IRoninConfig#AuthManager}.");
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}