// Compiled from CoreFileEnhancement.gsx
public gw.lang.enhancements.CoreFileEnhancement extends Object{

  public static void usingTempFile(IFunction1 b) {
    f = File.createTempFile("gwtemp", ".tmp");
    try {
      b.invoke(f);
    }
    finally {
      f.delete();
    }
    return;
  }

  public static String read(File $that$) {
    reader = StreamUtil.getInputStreamReader(new FileInputStream($that$));
    *temp0 = reader;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      return StreamUtil.getContent(reader);
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
  }

  public static void write(File $that$, String str) {
    if (!($that$.exists())) {
      $that$.createNewFile();
    }
    out = StreamUtil.getOutputStreamWriter(new FileOutputStream($that$, false));
    *temp0 = out;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      out.write(str);
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
    return;
  }

  public static [B readBytes(File $that$) {
    fis = new FileInputStream($that$);
    *temp0 = fis;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      fc = fis.getChannel();
      size = fc.size();
      if (size > (long) Integer.MAX_VALUE) {
        throw new BufferOverflowException();
      }
      buff = ByteBuffer.allocate((int) size);
      fc.read(buff);
      return buff.array();
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
  }

  public static void writeBytes(File $that$, [B content) {
    if (!($that$.exists())) {
      $that$.createNewFile();
    }
    out = new FileOutputStream($that$, false);
    *temp0 = out;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      out.write(content);
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
    return;
  }

  public static void copyTo(File $that$, File otherFile) {
    reader = new FileInputStream($that$);
    *temp0 = reader;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      out = new FileOutputStream(otherFile, false);
      *temp1 = out;
      GosuRuntimeMethods.invokeLockMethod(*temp1);
      try {
        StreamUtil.copy(reader, out);
      }
      finally {
        if (*temp1 != null) {
          *temp1.close();
        }
      }
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
    return;
  }

  public static boolean containsText(File $that$, String regExpStr) {
    regExp = [[
      *temp1 = [[
        *temp0 = new StringBuilder();
        *temp0.append(".*");
        *temp0.append(regExpStr);
        *temp0.append(".*");
        *temp0.toString()
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreStringEnhancement.toRegEx(*temp1)
    ]];
    reader = new BufferedReader(StreamUtil.getInputStreamReader(new FileInputStream($that$)));
    *temp2 = reader;
    GosuRuntimeMethods.invokeLockMethod(*temp2);
    try {
      line = reader.readLine();
      while (line != null) {
        if (regExp.matcher(line).matches()) {
          return true;
        }
        line = reader.readLine();
      }
    }
    finally {
      if (*temp2 != null) {
        *temp2.close();
      }
    }
    return false;
  }

  public static void deleteRecursively(File $that$) {
    [[
      *temp1 = $that$;
      *temp2 = [[
        *temp0 = new block_0_($that$);
        *temp0._returnType = TypeSystem.get(void.class);
        *temp0
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreFileEnhancement.eachFileInTree(*temp1, *temp2)
    ]];
    return;
  }

  public static void eachFileInTree(File $that$, IFunction1 operation) {
    /*foreach*/
        *temp0 = $that$.listFiles();

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        child = null;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        child = *temp0[*temp2];

        [[
          *temp3 = child;
          *temp4 = operation;
          if (*temp3 == null) {
            throw new NullPointerException();
          }
          CoreFileEnhancement.eachFileInTree(*temp3, *temp4)
        ]];
      }
    }
    operation.invoke($that$);
    return;
  }

  public static void eachChild(File $that$, IFunction1 operation$$unboxedParam) {
    operation = [[
      *temp0 = new IFunction1[1];
      *temp0[0] = operation$$unboxedParam;
      *temp0
    ]];
    [[
      *temp2 = $that$.listFiles();
      *temp3 = TypeSystem.get(java.io.File.class);
      *temp4 = [[
        *temp1 = new block_1_($that$, operation);
        *temp1._returnType = TypeSystem.get(void.class);
        *temp1
      ]];
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreArrayEnhancement.each(*temp2, *temp3, *temp4)
    ]];
    return;
  }

  public static String getExtension(File $that$) {
    lastDot = $that$.getName().lastIndexOf(".");
    return (0 <= lastDot ? $that$.getName().substring(lastDot + 1) : "");
  }

  public static String getNameSansExtension(File $that$) {
    fullName = $that$.getName();
    if ((EqualityExpressionTransformer.evaluate(fullName, TypeSystem.get(java.lang.String.class), true, ".", TypeSystem.get(java.lang.String.class)) || EqualityExpressionTransformer.evaluate(fullName, TypeSystem.get(java.lang.String.class), true, "..", TypeSystem.get(java.lang.String.class)))) {
      return fullName;
    }
    lastDot = fullName.lastIndexOf(".");
    if (lastDot != -1) {
      extent = [[
        *temp0 = fullName;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreStringEnhancement.getlength(*temp0)
      ]] - [[
        *temp2 = [[
          *temp1 = $that$;
          if (*temp1 == null) {
            throw new NullPointerException();
          }
          CoreFileEnhancement.getExtension(*temp1)
        ]];
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreStringEnhancement.getlength(*temp2)
      ]] - 1;
      if (extent >= 0) {
        return fullName.substring(0, extent);
      }
    }
    return fullName;
  }

  public static void eachLine(File $that$, IFunction1 lineProcessor) {
    reader = new BufferedReader(StreamUtil.getInputStreamReader(new FileInputStream($that$)));
    *temp0 = reader;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      line = reader.readLine();
      while (line != null) {
        lineProcessor.invoke(line);
        line = reader.readLine();
      }
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
    return;
  }

  public static boolean differsFrom(File $that$, File that) {
    if (that == null) {
      throw new NullPointerException();
    }
    if (!($that$.exists())) {
      throw new FileNotFoundException($that$.getAbsolutePath());
    }
    if (EqualityExpressionTransformer.evaluate($that$, TypeSystem.get(java.io.File.class), true, that, TypeSystem.get(java.io.File.class))) {
      return false;
    }
    if (!(that.exists())) {
      throw new FileNotFoundException(that.getAbsolutePath());
    }
    if ($that$.length() != that.length()) {
      return true;
    }
    bufferSize = (int) Math.min((long) 1 << 20, $that$.length());
    lfis = new FileInputStream($that$);
    *temp0 = lfis;
    GosuRuntimeMethods.invokeLockMethod(*temp0);
    try {
      rfis = new FileInputStream(that);
      *temp1 = rfis;
      GosuRuntimeMethods.invokeLockMethod(*temp1);
      try {
        lfc = lfis.getChannel();
        rfc = rfis.getChannel();
        lbuff = ByteBuffer.allocate(bufferSize);
        rbuff = ByteBuffer.allocate(bufferSize);
        while (lfc.position() < lfc.size()) {
          lfc.read(lbuff);
          rfc.read(rbuff);
          if (!(Arrays.equals(lbuff.array(), rbuff.array()))) {
            return true;
          }
          lbuff.clear();
          rbuff.clear();
        }
        return false;
      }
      finally {
        if (*temp1 != null) {
          *temp1.close();
        }
      }
    }
    finally {
      if (*temp0 != null) {
        *temp0.close();
      }
    }
  }

  public static boolean isReservedFileName(File $that$) {
    if (EqualityExpressionTransformer.evaluate(CoreSystemEnhancement.getPlatform(), TypeSystem.getByFullName("gw.util.OSType", "_default_"), true, OSType.Windows, TypeSystem.getByFullName("gw.util.OSType", "_default_"))) {
      WINDOWS_RESERVED_DEVICE_NAMES = [[
        *temp0 = new String[23];
        *temp0[0] = "CON";
        *temp0[1] = "PRN";
        *temp0[2] = "AUX";
        *temp0[3] = "CLOCK$";
        *temp0[4] = "NUL";
        *temp0[5] = "COM1";
        *temp0[6] = "COM2";
        *temp0[7] = "COM3";
        *temp0[8] = "COM4";
        *temp0[9] = "COM5";
        *temp0[10] = "COM6";
        *temp0[11] = "COM7";
        *temp0[12] = "COM8";
        *temp0[13] = "COM9";
        *temp0[14] = "LPT1";
        *temp0[15] = "LPT2";
        *temp0[16] = "LPT3";
        *temp0[17] = "LPT4";
        *temp0[18] = "LPT5";
        *temp0[19] = "LPT6";
        *temp0[20] = "LPT7";
        *temp0[21] = "LPT8";
        *temp0[22] = "LPT9";
        *temp0
      ]];
      if (!($that$.isFile())) {
        return false;
      }
      nameNoExtension = $that$.getName();
      lastDot = nameNoExtension.indexOf(".");
      if (lastDot > 0) {
        nameNoExtension = nameNoExtension.substring(0, lastDot);
      }
      nameNoExtension = nameNoExtension.toUpperCase();
      return [[
        *temp1 = WINDOWS_RESERVED_DEVICE_NAMES;
        *temp2 = TypeSystem.get(java.lang.String.class);
        *temp3 = nameNoExtension;
        if (*temp1 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.contains(*temp1, *temp2, *temp3)
      ]];
    }
    return false;
  }

  public static List getChildren(File $that$) {
    return [[
      *temp0 = $that$.listFiles();
      *temp1 = TypeSystem.get(java.io.File.class);
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreArrayEnhancement.toList(*temp0, *temp1)
    ]];
  }

  public static File getChild(File $that$, String name) {
    return new File($that$, name);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}