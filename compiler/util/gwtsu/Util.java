package gwtsu;

import com.google.gwt.core.client.JavaScriptObject;

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
  
  public static Class typeof(Object obj) {
    return obj.getClass();
  }
  
  public static String roninUrl(String className, String methodName, Object... argNamesTypesAndValues) {
    StringBuilder url = new StringBuilder("/");
    url.append(className)
            .append("/")
            .append(methodName);
    if (argNamesTypesAndValues.length > 0) {
      url.append("?");
      for (int i = 0; i < argNamesTypesAndValues.length; i += 3) {
        String argName = (String) argNamesTypesAndValues[i];
        Class argType = ((Class[]) argNamesTypesAndValues[i + 1])[0];
        Object argValue = ((Object[]) argNamesTypesAndValues[i + 2])[0];
        if (i > 0) {
          url.append("&");
        }
        url.append(argName)
                .append("=")
                // TODO kcp - not quite
                .append(argValue.toString());
      }
    }
    return url.toString();
  }
  
  public static native JavaScriptObject[] splitJSON(String json) /*-{
    return JSON.parse(json);
  }-*/;
}