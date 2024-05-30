package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Categoria;
import util.JPAUtil;

@ManagedBean
@SessionScoped
public class homeBean {
	private String searchValue;
	
	public void searchChange() {
		//
	}
	
	public homeBean() {
		System.out.println("teste");
	}
	
	public String getSearchValue() {
		System.out.println("chamado");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
	        transaction.begin();
	        
	        Categoria romance = new Categoria();
	        romance.setNome("Romance");

	        em.persist(romance);

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
