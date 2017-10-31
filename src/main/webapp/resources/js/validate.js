var TipoCampoEnum = {
    INPUT_TEXT: 1, //inputtext
    SELECT: 2, //selectonebutton,selectonemenu
    INPUT_NUMBER: 3 //inputnumber
};

function removeAllMsgs() {
    PF('growl').removeAll();
}

function isEmpty(idCampo, nomeCampo, tipoCampo) {
    var isEmpty = false;
    switch(tipoCampo) {
        case TipoCampoEnum.INPUT_TEXT: var campo = document.getElementById("fmMain:"+idCampo); isEmpty = campo != null && (campo.value.trim() == "" || campo.value.trim() == "0"); break;
        case TipoCampoEnum.SELECT: var select = PF(idCampo).getJQ().find(':checked').val(); isEmpty = select === undefined || select == ""; break;
        case TipoCampoEnum.INPUT_NUMBER: isEmpty = PF(idCampo).getValue() == ""; break;
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
    return isEmpty("itLogin", "Login", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("pwSenha", "Senha", TipoCampoEnum.INPUT_TEXT) ? false : true;
}

function validEmailEsqueceu() {
    removeAllMsgs();
    return isEmpty("itEmail", "Email", TipoCampoEnum.INPUT_TEXT) ? false : true;
}

function validUsuario() {
    removeAllMsgs();
    return isEmpty("itLogin", "Login", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("pwSenha", "Senha", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("itEmail", "Email", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("itNome", "Nome", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("sobPerfil", "Perfil", TipoCampoEnum.SELECT) ? false : true;
}

function validItemPedido() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("inPreco", "Preço", 3) ? false : true;
}

function validCliente() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome", TipoCampoEnum.INPUT_TEXT) ? false : isEmpty("imCelular1", "Celular1", TipoCampoEnum.INPUT_TEXT) ? false : true;
}

function validPedido() {
    removeAllMsgs();
    return isEmpty("somCliente", "Cliente", TipoCampoEnum.SELECT) ? false : isEmptyTable("dtItemPedidoCliente","pedido") ? false : true;
}

function validItemPedidoCliente() {
    removeAllMsgs();
    return isEmpty("somItemPedido", "Item Pedido", TipoCampoEnum.SELECT) ? false : isEmpty("itQuant", "Quant.", TipoCampoEnum.INPUT_TEXT) ? false : true;
}