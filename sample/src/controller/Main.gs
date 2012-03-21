package controller

uses client.DataObj
uses ronin.*

class Main extends RoninController {

  function index() {
    view.Main.render(Writer)
  }

  function data() : String {
    return new DataObj() {
        :P1 = "foo",
        :P2 = 8,
        :P3 = {
            new DataObj() {
                :P1 = "bar",
                :P2 = 100
            },
            new DataObj() {
                :P1 = "gar",
                :P2 = 300
            }
        }
    }.overlayToJSON()
  }

}