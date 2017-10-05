package br.com.tomcat.exception;

/**
 * Created by ronnie-msl on 13/09/17.
 */
public class UserEmailInvalidException extends NegocioException {

    public UserEmailInvalidException() {
        super("Nenhum usuário encontrado com o email informado!");
    }
}
