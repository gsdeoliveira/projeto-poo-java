package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    // Método para obter a sessão atual
    private static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    // Método para obter um atributo da sessão
    public static Object getAttribute(String name) {
        HttpSession session = getSession();
        return (session != null) ? session.getAttribute(name) : null;
    }

    // Método para definir um atributo na sessão
    public static void setAttribute(String name, Object value) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(name, value);
        }
    }

    // Método para remover um atributo da sessão
    public static void removeAttribute(String name) {
        HttpSession session = getSession();
        if (session != null) {
            session.removeAttribute(name);
        }
    }

    // Método para invalidar a sessão
    public static void invalidateSession() {
        HttpSession session = getSession();
        if (session != null) {
            session.invalidate();
        }
    }
}
