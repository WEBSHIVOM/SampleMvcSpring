$("#button").click(function() {
	alert("success");
	
	
	$("#regform").submit(function(e)
			{
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				$.ajax(
				{
					url : "register",
					
					type: "POST",
					data : postData,
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
	         
			
    $("#yourDialog").show(1000);
    
});
$(document).off("click","#button")
$(document).on("click","#button",function(){
	
});