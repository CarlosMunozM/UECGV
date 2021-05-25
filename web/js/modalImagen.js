$(document).ready(function(){
    var modal = document.getElementById("myModal");
   
    //Galeria
    $("article").find("img").click(function(){
        var url = $(this).attr("src");
        modal.style.display = "block";
        $("#img01").attr("src", url);
    });
    
    $("#close-modal").click(function(){
      modal.style.display = "none";  
      $("#img01").attr("src", "");
    });
});