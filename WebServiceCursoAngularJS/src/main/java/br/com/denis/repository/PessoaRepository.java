package br.com.denis.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.denis.entity.Produto;

public class PessoaRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
 
	public PessoaRepository() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("h2");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
		criarMassaTeste();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listAll() {
		return this.entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.id").getResultList();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void insert(Produto produto) {
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(produto);
		this.entityManager.getTransaction().commit();
	}
	
	public void insert(List<Produto> listProdutos) {
		for (Produto produto : listProdutos) {
			insert(produto);
		}
	}
	
	private void criarMassaTeste() {
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		
		p1.setDescricao("Sabão em pó");
		p1.setPreco(10.0);
		p1.setDataCriacao(new Date());
		
		p2.setDescricao("Macarrão");
		p2.setPreco(23.41);
		p2.setDataCriacao(new Date());
		
		p3.setDescricao("Leite condensado");
		p3.setPreco(7.99);
		p3.setDataCriacao(new Date());
		
		insert(p1);
		insert(p2);
		insert(p3);
		
	}
}
