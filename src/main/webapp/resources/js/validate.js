function removeAllMsgs() {
    PF('growl').removeAll();
}

function isEmpty(idCampo, nomeCampo, tipoCampo) {
    var isEmpty = false;
    switch(tipoCampo) {
        case 1: var campo = document.getElementById("fmMain:"+idCampo); isEmpty = campo != null && (campo.value.trim() == "" || campo.value.trim() == "0"); break; //inputtext
        case 2: var select = PF(idCampo).getJQ().find(':checked').val(); isEmpty = select === undefined || select == ""; break; //selectonebutton,selectonemenu
        case 3: isEmpty = PF(idCampo).getValue() == ""; break; //inputnumber
    }
	if(isEmpty) {
        PF('growl').renderMessage({"summary":"Alerta", "detail": "O campo " + nomeCampo + " é obrigatório.", "severity":"warn"});
	}
	return isEmpty;
}

function isEmptyTable(idTable, nomeItemTable) {
    var isEmpty = PF(idTable).isEmpty();
    if(isEmpty) {
        PF('growl').renderMessage({"summary":"Alerta", "detail": "É obrigatório adicionar ao menos um " + nomeItemTable + ".", "severity":"warn"});
    }
    return isEmpty;
}

function validLogin() {
    removeAllMsgs();
    return isEmpty("itLogin", "Login", 1) ? false : isEmpty("pwSenha", "Senha", 1) ? false : true;
}

function validEmailEsqueceu() {
    removeAllMsgs();
    return isEmpty("itEmail", "Email", 1) ? false : true;
}

function validUsuario() {
    removeAllMsgs();
    return isEmpty("itLogin", "Login", 1) ? false : isEmpty("pwSenha", "Senha", 1) ? false : isEmpty("itEmail", "Email", 1) ? false : isEmpty("itNome", "Nome", 1) ? false : isEmpty("sobPerfil", "Perfil", 2) ? false : true;
}

function validItemPedido() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome", 1) ? false : isEmpty("inPreco", "Preço", 3) ? false : true;
}

function validCliente() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome", 1) ? false : isEmpty("imCelular1", "Celular1", 1) ? false : true;
}

function validPedido() {
    removeAllMsgs();
    return isEmpty("somCliente", "Cliente", 2) ? false : isEmptyTable("dtItemPedidoCliente","pedido") ? false : true;
}

function validItemPedidoCliente() {
    removeAllMsgs();
    return isEmpty("somItemPedido", "Item Pedido", 2) ? false : isEmpty("itQuant", "Quant.", 1) ? false : true;
}