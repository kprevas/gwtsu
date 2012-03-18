// Compiled from CoreMapEnhancement.gsx
public gw.lang.enhancements.CoreMapEnhancement extends Object{
  internal final synthetic IType typeparam$K;
  internal final synthetic IType typeparam$V;

  public static Map readFromPropertiesFile(File file) {
    if (!(file.exists())) {
      return new HashMap();
    }
    ins = new FileInputStream(file);
    *temp0 = ins;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      x = new Properties();
      x.load(ins);
      return ((Map) x);
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
  }

  public static Set getKeys(Map $that$, IType typeparam$K, IType typeparam$V) {
    return $that$.keySet();
  }

  public static Collection getValues(Map $that$, IType typeparam$K, IType typeparam$V) {
    return $that$.values();
  }

  public static void eachKeyAndValue(Map $that$, IType typeparam$K, IType typeparam$V, IFunction2 eachBlock) {
    /*foreach*/
        *temp3 = ForEachStatementTransformer.makeIterator([[
      *temp0 = $that$;
      *temp1 = typeparam$K;
      *temp2 = typeparam$V;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.getKeys(*temp0, *temp1, *temp2)
    ]]);

        key = null;

    if (*temp3 != null) {
      while (*temp3.hasNext()) {
        key = ((Object) *temp3.next());

        eachBlock.invoke(key, ((Object) $that$.get(key)));
      }
    }
    return;
  }

  public static void eachKey(Map $that$, IType typeparam$K, IType typeparam$V, IFunction1 eachBlock) {
    /*foreach*/
        *temp3 = ForEachStatementTransformer.makeIterator([[
      *temp0 = $that$;
      *temp1 = typeparam$K;
      *temp2 = typeparam$V;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.getKeys(*temp0, *temp1, *temp2)
    ]]);

        key = null;

    if (*temp3 != null) {
      while (*temp3.hasNext()) {
        key = ((Object) *temp3.next());

        eachBlock.invoke(key);
      }
    }
    return;
  }

  public static void eachValue(Map $that$, IType typeparam$K, IType typeparam$V, IFunction1 eachBlock) {
    /*foreach*/
        *temp3 = ForEachStatementTransformer.makeIterator([[
      *temp0 = $that$;
      *temp1 = typeparam$K;
      *temp2 = typeparam$V;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.getKeys(*temp0, *temp1, *temp2)
    ]]);

        key = null;

    if (*temp3 != null) {
      while (*temp3.hasNext()) {
        key = ((Object) *temp3.next());

        eachBlock.invoke(((Object) $that$.get(key)));
      }
    }
    return;
  }

  public static Map mapValues(Map $that$, IType typeparam$K, IType typeparam$V, IType typeparam$V2, IFunction1 mapper$$unboxedParam) {
    mapper = [[
      *temp0 = new IFunction1[1];
      *temp0[0] = mapper$$unboxedParam;
      *temp0
    ]];
    returnMap = [[
      *temp1 = new HashMap[1];
      *temp1[0] = new HashMap();
      *temp1
    ]];
    [[
      *temp3 = $that$;
      *temp4 = typeparam$K;
      *temp5 = typeparam$V;
      *temp6 = [[
        *temp2 = new block_0_($that$, returnMap, mapper, typeparam$K, typeparam$V, typeparam$V2);
        *temp2._returnType = TypeSystem.get(void.class);
        *temp2
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.eachKeyAndValue(*temp3, *temp4, *temp5, *temp6)
    ]];
    return returnMap[0];
  }

  public static Map toAutoMap(Map $that$, IType typeparam$K, IType typeparam$V, IFunction1 defaultValue) {
    return new AutoMap(typeparam$K, typeparam$V, $that$, defaultValue);
  }

  public static Map copy(Map $that$, IType typeparam$K, IType typeparam$V) {
    return new HashMap($that$);
  }

  public static Map filterKeys(Map $that$, IType typeparam$K, IType typeparam$V, IFunction1 keyFilter) {
    iterator = $that$.entrySet().iterator();
    while (iterator.hasNext()) {
      entry = ((Map$Entry) iterator.next());
      if (!(((Boolean) keyFilter.invoke(entry.getKey())).booleanValue())) {
        iterator.remove();
      }
    }
    return $that$;
  }

  public static Map filterValues(Map $that$, IType typeparam$K, IType typeparam$V, IFunction1 valueFilter) {
    iterator = $that$.entrySet().iterator();
    while (iterator.hasNext()) {
      entry = ((Map$Entry) iterator.next());
      if (!(((Boolean) valueFilter.invoke(entry.getValue())).booleanValue())) {
        iterator.remove();
      }
    }
    return $that$;
  }

  public static void writeToPropertiesFile(Map $that$, IType typeparam$K, IType typeparam$V, File file) {
    [[
      *temp0 = $that$;
      *temp1 = typeparam$K;
      *temp2 = typeparam$V;
      *temp3 = file;
      *temp4 = "";
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.writeToPropertiesFile(*temp0, *temp1, *temp2, *temp3, *temp4)
    ]];
    return;
  }

  public static void writeToPropertiesFile(Map $that$, IType typeparam$K, IType typeparam$V, File file, String comments) {
    x = new Properties();
    x.putAll($that$);
    out = new FileOutputStream(file);
    *temp0 = out;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      x.store(out, comments);
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
    return;
  }

  public static int getCount(Map $that$, IType typeparam$K, IType typeparam$V) {
    return $that$.size();
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}