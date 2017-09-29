package br.com.tomcat.exception;

/**
 * Created by ronnie-msl on 13/09/17.
 */
public class UserPasswordInvalidException extends NegocioException {

    public UserPasswordInvalidException() {
        super("Usuário e/ou Senha Inválido!");
    }
}
