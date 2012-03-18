// Compiled from QueryExecutor.gs
public abstract interface tosa.impl.QueryExecutor extends Object implements IGosuClassObject {

  public abstract int count(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
  }

  public abstract List selectEntity(String profilerTag, IDBType targetType, String sqlStatement, IPreparedStatementParameter; parameters) {
  }

  public abstract void update(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
  }

  public abstract Object insert(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
  }

  public abstract void delete(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
  }

}