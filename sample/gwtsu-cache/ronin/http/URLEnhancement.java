// Compiled from URLEnhancement.gsx
public ronin.http.URLEnhancement extends Object{

  public static String get(URL $that$, Map params, HttpClient httpClient) {
    resp = [[
      *temp0 = $that$;
      *temp1 = params;
      *temp2 = httpClient;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      URLEnhancement.getRaw(*temp0, *temp1, *temp2)
    ]];
    return EntityUtils.toString(resp.getEntity());
  }

  public static HttpResponse getRaw(URL $that$, Map params, HttpClient httpClient) {
    url = $that$;
    paramList = [[
      *temp1 = [[
        *temp0 = params;
        (*temp0 == null ? ((Set) null) : *temp0.entrySet())
      ]];
      (*temp1 == null ? ((List) null) : [[
        *temp4 = *temp1;
        *temp5 = TypeSystem.getByFullName("java.util.Map.Entry", "_default_").getParameterizedType([[
          *temp3 = new IType[2];
          *temp3[0] = TypeSystem.get(java.lang.String.class);
          *temp3[1] = TypeSystem.get(java.lang.Object.class);
          *temp3
        ]]);
        *temp6 = TypeSystem.get(org.apache.http.message.BasicNameValuePair.class);
        *temp7 = [[
          *temp2 = new block_0_($that$);
          *temp2._returnType = TypeSystem.get(org.apache.http.message.BasicNameValuePair.class);
          *temp2
        ]];
        if (*temp4 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.map(*temp4, *temp5, *temp6, *temp7)
      ]])
    ]];
    if (paramList == null) {
      paramList = new ArrayList();
    }
    uri = URIUtils.createURI(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), URLEncodedUtils.format(paramList, "UTF-8"), ((String) null));
    getObj = new HttpGet(uri);
    httpClient = (httpClient == null ? new DefaultHttpClient() : httpClient);
    return httpClient.execute(getObj);
  }

  public static String post(URL $that$, Map form, HttpClient httpClient) {
    resp = [[
      *temp0 = $that$;
      *temp1 = form;
      *temp2 = httpClient;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      URLEnhancement.postRaw(*temp0, *temp1, *temp2)
    ]];
    return EntityUtils.toString(resp.getEntity());
  }

  public static HttpResponse postRaw(URL $that$, Map form, HttpClient httpClient) {
    url = $that$;
    paramList = [[
      *temp1 = [[
        *temp0 = form;
        (*temp0 == null ? ((Set) null) : *temp0.entrySet())
      ]];
      (*temp1 == null ? ((List) null) : [[
        *temp4 = *temp1;
        *temp5 = TypeSystem.getByFullName("java.util.Map.Entry", "_default_").getParameterizedType([[
          *temp3 = new IType[2];
          *temp3[0] = TypeSystem.get(java.lang.String.class);
          *temp3[1] = TypeSystem.get(java.lang.Object.class);
          *temp3
        ]]);
        *temp6 = TypeSystem.get(org.apache.http.message.BasicNameValuePair.class);
        *temp7 = [[
          *temp2 = new block_1_($that$);
          *temp2._returnType = TypeSystem.get(org.apache.http.message.BasicNameValuePair.class);
          *temp2
        ]];
        if (*temp4 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.map(*temp4, *temp5, *temp6, *temp7)
      ]])
    ]];
    if (paramList == null) {
      paramList = new ArrayList();
    }
    uri = URIUtils.createURI(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), "", ((String) null));
    postObject = new HttpPost(uri);
    postObject.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
    httpClient = (httpClient == null ? new DefaultHttpClient() : httpClient);
    return httpClient.execute(postObject);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}