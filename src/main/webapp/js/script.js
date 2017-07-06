
$(document).ready(function(){
$("#submit").click(function(){
var name = $("#name").val();
var email = $("#email").val();
var password = $("#id").val();
var contact = $("#address").val();
// Returns successful data submission message when the entered information is stored in database.
var dataString = 'name1='+ name + '&email1='+ email + '&id1='+ id + '&address1='+ address;
if(name==''||email==''||id==''||address=='')
{
alert("Please Fill All Fields");
}
else
{
// AJAX Code To Submit Form.
$.ajax({
type: "POST",
url: "register1",
data: dataString,
cache: false,
success: function(result){
alert(result);
}
});
}
return false;
});
});