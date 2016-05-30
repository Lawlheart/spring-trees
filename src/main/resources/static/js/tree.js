$(document).ready(function() {
   $.get("/data/trees/1", function(data) {
       console.log(data);
   })
});