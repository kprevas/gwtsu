package controller

uses ronin.*

class Main extends RoninController {

  function index() {
    view.Main.render(Writer)
  }

}