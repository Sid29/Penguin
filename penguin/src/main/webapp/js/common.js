(function($) {
	$(document).ready(function() {


		//submitFormConnection
		$('#submitFormConnection').click(function() {

			var formDataConnection = JSON.stringify($("#connectionForm").serializeArray());
			var data = ""; 
			var params = $("#connectionForm").serializeArray(); 
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
				url: "/login",
				data: data,
				success: function() {
					$.get(function(data) {
						alert('page content: ' + data);
					}
					);
				},
				error: function() {
					alert('Le login et/ou le mot de passe est(sont) erroné(s).');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});


		//submitFormInscription
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
					$("#addUser").modal('hide');
				},
				error: function() {
					alert('Un problème s\'est produit lors de la transmission des données.');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});

	});
})(jQuery);