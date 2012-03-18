// Compiled from CoreIteratorEnhancement.gsx
public gw.lang.enhancements.CoreIteratorEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static List toList(Iterator $that$, IType typeparam$T) {
    ll = new LinkedList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        e = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        e = ((Object) *temp0.next());

        ll.add(e);
      }
    }
    return ll;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}