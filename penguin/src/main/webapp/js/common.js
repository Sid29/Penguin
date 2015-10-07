(function($) {
	$(document).ready(function() {
	
		// document.getElementById('title').innerHTML = title;
		
		$('#submitFormInscription').click(function() {
		
			var formDataInscription = JSON.stringify($("#inscriptionForm").serializeArray());
			
			var data = ""; 
			var params = $("#inscriptionForm").serializeArray(); 
			for (var i = 0; i < params.length; i++) { 
				var param = params[i]; 
				if (param) { 
					data += param.name + '=' + param.value; 
					if (i < params.length - 1) { 
						data += '&'; 
					}
				} 
			}
			 
			$.ajax({
			  type: "GET",
			  url: "/addUser",
			  data: data,
			  success: function() {
			  	alert('data envoyée ' + formDataInscription)
			  },
			  error: function() {
			  	alert('ouiiiiin');
			  },
			  dataType: "json",
			  contentType : "application/json"
			});
			
		});
	
	});
})(jQuery);