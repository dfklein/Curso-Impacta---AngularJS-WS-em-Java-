package br.com.denis.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.denis.entity.ProdutoORM;

public class ProdutoRepository {

	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public ProdutoRepository() {

		this.entityManagerFactory = Persistence.createEntityManagerFactory("h2");
		this.entityManager = this.entityManagerFactory.createEntityManager();

		criarMassaTeste();
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver"); // driver para h2 db
			return DriverManager.getConnection("jdbc:h2:tcp://localhost:9001/db/meudb", "sa", "123"); // retorna
																										// db
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new SQLException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProdutoORM> listAll() {
		return this.entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.id").getResultList();
	}

	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 */
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
