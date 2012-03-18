// Compiled from CoreLockEnhancement.gsx
public gw.lang.enhancements.CoreLockEnhancement extends Object{

  public static void with(Lock $that$, IFunction0 b) {
    $that$.lock();
    try {
      b.invoke();
    }
    finally {
      $that$.unlock();
    }
    return;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}