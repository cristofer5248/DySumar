  
            var submitIcon = $('.searchbox-icon');
            var inputBox = $('.searchbox-input');
            var vamonos = $('#vamonos');
            var searchBox = $('.searchbox');
            var isOpen = false;
            
            
            vamonos.click(function(){            	
            	var opc = $("#opcSearch").val();            	
            	location.href = $("#urlenviar").val()+opc+"/"+$("#btn-search").val();
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
            }