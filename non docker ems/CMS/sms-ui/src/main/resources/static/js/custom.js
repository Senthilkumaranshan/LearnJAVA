
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

document.getElementById("emp-detail").onclick = function(){
//window.location = "http://localhost:8989/employees";

document.getElementsByTagName("pills-employee-tab")[0].setAttribute("class", "nav-link active");
document.getElementsByTagName("pills-home-tab")[0].removeAttribute("class");
document.getElementsByTagName("pills-home-tab")[0].setAttribute("class", "nav-link");

}



})();

