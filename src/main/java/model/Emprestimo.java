package model;

import java.util.Date;

public class Emprestimo {
	
	private Long id;
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public Date getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}
	public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}
	public Date getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}
	public void setDataDevolucaoReal(Date dataDevolucaoReal) {
		this.dataDevolucaoReal = dataDevolucaoReal;
	}

}
