package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import model.Usuario;
import util.JPAUtil;

@ManagedBean
@SessionScoped
public class loginBean {
    private String email;
    private String password;
    private Boolean loggedIn = false;
    private Boolean admin = true;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public Boolean getAdmin() {
		return admin;
	}
    
    public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

    public void login() {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            Usuario user = query.getSingleResult();

            if (BCrypt.checkpw(password, user.getSenha())) {
                loggedIn = true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao fazer login", "Usuário ou senha inválidos"));
            }
        } catch (NoResultException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao fazer login", "Usuário ou senha inválidos"));
        } finally {
            em.close();
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        loggedIn = false;
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
