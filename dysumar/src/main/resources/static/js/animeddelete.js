/*$("a").click(function(){
	if($(this).hasClass("confirm")){
		$(this).addClass("done");
		$("span").text("Deleted");
	} else {
		$(this).addClass("confirm");
		$("span").text("Are you sure?");
	}
});

// Reset
$("a").on('mouseout', function(){
	if($(this).hasClass("confirm") || $(this).hasClass("done")){
		setTimeout(function(){
			$("a").removeClass("confirm").removeClass("done");
			$("span").text("Delete");
		}, 3000);
	}
});*/