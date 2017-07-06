var request=new XMLHttpRequest();  
function searchInfo(){  
var name=document.vinform.name.value;  
var url="search?searchdata="+name;  
alert("connect to server");
try{  
request.onreadystatechange=function(){  
if(request.readyState==4){  
var val=request.responseText;  
document.getElementById('mylocation').innerHTML=val;  
}  
}//end of function  
request.open("POST",url,true);  
request.send();  
}catch(e){alert("Unable to connect to server");}  
}  