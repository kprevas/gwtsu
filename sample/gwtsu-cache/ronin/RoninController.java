// Compiled from RoninController.gs
public ronin.RoninController extends Object implements IRoninUtils, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public static void redirect(MethodReference target) {
    IRoninUtilsEnhancement.getResponse().sendRedirect(URLUtil.urlFor(target));
    return;
  }

  public static void postLoginRedirect(MethodReference defaultTarget) {
    RoninController.postLoginRedirect(URLUtil.urlFor(defaultTarget));
    return;
  }

  public static void postLoginRedirect(String defaultTarget) {
    if (IRoninUtilsEnhancement.getPostLoginRedirect() != null) {
      IRoninUtilsEnhancement.getResponse().sendRedirect(IRoninUtilsEnhancement.getPostLoginRedirect());
    } else {
      if (defaultTarget != null) {
        IRoninUtilsEnhancement.getResponse().sendRedirect(defaultTarget);
      }
    }
    return;
  }

  public static void bounce() {
    redirectTo = IRoninUtilsEnhancement.getReferrer();
    currentUrl = IRoninUtilsEnhancement.getRequest().getRequestURL();
    if ([[
      *temp0 = IRoninUtilsEnhancement.getRequest().getQueryString();
      (*temp0 == null ? false : [[
        *temp1 = *temp0;
        if (*temp1 == null) {
          throw new NullPointerException();
        }
        CoreStringEnhancement.isHasContent(*temp1)
      ]])
    ]]) {
      currentUrl.append("?").append(IRoninUtilsEnhancement.getRequest().getQueryString());
    }
    if ((redirectTo == null || EqualityExpressionTransformer.evaluate(redirectTo, TypeSystem.get(java.lang.String.class), true, currentUrl.toString(), TypeSystem.get(java.lang.String.class)))) {
      IRoninUtilsEnhancement.getResponse().sendRedirect([[
        *temp2 = IRoninUtilsEnhancement.getRequest();
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        HttpServletRequestEnhancement.getRootURL(*temp2)
      ]]);
    } else {
      IRoninUtilsEnhancement.getResponse().sendRedirect(redirectTo);
    }
    return;
  }

  protected boolean beforeRequest(Map params) {
    return true;
  }

  protected void afterRequest(Map params) {
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("redirect(gw.lang.reflect.features.MethodReference<java.lang.Object, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("target", "If the redirect is followed, it will have the effect of calling this method.  Arguments
must be bound."));
    builder.addGosuAnnotation(new URLMethodValidator());
    builder.startAnnotationInfoForFeature("postLoginRedirect(gw.lang.reflect.features.MethodReference<java.lang.Object, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("defaultTarget", "Controller method to redirect the user to if they were not redirected to the login page."));
    builder.addGosuAnnotation(new URLMethodValidator());
    builder.startAnnotationInfoForFeature("postLoginRedirect(java.lang.String)");
    builder.addGosuAnnotation(new Param("defaultTarget", "URL to redirect the user to if they were not redirected to the login page."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Deprecated.class));
    builder.withArg("value", "Use postLoginRedirect(Foo#bar()) instead.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("beforeRequest(java.util.Map<java.lang.String, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("params", "The parameters which will be passed to the controller method."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "False if the request should be aborted before the controller method is called.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("afterRequest(java.util.Map<java.lang.String, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("params", "The parameters which were passed to the controller method."));
    return builder.getAnnotations();
  }

}