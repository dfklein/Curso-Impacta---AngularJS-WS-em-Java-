package br.com.denis.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.denis.entity.ProdutoORM;

public class ProdutoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
 
	public ProdutoRepository() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("h2");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
		criarMassaTeste();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProdutoORM> listAll() {
		return this.entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.id").getResultList();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void insert(ProdutoORM produto) {
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(produto);
		this.entityManager.getTransaction().commit();
	}
	
	public void insert(List<ProdutoORM> listProdutos) {
		for (ProdutoORM produto : listProdutos) {
			insert(produto);
		}
	}
	
	public void update(ProdutoORM produto) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(produto);
		this.entityManager.getTransaction().commit();
		
	}
	
	public void remove(Long id) {
		ProdutoORM produto = findById(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(produto);
		this.entityManager.getTransaction().commit();
		
	}
	
	public ProdutoORM findById(Long id) {
		return this.entityManager.find(ProdutoORM.class, id);
	}

	private void criarMassaTeste() {
		ProdutoORM p1 = new ProdutoORM();
		ProdutoORM p2 = new ProdutoORM();
		ProdutoORM p3 = new ProdutoORM();
		
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
