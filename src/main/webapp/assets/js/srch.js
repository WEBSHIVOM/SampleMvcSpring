$("#button").click(function() {
	alert("success");
	
	
	$("#SEARCHform").submit(function(e)
			{
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				$.ajax(
				{
					url : "search1",
					
					type: "POST",
					data : postData,
					success:function(data, textStatus, jqXHR) 
					{
						window.location.reload();
					},
					error: function(jqXHR, textStatus, errorThrown) 
					{
					}
				});
			    e.preventDefault();	//STOP default action
			});
	         
			
   
});