$(document).ready(
		function() {

			// SUBMIT FORM
			$("#formGiro").submit(function(event) {
				
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});
			

				// SUBMIT FORM
				$("#formDireccion").submit(function(event) {
					
					// Prevent the form from submitting via the browser.
					event.preventDefault();
					ajaxPost2();
				});
			
			function ajaxPost() {
				var data = $("#nombreGiro").val();
				$.ajax({
		             type: "get",
		             dataType : "json",
		             url: "/giro/saveExpress/"+$("#nombreGiro").val(),
		             timeout: 600000,
		             
		             success: function (response) {
		            	 console.log(data);
		                 $("#btnGiro").prop("disabled", true);
		                 $('#exampleModal2').modal('toggle');
		                 alertify.alert('Exito!', 'Nuevo giro registrado, ahora puedes buscarlo en el formulario!', function(){ alertify.success('Ok'); });
		                 // ...
		             },
		             error: function () {
		            	 alertify.alert('Error!', 'No se pudo guardar, contactar a soporte tecnico.', function(){ alertify.success('Ok'); });
		                 $("#btnGiro").prop("disabled", false);
		                 // ...
		             }
			});

			}
			function ajaxPost2() {
				var data = $("#nombreDireccion").val();
				$.ajax({
		             type: "get",
		             dataType : "json",
		             url: "saveDExpress/"+$("#nombreDireccion").val(),
		             timeout: 600000,
		             
		             success: function (data) {
		            	 console.log(data);
		                 $("#btnDireccion").prop("disabled", true);		                 
		                 $('#exampleModal3').modal('toggle');
		                 alertify.alert('Exito!', 'Nueva direccion registrada y agregada al formulario', function(){ alertify.success('Ok'); });
		                 $('#direccionapellido').val(data);
		                 $("#direccionapellido").prop("disabled", true);
		                 // ...
		             },
		             error: function () {
		            	 alertify.alert('Error!', 'No se pudo guardar, contactar a soporte tecnico.', function(){ alertify.success('Ok'); });
		                 $("#btnDireccion").prop("disabled", false);
		                 // ...
		             }
			});

			}

		})