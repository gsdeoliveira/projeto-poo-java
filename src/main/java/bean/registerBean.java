package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

import model.Usuario;
import util.JPAUtil;

@ManagedBean
@SessionScoped
public class registerBean {
	
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String telefone;
	
	public void register() {
	    // Verificar se as senhas coincidem
	    if (!password.equals(confirmPassword)) {
	        // Senhas não coincidem
	        return;
	    }

	    EntityManager em = JPAUtil.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        transaction.begin();

	        // Verificar se já existe um usuário com o mesmo e-mail
	        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class);
	        query.setParameter("email", email);
	        Long count = query.getSingleResult();

	        if (count > 0) {
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar usuário", "Email já cadastrado."));
	            return;
	        }

	        // Criptografar a senha
	        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

	        Usuario user = new Usuario();
	        user.setNome(username);
	        user.setSenha(hashedPassword);
	        user.setEmail(email);
	        user.setTelefone(telefone);

	        em.persist(user);

	        transaction.commit();
	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado!", "Usuário " + username + " cadastrado com sucesso."));

	    } catch (Exception e) {
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar usuário", "Não foi possível cadastrar o usuário."));
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        // Fechar a sessão do Hibernate
	        em.close();
	    }
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
