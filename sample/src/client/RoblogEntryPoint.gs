package client

uses com.google.gwt.core.*
uses com.google.gwt.core.client.*
uses com.google.gwt.http.client.*
uses com.google.gwt.user.client.ui.*
uses java.lang.*

class RoblogEntryPoint implements EntryPoint {

  /**
   * This is the entry point method.
   */
  override function onModuleLoad() {
    var builder = new RequestBuilder(RequestBuilder.GET, "Postcx/recent")
    builder.sendRequest(null, new RequestCallback() {
      override function onError(req : Request, e : Throwable) {

      }
      override function onResponseReceived(req : Request, resp : Response) {
        var posts : List<Post> = {}
        posts.parse(resp.Text)
      }
    })
  }
}
