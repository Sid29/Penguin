document.getElementById('title').innerHTML = title;




url = "/controller";
dataRequest = "";
$.ajax({
  url: "http://fiddle.jshell.net/favicon.png",
  beforeSend: function(xhr) {
    xhr.overrideMimeType("text/plain; charset=x-user-defined");
  }
})
  .done(function(data) {
    if (console && console.log) {
      console.log( "Sample of data:", data.slice( 0, 100 ) );
    }
  });