package gwtsu;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
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
import gw.lang.reflect.IType;
import gw.lang.reflect.Modifier;
import gw.lang.reflect.TypeSystem;
import gw.lang.reflect.gs.IGosuClass;
import gw.util.GosuClassUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author kprevas
 */
public class GWTSuIRClassCompiler {
  
  private final static Map<String, String> replacementMethods = Maps.newHashMap();

  static {
    replacementMethods.put("gw.internal.gosu.ir.transform.statement.ForEachStatementTransformer.makeIterator",
            "Util.makeIterator");
  }
  
  private IRClass irClass;
  private Stack<StringBuilder> auxMethodsBuilders = new Stack<StringBuilder>();
  private int numAuxMethods = 0;
  private boolean isOverlay;

  public GWTSuIRClassCompiler(IRClass irClass) {
    this.irClass = irClass;
  }

  public String compileToJava() {
    StringBuilder builder = new StringBuilder();
    builder.append("package ")
            .append(GosuClassUtil.getPackage(getTypeName(irClass.getThisType())))
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
    String typeName = getTypeName(irClass.getThisType());
    builder.append(typeName.substring(typeName.lastIndexOf(".") + 1));
    IRType superType = irClass.getSuperType();
    if (superType != null) {
      if (getTypeName(superType).equals("gwtsu.JSONOverlay")) {
        isOverlay = true;
        builder.append(" extends com.google.gwt.core.client.JavaScriptObject");
      } else {
        builder.append(" extends ")
                .append(getTypeName(superType));
      }
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
    IRStatement methodBody = method.getMethodBody();
    if (isOverlay) {
      if (method.getName().startsWith("get") && method.getParameters().isEmpty()) {
        if (methodBody instanceof IRStatementList) {
          List<IRStatement> statements = ((IRStatementList) methodBody).getStatements();
          if (statements.size() == 1 && statements.get(0) instanceof IRReturnStatement) {
            IRExpression returnValue = ((IRReturnStatement) statements.get(0)).getReturnValue();
            if (returnValue instanceof IRFieldGetExpression) {
              builder.append("public final native ")
                      .append(getTypeName(((IRFieldGetExpression) returnValue).getFieldType()))
                      .append(" ")
                      .append(method.getName())
                      .append("() /*-{ return this.")
                      .append(method.getName().substring("get".length()))
                      .append("; }-*/;\n");
              return;
            }
          }
        }
      }
      if (method.getName().startsWith("set") 
              && method.getReturnType().isVoid() 
              && method.getParameters().size() == 1) {
        if (methodBody instanceof IRStatementList) {
          List<IRStatement> statements = ((IRStatementList) methodBody).getStatements();
          if (statements.size() == 2
                  && statements.get(0) instanceof IRFieldSetStatement
                  && statements.get(1) instanceof IRReturnStatement) {
            builder.append("public final native void ")
                    .append(method.getName())
                    .append("(")
                    .append(getTypeName(method.getParameters().get(0).getType()))
                    .append(" ")
                    .append(method.getParameters().get(0).getName())
                    .append(") /*-{ this.")
                    .append(method.getName().substring("set".length()))
                    .append(" = ")
                    .append(method.getParameters().get(0).getName())
                    .append("; }-*/;\n");
            return;
          }
        }
      }
      if (method.getName().equals("<init>") 
              && method.getParameters().size() == 1
              && method.getParameters().get(0).getType().getName().equals("java.lang.String")) {
        builder.append("public static native ")
                .append(getTypeName(irClass.getThisType()))
                .append(" $gwtsu$parse(json : String) /*-{ return JSON.parse(json); }-*/;\n");
        return;
      }
    }
    Map<String, IRSymbol> symbols = Maps.newLinkedHashMap();
    for (IRAnnotation annotation : method.getAnnotations()) {
      appendAnnotation(builder, annotation);
    }
    if (!method.getName().equals("<clinit>")) {
      builder.append(Modifier.toModifierString(method.getModifiers()))
              .append(" ");
      if (method.getName().equals("<init>")) {
        builder.append(GosuClassUtil.getNameNoPackage(getTypeName(ownerType.getThisType())));
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
    if (methodBody != null) {
      if (!(methodBody instanceof IRStatementList)) {
        builder.append("{\n");
      }
      appendStatement(builder, methodBody, symbols);
      if (!(methodBody instanceof IRStatementList)) {
        builder.append("}\n");
      }
    } else {
      builder.append(";\n");
    }
    while (!auxMethodsBuilders.empty()) {
      builder.append(auxMethodsBuilders.pop());
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
      }
      builder.append(symbolName)
              .append(" = ");
      appendExpression(builder, assignmentStatement.getValue(), symbols);
      builder.append(";\n");
      symbols.put(symbolName, assignmentStatement.getSymbol());
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
      IRElement ancestor = statement.getParent();
      while (ancestor != null) {
        if (ancestor instanceof IRMethodStatement &&
                ((IRMethodStatement) ancestor).getName().endsWith("init>")) {
          return;
        }
        ancestor = ancestor.getParent();
      }
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
        if (child != null) {
          appendStatement(builder, child, innerSymbols);
        }
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
      transformCompositeExpression(builder, (IRCompositeExpression) expression, symbols);
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
              getTypeName(methodCallExpression.getOwnersType()) + "." + methodCallExpression.getName());
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
      List<IRExpression> args = newExpression.getArgs();
      if (TypeSystem.getByFullName("gwtsu.JSONOverlay")
              .isAssignableFrom(newExpression.getOwnersType().getType()) &&
              args.size() == 1 &&
              args.get(0).getType().getName().equals("java.lang.String")) {
        builder.append(getTypeName(newExpression.getOwnersType()))
            .append(".$gwtsu$parse(");
        appendExpression(builder, args.get(0), symbols);
        builder.append(")");
      } else {
        builder.append("new ")
                .append(getTypeName(newExpression.getOwnersType()))
                .append("(");
        for (int i = 0, argsSize = args.size(); i < argsSize; i++) {
          if (i > 0) {
            builder.append(", ");
          }
          appendExpression(builder, args.get(i), symbols);
        }
        builder.append(")");
      }
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
    IType iType = type.getType();
    if (iType instanceof IGosuClass) {
      return ((IGosuClass) iType).getBackingClass().getName();
    }
    return iType.getName();
  }

  private void transformCompositeExpression(StringBuilder builder, IRCompositeExpression expression, Map<String, IRSymbol> symbols) {
    StringBuilder auxMethodsBuilder = new StringBuilder();
    auxMethodsBuilders.push(auxMethodsBuilder);
    if (expression.getElements().size() == 2 &&
            expression.getElements().get(0) instanceof IRAssignmentStatement &&
            expression.getElements().get(1) instanceof IRTernaryExpression) {
      // Null-safe property/method access?
      IRTernaryExpression ternaryExpression = (IRTernaryExpression) expression.getElements().get(1);
      if (ternaryExpression.getTrueValue() instanceof IRCastExpression &&
              ((IRCastExpression) ternaryExpression.getTrueValue()).getRoot() instanceof IRNullLiteral &&
              ternaryExpression.getTest() instanceof IREqualityExpression &&
              ((IREqualityExpression) ternaryExpression.getTest()).getRhs() instanceof IRNullLiteral) {
        IRAssignmentStatement assignmentStatement = (IRAssignmentStatement) expression.getElements().get(0);
        String rootTypeName = getTypeName(assignmentStatement.getSymbol().getType());
        String rtnTypeName = getTypeName(ternaryExpression.getResultType());
        String auxMethodName = "$gwtsu$aux" + (numAuxMethods++);
        String argName = getSymbolName(assignmentStatement.getSymbol());
        builder.append(auxMethodName)
                .append("(");
        appendExpression(builder, assignmentStatement.getValue(), symbols);
        appendSymbolsAsArgs(builder, symbols, true);
        builder.append(")");
        auxMethodsBuilder.append("private ")
                .append(rtnTypeName)
                .append(auxMethodName)
                .append("(")
                .append(rootTypeName)
                .append(" ")
                .append(argName);
        appendSymbolsAsParams(auxMethodsBuilder, symbols, true);
        auxMethodsBuilder.append(") {\n")
                .append("return ");
        appendExpression(auxMethodsBuilder, ternaryExpression, symbols);
        auxMethodsBuilder.append(";\n}\n");
        return;
      }
    }
    // TODO kcp - check for other types of known composite expressions
    // Fallback: if elements are statements + 1 expression, transform directly into aux method.
    IRElement lastElement = expression.getElements().get(expression.getElements().size() - 1);
    if (lastElement instanceof IRExpression) {
      boolean areAllStatements = true;
      for (IRElement element : expression.getElements()) {
        areAllStatements = areAllStatements && (element instanceof IRStatement || element == lastElement);
      }
      if (areAllStatements) {
        String auxMethodName = "$gwtsu$aux" + (numAuxMethods++);
        builder.append(auxMethodName)
                .append("(");
        appendSymbolsAsArgs(builder, symbols, false);
        builder.append(")");
        auxMethodsBuilder.append("private ")
                .append(getTypeName(((IRExpression) lastElement).getType()))
                .append(" ")
                .append(auxMethodName)
                .append("(");
        appendSymbolsAsParams(auxMethodsBuilder, symbols, false);
        auxMethodsBuilder.append(") {\n");
        for (IRElement element : expression.getElements()) {
          if (element instanceof IRStatement) {
            appendStatement(auxMethodsBuilder, (IRStatement) element, symbols);
          } else {
            auxMethodsBuilder.append("return ");
            appendExpression(auxMethodsBuilder, (IRExpression) element, symbols);
            auxMethodsBuilder.append(";\n");
          }
        }
        auxMethodsBuilder.append("}\n");
        return;
      }
    }
    // TODO kcp
    builder.append("/* unknown IRCompositeExpression */");
  }

  private void appendSymbolsAsArgs(StringBuilder builder, Map<String, IRSymbol> symbols, boolean comma) {
    for (Map.Entry<String, IRSymbol> symbol : symbols.entrySet()) {
      if (comma) {
        builder.append(", ");
      }
      comma = true;
      builder.append(symbol.getKey());
    }
  }

  private void appendSymbolsAsParams(StringBuilder builder, Map<String, IRSymbol> symbols, boolean comma) {
    for (Map.Entry<String, IRSymbol> symbol : symbols.entrySet()) {
      if (comma) {
        builder.append(", ");
      }
      comma = true;
      builder.append(getTypeName(symbol.getValue().getType()))
              .append(" ")
              .append(symbol.getKey());
    }
  }

}