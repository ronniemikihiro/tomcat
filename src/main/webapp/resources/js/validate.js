function removeAllMsgs() {
    PF('growl').removeAll();
}

function isEmpty(idCampo, nomeCampo) {
    var campo = document.getElementById("fmMain:"+idCampo);
    var isEmpty = false;
    if(campo.className.indexOf("inputtext") != -1) {
        isEmpty = campo.value.trim() == "";
    }else if(campo.className.indexOf("select") != -1) {
        isEmpty = PF(idCampo).getJQ().find(':checked').val() === undefined;
    }else if(campo.className.indexOf("inputnumber") != -1) {
        isEmpty = PF(idCampo).getValue() == "";
    }
	if(isEmpty) {
        PF('growl').renderMessage({"summary":"Alerta", "detail": "O campo " + nomeCampo + " é obrigatório.", "severity":"warn"});
	}
	return isEmpty;
}

function validLogin() {
    removeAllMsgs();
    return isEmpty("itLogin", "Login") ? false : isEmpty("pwSenha", "Senha") ? false : true;
}

function validUsuario() {
    removeAllMsgs();
    return isEmpty("itLogin", "Login") ? false : isEmpty("pwSenha", "Senha") ? false : isEmpty("itNome", "Nome") ? false : isEmpty("sobPerfil", "Perfil") ? false : true;
}

function validItemPedido() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome") ? false : isEmpty("inPreco", "Preço") ? false : true;
}

function validCliente() {
    removeAllMsgs();
    return isEmpty("itNome", "Nome") ? false : isEmpty("imCelular1", "Celular1") ? false : true;
}