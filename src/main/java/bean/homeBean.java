package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Genero;
import util.JPAUtil;

@ManagedBean
@SessionScoped
public class homeBean {
	private String searchValue;
	private List<Genero> generos;
	
	@PostConstruct
	public void init() {
		generos = getListaGenero();
	}
	
	public List<Genero> getListaGenero() {
		EntityManager em = JPAUtil.getEntityManager();
        
        try {
        	
            String jpql = "SELECT g FROM Genero g";
            TypedQuery<Genero> query = em.createQuery(jpql, Genero.class);
            generos = query.getResultList();
            return query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
	}
	
	public void searchChange() {
		//
	}
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public List<Genero> getGeneros() {
		generos = getListaGenero();
		if(generos.size() > 0) {
			System.out.println(generos.get(0));
			System.out.println(generos.get(0).getLivros());
			if(generos.get(0).getLivros().size() > 0) {
				System.out.println(generos.get(0).getLivros().get(0));
				System.out.println(generos.get(0).getLivros().get(0).getTitulo());
			}
		}
		return generos;
	}
	
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
}
