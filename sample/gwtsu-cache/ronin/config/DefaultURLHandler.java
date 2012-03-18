// Compiled from DefaultURLHandler.gs
public ronin.config.DefaultURLHandler extends Object implements IURLHandler, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public IMethodInfo getControllerMethod(String; request) {
    controllerType = this.getControllerType(request);
    action = [[
      *temp0 = new String[1];
      *temp0[0] = this.getActionName(request);
      *temp0
    ]];
    actionMethod = null;
    actionMethod = ((IMethodInfo) [[
      *temp2 = controllerType.getTypeInfo().getMethods();
      *temp3 = TypeSystem.get(gw.lang.reflect.IMethodInfo.class);
      *temp4 = [[
        *temp1 = new block_0_(this, action);
        *temp1._returnType = TypeSystem.get(boolean.class);
        *temp1
      ]];
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.firstWhere(*temp2, *temp3, *temp4)
    ]]);
    if (actionMethod == null) {
      if (EqualityExpressionTransformer.evaluate(Ronin.getMode(), TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.PRODUCTION, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
        throw new FourOhFourException([[
          *temp5 = new StringBuilder();
          *temp5.append("Action ");
          *temp5.append(action[0]);
          *temp5.append(" not found.");
          *temp5.toString()
        ]]);
      } else {
        throw new FourOhFourException([[
          *temp6 = new StringBuilder();
          *temp6.append("Function ");
          *temp6.append(action[0]);
          *temp6.append(" not found on controller ");
          *temp6.append(controllerType.getName());
          *temp6.toString()
        ]]);
      }
    }
    return actionMethod;
  }

  protected IType getControllerType(String; request) {
    controllerType = null;
    if ((request.length < 1 || !([[
      *temp0 = request[0];
      (*temp0 == null ? false : [[
        *temp1 = *temp0;
        if (*temp1 == null) {
          throw new NullPointerException();
        }
        CoreStringEnhancement.isHasContent(*temp1)
      ]])
    ]]))) {
      if (Ronin.getDefaultController() == null) {
        throw new MalformedURLException();
      } else {
        controllerType = Ronin.getDefaultController();
      }
    } else {
      controller = request[0];
      controllerType = TypeSystem.getByFullNameIfValid([[
        *temp2 = new StringBuilder();
        *temp2.append("controller.");
        *temp2.append(controller);
        *temp2.toString()
      ]]);
      if (controllerType == null) {
        throw new FourOhFourException([[
          *temp3 = new StringBuilder();
          *temp3.append("Controller ");
          *temp3.append(controller);
          *temp3.append(" not found.");
          *temp3.toString()
        ]]);
      } else {
        if (!(((GosuClass_Proxy) TypeSystem.getByFullName("ronin.RoninController", "_default_")).isAssignableFrom(controllerType))) {
          throw new FourOhFourException([[
            *temp4 = new StringBuilder();
            *temp4.append("Controller ");
            *temp4.append(controller);
            *temp4.append(" is not a valid controller.");
            *temp4.toString()
          ]]);
        }
      }
    }
    return controllerType;
  }

  protected String getActionName(String; request) {
    if (request.length < 2) {
      return Ronin.getDefaultAction();
    } else {
      return request[1];
    }
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}