package gwtsu;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import gw.lang.ir.IRClass;
import gw.lang.ir.IRExpression;
import gw.lang.ir.IRStatement;
import gw.lang.ir.IRSymbol;
import gw.lang.ir.IRType;
import gw.lang.ir.expression.IRArithmeticExpression;
import gw.lang.ir.expression.IRArrayLengthExpression;
import gw.lang.ir.expression.IRArrayLoadExpression;
import gw.lang.ir.expression.IRCastExpression;
import gw.lang.ir.expression.IRConditionalAndExpression;
import gw.lang.ir.expression.IRConditionalOrExpression;
import gw.lang.ir.expression.IREqualityExpression;
import gw.lang.ir.expression.IRFieldGetExpression;
import gw.lang.ir.expression.IRInstanceOfExpression;
import gw.lang.ir.expression.IRMethodCallExpression;
import gw.lang.ir.expression.IRNegationExpression;
import gw.lang.ir.expression.IRNewArrayExpression;
import gw.lang.ir.expression.IRNewExpression;
import gw.lang.ir.expression.IRNewMultiDimensionalArrayExpression;
import gw.lang.ir.expression.IRNotExpression;
import gw.lang.ir.expression.IRRelationalExpression;
import gw.lang.ir.expression.IRTernaryExpression;
import gw.lang.ir.statement.IRArrayStoreStatement;
import gw.lang.ir.statement.IRAssignmentStatement;
import gw.lang.ir.statement.IRCaseClause;
import gw.lang.ir.statement.IRCatchClause;
import gw.lang.ir.statement.IRDoWhileStatement;
import gw.lang.ir.statement.IRFieldSetStatement;
import gw.lang.ir.statement.IRForEachStatement;
import gw.lang.ir.statement.IRIfStatement;
import gw.lang.ir.statement.IRMethodCallStatement;
import gw.lang.ir.statement.IRMethodStatement;
import gw.lang.ir.statement.IRReturnStatement;
import gw.lang.ir.statement.IRStatementList;
import gw.lang.ir.statement.IRSwitchStatement;
import gw.lang.ir.statement.IRThrowStatement;
import gw.lang.ir.statement.IRTryCatchFinallyStatement;
import gw.lang.ir.statement.IRWhileStatement;
import gw.lang.reflect.IConstructorInfo;
import gw.lang.reflect.IExceptionInfo;
import gw.lang.reflect.IMethodInfo;
import gw.lang.reflect.IType;
import gw.lang.reflect.gs.IGosuClass;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class description...
 *
 * @author kprevas
 */
public class CheckedExceptionAnalyzer {
  
  public static class ExceptionMap {
    private Map<IMethodInfo, List<IType>> map = Maps.newHashMap();
    
    public List<IType> getExceptions(IMethodInfo method) {
      List<IType> exceptions = map.get(method);
      return exceptions != null ? exceptions : Collections.<IType>emptyList();
    }
    
    public void putException(IMethodInfo method, IType exceptionType) {
      List<IType> types = map.get(method);
      if (types == null) {
        types = Lists.newArrayList();
        map.put(method, types);
      }
      types.add(exceptionType);
    }

    public void removeException(IMethodInfo method, IType exceptionType) {
      List<IType> types = map.get(method);
      if (types != null) {
        types.remove(exceptionType);
      }
    }

    public void merge(ExceptionMap otherMap) {
      map.putAll(otherMap.map);
    }
  }
  
  public static void findCheckedExceptions(IGosuClass type, 
                                           ExceptionMap exceptionMap, 
                                           Map<IGosuClass, IRClass> compiledClasses) {
    IRClass irClass = compiledClasses.get(type);
    for (IRMethodStatement methodStatement : irClass.getMethods()) {
      IMethodInfo methodInfo = getMethodInfoFromSymbols(type, methodStatement.getName(), methodStatement.getParameters());
      findCheckedExceptions(methodStatement.getMethodBody(), methodInfo, exceptionMap);
    }
  }

  private static void findCheckedExceptions(IRStatement statement,
                                            IMethodInfo methodInfo,
                                            ExceptionMap exceptionMap) {
    if (statement instanceof IRThrowStatement) {
      exceptionMap.putException(methodInfo, ((IRThrowStatement) statement).getException().getType().getType());
      findCheckedExceptions(((IRThrowStatement) statement).getException(), methodInfo, exceptionMap);
    } else if (statement instanceof IRTryCatchFinallyStatement) {
      ExceptionMap tryBlockExceptionMap = new ExceptionMap();
      findCheckedExceptions(((IRTryCatchFinallyStatement) statement).getTryBody(), methodInfo, tryBlockExceptionMap);
      for (IRCatchClause catchClause : ((IRTryCatchFinallyStatement) statement).getCatchStatements()) {
        tryBlockExceptionMap.removeException(methodInfo, catchClause.getIdentifier().getType().getType());
        findCheckedExceptions(catchClause.getBody(), methodInfo, exceptionMap);
      }
      exceptionMap.merge(tryBlockExceptionMap);
      findCheckedExceptions(((IRTryCatchFinallyStatement) statement).getFinallyBody(), methodInfo, exceptionMap);
    } else if (statement instanceof IRDoWhileStatement) {
      findCheckedExceptions(((IRDoWhileStatement) statement).getBody(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRDoWhileStatement) statement).getLoopTest(), methodInfo, exceptionMap);
    } else if (statement instanceof IRWhileStatement) {
      findCheckedExceptions(((IRWhileStatement) statement).getLoopTest(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRWhileStatement) statement).getBody(), methodInfo, exceptionMap);
    } else if (statement instanceof IRForEachStatement) {
      findCheckedExceptions(((IRForEachStatement) statement).getLoopTest(), methodInfo, exceptionMap);
      for (IRStatement init : ((IRForEachStatement) statement).getInitializers()) {
        findCheckedExceptions(init, methodInfo, exceptionMap);
      }
      for (IRStatement inc : ((IRForEachStatement) statement).getIncrementors()) {
        findCheckedExceptions(inc, methodInfo, exceptionMap);
      }
      findCheckedExceptions(((IRForEachStatement) statement).getBody(), methodInfo, exceptionMap);      
    } else if (statement instanceof IRIfStatement) {
      findCheckedExceptions(((IRIfStatement) statement).getExpression(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRIfStatement) statement).getIfStatement(), methodInfo, exceptionMap);      
      findCheckedExceptions(((IRIfStatement) statement).getElseStatement(), methodInfo, exceptionMap);      
    } else if (statement instanceof IRStatementList) {
      for (IRStatement child : ((IRStatementList) statement).getStatements()) {
        findCheckedExceptions(child, methodInfo, exceptionMap);
      }
    } else if (statement instanceof IRSwitchStatement) {
      findCheckedExceptions(((IRSwitchStatement) statement).getInit(), methodInfo, exceptionMap);
      for (IRCaseClause caseClause : ((IRSwitchStatement) statement).getCases()) {
        for (IRStatement caseStatement : caseClause.getStatements()) {
          findCheckedExceptions(caseStatement, methodInfo, exceptionMap);
        }
      }
      for (IRStatement defaultStatement : ((IRSwitchStatement) statement).getDefaultStatements()) {
        findCheckedExceptions(defaultStatement, methodInfo, exceptionMap);
      }
    } else if (statement instanceof IRReturnStatement) {
      IRExpression returnValue = ((IRReturnStatement) statement).getReturnValue();
      if (returnValue != null) {
        findCheckedExceptions(returnValue, methodInfo, exceptionMap);
      }
    } else if (statement instanceof IRArrayStoreStatement) {
      findCheckedExceptions(((IRArrayStoreStatement) statement).getTarget(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRArrayStoreStatement) statement).getIndex(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRArrayStoreStatement) statement).getValue(), methodInfo, exceptionMap);
    } else if (statement instanceof IRAssignmentStatement) {
      findCheckedExceptions(((IRAssignmentStatement) statement).getValue(), methodInfo, exceptionMap);
    } else if (statement instanceof IRFieldSetStatement) {
      findCheckedExceptions(((IRFieldSetStatement) statement).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRFieldSetStatement) statement).getRhs(), methodInfo, exceptionMap);
    } else if (statement instanceof IRMethodCallStatement) {
      findCheckedExceptions(((IRMethodCallStatement) statement).getExpression(), methodInfo, exceptionMap);
    }
  }

  private static void findCheckedExceptions(IRExpression expression, IMethodInfo methodInfo, ExceptionMap exceptionMap) {
    if (expression instanceof IRMethodCallExpression) {
      IRMethodCallExpression methodCallExpression = (IRMethodCallExpression) expression;
      IType ownersType = methodCallExpression.getOwnersType().getType();
      if (methodCallExpression.getName().equals("<init>")) {
        IConstructorInfo target = getCtorInfoFromTypes(ownersType, methodCallExpression.getParameterTypes());
        for (IExceptionInfo exceptionInfo : target.getExceptions()) {
          exceptionMap.putException(methodInfo, exceptionInfo.getExceptionType());
        }
      } else {
        IMethodInfo target = getMethodInfoFromTypes(ownersType, methodCallExpression.getName(),
                methodCallExpression.getParameterTypes());
        for (IExceptionInfo exceptionInfo : target.getExceptions()) {
          exceptionMap.putException(methodInfo, exceptionInfo.getExceptionType());
        }
      }
    } else if (expression instanceof IRNewExpression) {
      IType ownersType = ((IRNewExpression) expression).getOwnersType().getType();
      IConstructorInfo target = getCtorInfoFromTypes(ownersType, ((IRNewExpression) expression).getParameterTypes());
      for (IExceptionInfo exceptionInfo : target.getExceptions()) {
        exceptionMap.putException(methodInfo, exceptionInfo.getExceptionType());
      }
    } else if (expression instanceof IRArrayLoadExpression) {
      findCheckedExceptions(((IRArrayLoadExpression) expression).getRoot(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRArrayLoadExpression) expression).getIndex(), methodInfo, exceptionMap);
    } else if (expression instanceof IRNotExpression) {
      findCheckedExceptions(((IRNotExpression) expression).getRoot(), methodInfo, exceptionMap);
    } else if (expression instanceof IRNewMultiDimensionalArrayExpression) {
      for (IRExpression size : ((IRNewMultiDimensionalArrayExpression) expression).getSizeExpressions()) {
        findCheckedExceptions(size, methodInfo, exceptionMap);
      }
    } else if (expression instanceof IRRelationalExpression) {
      findCheckedExceptions(((IRRelationalExpression) expression).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRRelationalExpression) expression).getRhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRArithmeticExpression) {
      findCheckedExceptions(((IRArithmeticExpression) expression).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRArithmeticExpression) expression).getRhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRFieldGetExpression) {
      findCheckedExceptions(((IRFieldGetExpression) expression).getLhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRTernaryExpression) {
      findCheckedExceptions(((IRTernaryExpression) expression).getTest(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRTernaryExpression) expression).getTrueValue(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRTernaryExpression) expression).getFalseValue(), methodInfo, exceptionMap);
    } else if (expression instanceof IRConditionalOrExpression) {
      findCheckedExceptions(((IRConditionalOrExpression) expression).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRConditionalOrExpression) expression).getRhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRNewArrayExpression) {
      findCheckedExceptions(((IRNewArrayExpression) expression).getSizeExpression(), methodInfo, exceptionMap);
    } else if (expression instanceof IRCastExpression) {
      findCheckedExceptions(((IRCastExpression) expression).getRoot(), methodInfo, exceptionMap);
    } else if (expression instanceof IRArrayLengthExpression) {
      findCheckedExceptions(((IRArrayLengthExpression) expression).getRoot(), methodInfo, exceptionMap);
    } else if (expression instanceof IRNegationExpression) {
      findCheckedExceptions(((IRNegationExpression) expression).getRoot(), methodInfo, exceptionMap);
    } else if (expression instanceof IREqualityExpression) {
      findCheckedExceptions(((IREqualityExpression) expression).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IREqualityExpression) expression).getRhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRConditionalAndExpression) {
      findCheckedExceptions(((IRConditionalAndExpression) expression).getLhs(), methodInfo, exceptionMap);
      findCheckedExceptions(((IRConditionalAndExpression) expression).getRhs(), methodInfo, exceptionMap);
    } else if (expression instanceof IRInstanceOfExpression) {
      findCheckedExceptions(((IRInstanceOfExpression) expression).getRoot(), methodInfo, exceptionMap);
    }
  }

  public static IMethodInfo getMethodInfoFromSymbols(IGosuClass type, String name, List<IRSymbol> parameters) {
    IType[] paramTypes = new IType[parameters.size()];
    for (int i = 0, parametersSize = parameters.size(); i < parametersSize; i++) {
      IRSymbol parameter = parameters.get(i);
      paramTypes[i] = parameter.getType().getType();
    }
    IMethodInfo method = type.getTypeInfo().getMethod(name, paramTypes);
    if (method == null && (name.startsWith("get") || name.startsWith("set"))) {
      method = type.getTypeInfo().getMethod("@" + name.substring(3), paramTypes);
    }
    return method;
  }

  private static IMethodInfo getMethodInfoFromTypes(IType type, String name, List<IRType> parameterTypes) {
    IType[] paramTypes = new IType[parameterTypes.size()];
    for (int i = 0, parametersSize = parameterTypes.size(); i < parametersSize; i++) {
      IRType parameter = parameterTypes.get(i);
      paramTypes[i] = parameter.getType();
    }
    IMethodInfo method = type.getTypeInfo().getMethod(name, paramTypes);
    if (method == null && (name.startsWith("get") || name.startsWith("set"))) {
      method = type.getTypeInfo().getMethod("@" + name.substring(3), paramTypes);
    }
    return method;
  }

  private static IConstructorInfo getCtorInfoFromTypes(IType type, List<IRType> parameterTypes) {
    IType[] paramTypes = new IType[parameterTypes.size()];
    for (int i = 0, parametersSize = parameterTypes.size(); i < parametersSize; i++) {
      IRType parameter = parameterTypes.get(i);
      paramTypes[i] = parameter.getType();
    }
    return type.getTypeInfo().getConstructor(paramTypes);
  }
}