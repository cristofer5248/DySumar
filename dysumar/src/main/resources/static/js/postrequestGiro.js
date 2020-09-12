$(document).ready(
		function() {

			// SUBMIT FORM
			$("#btnGiro").submit(function(event) {
				alert('hi');
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {
				var data = {}
				data["nombreGiro"] = $("#nombreGiro").val();
				
				$.ajax({
		             type: "POST",
		             contentType: "application/json",
		             url: "/saveExpress",
		             data: JSON.stringify(data),
		             dataType: 'json',
		             timeout: 600000,
		             success: function (data) {
		                 $("#btnGiro").prop("disabled", false);
		                 //...
		             },
		             error: function (e) {
		                 $("#btnGiro").prop("disabled", false);
		                 //...
		             }
			});
				

//				// DO POST
//				$.ajax({
//					type : "POST",
//					contentType : "application/json",
//					url : "/saveExpress",
//					data : JSON.stringify(giroOb),
//					dataType : 'json',
//					success : function(result) {
//						if (result.status == "success") {
////							$("#postResultDiv").html(
////									"" + result.data.bookName
////											+ "Post Successfully! <br>"
////											+ "---> Congrats !!" + "</p>");
//						} else {
////							$("#postResultDiv").html("<strong>Error</strong>");
//						}
//						console.log(result);
//					},
//					error : function(e) {
//						alert("Error!")
//						console.log("ERROR: ", e);
//					}
//				});

			}

		})