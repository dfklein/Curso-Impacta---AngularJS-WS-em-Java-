package br.com.denis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.denis.entity.ProdutoORM;
import br.com.denis.helper.RootElementHelper;
import br.com.denis.http.Produto;
import br.com.denis.repository.ProdutoRepository;

/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/service")
public class ServiceController {

//	private final ProdutoRepository rep = new ProdutoRepository();
	private List<ProdutoORM> listProdutoORM;
	private List<Produto> listProdutoHttp;
	
	public ServiceController() {
		if (listProdutoORM == null) {
			listProdutoORM = new ArrayList<>();
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

			listProdutoORM.add(p1);
			listProdutoORM.add(p2);
			listProdutoORM.add(p3);
			
		}
	}
	
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(Produto produto) {
		
		try {
			ProdutoORM entity = new ProdutoORM();
			
			entity.setDescricao(produto.getDescricao());
			entity.setDataCriacao(produto.getDataCriacao());
			entity.setPreco(produto.getPreco());
			
//			rep.insert(entity);
			
			return "Registro cadastrado com sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
		
	}
	
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar(Produto produto) {
		try {
			ProdutoORM entity = new ProdutoORM();
			
			entity.setDescricao(produto.getDescricao());
			entity.setDataCriacao(produto.getDataCriacao());
			entity.setPreco(produto.getPreco());
			
//			rep.update(entity);
			
			return "Registro alterado com sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
	}
	
	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todosProdutos")
	public List<Produto> todosProdutos(){
//		List<Produto> pessoas =  new ArrayList<Produto>();
//		 
//		List<ProdutoORM> listaEntityPessoas = rep.listAll();
// 
//		for (ProdutoORM entity : listaEntityPessoas) {
// 
//			pessoas.add(new Produto(entity.getId(), entity.getDescricao(), entity.getDataCriacao(), entity.getPreco()));
//		}
// 
//		return pessoas;
		return RootElementHelper.converterOrmParaHttp(listProdutoORM);
	}
 
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getProduto/{id}")
	public Produto findById(@PathParam("id") Integer id) {
		
		return RootElementHelper.converterOrmParaHttp(listProdutoORM.get(id));
 
//		ProdutoORM entity = rep.findById(id);
//		 
//		if(entity != null)
//			return new Produto(entity.getId(), entity.getDescricao(), entity.getDataCriacao(), entity.getPreco());
// 
//		return null;
	}
 
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("codigo") Integer codigo){
 
		try {
			listProdutoORM.remove(codigo);
 
//			rep.remove(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}
}
