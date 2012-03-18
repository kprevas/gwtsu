// Compiled from Ronin.gs
public ronin.Ronin extends Object implements IGosuClassObject {
  public static IRoninConfig _CONFIG;
  public static ThreadLocal _CURRENT_REQUEST;

  internal static void <clinit>() {

    Ronin._CURRENT_REQUEST = new ThreadLocal();
    return;
  }

  internal void <init>() {
    this.<init>();
    return;
  }

  public static IRoninConfig getConfig() {
    return Ronin._CONFIG;
  }

  public static void setConfig(IRoninConfig __value_) {
    Ronin._CONFIG = __value_;
    return;
  }

  internal static void init(RoninServlet servlet, ApplicationMode m, File src) {
    if (Ronin._CONFIG != null) {
      *temp0 = "Cannot initialize a Ronin application multiple times!";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    }
    cfg = TypeSystem.getByFullNameIfValid("config.RoninConfig");
    defaultWarning = false;
    if (cfg != null) {
      ctor = cfg.getTypeInfo().getConstructor([[
        *temp1 = new IType[2];
        *temp1[0] = TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_");
        *temp1[1] = TypeSystem.getByFullName("ronin.RoninServlet", "_default_");
        *temp1
      ]]);
      if (ctor == null) {
        *temp2 = "config.RoninConfig must have a constructor with the same signature as ronin.config.RoninConfig";
        if ((*temp2 instanceof Throwable)) {
          throw ((Throwable) *temp2);
        } else {
          throw new EvaluationException(((String) *temp2));
        }
      }
      Ronin._CONFIG = [[
        *temp4 = ctor.getConstructor().newInstance([[
          *temp3 = new Object[2];
          *temp3[0] = m;
          *temp3[1] = servlet;
          *temp3
        ]]);
        ((*temp4 instanceof IRoninConfig) ? ((IRoninConfig) *temp4) : ((IRoninConfig) TypeAsTransformer.coerceValue(*temp4, TypeSystem.getByFullName("ronin.config.IRoninConfig", "_default_"), RuntimeCoercer.instance())))
      ]];
    } else {
      Ronin._CONFIG = new DefaultRoninConfig(m, servlet);
      defaultWarning = true;
    }
    roninLogger = TypeSystem.getByFullNameIfValid("ronin.RoninLoggerFactory");
    if (roninLogger != null) {
      roninLogger.getTypeInfo().getMethod("init", [[
        *temp5 = MetaType.get(TypeSystem.getByFullName("ronin.config.LogLevel", "_default_")).makeArrayInstance(1);
        MetaType.get(TypeSystem.getByFullName("ronin.config.LogLevel", "_default_")).setArrayComponent(*temp5, 0, TypeSystem.getByFullName("ronin.config.LogLevel", "_default_"));
        ((IType;) *temp5)
      ]]).getCallHandler().handleCall(null, [[
        *temp6 = new LogLevel[1];
        *temp6[0] = Ronin.getLogLevel();
        *temp6
      ]]);
    }
    if (defaultWarning) {
      Ronin.log("No configuration was found at config.RoninConfig, using the default configuration...", LogLevel.WARN, ((String) null), ((Throwable) null));
    }
    Quartz.maybeStart();
    GosuRuntimeMethods.invokeMethod(GosuRuntimeMethods.lookUpClass("Lronin/ReloadManager;"), "setSourceRoot", [[
      *temp7 = new Class[1];
      *temp7[0] = java.io.File.class;
      *temp7
    ]], null, [[
      *temp8 = new Object[1];
      *temp8[0] = src;
      *temp8
    ]]);
    return;
  }

  public static Trace getCurrentTrace() {
    return [[
      *temp0 = Ronin.getCurrentRequest();
      (*temp0 == null ? ((Trace) null) : *temp0.getTrace())
    ]];
  }

  public static RoninRequest getCurrentRequest() {
    return ((RoninRequest) Ronin._CURRENT_REQUEST.get());
  }

  internal static void setCurrentRequest(RoninRequest req) {
    Ronin._CURRENT_REQUEST.set(req);
    return;
  }

  public static ApplicationMode getMode() {
    return ([[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((ApplicationMode) null) : *temp0.getMode())
    ]] != null ? [[
      *temp1 = Ronin._CONFIG;
      (*temp1 == null ? ((ApplicationMode) null) : *temp1.getMode())
    ]] : ApplicationMode.TESTING);
  }

  public static LogLevel getLogLevel() {
    return ([[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((LogLevel) null) : *temp0.getLogLevel())
    ]] != null ? [[
      *temp1 = Ronin._CONFIG;
      (*temp1 == null ? ((LogLevel) null) : *temp1.getLogLevel())
    ]] : LogLevel.DEBUG);
  }

  public static boolean isTraceEnabled() {
    return (Ronin._CONFIG != null ? Ronin._CONFIG.isTraceEnabled() : true);
  }

  public static String getDefaultAction() {
    return [[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((String) null) : *temp0.getDefaultAction())
    ]];
  }

  public static IType getDefaultController() {
    return [[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((IType) null) : *temp0.getDefaultController())
    ]];
  }

  public static RoninServlet getRoninServlet() {
    return [[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((RoninServlet) null) : *temp0.getRoninServlet())
    ]];
  }

  public static IErrorHandler getErrorHandler() {
    return [[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((IErrorHandler) null) : *temp0.getErrorHandler())
    ]];
  }

  public static ILogHandler getLogHandler() {
    return [[
      *temp0 = Ronin._CONFIG;
      (*temp0 == null ? ((ILogHandler) null) : *temp0.getLogHandler())
    ]];
  }

  public static void log(Object msg, LogLevel level, String component, Throwable exception) {
    if (level == null) {
      level = LogLevel.INFO;
    }
    if (RelationalExpressionTransformer.evaluate(Ronin.getLogLevel(), TypeSystem.getByFullName("ronin.config.LogLevel", "_default_"), "<=", level, TypeSystem.getByFullName("ronin.config.LogLevel", "_default_"))) {
      msgStr = null;
      if ([[
        *temp0 = msg;
        (*temp0 == null ? false : new BlockType(TypeSystem.get(java.lang.String.class), [[
          *temp1 = new IType[0];
          *temp1
        ]], [[
          *temp2 = new String[0];
          *temp2
        ]], [[
          *temp3 = new IExpression[0];
          *temp3
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
      ]]) {
        msgStr = ((String) ((IFunction0) TypeAsTransformer.coerceValue(msg, new BlockType(TypeSystem.get(java.lang.String.class), [[
          *temp4 = new IType[0];
          *temp4
        ]], [[
          *temp5 = new String[0];
          *temp5
        ]], [[
          *temp6 = new IExpression[0];
          *temp6
        ]]), RuntimeCoercer.instance())).invoke());
      } else {
        msgStr = ((String) TypeAsTransformer.coerceValue(msg, TypeSystem.get(java.lang.String.class), StringCoercer.instance()));
      }
      if ([[
        *temp7 = Ronin._CONFIG;
        (*temp7 == null ? ((ILogHandler) null) : *temp7.getLogHandler())
      ]] != null) {
        Ronin._CONFIG.getLogHandler().log(msgStr, level, component, exception);
      } else {
        switch(        *temp8 = level;
) {
          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.TRACE):
            LoggerFactory.getLogger((component != null ? component : Logger.ROOT_LOGGER_NAME)).trace(msgStr, exception);
            break;          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.DEBUG):
            LoggerFactory.getLogger((component != null ? component : Logger.ROOT_LOGGER_NAME)).debug(msgStr, exception);
            break;          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.INFO):
            LoggerFactory.getLogger((component != null ? component : Logger.ROOT_LOGGER_NAME)).info(msgStr, exception);
            break;          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.WARN):
            LoggerFactory.getLogger((component != null ? component : Logger.ROOT_LOGGER_NAME)).warn(msgStr, exception);
            break;          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.ERROR):
          case SwitchStatementTransformer.areEqual(*temp8, LogLevel.FATAL):
            LoggerFactory.getLogger((component != null ? component : Logger.ROOT_LOGGER_NAME)).error(msgStr, exception);
            break;        }
      }
    }
    return;
  }

  public static Object cache(IType typeparam$T, IFunction0 value, String name, CacheStore store) {
    if ((store == null || EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.REQUEST, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_")))) {
      return Ronin._CONFIG.getRequestCache().getValue(typeparam$T, value, name);
    } else {
      if (EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.SESSION, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"))) {
        return Ronin._CONFIG.getSessionCache().getValue(typeparam$T, value, name);
      } else {
        if (EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.APPLICATION, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"))) {
          return Ronin._CONFIG.getApplicationCache().getValue(typeparam$T, value, name);
        } else {
          *temp1 = [[
            *temp0 = new StringBuilder();
            *temp0.append("Don't know about CacheStore ");
            *temp0.append(((String) TypeAsTransformer.coerceValue(store, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp0.toString()
          ]];
          if ((*temp1 instanceof Throwable)) {
            throw ((Throwable) *temp1);
          } else {
            throw new EvaluationException(((String) *temp1));
          }
        }
      }
    }
  }

  public static void invalidate(IType typeparam$T, String name, CacheStore store) {
    if ((store == null || EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.REQUEST, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_")))) {
      Ronin._CONFIG.getRequestCache().invalidate(name);
    } else {
      if (EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.SESSION, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"))) {
        Ronin._CONFIG.getSessionCache().invalidate(name);
      } else {
        if (EqualityExpressionTransformer.evaluate(store, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"), true, CacheStore.APPLICATION, TypeSystem.getByFullName("ronin.Ronin.CacheStore", "_default_"))) {
          Ronin._CONFIG.getApplicationCache().invalidate(name);
        } else {
          *temp1 = [[
            *temp0 = new StringBuilder();
            *temp0.append("Don't know about CacheStore ");
            *temp0.append(((String) TypeAsTransformer.coerceValue(store, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp0.toString()
          ]];
          if ((*temp1 instanceof Throwable)) {
            throw ((Throwable) *temp1);
          } else {
            throw new EvaluationException(((String) *temp1));
          }
        }
      }
    }
    return;
  }

  public static void loadChanges() {
    GosuRuntimeMethods.invokeMethod(GosuRuntimeMethods.lookUpClass("Lronin/ReloadManager;"), "detectAndReloadChangedResources", [[
      *temp0 = new Class[0];
      *temp0
    ]], null, [[
      *temp1 = new Object[0];
      *temp1
    ]]);
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("log(java.lang.Object, ronin.config.LogLevel, java.lang.String, java.lang.Throwable)");
    builder.addGosuAnnotation(new Param("msg", "The text of the message to log, or a block which returns said text."));
    builder.addGosuAnnotation(new Param("level", "(Optional) The level at which to log the message."));
    builder.addGosuAnnotation(new Param("component", "(Optional) The logical component from whence the message originated."));
    builder.addGosuAnnotation(new Param("exception", "(Optional) An exception to associate with the message."));
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
    return builder.getAnnotations();
  }

}