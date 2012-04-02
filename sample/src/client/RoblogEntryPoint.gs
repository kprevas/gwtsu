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
    RootPanel.get("main").add(new Label("Loading..."))
    var builder = new RequestBuilder(RequestBuilder.GET, "Postcx/recent?page=0")
    builder.sendRequest(null, new RequestCallback() {
      override function onError(req : Request, e : Throwable) {

      }
      override function onResponseReceived(req : Request, resp : Response) {
        var posts : List<Post> = {}
        posts.parse(resp.Text)
        var main = RootPanel.get("main")
        main.clear()
        for(post in posts) {
          main.add(new Label(post.Title))
          main.add(new Label(post.Body))
          main.add(new Label(post.Posted))
        }
      }
    })
  }
}
