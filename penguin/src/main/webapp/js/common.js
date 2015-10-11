(function($) {
	$(document).ready(function() {
		var connected = false;

		//submitFormConnection
		$('#submitFormConnection').click(function() {
			var dataFormConnection = ""; 
			var params = $("#connectionForm").serializeArray(); 
			for (var i = 0; i < params.length; i++) { 
				var param = params[i]; 
				if (param) { 
					dataFormConnection += param.name + '=' + param.value; 
					if (i < params.length - 1) { 
						dataFormConnection += '&'; 
					}
				} 
			}
			$.ajax({
				type: "GET",
				url: "/login",
				data: dataFormConnection,
				success: function() {
					getUserConnected();
					$("#connection").hide();
				},
				error: function() {
					$("#connection").hide();
					alert('Le login et/ou le mot de passe est(sont) erroné(s).');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});		

		//getUserConnected
		function getUserConnected(){
			$.ajax({
				type: "GET",
				url: "/userConnected",
				success: function(json) {
					alert( "success : JSON Data: " + json);
				},
				error: function(json) {
					alert("error : " + json);
				},
				dataType: "json",
				contentType : "application/json"
			});
		}

		//submitFormInscription
		$('#submitFormInscription').click(function() {
			var dataFormInscription = ""; 
			var params = $("#inscriptionForm").serializeArray(); 
			for (var i = 0; i < params.length; i++) { 
				var param = params[i]; 
				if (param) { 
					dataFormInscription += param.name + '=' + param.value; 
					if (i < params.length - 1) { 
						dataFormInscription += '&'; 
					}
				} 
			}
			$.ajax({
				type: "GET",
				url: "/addUser",
				data: dataFormInscription,
				success: function() {
					$("#addUser").hide();
				},
				error: function() {
					$("#addUser").hide();
					alert('Un problème s\'est produit lors de la transmission des données.');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});

		//change firstname
		$('#buttonChangeFirstname').click(function(){
			var firstname = null;
			$.ajax({
				type: "GET",
				url: "/userConnected",
				success: function(json) {
					firstname = $.parseJSON(json);
					alert( "success : JSON Data: " + firstname);
				},
				error: function(json) {
					firstname = $.parseJSON(json);
					alert("error : " + firstname);
				}
			});
			firstname = "je n arrive pas a decoder l objet... shiiiiiiiit";
			document.getElementById("name_pengu").innerHTML = firstname;
		});


		//submitArticle
		$('#submitArticle').click(function() {
			var dataFormArticle = ""; 
			var params = $("#articleForm").serializeArray(); 
			for (var i = 0; i < params.length; i++) { 
				var param = params[i]; 
				if (param) { 
					dataFormArticle += param.name + '=' + param.value; 
					if (i < params.length - 1) { 
						dataFormArticle += '&'; 
					}
				} 
			}
			$.ajax({
				type: "GET",
				url: "/addArticle",
				data: dataFormArticle,
				success: function() {
					getUserConnected();
					$("#createArticle").hide();
				},
				error: function() {
					$("#createArticle").hide();
					alert('Le login et/ou le mot de passe est(sont) erroné(s).');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});	


		//submitComment
		$('#submitComment').click(function() {
			var dataFormComment = ""; 
			var params = $("#commentForm").serializeArray(); 
			alert(params);
			dataFormComment = 'articleId =' + '1' + '&';
			dataFormComment += params[1].name + '=' + params[1].value; 
			
			$.ajax({
				type: "GET",
				url: "/addComment",
				data: dataFormComment,
				success: function() {
					$("#createComment").hide();
				},
				error: function() {
					$("#createComment").hide();
					alert('Le commentaire n\'a pas été enregistré. Veuillez le ressaisir s\'il vous plaît.');
				},
				dataType: "json",
				contentType : "application/json"
			});
		});	


		//refresh article
		$('#refreshLastArticle').click(function getLastArticle(){
			var title = "";
			var body = "";
			var author = "";
			var date_article = "";
			$.ajax({
				type: "GET",
				url: "/lastArticleWithComments",
				success: function(json) {
					article = $.parseJSON(json);
					alert(json);
					alert(article);
					title = article.title;
					body = article.body;
					alert( "success : JSON Data: " + title);
				},
				error: function(json) {
					article = $.parseJSON(json);
					title = article[1][title];
					body = article[1][body];
					alert("json = "+json);
					alert("article = "+article);
					alert("error : " + title);
				}
			});
			alert('title '+title);
			alert('body '+body);
			document.getElementById("titleLastArticle").innerHTML = title;
			document.getElementById("bodyLastArticle").innerHTML = body;
		});		
		
		
		
	});
})(jQuery);