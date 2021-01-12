$(document).ready(
//		var regExdui= document.getElementById('dui').value;
//		var duiRegEx= /^\d{8}-\d{1}$/g;
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
		                 alertify.alert('Exito!', 'Nuevo giro registrado , ahora puedes buscarlo en el formulario!', function(){ alertify.success('Ok'); });
		                 $('#cargar_giro').val(response);
		                 $("#cargar_giro").prop("readonly", true);
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
				var data2 = data.split('/').join( 'jj');
				data = data2;  
				
				$.ajax({
		             type: "get",
		             dataType : "json",
		             url: "saveDExpress/"+data2,
		             timeout: 600000,
		             
		             success: function (data) {
		            	 console.log(data);
		                 $("#btnDireccion").prop("disabled", true);		                 
		                 $('#exampleModal3').modal('toggle');
		                 alertify.alert('Exito!', 'Direccion registrada/ encontrada  y agregada al formulario', function(){ alertify.success('Ok'); });
		                 $('#direccionapellido').val(data);
		                 $("#direccionapellido").prop("readonly", true);
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