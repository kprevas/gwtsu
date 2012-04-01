package gwtsu;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import gw.lang.ir.IRClass;
import gw.lang.ir.IRElement;
import gw.lang.ir.IRExpression;
import gw.lang.ir.IRStatement;
import gw.lang.ir.IRSymbol;
import gw.lang.ir.IRType;
import gw.lang.ir.expression.IRArithmeticExpression;
import gw.lang.ir.expression.IRArrayLengthExpression;
import gw.lang.ir.expression.IRArrayLoadExpression;
import gw.lang.ir.expression.IRCastExpression;
import gw.lang.ir.expression.IRCompositeExpression;
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
import gw.lang.reflect.IBlockType;
import gw.lang.reflect.IConstructorInfo;
import gw.lang.reflect.IExceptionInfo;
import gw.lang.reflect.IFeatureInfo;
import gw.lang.reflect.IMethodInfo;
import gw.lang.reflect.IParameterInfo;
import gw.lang.reflect.IRelativeTypeInfo;
import gw.lang.reflect.IType;
import gw.lang.reflect.TypeSystem;
import gw.lang.reflect.gs.IGosuClass;
import gw.lang.reflect.gs.IGosuEnhancement;

import java.util.Arrays;
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
    private Map<IFeatureInfo, List<IType>> map = Maps.newHashMap();
    private Map<IRCompositeExpression, List<IType>> compositeExprMap = Maps.newHashMap();

    public List<IType> getExceptions(IFeatureInfo method) {
      List<IType> exceptions = map.get(method);
      return exceptions != null ? exceptions : Collections.<IType>emptyList();
    }

    public void putException(IFeatureInfo feature, IType exceptionType) {
      if (!TypeSystem.get(RuntimeException.class).isAssignableFrom(exceptionType)) {
        List<IType> types = map.get(feature);
        if (types == null) {
          types = Lists.newArrayList();
          map.put(feature, types);
        }
        types.add(exceptionType);
      }
    }

    public List<IType> getExceptions(IRCompositeExpression compositeExpression) {
      List<IType> exceptions = compositeExprMap.get(compositeExpression);
      return exceptions != null ? exceptions : Collections.<IType>emptyList();
    }

    public void putException(IRCompositeExpression compositeExpression, IType exceptionType) {
      if (!TypeSystem.get(RuntimeException.class).isAssignableFrom(exceptionType)) {
        List<IType> types = compositeExprMap.get(compositeExpression);
        if (types == null) {
          types = Lists.newArrayList();
          compositeExprMap.put(compositeExpression, types);
        }
        types.add(exceptionType);
      }
    }

    public void removeException(IFeatureInfo method, IType exceptionType) {
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
    if (irClass != null) {
      for (IRMethodStatement methodStatement : irClass.getMethods()) {
        IFeatureInfo featureInfo;
        if (methodStatement.getName().equals("<init>")) {
          featureInfo = getCtorInfoFromSymbols(type, methodStatement.getParameters());
        } else {
          featureInfo = getMethodInfoFromSymbols(type, methodStatement.getName(), methodStatement.getParameters());
        }
        if (featureInfo != null) {
          findCheckedExceptions(methodStatement.getMethodBody(), featureInfo, exceptionMap);
        }
      }
    }
  }

  private static void findCheckedExceptions(IRStatement statement,
                                            IFeatureInfo featureInfo,
                                            ExceptionMap exceptionMap) {
    if (statement instanceof IRThrowStatement) {
      exceptionMap.putException(featureInfo, ((IRThrowStatement) statement).getException().getType().getType());
      findCheckedExceptions(((IRThrowStatement) statement).getException(), featureInfo, exceptionMap);
    } else if (statement instanceof IRTryCatchFinallyStatement) {
      ExceptionMap tryBlockExceptionMap = new ExceptionMap();
      findCheckedExceptions(((IRTryCatchFinallyStatement) statement).getTryBody(), featureInfo, tryBlockExceptionMap);
      for (IRCatchClause catchClause : ((IRTryCatchFinallyStatement) statement).getCatchStatements()) {
        tryBlockExceptionMap.removeException(featureInfo, catchClause.getIdentifier().getType().getType());
        findCheckedExceptions(catchClause.getBody(), featureInfo, exceptionMap);
      }
      exceptionMap.merge(tryBlockExceptionMap);
      findCheckedExceptions(((IRTryCatchFinallyStatement) statement).getFinallyBody(), featureInfo, exceptionMap);
    } else if (statement instanceof IRDoWhileStatement) {
      findCheckedExceptions(((IRDoWhileStatement) statement).getBody(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRDoWhileStatement) statement).getLoopTest(), featureInfo, exceptionMap);
    } else if (statement instanceof IRWhileStatement) {
      findCheckedExceptions(((IRWhileStatement) statement).getLoopTest(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRWhileStatement) statement).getBody(), featureInfo, exceptionMap);
    } else if (statement instanceof IRForEachStatement) {
      findCheckedExceptions(((IRForEachStatement) statement).getLoopTest(), featureInfo, exceptionMap);
      for (IRStatement init : ((IRForEachStatement) statement).getInitializers()) {
        findCheckedExceptions(init, featureInfo, exceptionMap);
      }
      for (IRStatement inc : ((IRForEachStatement) statement).getIncrementors()) {
        findCheckedExceptions(inc, featureInfo, exceptionMap);
      }
      findCheckedExceptions(((IRForEachStatement) statement).getBody(), featureInfo, exceptionMap);
    } else if (statement instanceof IRIfStatement) {
      IRIfStatement ifStatement = (IRIfStatement) statement;
      if (ifStatement.getExpression() instanceof IRInstanceOfExpression) {
        IRInstanceOfExpression instanceOfExpression = (IRInstanceOfExpression) ifStatement.getExpression();
        IType testType = instanceOfExpression.getTestType().getType();
        IType rootType = instanceOfExpression.getRoot().getType().getType();
        if (!testType.isInterface() && !rootType.isAssignableFrom(testType)
                && !testType.isAssignableFrom(rootType)) {
          findCheckedExceptions(ifStatement.getElseStatement(), featureInfo, exceptionMap);
          return;
        }
      }
      findCheckedExceptions(ifStatement.getExpression(), featureInfo, exceptionMap);
      findCheckedExceptions(ifStatement.getIfStatement(), featureInfo, exceptionMap);
      findCheckedExceptions(ifStatement.getElseStatement(), featureInfo, exceptionMap);
    } else if (statement instanceof IRStatementList) {
      for (IRStatement child : ((IRStatementList) statement).getStatements()) {
        findCheckedExceptions(child, featureInfo, exceptionMap);
      }
    } else if (statement instanceof IRSwitchStatement) {
      findCheckedExceptions(((IRSwitchStatement) statement).getInit(), featureInfo, exceptionMap);
      for (IRCaseClause caseClause : ((IRSwitchStatement) statement).getCases()) {
        for (IRStatement caseStatement : caseClause.getStatements()) {
          findCheckedExceptions(caseStatement, featureInfo, exceptionMap);
        }
      }
      for (IRStatement defaultStatement : ((IRSwitchStatement) statement).getDefaultStatements()) {
        findCheckedExceptions(defaultStatement, featureInfo, exceptionMap);
      }
    } else if (statement instanceof IRReturnStatement) {
      IRExpression returnValue = ((IRReturnStatement) statement).getReturnValue();
      if (returnValue != null) {
        findCheckedExceptions(returnValue, featureInfo, exceptionMap);
      }
    } else if (statement instanceof IRArrayStoreStatement) {
      findCheckedExceptions(((IRArrayStoreStatement) statement).getTarget(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRArrayStoreStatement) statement).getIndex(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRArrayStoreStatement) statement).getValue(), featureInfo, exceptionMap);
    } else if (statement instanceof IRAssignmentStatement) {
      findCheckedExceptions(((IRAssignmentStatement) statement).getValue(), featureInfo, exceptionMap);
    } else if (statement instanceof IRFieldSetStatement) {
      findCheckedExceptions(((IRFieldSetStatement) statement).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRFieldSetStatement) statement).getRhs(), featureInfo, exceptionMap);
    } else if (statement instanceof IRMethodCallStatement) {
      findCheckedExceptions(((IRMethodCallStatement) statement).getExpression(), featureInfo, exceptionMap);
    }
  }

  private static void findCheckedExceptions(IRExpression expression, IFeatureInfo featureInfo, ExceptionMap exceptionMap) {
    if (expression instanceof IRMethodCallExpression) {
      IRMethodCallExpression methodCallExpression = (IRMethodCallExpression) expression;
      IType ownersType = methodCallExpression.getOwnersType().getType();
      if (methodCallExpression.getName().equals("<init>")) {
        IConstructorInfo target = getCtorInfoFromTypes(ownersType, methodCallExpression.getParameterTypes());
        for (IExceptionInfo exceptionInfo : target.getExceptions()) {
          exceptionMap.putException(featureInfo, exceptionInfo.getExceptionType());
        }
      } else {
        IMethodInfo target = getMethodInfoFromTypes(ownersType, methodCallExpression.getName(),
                methodCallExpression.getParameterTypes());
        for (IExceptionInfo exceptionInfo : target.getExceptions()) {
          exceptionMap.putException(featureInfo, exceptionInfo.getExceptionType());
        }
        for (IType exceptionType : exceptionMap.getExceptions(target)) {
          exceptionMap.putException(featureInfo, exceptionType);
        }
      }
    } else if (expression instanceof IRNewExpression) {
      IType ownersType = ((IRNewExpression) expression).getOwnersType().getType();
      IConstructorInfo target = getCtorInfoFromTypes(ownersType, ((IRNewExpression) expression).getParameterTypes());
      for (IExceptionInfo exceptionInfo : target.getExceptions()) {
        exceptionMap.putException(featureInfo, exceptionInfo.getExceptionType());
      }
      for (IType exceptionType : exceptionMap.getExceptions(target)) {
        exceptionMap.putException(featureInfo, exceptionType);
      }
    } else if (expression instanceof IRCompositeExpression) {
      ExceptionMap compositeExceptionMap = new ExceptionMap();
      for (IRElement element : ((IRCompositeExpression) expression).getElements()) {
        if (element instanceof IRStatement) {
          findCheckedExceptions((IRStatement) element, featureInfo, compositeExceptionMap);
        } else if (element instanceof IRExpression) {
          findCheckedExceptions((IRExpression) element, featureInfo, compositeExceptionMap);
        }
      }
      for (IType compositeException : compositeExceptionMap.getExceptions(featureInfo)) {
        exceptionMap.putException((IRCompositeExpression) expression, compositeException);
      }
      exceptionMap.merge(compositeExceptionMap);
    } else if (expression instanceof IRArrayLoadExpression) {
      findCheckedExceptions(((IRArrayLoadExpression) expression).getRoot(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRArrayLoadExpression) expression).getIndex(), featureInfo, exceptionMap);
    } else if (expression instanceof IRNotExpression) {
      findCheckedExceptions(((IRNotExpression) expression).getRoot(), featureInfo, exceptionMap);
    } else if (expression instanceof IRNewMultiDimensionalArrayExpression) {
      for (IRExpression size : ((IRNewMultiDimensionalArrayExpression) expression).getSizeExpressions()) {
        findCheckedExceptions(size, featureInfo, exceptionMap);
      }
    } else if (expression instanceof IRRelationalExpression) {
      findCheckedExceptions(((IRRelationalExpression) expression).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRRelationalExpression) expression).getRhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRArithmeticExpression) {
      findCheckedExceptions(((IRArithmeticExpression) expression).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRArithmeticExpression) expression).getRhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRFieldGetExpression) {
      findCheckedExceptions(((IRFieldGetExpression) expression).getLhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRTernaryExpression) {
      findCheckedExceptions(((IRTernaryExpression) expression).getTest(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRTernaryExpression) expression).getTrueValue(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRTernaryExpression) expression).getFalseValue(), featureInfo, exceptionMap);
    } else if (expression instanceof IRConditionalOrExpression) {
      findCheckedExceptions(((IRConditionalOrExpression) expression).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRConditionalOrExpression) expression).getRhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRNewArrayExpression) {
      findCheckedExceptions(((IRNewArrayExpression) expression).getSizeExpression(), featureInfo, exceptionMap);
    } else if (expression instanceof IRCastExpression) {
      findCheckedExceptions(((IRCastExpression) expression).getRoot(), featureInfo, exceptionMap);
    } else if (expression instanceof IRArrayLengthExpression) {
      findCheckedExceptions(((IRArrayLengthExpression) expression).getRoot(), featureInfo, exceptionMap);
    } else if (expression instanceof IRNegationExpression) {
      findCheckedExceptions(((IRNegationExpression) expression).getRoot(), featureInfo, exceptionMap);
    } else if (expression instanceof IREqualityExpression) {
      findCheckedExceptions(((IREqualityExpression) expression).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IREqualityExpression) expression).getRhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRConditionalAndExpression) {
      findCheckedExceptions(((IRConditionalAndExpression) expression).getLhs(), featureInfo, exceptionMap);
      findCheckedExceptions(((IRConditionalAndExpression) expression).getRhs(), featureInfo, exceptionMap);
    } else if (expression instanceof IRInstanceOfExpression) {
      findCheckedExceptions(((IRInstanceOfExpression) expression).getRoot(), featureInfo, exceptionMap);
    }
  }

  public static IMethodInfo getMethodInfoFromSymbols(IGosuClass type, String name, List<IRSymbol> parameters) {
    List<IRType> types = Lists.newArrayList();
    for (IRSymbol parameter : parameters) {
      types.add(parameter.getType());
    }
    return getMethodInfoFromTypes(type, name, types);
  }

  private static IMethodInfo getMethodInfoFromTypes(IType type, String name, List<IRType> parameterTypes) {
    IType[] paramTypes = new IType[parameterTypes.size()];
    boolean hasBlocks = false;
    for (int i = 0, parametersSize = parameterTypes.size(); i < parametersSize; i++) {
      IRType parameter = parameterTypes.get(i);
      paramTypes[i] = parameter.getType();
      if (paramTypes[i].getName().startsWith("gw.lang.function.IFunction")) {
        hasBlocks = true;
      }
    }
    IMethodInfo method = null;
    if (hasBlocks) {
      List<? extends IMethodInfo> methods;
      if (type.getTypeInfo() instanceof IRelativeTypeInfo) {
        methods = ((IRelativeTypeInfo) type.getTypeInfo()).getMethods(type);
      } else {
        methods = type.getTypeInfo().getMethods();
      }
      for (IMethodInfo methodInfo : methods) {
        IParameterInfo[] parameters = methodInfo.getParameters();
        if (parameters.length == paramTypes.length) {
          boolean match = true;
          for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            IParameterInfo parameterInfo = parameters[i];
            if (paramTypes[i].getName().startsWith("gw.lang.function.IFunction")
                    && parameterInfo.getFeatureType() instanceof IBlockType) {
              IMethodInfo blockMethod = paramTypes[i].getTypeInfo().getMethods().get(0);
              IBlockType blockType = (IBlockType) parameterInfo.getFeatureType();
              if (!blockMethod.getReturnType().equals(blockType.getReturnType())) {
                match = false;
                break;
              }
              if (blockMethod.getParameters().length != blockType.getParameterTypes().length) {
                match = false;
                break;
              }
              IParameterInfo[] blockParams = blockMethod.getParameters();
              for (int j = 0, blockParamsLength = blockParams.length; j < blockParamsLength; j++) {
                if (!blockParams[i].getFeatureType().equals(blockType.getParameterTypes()[i])) {
                  match = false;
                  break;
                }
              }
            } else {
              if (!parameterInfo.getFeatureType().equals(paramTypes[i])) {
                match = false;
                break;
              }
            }
          }
          if (match) {
            method = methodInfo;
            break;
          }
        }
      }
    } else {
      if (type.getTypeInfo() instanceof IRelativeTypeInfo) {
        method = ((IRelativeTypeInfo) type.getTypeInfo()).getMethod(type, name, paramTypes);
      } else {
        method = type.getTypeInfo().getMethod(name, paramTypes);
      }
    }
    if (method == null && (name.startsWith("get") || name.startsWith("set"))) {
      method = type.getTypeInfo().getMethod("@" + name.substring(3), paramTypes);
    }
    if (method == null) {
      if (type instanceof IGosuEnhancement) {
        int actualStart = 1;
        IType enhancedType = ((IGosuEnhancement) type).getEnhancedType();
        if (enhancedType.isGenericType()) {
          actualStart += enhancedType.getGenericTypeVariables().length;
        }
        if (parameterTypes.size() >= actualStart) {
          method = getMethodInfoFromTypes(type, name, parameterTypes.subList(actualStart, parameterTypes.size()));
        }
      }
      if (method == null && type.isGenericType()) {
        int numTypeVars = type.getGenericTypeVariables().length;
        if (parameterTypes.size() > numTypeVars) {
          method = getMethodInfoFromTypes(
                  type.getParameterizedType(Arrays.copyOfRange(paramTypes, 0, numTypeVars)),
                  name,
                  parameterTypes.subList(numTypeVars, parameterTypes.size()));
        }
      }

    }
    return method;
  }

  public static IConstructorInfo getCtorInfoFromSymbols(IGosuClass type, List<IRSymbol> parameters) {
    List<IRType> types = Lists.newArrayList();
    for (IRSymbol parameter : parameters) {
      types.add(parameter.getType());
    }
    return getCtorInfoFromTypes(type, types);
  }

  private static IConstructorInfo getCtorInfoFromTypes(IType type, List<IRType> parameterTypes) {
    IType[] paramTypes = new IType[parameterTypes.size()];
    boolean hasBlocks = false;
    for (int i = 0, parametersSize = parameterTypes.size(); i < parametersSize; i++) {
      IRType parameter = parameterTypes.get(i);
      paramTypes[i] = parameter.getType();
      if (paramTypes[i].getName().startsWith("gw.lang.function.IFunction")) {
        hasBlocks = true;
      }
    }
    IConstructorInfo ctor = null;
    if (hasBlocks) {
      List<? extends IConstructorInfo> constructors;
      if (type.getTypeInfo() instanceof IRelativeTypeInfo) {
        constructors = ((IRelativeTypeInfo) type.getTypeInfo()).getConstructors(type);
      } else {
        constructors = type.getTypeInfo().getConstructors();
      }
      for (IConstructorInfo constructorInfo : constructors) {
        IParameterInfo[] parameters = constructorInfo.getParameters();
        if (parameters.length == paramTypes.length) {
          boolean match = true;
          for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
            IParameterInfo parameterInfo = parameters[i];
            if (paramTypes[i].getName().startsWith("gw.lang.function.IFunction")
                    && parameterInfo.getFeatureType() instanceof IBlockType) {
              IMethodInfo blockMethod = paramTypes[i].getTypeInfo().getMethods().get(0);
              IBlockType blockType = (IBlockType) parameterInfo.getFeatureType();
              if (!blockMethod.getReturnType().equals(blockType.getReturnType())) {
                match = false;
                break;
              }
              if (blockMethod.getParameters().length != blockType.getParameterTypes().length) {
                match = false;
                break;
              }
              IParameterInfo[] blockParams = blockMethod.getParameters();
              for (int j = 0, blockParamsLength = blockParams.length; j < blockParamsLength; j++) {
                if (!blockParams[i].getFeatureType().equals(blockType.getParameterTypes()[i])) {
                  match = false;
                  break;
                }
              }
            } else {
              if (!parameterInfo.getFeatureType().equals(paramTypes[i])) {
                match = false;
                break;
              }
            }
          }
          if (match) {
            ctor = constructorInfo;
            break;
          }
        }
      }
    } else {
      if (type.getTypeInfo() instanceof IRelativeTypeInfo) {
        ctor = ((IRelativeTypeInfo) type.getTypeInfo()).getConstructor(type, paramTypes);
      } else {
        ctor = type.getTypeInfo().getConstructor(paramTypes);
      }
    }
    return ctor;
  }
}