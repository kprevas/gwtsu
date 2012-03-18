// Compiled from QueryResult.gs
public abstract interface tosa.api.QueryResult extends Object implements Iterable {

  public abstract int size() {
  }

  public abstract int getCount() {
  }

  public abstract Object get(int idx) {
  }

  public abstract QueryResult orderBy(IPropertyReference sortColumn, SortDirection sortDirection) {
  }

  public abstract QueryResult orderBySql(String sql) {
  }

  public abstract QueryResult clearOrderBys() {
  }

  public abstract QueryResult page(int startPage, int pageSize, int startOffset) {
  }

  public abstract List loadPage(int pageNumber) {
  }

  public abstract QueryResult clearPaging() {
  }

  public abstract QueryResult clear() {
  }

  public abstract QueryResult clone() {
  }

}