package gwtsu;

import java.util.Iterator;

public class Util {
  public static Iterator makeIterator(Object iterable) {
    if (iterable instanceof Iterable) {
      return ((Iterable) iterable).iterator();
    } else if (iterable instanceof Object[]) {
      final Object[] arr = (Object[]) iterable;
      return new Iterator() {
        int index = 0;
        public boolean hasNext() {
          return index < arr.length;
        }
        public Object next() {
          return arr[index++];
        }
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    }
    throw new IllegalStateException();
  }
  
  public static Class getByFullName(String className, String module) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}