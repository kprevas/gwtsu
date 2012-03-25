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
    var other = new OtherClientClass()
    var label = new Label(other.Label)
    RootPanel.get("main").add(label)
    var label2 = new Label(other.enhx())
    RootPanel.get("main").add(label2)
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
