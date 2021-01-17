function addcl() {
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl() {
	let parent = this.parentNode.parentNode;
	if (this.value == "") {
		parent.classList.remove("focus");
	}
}

// $('input[type=text]').forEach(input => {
// input.addEventListener("focus", addcl);
// input.addEventListener("blur", remcl);
// });

function cleanh51() {
	document.getElementsByTagName("h5")[1].style.display = 'none';
}
function cleanh5() {
	document.getElementsByTagName("h5")[2].style.display = 'none';
}