function showAndroidToast(toast) {
	Android.showToast(toast);
	alert(toast);
}

function saveLocal(data) {
	localStorage.setItem('key', data);
}

function getLocal(key) {
	var value = localStorage.getItem('key');
	document.getElementsByTagName('h1')[0].innerHTML = value;
}

function foollishCallBack(data) {
	showAndroidToast(data.age);
}
