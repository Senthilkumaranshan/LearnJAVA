
(function() {
document.getElementById("pills-employee-tab").onclick = function(){
//window.location = "http://localhost:8989/employees";

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})


}

$(".empdelete").click(function(){

  var id = $(this).data('emp');
  var $tr = $(this).closest('tr');

  $.ajax({
      type: 'DELETE',
      url: '/employee/'+id,
      data: { id: id },
      cache: false,
      success: function(result) {

      $tr.find('td').fadeOut(1000,function(){ $tr.remove();});

      }
  });

});

//$(".createemp").click(function(event){
//
//    event.preventDefault();
//
//    var name = $('#name').val();
//    $.ajax({
//          type: 'POST',
//          url: '/employee',
//          data: { name: name },
//          cache: false,
//          success: function(result) {
//
//          console.log("test");
//
//          }
//      });
//
//
//});



})();

