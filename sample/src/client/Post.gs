package client

uses gwtsu.JSONOverlay

class Post extends JSONOverlay {
  var _title : String as Title
  var _body : String as Body
  var _posted : String as Posted

  construct(dbPost: db.roblog.Post) {
    Title = dbPost.Title
    Body = dbPost.Body
    Posted = dbPost.Posted as String
  }
}