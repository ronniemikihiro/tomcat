package br.com.tomcat.exception;

/**
 * Created by ronnie-msl on 13/09/17.
 */
public class NegocioException extends Exception {

    public NegocioException(final String mensagem) {
        super(mensagem);
    }
}
