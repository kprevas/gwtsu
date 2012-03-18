// Compiled from Cache.gs
public ronin.Cache extends Object implements IGosuClassObject {
  public CacheStore _store;
  public final static Object NULL_SENTINEL;

  internal static void <clinit>() {
    Cache.NULL_SENTINEL = new Object();
    return;
  }

  public void <init>(CacheStore s) {
    this.<init>();

    this.setStore(s);
    return;
  }

  public CacheStore getStore() {
    return this._store;
  }

  public void setStore(CacheStore __value_) {
    this._store = __value_;
    return;
  }

  public Object getValue(IType typeparam$T, IFunction0 value, String name) {
    cacheName = this.makeCacheName(value, name);
    *temp2 = [[
      *temp0 = Ronin.getCurrentTrace();
      (*temp0 == null ? ((TraceElement) null) : *temp0.withMessage([[
        *temp1 = new StringBuilder();
        *temp1.append("Cache Load ");
        *temp1.append(cacheName);
        *temp1.toString()
      ]], true))
    ]];
    if (*temp2 != null) {
      *temp2.enter();
    }
    try {
      return this.findInStore(typeparam$T, cacheName, value);
    }
    finally {
      if (*temp2 != null) {
        *temp2.exit();
      }
    }
  }

  public void invalidate(String name) {
    *temp1 = [[
      *temp0 = this.getStore().getLock();
      (*temp0 == null ? ((Lock) null) : *temp0.writeLock())
    ]];
    if (*temp1 != null) {
      *temp1.lock();
    }
    try {
      cachedValue = [[
        *temp2 = this.getStore().loadValue(name);
        ((*temp2 instanceof CachedValue) ? ((CachedValue) *temp2) : ((CachedValue) TypeAsTransformer.coerceValue(*temp2, TypeSystem.getByFullName("ronin.Cache.CachedValue", "_default_").getParameterizedType([[
          *temp3 = new IType[1];
          *temp3[0] = TypeSystem.get(java.lang.Object.class);
          *temp3
        ]]), RuntimeCoercer.instance())))
      ]];
      if (cachedValue != null) {
        Ronin.log([[
          *temp4 = new StringBuilder();
          *temp4.append("CACHE INVALIDATE : ");
          *temp4.append(name);
          *temp4.append(", req:");
          *temp4.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getRequests().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
          *temp4.append(", misses:");
          *temp4.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getMisses().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
          *temp4.toString()
        ]], LogLevel.DEBUG, "Ronin Cache", ((Throwable) null));
        cachedValue.setValue(null);
      }
    }
    finally {
      if (*temp1 != null) {
        *temp1.unlock();
      }
    }
    return;
  }

  internal Object findInStore(IType typeparam$T, String name, IFunction0 blk) {
    cachedValue = null;
    value = null;
    *temp1 = [[
      *temp0 = this.getStore().getLock();
      (*temp0 == null ? ((Lock) null) : *temp0.readLock())
    ]];
    if (*temp1 != null) {
      *temp1.lock();
    }
    try {
      cachedValue = [[
        *temp2 = this.getStore().loadValue(name);
        ((*temp2 instanceof CachedValue) ? ((CachedValue) *temp2) : ((CachedValue) TypeAsTransformer.coerceValue(*temp2, TypeSystem.getByFullName("ronin.Cache.CachedValue", "_default_").getParameterizedType([[
          *temp3 = new IType[1];
          *temp3[0] = typeparam$T;
          *temp3
        ]]), RuntimeCoercer.instance())))
      ]];
      value = [[
        *temp4 = cachedValue;
        (*temp4 == null ? ((Object) null) : *temp4.getValue())
      ]];
    }
    finally {
      if (*temp1 != null) {
        *temp1.unlock();
      }
    }
    if (value == null) {
      *temp6 = [[
        *temp5 = this.getStore().getLock();
        (*temp5 == null ? ((Lock) null) : *temp5.writeLock())
      ]];
      if (*temp6 != null) {
        *temp6.lock();
      }
      try {
        cachedValue = [[
          *temp7 = this.getStore().loadValue(name);
          ((*temp7 instanceof CachedValue) ? ((CachedValue) *temp7) : ((CachedValue) TypeAsTransformer.coerceValue(*temp7, TypeSystem.getByFullName("ronin.Cache.CachedValue", "_default_").getParameterizedType([[
            *temp8 = new IType[1];
            *temp8[0] = typeparam$T;
            *temp8
          ]]), RuntimeCoercer.instance())))
        ]];
        if (cachedValue == null) {
          cachedValue = new CachedValue(typeparam$T);
          this.getStore().saveValue(name, cachedValue);
        }
        value = cachedValue.getValue();
        if (value == null) {
          value = ((Object) blk.invoke());
          if (value == null) {
            value = Cache.NULL_SENTINEL;
          }
          cachedValue.setValue(((Object) TypeAsTransformer.coerceValue(value, typeparam$T, TypeVariableCoercer.instance())));
          cachedValue.getRequests().incrementAndGet();
          cachedValue.getMisses().incrementAndGet();
          if ((Ronin.getCurrentTrace() != null && !(name.startsWith("__ronin__")))) {
            [[
              *temp9 = Ronin.getCurrentTrace();
              if (*temp9 != null) {
                *temp9.addMessage([[
                  *temp10 = new StringBuilder();
                  *temp10.append("CACHE MISS : ");
                  *temp10.append(name);
                  *temp10.append(", req:");
                  *temp10.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getRequests().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
                  *temp10.append(", misses:");
                  *temp10.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getMisses().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
                  *temp10.toString()
                ]]);
              }

            ]];
          }
        }
      }
      finally {
        if (*temp6 != null) {
          *temp6.unlock();
        }
      }
    } else {
      cachedValue.getRequests().incrementAndGet();
      if ((Ronin.getCurrentTrace() != null && !(name.startsWith("__ronin__")))) {
        [[
          *temp11 = Ronin.getCurrentTrace();
          if (*temp11 != null) {
            *temp11.addMessage([[
              *temp12 = new StringBuilder();
              *temp12.append("CACHE HIT : ");
              *temp12.append(name);
              *temp12.append(", req:");
              *temp12.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getRequests().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
              *temp12.append(", misses:");
              *temp12.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(cachedValue.getMisses().get()), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
              *temp12.toString()
            ]]);
          }

        ]];
      }
    }
    if (EqualityExpressionTransformer.evaluate(value, TypeSystem.get(java.lang.Object.class), true, Cache.NULL_SENTINEL, TypeSystem.get(java.lang.Object.class))) {
      return null;
    } else {
      return ((Object) TypeAsTransformer.coerceValue(value, typeparam$T, TypeVariableCoercer.instance()));
    }
  }

  internal String makeCacheName(Object value, String name) {
    if (name != null) {
      return name;
    } else {
      return ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "__ronincache__", value.getClass().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
    }
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("Cache(ronin.Cache.CacheStore)");
    builder.addGosuAnnotation(new Param("s", "The object responsible for storing cached values."));
    builder.startAnnotationInfoForFeature("getValue(block():T, java.lang.String)");
    builder.addGosuAnnotation(new Param("value", "A block which computes the value when invoked."));
    builder.addGosuAnnotation(new Param("name", "A unique identifier for the value."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The retrieved or computed value.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("invalidate(java.lang.String)");
    builder.addGosuAnnotation(new Param("name", "The identifier under which the value was stored."));
    return builder.getAnnotations();
  }

}