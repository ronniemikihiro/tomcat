package br.com.tomcat.util;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public abstract class JSFUtil {

	private JSFUtil(){}

	public static void addInfo(final String msgn) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msgn));
		manterMensagemFlash();
	}

	public static void addInfo(Throwable throwable) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", throwable.getMessage()));
		manterMensagemFlash();
	}

	public static void addWarn(final String msgn) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", msgn));
		manterMensagemFlash();
	}

	public static void addWarn(final Throwable throwable) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", throwable.getMessage()));
		manterMensagemFlash();
	}

	public static void addError(final String msgn) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msgn));
		manterMensagemFlash();
	}

	public static void addError(final Throwable throwable) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", throwable.getMessage()));
		manterMensagemFlash();
	}

	public static void goPage(final String pagina) {
		final FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler().handleNavigation(fc, null, pagina+"?faces-redirect=true");
	}

	public static void addPropSession(final String propriedade, final Object valor){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(propriedade, valor);
	}

	public static Object getPropSession(final String propriedade){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(propriedade);
	}

	public static void removePropSession(final String propriedade){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(propriedade);
	}

	public static void openDialog(final String idDialog) {
		RequestContext.getCurrentInstance().execute("PF('" + idDialog + "').show()");
	}

	public static void closeDialog(final String idDialog) {
		RequestContext.getCurrentInstance().execute("PF('" + idDialog + "').hide()");
	}

	public static void update(final String id) {
		RequestContext.getCurrentInstance().update(id);
	}

	public static void update(final List<String> listaIds) {
		RequestContext.getCurrentInstance().update(listaIds);
	}

	public static void executeJS(final String javascript) {
		RequestContext.getCurrentInstance().execute(javascript);
	}

	private static void manterMensagemFlash() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}