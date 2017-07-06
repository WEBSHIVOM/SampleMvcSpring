$("#Uploadbutton").click(function() {
	alert("a")
	$("#uploadform").submit(function(e)
			{
		alert("avbd")
				var formData = new FormData();

formData.append('file',$('#file')[0].files[0]);
				
				$.ajax(
				{
					url : "upload",
					
					type: "POST",
					data : formData,
					success:function(data, textStatus, jqXHR) 
					{
						console.log(data)
						window.location.reload();
					},
					error: function(jqXHR, textStatus, errorThrown) 
					{
					}
				});
			    e.preventDefault();	//STOP default action
			});
	         
			
    $("#Dialog").show(1000);
    
});
$(document).off("click","#Uploadbutton")
$(document).on("click","#Uploadbutton",function(){
	
});