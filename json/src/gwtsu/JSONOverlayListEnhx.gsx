package gwtsu

enhancement JSONOverlayListEnhx <T extends JSONOverlay> : List<T> {

  function parse(json : String) {
    throw "This method should never be invoked server-side."
  }

  function overlayToJSON() : String {
    return "[" + this.map(\obj -> obj.overlayToJSON()).join(",") + "]"
  }

}