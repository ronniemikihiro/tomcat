package br.com.tomcat.util;

import br.com.tomcat.entity.Entity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@SuppressWarnings("rawtypes")
@FacesConverter(value = "converterEntity")
public class ConverterEntity implements Converter {

	/**
     * <p> Método que recebe o valor da tela retorna o mesmo sem formatação. <p>
	 * 
	 * @param contexto
	 * @param componente
	 * @param valor
	 * @return Object
	 * @author ronnie-msl
	 */
	@Override
	public final Object getAsObject(final FacesContext contexto, final UIComponent componente, final String valor) {
		return StringUtil.isNullEmpty(valor) ? null : componente.getAttributes().get(valor);
	}

	/**
	 * <p> Método que recebe o objeto e retorna o mesmo como String. <p>
	 * 
	 * @param contexto
	 * @param componente
	 * @param valor
	 * @return String
	 * @author ronnie-msl
	 */
	@Override
	public final String getAsString(final FacesContext contexto, final UIComponent componente, final Object valor) {
		if(ObjectUtil.nonNull(valor)) {
			final Entity entity = (Entity) valor;
			final String chave = String.valueOf(entity.getId());
			componente.getAttributes().put(chave, entity);
			return chave;
		}
		return null;
	}

}