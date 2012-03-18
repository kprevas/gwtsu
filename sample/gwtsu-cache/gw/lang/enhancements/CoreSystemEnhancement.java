// Compiled from CoreSystemEnhancement.gsx
public gw.lang.enhancements.CoreSystemEnhancement extends Object{

  public static String getCurrentUser() {
    return System.getProperty("user.name");
  }

  public static OSType getPlatform() {
    g_iPlatform = null;
    try {
      strOSName = System.getProperty("os.name");
      if (strOSName != null) {
        strOSName = strOSName.toLowerCase();
        if (strOSName.startsWith("windows")) {
          g_iPlatform = OSType.Windows;
        } else {
          if (strOSName.startsWith("mac")) {
            g_iPlatform = OSType.Macintosh;
          } else {
            if (strOSName.startsWith("solaris")) {
              g_iPlatform = OSType.Solaris;
            } else {
              if (strOSName.startsWith("linux")) {
                g_iPlatform = OSType.Linux;
              } else {
                if (strOSName.startsWith("os/2")) {
                  g_iPlatform = OSType.OS2;
                } else {
                  if (strOSName.startsWith("aix")) {
                    g_iPlatform = OSType.AIX;
                  } else {
                    if (strOSName.startsWith("hp")) {
                      g_iPlatform = OSType.HPUX;
                    } else {
                      if (strOSName.startsWith("netware")) {
                        g_iPlatform = OSType.Netware;
                      } else {
                        g_iPlatform = OSType.Undefined;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    catch( Throwable t) {
      t.printStackTrace();
    }
    return g_iPlatform;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}