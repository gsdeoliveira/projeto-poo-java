package bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Genero;
import model.Livro;
import util.JPAUtil;

@ManagedBean
@ViewScoped
public class bookBean {
	
	private String titulo;
	private String isbn;
	private Date dataPublicacao;
	private Integer qtdDisponivel;
	private String editora;
	private List<Genero> generos;
	private Long generoSelecionado;
	private String autor;
	
	@PostConstruct
	public void init() {
		
		// Inicializando a lista de gêneros
		generos = Arrays.asList(
			new Genero("Ficção"),
			new Genero("Não Ficção"),
			new Genero("Romance"),
			new Genero("Fantasia"),
			new Genero("História")
			
		);
		
		for(Genero genero: generos) {
		    EntityManager em = JPAUtil.getEntityManager();
		    EntityTransaction transaction = em.getTransaction();
		    
		  try {
	          transaction.begin();

	          // Salvar o livro no banco de dados
	          em.persist(genero);
	
	          // Commit da transação
	          transaction.commit();

	      } catch (Exception e) {
	          // Rollback da transação em caso de erro
	          if (transaction != null && transaction.isActive()) {
	              transaction.rollback();
	          }
	          e.printStackTrace();
	      } finally {
	
	          if (em != null && em.isOpen()) {
	              em.close();
	          }
	      }
		}
	}
	
	public void adicionar() {
	    
	    EntityManager em = JPAUtil.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
	    	
            transaction.begin();
            
            Genero generoExistente = em.find(Genero.class, generoSelecionado);

            Livro livro = new Livro();
            
            livro.setGenero(generoExistente);
            
            livro.setTitulo(titulo);
            livro.setIsbn(isbn);
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataPublicacao);

            int ano = calendar.get(Calendar.YEAR);
            
            livro.setAnoPublicacao(ano);
            livro.setQtdDisponivel(qtdDisponivel);
            livro.setEditora(editora);

            // Salvar o livro no banco de dados
            em.persist(livro);

            // Commit da transação
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro cadastrado com sucesso!", "Livro " + titulo + " foi cadastrado"));

        } catch (Exception e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha ao cadastrar", "Não foi possível cadastrar o livro"));
            // Rollback da transação em caso de erro
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }
        }
	    
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public Integer getQtdDisponivel() {
		return qtdDisponivel;
	}
	public void setQtdDisponivel(Integer qtdDisponivel) {
		this.qtdDisponivel = qtdDisponivel;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public List<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(List<Genero> categorias) {
		this.generos = categorias;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Long getGeneroSelecionado() {
		return generoSelecionado;
	}
	public void setGeneroSelecionado(Long generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

}
