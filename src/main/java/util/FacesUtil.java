package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    // Método para adicionar mensagem de informação
    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    // Método para adicionar mensagem de erro
    public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    // Método para obter o contexto atual do JSF
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    // Método para obter parâmetros de requisição
    public static String getRequestParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    // Método para obter um bean gerenciado pelo JSF
    public static Object getManagedBean(String beanName) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(beanName);
    }
}
