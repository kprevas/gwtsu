package gwtsu

uses gw.util.GosuEscapeUtil
uses java.lang.Iterable
uses java.lang.StringBuilder

enhancement JSONOverlayEnhx : JSONOverlay {

  function overlayToJSON() : String {
    var json = new StringBuilder("{")
    for (prop in this.Type.TypeInfo.Properties) {
      json.append("\"").append(prop.Name).append("\":")
      var value = prop.Accessor.getValue(this)
      appendValue(value, json)
    }
    json.append("}")
    return json.toString()
  }

  private function appendValue(value : Object, json : StringBuilder) {
    if (value typeis JSONOverlay) {
      json.append(value.overlayToJSON())
    } else if (value typeis Iterable) {
      json.append("[")
      for (child in value index i) {
        if (i > 0) {
          json.append(",")
        }
        appendValue(child, json)
      }
      json.append("]")
    } else if ((typeof value).Array) {
      json.append("[")
      for (child in value index i) {
        if (i > 0) {
          json.append(",")
        }
        appendValue(child, json)
      }
      json.append("]")
    } else if (value typeis short or
        value typeis int or
        value typeis long or
        value typeis float or
        value typeis double or
        value typeis boolean) {
      json.append(value)
    } else if (value == null) {
      json.append("null")
    } else {
      json.append("\"")
          .append(GosuEscapeUtil.escapeForJava(value.toString()))
          .append("\"")
    }
  }

}