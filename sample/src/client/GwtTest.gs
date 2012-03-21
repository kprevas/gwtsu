package client

uses com.google.gwt.core.*
uses com.google.gwt.core.client.*
uses com.google.gwt.user.client.ui.*

class GwtTest implements EntryPoint {

  /**
   * This is the entry point method.
   */
  function onModuleLoad() {
    var label = new Label(new OtherClientClass().Label)
    RootPanel.get("main").add(label)
  }
}
