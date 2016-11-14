/** Obtencion del objeto XMLHttpRequest */
function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}

/** Envio XMLHttpRequest por GET */
/** Completar argumentos */
function sendRequestGet(uri) {
  var request = getRequestObject();
  request.onreadystatechange =  function() { handleResponse(request); };
  request.open("GET", uri, true);
  request.send(null);
}

/** Envio XMLHttpRequest por POST */
/** Completar argumentos */
function sendRequestPost(uri, data) {
  var request = getRequestObject();
  request.onreadystatechange =  function() { handleResponse(request); };
  request.open("POST", uri, true);
  request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  request.send(data);
}

/** Manejador de Evento */
/** Completar argumentos */
function handleResponse(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		/** COMPLETAR */
		document.getElementById("contenedorTabla").innerHTML = request.responseText;
	}
}