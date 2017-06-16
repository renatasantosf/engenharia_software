package util;


import bean.RetiradasMB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Livro;

/**
 *
 * @author lhries
 */
@FacesConverter(forClass=Livro.class)
public class LivroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RetiradasMB bean = context.getApplication().evaluateExpressionGet(context, "#{retiradasMB}", RetiradasMB.class);
        Livro livro = bean.buscarLivroPorNome(value);
        return livro;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null)
            return((Livro)value).getNome();
        return null;
    }
    
}
