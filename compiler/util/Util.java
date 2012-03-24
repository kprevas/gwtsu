public class Util {
  public static Iterator makeIterator(Object iterable) {
    if (iterable instanceof Iterable) {
      return ((Iterable) iterable).iterator();
    } else if (iterable instanceof Object[]) {
      Object[] arr = (Object[]) iterable;
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
  }
}