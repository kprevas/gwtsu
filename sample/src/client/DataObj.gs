package client

uses gwtsu.JSONOverlay

class DataObj extends JSONOverlay {
  var _p1 : String as P1
  var _p2 : int as P2
  var _p3 : List<DataObj> as P3

  construct(json : String) {
    super(json)
  }
}