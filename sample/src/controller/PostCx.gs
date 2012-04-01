package controller

uses db.roblog.Post
uses client.DataObj
uses ronin.*

class PostCx extends RoninController {

  function recent(page : int) {
    if(page == null) {
      page = 0
    }
    var posts = Post.selectAll().orderBy(Post#Posted, DESC)
        .page(:startPage = page, :pageSize = 20).loadPage()
    return posts.map(\p -> new client.Post(p)).overlayToJSON()
  }

  function post(post : Post) {
    return new client.Post(post)
  }

}