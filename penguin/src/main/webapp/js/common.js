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
					return true;
				},
				error: function() {
					debugger;
					getUserConnected();
					$("#connection").hide();
					alert('Le login et/ou le mot de passe est(sont) erroné(s).');
					return false;
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
					firstname = json[0].firstname;
					alert('firstname userConnected =' + firstname);
					document.getElementById("name_pengu").innerHTML = firstname;
					return firstname;
				},
				error: function() {
					alert('Aucun utilisateur n\'est connecté.');
					return false;
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
			var title;
			var body;
			var author = "";
			var date_article = "";
			$.ajax({
				type: "GET",
				url: "/lastArticleWithComments",
				success: function(json) {
				    title = json[0].title;
				    author = json[0].author.firstname + " " + json[0].author.lastname;
				    body = json[0].body;
				    date_article = (json[0].dateCreation).toString();
					document.getElementById("titleLastArticle").innerHTML = title;
					document.getElementById("bodyLastArticle").innerHTML = body;
					document.getElementById("authorLastArticle").innerHTML = author;
					document.getElementById("dateLastArticle").innerHTML = date_article;
				},
				error: function() {
					alert('Il y a eu une erreur lors de la récupération des données, veuillez nous excuser.')
				}
			});
		});		
		
		
		
	});
})(jQuery);