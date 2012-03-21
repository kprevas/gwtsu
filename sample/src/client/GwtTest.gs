package client

uses com.google.gwt.core.*
uses com.google.gwt.core.client.*
uses com.google.gwt.http.client.*
uses com.google.gwt.user.client.ui.*
uses java.lang.*

class GwtTest implements EntryPoint {

  /**
   * This is the entry point method.
   */
  override function onModuleLoad() {
    var label = new Label(new OtherClientClass().Label)
    RootPanel.get("main").add(label)
    var builder = new RequestBuilder(RequestBuilder.GET, "Main/data")
    builder.sendRequest(null, new RequestCallback() {
      override function onError(req : Request, e : Throwable) {

      }
      override function onResponseReceived(req : Request, resp : Response) {
        var data = new DataObj(resp.Text)
        label.Text = data.P1
      }
    })
  }
}
