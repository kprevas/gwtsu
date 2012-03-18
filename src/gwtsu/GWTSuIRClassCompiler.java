package gwtsu;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.google.gwt.thirdparty.guava.common.collect.Sets;
import gw.lang.ir.IRAnnotation;
import gw.lang.ir.IRClass;
import gw.lang.ir.IRElement;
import gw.lang.ir.IRExpression;
import gw.lang.ir.IRStatement;
import gw.lang.ir.IRSymbol;
import gw.lang.ir.IRType;
import gw.lang.ir.expression.IRArithmeticExpression;
import gw.lang.ir.expression.IRArrayLengthExpression;
import gw.lang.ir.expression.IRArrayLoadExpression;
import gw.lang.ir.expression.IRBooleanLiteral;
import gw.lang.ir.expression.IRCastExpression;
import gw.lang.ir.expression.IRCharacterLiteral;
import gw.lang.ir.expression.IRClassLiteral;
import gw.lang.ir.expression.IRCompositeExpression;
import gw.lang.ir.expression.IRConditionalAndExpression;
import gw.lang.ir.expression.IRConditionalOrExpression;
import gw.lang.ir.expression.IREqualityExpression;
import gw.lang.ir.expression.IRFieldGetExpression;
import gw.lang.ir.expression.IRIdentifier;
import gw.lang.ir.expression.IRInstanceOfExpression;
import gw.lang.ir.expression.IRMethodCallExpression;
import gw.lang.ir.expression.IRNegationExpression;
import gw.lang.ir.expression.IRNewArrayExpression;
import gw.lang.ir.expression.IRNewExpression;
import gw.lang.ir.expression.IRNewMultiDimensionalArrayExpression;
import gw.lang.ir.expression.IRNoOpExpression;
import gw.lang.ir.expression.IRNotExpression;
import gw.lang.ir.expression.IRNullLiteral;
import gw.lang.ir.expression.IRNumericLiteral;
import gw.lang.ir.expression.IRPrimitiveTypeConversion;
import gw.lang.ir.expression.IRRelationalExpression;
import gw.lang.ir.expression.IRStringLiteralExpression;
import gw.lang.ir.expression.IRTernaryExpression;
import gw.lang.ir.statement.IRArrayStoreStatement;
import gw.lang.ir.statement.IRAssignmentStatement;
import gw.lang.ir.statement.IRBreakStatement;
import gw.lang.ir.statement.IRCatchClause;
import gw.lang.ir.statement.IRContinueStatement;
import gw.lang.ir.statement.IRDoWhileStatement;
import gw.lang.ir.statement.IREvalStatement;
import gw.lang.ir.statement.IRFieldDecl;
import gw.lang.ir.statement.IRFieldSetStatement;
import gw.lang.ir.statement.IRForEachStatement;
import gw.lang.ir.statement.IRIfStatement;
import gw.lang.ir.statement.IRMethodCallStatement;
import gw.lang.ir.statement.IRMethodStatement;
import gw.lang.ir.statement.IRMonitorLockAcquireStatement;
import gw.lang.ir.statement.IRMonitorLockReleaseStatement;
import gw.lang.ir.statement.IRNoOpStatement;
import gw.lang.ir.statement.IRReturnStatement;
import gw.lang.ir.statement.IRStatementList;
import gw.lang.ir.statement.IRSwitchStatement;
import gw.lang.ir.statement.IRSyntheticStatement;
import gw.lang.ir.statement.IRThrowStatement;
import gw.lang.ir.statement.IRTryCatchFinallyStatement;
import gw.lang.ir.statement.IRWhileStatement;
import gw.lang.reflect.Modifier;
import gw.util.GosuClassUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kprevas
 */
public class GWTSuIRClassCompiler {
  
  private final static Map<String, String> replacementMethods = Maps.newHashMap();

  {
    replacementMethods.put("gw.internal.gosu.ir.transform.statement.ForEachStatementTransformer.makeIterator",
            "Util.makeIterator");
  }
  
  private IRClass irClass;

  public String compileToJava(IRClass irClass) {
    this.irClass = irClass;
    StringBuilder builder = new StringBuilder();
    builder.append("package ")
            .append(GosuClassUtil.getPackage(irClass.getName()))
            .append(";\n");
    appendClass(builder);
    return builder.toString();
  }

  private void appendClass(StringBuilder builder) {
    for (IRAnnotation annotation : irClass.getAnnotations()) {
      appendAnnotation(builder, annotation);
    }
    builder.append(Modifier.toModifierString(
            irClass.getModifiers() & ~Modifier.SYNCHRONIZED));
    if (irClass.getThisType().isInterface()) {
      builder.append(" interface ");
    } else {
      builder.append(" class ");
    }
    builder.append(irClass.getThisType().getRelativeName());
    if (irClass.getSuperType() != null) {
      builder.append(" extends ")
              .append(getTypeName(irClass.getSuperType()));
    }
    List<IRType> interfaces = Lists.newArrayList(irClass.getInterfaces());
    Iterator<IRType> iterator = interfaces.iterator();
    while (iterator.hasNext()) {
      IRType type = iterator.next();
      if (getTypeName(type).equals("gw.lang.reflect.gs.IGosuClassObject")) {
        iterator.remove();
      }
    }
    if (!interfaces.isEmpty()) {
      builder.append(" implements ");
      for (int i = 0, interfacesSize = interfaces.size(); i < interfacesSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        builder.append(getTypeName(interfaces.get(i)));
      }
    }
    builder.append(" {\n");
//    for (IRClass.InnerClassInfo innerClassInfo : irClass.getInnerClasses()) {
//      appendClass(builder, innerClassInfo.getInnerClass());
//    }
    for (IRFieldDecl field : irClass.getFields()) {
      appendField(builder, field);
    }
    for (IRMethodStatement method : irClass.getMethods()) {
      appendMethod(builder, method, irClass);
    }
    builder.append("}");
  }

  private void appendField(StringBuilder builder, IRFieldDecl field) {
    for (IRAnnotation annotation : field.getAnnotations()) {
      appendAnnotation(builder, annotation);
    }
    builder.append(Modifier.toModifierString(field.getModifiers()))
            .append(" ")
            .append(getTypeName(field.getType()))
            .append(" ")
            .append(field.getName());
    // TODO kcp - initializer?
    builder.append(";\n");
  }

  private void appendMethod(StringBuilder builder, IRMethodStatement method, IRClass ownerType) {
    if (method.getName().equals("getIntrinsicType") || method.getName().startsWith("$")) {
      return;
    }
    Map<String, IRSymbol> symbols = Maps.newHashMap();
    for (IRAnnotation annotation : method.getAnnotations()) {
      appendAnnotation(builder, annotation);
    }
    if (!method.getName().equals("<clinit>")) {
      builder.append(Modifier.toModifierString(method.getModifiers()))
              .append(" ");
      if (method.getName().equals("<init>")) {
        builder.append(ownerType.getThisType().getRelativeName());
      } else {
        builder.append(getTypeName(method.getReturnType()))
                .append(" ")
                .append(method.getName());
      }
      builder.append("(");
      List<IRSymbol> parameters = method.getParameters();
      for (int i = 0, parametersSize = parameters.size(); i < parametersSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        IRSymbol parameter = parameters.get(i);
        String paramName = getSymbolName(parameter);
        builder.append(getTypeName(parameter.getType()))
                .append(" ")
                .append(paramName);
        symbols.put(paramName, parameter);
      }
      builder.append(") ");
    }
    if (method.getMethodBody() != null) {
      if (!(method.getMethodBody() instanceof IRStatementList)) {
        builder.append("{\n");
      }
      appendStatement(builder, method.getMethodBody(), symbols);
      if (!(method.getMethodBody() instanceof IRStatementList)) {
        builder.append("}\n");
      }
    } else {
      builder.append(";\n");
    }
  }

  private void appendStatement(StringBuilder builder, IRStatement statement, Map<String, IRSymbol> symbols) {
    if (statement instanceof IRFieldDecl) {
      // TODO kcp
      builder.append("/* IRFieldDecl */");
    } else if (statement instanceof IRMethodStatement) {
      // TODO kcp
      builder.append("/* IRMethodStatement */");
    } else if (statement instanceof IRArrayStoreStatement) {
      IRArrayStoreStatement arrayStoreStatement = (IRArrayStoreStatement) statement;
      appendExpression(builder, arrayStoreStatement.getTarget(), symbols);
      builder.append("[");
      appendExpression(builder, arrayStoreStatement.getIndex(), symbols);
      builder.append("] = ");
      appendExpression(builder, arrayStoreStatement.getValue(), symbols);
      builder.append(";\n");
    } else if (statement instanceof IRAssignmentStatement) {
      IRAssignmentStatement assignmentStatement = (IRAssignmentStatement) statement;
      String symbolName = getSymbolName(assignmentStatement.getSymbol());
      if (!symbols.containsKey(symbolName)) {
        builder.append(getTypeName(assignmentStatement.getSymbol().getType()))
                .append(" ");
        symbols.put(symbolName, assignmentStatement.getSymbol());
      }
      builder.append(symbolName)
              .append(" = ");
      appendExpression(builder, assignmentStatement.getValue(), symbols);
      builder.append(";\n");
    } else if (statement instanceof IRBreakStatement) {
      builder.append("break;\n");
    } else if (statement instanceof IRContinueStatement) {
      builder.append("continue;\n");
    } else if (statement instanceof IRDoWhileStatement) {
      IRDoWhileStatement doWhileStatement = (IRDoWhileStatement) statement;
      builder.append("do ");
      appendStatement(builder, doWhileStatement.getBody(), Maps.newHashMap(symbols));
      builder.append("while(");
      appendExpression(builder, doWhileStatement.getLoopTest(), symbols);
      builder.append(");\n");
    } else if (statement instanceof IREvalStatement) {
      // TODO kcp
      builder.append("/* IREvalStatement */");
    } else if (statement instanceof IRFieldSetStatement) {
      IRFieldSetStatement fieldSetStatement = (IRFieldSetStatement) statement;
      if (fieldSetStatement.getLhs() != null) {
        appendExpression(builder, fieldSetStatement.getLhs(), symbols);
        builder.append(".");
      }
      builder.append(fieldSetStatement.getName())
              .append(" = ");
      appendExpression(builder, fieldSetStatement.getRhs(), symbols);
      builder.append(";\n");
    } else if (statement instanceof IRForEachStatement) {
      IRForEachStatement forEachStatement = (IRForEachStatement) statement;
      Map<String, IRSymbol> innerSymbols = Maps.newHashMap(symbols);
      builder.append("for (");
      List<IRStatement> initializers = forEachStatement.getInitializers();
      for (int i = 0, initializersSize = initializers.size(); i < initializersSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        appendStatement(builder, initializers.get(i), innerSymbols);
        if (builder.substring(builder.length() - 2, builder.length()).equals(";\n")) {
          builder.setLength(builder.length() - 2);
        }
      }
      builder.append("; ");
      appendExpression(builder, forEachStatement.getLoopTest(), symbols);
      builder.append("; ");
      List<IRStatement> incrementors = forEachStatement.getIncrementors();
      for (int i = 0, incrementorsSize = incrementors.size(); i < incrementorsSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        appendStatement(builder, incrementors.get(i), innerSymbols);
        if (builder.substring(builder.length() - 2, builder.length()).equals(";\n")) {
          builder.setLength(builder.length() - 2);
        }
      }
      builder.append(") ");
      appendStatement(builder, forEachStatement.getBody(), innerSymbols);
    } else if (statement instanceof IRIfStatement) {
      IRIfStatement ifStatement = (IRIfStatement) statement;
      builder.append("if (");
      appendExpression(builder, ifStatement.getExpression(), symbols);
      builder.append(") ");
      appendStatement(builder, ifStatement.getIfStatement(), Maps.newHashMap(symbols));
      if (ifStatement.getElseStatement() != null) {
        builder.append(" else ");
        appendStatement(builder, ifStatement.getElseStatement(), Maps.newHashMap(symbols));
      }
    } else if (statement instanceof IRMethodCallStatement) {
      appendExpression(builder, ((IRMethodCallStatement) statement).getExpression(), symbols);
      builder.append(";\n");
    } else if (statement instanceof IRMonitorLockAcquireStatement) {
      // TODO kcp
      builder.append("/* IRMonitorLockAcquireStatement */");
    } else if (statement instanceof IRMonitorLockReleaseStatement) {
      // TODO kcp
      builder.append("/* IRMonitorLockReleaseStatement */");
    } else if (statement instanceof IRNoOpStatement) {
      // no-op
    } else if (statement instanceof IRReturnStatement) {
      builder.append("return");
      IRReturnStatement returnStatement = (IRReturnStatement) statement;
      if (returnStatement.getReturnValue() != null) {
        builder.append(" ");
        appendExpression(builder, returnStatement.getReturnValue(), symbols);
      }
      builder.append(";\n");
    } else if (statement instanceof IRStatementList) {
      builder.append("{\n");
      Map<String, IRSymbol> innerSymbols = Maps.newHashMap(symbols);
      for (IRStatement child : ((IRStatementList) statement).getStatements()) {
        appendStatement(builder, child, innerSymbols);
      }
      builder.append("}\n");
    } else if (statement instanceof IRSwitchStatement) {
      // TODO kcp
      builder.append("/* IRSwitchStatement */");
    } else if (statement instanceof IRSyntheticStatement) {
      // TODO kcp
      builder.append("/* IRSyntheticStatement */");
    } else if (statement instanceof IRThrowStatement) {
      builder.append("throw ");
      appendExpression(builder, ((IRThrowStatement) statement).getException(), symbols);
      builder.append(";\n");
    } else if (statement instanceof IRTryCatchFinallyStatement) {
      builder.append("try ");
      IRTryCatchFinallyStatement tryCatchFinallyStatement = (IRTryCatchFinallyStatement) statement;
      appendStatement(builder, tryCatchFinallyStatement.getTryBody(), Maps.newHashMap(symbols));
      for (IRCatchClause catchClause : tryCatchFinallyStatement.getCatchStatements()) {
        builder.append(" catch (")
                .append(getTypeName(catchClause.getIdentifier().getType()))
                .append(" ")
                .append(getSymbolName(catchClause.getIdentifier()))
                .append(") ");
        appendStatement(builder, catchClause.getBody(), Maps.newHashMap(symbols));
      }
      if (tryCatchFinallyStatement.getFinallyBody() != null) {
        builder.append(" finally ");
        appendStatement(builder, tryCatchFinallyStatement.getFinallyBody(), Maps.newHashMap(symbols));
      }
    } else if (statement instanceof IRWhileStatement) {
      builder.append("while (");
      IRWhileStatement whileStatement = (IRWhileStatement) statement;
      appendExpression(builder, whileStatement.getLoopTest(), symbols);
      builder.append(") ");
      appendStatement(builder, whileStatement.getBody(), Maps.newHashMap(symbols));
    } else {
      throw new IllegalArgumentException("Unknown statement type " + statement.getClass().getSimpleName());
    }
  }

  private void appendExpression(StringBuilder builder, IRExpression expression, Map<String, IRSymbol> symbols) {
    if (expression instanceof IRArithmeticExpression) {
      IRArithmeticExpression arithmeticExpression = (IRArithmeticExpression) expression;
      appendExpression(builder, arithmeticExpression.getLhs(), symbols);
      builder.append(" ")
              .append(arithmeticExpression.getOp())
              .append(" ");
      appendExpression(builder, arithmeticExpression.getRhs(), symbols);
    } else if (expression instanceof IRArrayLengthExpression) {
      appendExpression(builder, ((IRArrayLengthExpression) expression).getRoot(), symbols);
      builder.append(".length");
    } else if (expression instanceof IRArrayLoadExpression) {
      // TODO kcp
      builder.append("/* IRArrayLoadExpression */");
    } else if (expression instanceof IRBooleanLiteral) {
      builder.append(((IRBooleanLiteral) expression).getValue());
    } else if (expression instanceof IRCastExpression) {
      builder.append("((")
              .append(getTypeName(expression.getType()))
              .append(") ");
      appendExpression(builder, ((IRCastExpression) expression).getRoot(), symbols);
      builder.append(")");
    } else if (expression instanceof IRCharacterLiteral) {
      builder.append("'")
              .append(((IRCharacterLiteral) expression).getValue())
              .append("'");
    } else if (expression instanceof IRClassLiteral) {
      builder.append(getTypeName(((IRClassLiteral) expression).getLiteralType()));
    } else if (expression instanceof IRCompositeExpression) {
      // TODO kcp
      builder.append("/* IRCompositeExpression */");
    } else if (expression instanceof IRConditionalAndExpression) {
      IRConditionalAndExpression andExpression = (IRConditionalAndExpression) expression;
      appendExpression(builder, andExpression.getLhs(), symbols);
      builder.append(" && ");
      appendExpression(builder, andExpression.getRhs(), symbols);
    } else if (expression instanceof IRConditionalOrExpression) {
      IRConditionalOrExpression orExpression = (IRConditionalOrExpression) expression;
      appendExpression(builder, orExpression.getLhs(), symbols);
      builder.append(" || ");
      appendExpression(builder, orExpression.getRhs(), symbols);
    } else if (expression instanceof IREqualityExpression) {
      IREqualityExpression equalityExpression = (IREqualityExpression) expression;
      appendExpression(builder, equalityExpression.getLhs(), symbols);
      builder.append(" == ");
      appendExpression(builder, equalityExpression.getRhs(), symbols);
    } else if (expression instanceof IRFieldGetExpression) {
      IRFieldGetExpression fieldGetExpression = (IRFieldGetExpression) expression;
      if (fieldGetExpression.getLhs() != null) {
        appendExpression(builder, fieldGetExpression.getLhs(), symbols);
        builder.append(".");
      }
      builder.append(fieldGetExpression.getName());
    } else if (expression instanceof IRIdentifier) {
      builder.append(getSymbolName(((IRIdentifier) expression).getSymbol()));
    } else if (expression instanceof IRInstanceOfExpression) {
      IRInstanceOfExpression instanceOfExpression = (IRInstanceOfExpression) expression;
      appendExpression(builder, instanceOfExpression.getRoot(), symbols);
      builder.append(" instanceof ")
              .append(getTypeName(instanceOfExpression.getTestType()));
    } else if (expression instanceof IRMethodCallExpression) {
      IRMethodCallExpression methodCallExpression = (IRMethodCallExpression) expression;
      boolean skipName = false;
      String replacement = replacementMethods.get(
              methodCallExpression.getOwnersType().getName() + "." + methodCallExpression.getName());
      IRExpression root = methodCallExpression.getRoot();
      if (root != null) {
        if (root instanceof IRIdentifier && getSymbolName(((IRIdentifier) root).getSymbol()).equals("this")
                && methodCallExpression.getName().equals("<init>")) {
          IRElement ancestor = expression.getParent();
          while (ancestor.getParent() != null && !(ancestor instanceof IRMethodStatement)) {
            ancestor = ancestor.getParent();
          }
          if (((IRMethodStatement) ancestor).getName().equals("<init>") &&
                  ((IRMethodStatement) ancestor).getParameters().size() == methodCallExpression.getArgs().size()) {
            // super call??
            builder.append("super(");
          } else {
            builder.append("this(");
          }
          skipName = true;
        } else {
          appendExpression(builder, root, symbols);
          builder.append(".");
        }
      } else {
        if (!irClass.getThisType().isAssignableFrom(methodCallExpression.getOwnersType()) && replacement == null) {
          builder.append(getTypeName(methodCallExpression.getOwnersType()))
                  .append(".");
        }
      }
      if (!skipName) {
        if (replacement != null) {
          builder.append(replacement);
        } else {
          builder.append(methodCallExpression.getName());
        }
        builder.append("(");
      }
      List<IRExpression> args = methodCallExpression.getArgs();
      for (int i = 0, argsSize = args.size(); i < argsSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        appendExpression(builder, args.get(i), symbols);
      }
      builder.append(")");
    } else if (expression instanceof IRNegationExpression) {
      builder.append("-");
      appendExpression(builder, ((IRNegationExpression) expression).getRoot(), symbols);
    } else if (expression instanceof IRNewArrayExpression) {
      IRNewArrayExpression newArrayExpression = (IRNewArrayExpression) expression;
      builder.append("new ")
              .append(getTypeName(newArrayExpression.getComponentType()))
              .append("[");
      if (newArrayExpression.getSizeExpression() != null) {
        appendExpression(builder, newArrayExpression.getSizeExpression(), symbols);
      }
      builder.append("]");
    } else if (expression instanceof IRNewExpression) {
      IRNewExpression newExpression = (IRNewExpression) expression;
      builder.append("new ")
              .append(getTypeName(newExpression.getOwnersType()))
              .append("(");
      List<IRExpression> args = newExpression.getArgs();
      for (int i = 0, argsSize = args.size(); i < argsSize; i++) {
        if (i > 0) {
          builder.append(", ");
        }
        appendExpression(builder, args.get(i), symbols);
      }
      builder.append(")");
    } else if (expression instanceof IRNewMultiDimensionalArrayExpression) {
      IRNewMultiDimensionalArrayExpression arrayExpression = (IRNewMultiDimensionalArrayExpression) expression;
      builder.append("new ")
              .append(getTypeName(arrayExpression.getResultType()));
      for (IRExpression size : arrayExpression.getSizeExpressions()) {
        builder.append("[");
        appendExpression(builder, size, symbols);
        builder.append("]");
      }
    } else if (expression instanceof IRNotExpression) {
      builder.append("!");
      appendExpression(builder, ((IRNotExpression) expression).getRoot(), symbols);
    } else if (expression instanceof IRNoOpExpression) {
      // no-op
    } else if (expression instanceof IRNullLiteral) {
      builder.append("null");
    } else if (expression instanceof IRNumericLiteral) {
      builder.append(((IRNumericLiteral) expression).getValue());
    } else if (expression instanceof IRPrimitiveTypeConversion) {
      // TODO kcp
      builder.append("/* IRPrimitiveTypeConversion */");
    } else if (expression instanceof IRRelationalExpression) {
      IRRelationalExpression relationalExpression = (IRRelationalExpression) expression;
      appendExpression(builder, relationalExpression.getLhs(), symbols);
      builder.append(" ")
              .append(relationalExpression.getOp())
              .append(" ");
      appendExpression(builder, relationalExpression.getRhs(), symbols);
    } else if (expression instanceof IRStringLiteralExpression) {
      builder.append("\"")
              .append(((IRStringLiteralExpression) expression).getValue())  // TODO kcp - escape?
              .append("\"");
    } else if (expression instanceof IRTernaryExpression) {
      IRTernaryExpression ternaryExpression = (IRTernaryExpression) expression;
      appendExpression(builder, ternaryExpression.getTest(), symbols);
      builder.append(" ? ");
      appendExpression(builder, ternaryExpression.getTrueValue(), symbols);
      builder.append(" : ");
      appendExpression(builder, ternaryExpression.getFalseValue(), symbols);
    } else {
      throw new IllegalArgumentException("Unknown expression type " + expression.getClass().getSimpleName());
    }
  }

  private void appendAnnotation(StringBuilder builder, IRAnnotation annotation) {
    builder.append("@")
            .append(getTypeName(annotation.getDescriptor()))
                    // TODO kcp - args?
            .append("\n");
  }

  private String getSymbolName(IRSymbol symbol) {
    return symbol.getName().replace('*', '$');
  }

  private String getTypeName(IRType type) {
    if (type.isArray()) {
      return getTypeName(type.getComponentType()) + "[]";
    } else if (type.isPrimitive()) {
      if (type.isBoolean()) {
        return "boolean";
      }
      if (type.isByte()) {
        return "byte";
      }
      if (type.isChar()) {
        return "char";
      }
      if (type.isDouble()) {
        return "double";
      }
      if (type.isFloat()) {
        return "float";
      }
      if (type.isInt()) {
        return "int";
      }
      if (type.isLong()) {
        return "long";
      }
      if (type.isShort()) {
        return "short";
      }
      if (type.isVoid()) {
        return "void";
      }
      throw new IllegalArgumentException("Unknown primitive type " + type.getName());
    } else {
      return type.getName();
    }
  }

}