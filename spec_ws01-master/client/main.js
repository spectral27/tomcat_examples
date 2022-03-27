window.onload = function() {
    selectAllItems();
}

function selectAllItems() {
	var request =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
			"xmlns:end=\"http://endpoint.spec_ws01/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
					"<end:selectAllItems/>" +
				"</soapenv:Body>" +
			"</soapenv:Envelope>";
   
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST', 'http://localhost:8080/spec_ws01/ws/items');
	xhttp.setRequestHeader('Content-Type', 'text/xml');
	xhttp.onload = function() {
		var parser = new DOMParser();
		var xml = parser.parseFromString(xhttp.responseText, 'text/xml');
		var itemsNodes = xml.getElementsByTagName('item');
		var rows = "";
		for (i = 0; i < itemsNodes.length; i++) {
			rows +=
				"<tr>" +
				"<td>" + itemsNodes[i].childNodes[0].firstChild.data + "</td>" +
				"<td>" + itemsNodes[i].childNodes[1].firstChild.data + "</td>" +
				"</tr>";
		}
		document.getElementById("mainTable").innerHTML = rows;
	}
	xhttp.send(request);
}

function insertItem() {
	var request =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
			"xmlns:end=\"http://endpoint.spec_ws01/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
					"<end:insertItem>" +
						"<arg0>" +
							"<value>" +
								document.getElementById('insertValue').value +
							"</value>" +
						"</arg0>" + 
					"</end:insertItem>" +
				"</soapenv:Body>" +
			"</soapenv:Envelope>";

    var xhttp = new XMLHttpRequest();
    xhttp.open('POST', 'http://localhost:8080/spec_ws01/ws/items');
    xhttp.setRequestHeader('Content-Type', 'text/xml');
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send(request);
}

function updateItem() {
	var request =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
			"xmlns:end=\"http://endpoint.spec_ws01/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
					"<end:updateItem>" +
						"<arg0>" +
							document.getElementById('updateId').value +
						"</arg0>" +
						"<arg1>" +
							"<value>" +
								document.getElementById('updateValue').value +
							"</value>" +
						"</arg1>" +
					"</end:updateItem>" +
				"</soapenv:Body>" +
			"</soapenv:Envelope>";
	
    var xhttp = new XMLHttpRequest();
    xhttp.open('POST', 'http://localhost:8080/spec_ws01/ws/items');
    xhttp.setRequestHeader('Content-Type', 'text/xml');
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send(request);
}

function deleteItem() {
	var request =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
			"xmlns:end=\"http://endpoint.spec_ws01/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
					"<end:deleteItem>" +
						"<arg0>" +
							document.getElementById('deleteId').value +
						"</arg0>" +
					"</end:deleteItem>" +
				"</soapenv:Body>" +
			"</soapenv:Envelope>";
					
    var xhttp = new XMLHttpRequest();
    xhttp.open('POST', 'http://localhost:8080/spec_ws01/ws/items');
	xhttp.setRequestHeader('Content-Type', 'text/xml');
    xhttp.onload = function() {
        selectAllItems();
    }
    xhttp.send(request);
}
