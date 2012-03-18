// Compiled from CoreReadWriteLockEnhancement.gsx
public gw.lang.enhancements.CoreReadWriteLockEnhancement extends Object{

  public static Object read(ReadWriteLock $that$, IType typeparam$T, IFunction0 b) {
    returnVal = null;
    readLock = $that$.readLock();
    readLock.lock();
    try {
      returnVal = ((Object) b.invoke());
    }
    finally {
      readLock.unlock();
    }
    return returnVal;
  }

  public static void write(ReadWriteLock $that$, IFunction0 b) {
    writeLock = $that$.writeLock();
    [[
      *temp0 = writeLock;
      *temp1 = b;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreLockEnhancement.with(*temp0, *temp1)
    ]];
    return;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}