  
            var submitIcon = $('.searchbox-icon');
            var inputBox = $('.searchbox-input');
            var vamonos = $('#vamonos');
            var searchBox = $('.searchbox');
            var isOpen = false;
            
            
            vamonos.click(function(){              	
            	var opc = $("#opcSearch").val();
            	var txtSearch = $("#btn-search").val();
            	var txtSearchX = txtSearch.replace(/\//g, "-");            	
            	location.href = $("#urlenviar").val()+opc+"/"+txtSearchX;
            	
            });
            submitIcon.click(function(){
                if(isOpen == false){
                    searchBox.addClass('searchbox-open');
                    inputBox.focus();
                    isOpen = true;
                    
                } else {                	
                    searchBox.removeClass('searchbox-open');
                    inputBox.focusout();
                    isOpen = false;
                    
                }
            });  
             submitIcon.mouseup(function(){
                    return false;
                });
            searchBox.mouseup(function(){
                    return false;
                });
            $(document).mouseup(function(){
                    if(isOpen == true){
                        $('.searchbox-icon').css('display','block');
                        
                        submitIcon.click();
                    }
                });
        
            function buttonUp(){
                var inputVal = $('.searchbox-input').val();
                inputVal = $.trim(inputVal).length;
                if( inputVal !== 0){
                    $('.searchbox-icon').css('display','none');
                } else {
                	
                    $('.searchbox-input').val('');
                    $('.searchbox-icon').css('display','block');
                }                                    
                var opc = $("#opcSearch").val();
                var text_temp = $("#btn-search").val();
                var txtSearchX = text_temp.replace(/\//g, "zzz");
                $('#formsearch').attr('action', $("#urlenviar").val()+opc+"/"+txtSearchX);
            }
$("#opcSearch" ).change(function() {

  $("#btn-search").focus();
});