// Compiled from CoreProjectEnhancement.gsx
public gw.vark.CoreProjectEnhancement extends Object{

  public static Target registerTarget(Project $that$, String name, IFunction0 op$$unboxedParam) {
    op = [[
      *temp0 = new IFunction0[1];
      *temp0[0] = op$$unboxedParam;
      *temp0
    ]];
    target = new AnonymouS__0($that$, op);
    target.setName(name);
    AardvarkProgram.getInstance($that$).getRuntimeGeneratedTargets().add(target);
    return target;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}