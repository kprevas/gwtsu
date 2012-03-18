// Compiled from PomHelper.gs
public gw.vark.PomHelper extends Object implements IAardvarkUtils, IGosuClassObject {
  public File _file;
  public File _dir;
  public Pom _pom;
  public Model _model;
  public PomHelper _parent;
  public List _children;
  public Map _allInTree;

  public void <init>(File pomFile) {
    this.<init>(pomFile, ((PomHelper) null));
    return;
  }

  public void <init>(File pomFile, PomHelper parent_) {
    this.<init>();





    this._children = new ArrayList();
    this._allInTree = new HashMap();
    if (!(pomFile.exists())) {
      CoreIAardvarkUtilsEnhancement.buildException([[
        *temp0 = new StringBuilder();
        *temp0.append("POM file ");
        *temp0.append(pomFile.getPath());
        *temp0.append(" not found");
        *temp0.toString()
      ]]);
    }
    this._file = pomFile;
    this._dir = pomFile.getParentFile();
    this._pom = PomHelper.parsePom(pomFile);
    this._model = this._pom.getModel(this._pom);
    this._parent = parent_;
    Aardvark.getProject().addReference([[
      *temp1 = new StringBuilder();
      *temp1.append("pom.");
      *temp1.append(this.getId());
      *temp1.toString()
    ]], this._pom);
    /*foreach*/
        *temp2 = ForEachStatementTransformer.makeIterator(this.getModel().getModules());

        module = null;

    if (*temp2 != null) {
      while (*temp2.hasNext()) {
        module = ((String) *temp2.next());

        child = new PomHelper([[
          *temp4 = this._dir;
          *temp5 = [[
            *temp3 = new StringBuilder();
            *temp3.append(module);
            *temp3.append("/pom.xml");
            *temp3.toString()
          ]];
          if (*temp4 == null) {
            throw new NullPointerException();
          }
          CoreFileEnhancement.file(*temp4, *temp5)
        ]], this);
        this._children.add(child);
        this._allInTree.putAll(child.getAllInTree());
      }
    }
    this._allInTree.put(this.getModel().getArtifactId(), this);
    return;
  }

  public static PomHelper load(File pomFile) {
    return new PomHelper(pomFile);
  }

  internal static Pom parsePom(File file) {
    pom = ((Pom) PomHelper.initTask(TypeSystem.get(org.sonatype.aether.ant.types.Pom.class), new Pom(), "pom"));
    pom.setFile(file);
    pom.execute();
    return pom;
  }

  internal static Task initTask(IType typeparam$T, Task task, String name) {
    task.setProject(Aardvark.getProject());
    task.setTaskName(name);
    task.init();
    return task;
  }

  public File getFile() {
    return this._file;
  }

  public void setFile(File __value_) {
    this._file = __value_;
    return;
  }

  public File getDir() {
    return this._dir;
  }

  public void setDir(File __value_) {
    this._dir = __value_;
    return;
  }

  public Pom getPom() {
    return this._pom;
  }

  public void setPom(Pom __value_) {
    this._pom = __value_;
    return;
  }

  public Model getModel() {
    return this._model;
  }

  public void setModel(Model __value_) {
    this._model = __value_;
    return;
  }

  public PomHelper getParent() {
    return this._parent;
  }

  public void setParent(PomHelper __value_) {
    this._parent = __value_;
    return;
  }

  public List getChildren() {
    return this._children;
  }

  public void setChildren(List __value_) {
    this._children = __value_;
    return;
  }

  public Map getAllInTree() {
    return this._allInTree;
  }

  public void setAllInTree(Map __value_) {
    this._allInTree = __value_;
    return;
  }

  public String getId() {
    return this.getModel().getId();
  }

  public DependenciesWrapper dependencies(MavenScope scope, List additionalDeps) {
    dependencies = new DependenciesWrapper(this, scope, additionalDeps);
    return dependencies;
  }

  public String toString() {
    return ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "PomHelper [", this.getId(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), "] (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this.getFile(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.io.File.class), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
  }

  public int hashCode() {
    return this.getId().hashCode();
  }

  public boolean equals(Object that) {
    return ((that != null && [[
      *temp0 = that;
      (*temp0 == null ? false : TypeSystem.getByFullName("gw.vark.PomHelper", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) && EqualityExpressionTransformer.evaluate([[
      *temp1 = that;
      ((*temp1 instanceof PomHelper) ? ((PomHelper) *temp1) : ((PomHelper) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("gw.vark.PomHelper", "_default_"), RuntimeCoercer.instance())))
    ]].getId(), TypeSystem.get(java.lang.String.class), true, this.getId(), TypeSystem.get(java.lang.String.class)));
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}