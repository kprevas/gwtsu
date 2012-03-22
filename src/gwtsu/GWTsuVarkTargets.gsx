package gwtsu

uses gw.vark.*
uses gw.vark.annotations.*
uses gw.vark.antlibs.*

enhancement GWTsuVarkTargets : gw.vark.AardvarkFile {

  @Target
  function gwtCompile(modules : String) {
    Ant.java(
        :args = "-war html/public -deploy html/WEB-INF -strict ${modules}",
        :jvmargs = "-Xmx1024m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005",
        :classname = "gwtsu.GWTsuCompiler",
        :classpath = this.classpath(this.file("src"))
            .withPath(this.fixedPom().dependencies(COMPILE, :additionalDeps = {
                new() {:GroupId = "org.gosu-lang.gosu", :ArtifactId = "gosu-core", :Version = "0.9-12"}}).Path),
        :fork = true)
  }
}
