// Compiled from RoninFilter.gs
public ronin.RoninFilter extends Object implements Filter, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public void init(FilterConfig p0) {
    return;
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
    if ([[
      *temp2 = [[
        *temp0 = Ronin.getConfig().getFilters();
        *temp1 = TypeSystem.get(javax.servlet.Filter.class);
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.isHasElements(*temp0, *temp1)
      ]];
      (*temp2 == null ? false : *temp2.booleanValue())
    ]]) {
      chain = this.makeFilterChain(chain);
    }
    chain.doFilter(req, resp);
    return;
  }

  public FilterChain makeFilterChain(FilterChain last) {
    reversedFilters = [[
      *temp0 = Ronin.getConfig().getFilters();
      *temp1 = TypeSystem.get(javax.servlet.Filter.class);
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.reverse(*temp0, *temp1)
    ]];
    chain = last;
    /*foreach*/
        *temp2 = ForEachStatementTransformer.makeIterator(reversedFilters);

        filter = [[
      *temp3 = new Filter[1];
      *temp3[0] = null;
      *temp3
    ]];

    if (*temp2 != null) {
      while (*temp2.hasNext()) {
        filter = [[
          *temp4 = new Filter[1];
          *temp4[0] = ((Filter) *temp2.next());
          *temp4
        ]];

        next = [[
          *temp5 = new FilterChain[1];
          *temp5[0] = chain;
          *temp5
        ]];
        chain = ((FilterChain) TypeAsTransformer.coerceValue([[
          *temp6 = new block_0_(this, filter, next);
          *temp6._returnType = TypeSystem.get(void.class);
          *temp6
        ]], TypeSystem.get(javax.servlet.FilterChain.class), FunctionToInterfaceCoercer.instance()));
      }
    }
    return chain;
  }

  public void destroy() {
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}