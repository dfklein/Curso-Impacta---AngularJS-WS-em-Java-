package br.com.denis.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.denis.entity.Produto;
import br.com.denis.repository.ProdutoRepository;

/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/service")
public class ServiceController {

	private final ProdutoRepository rep = new ProdutoRepository();
	
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
			Produto entity = new Produto();
			
			entity.setDescricao(produto.getDescricao());
			entity.setDataCriacao(produto.getDataCriacao());
			entity.setPreco(produto.getPreco());
			
			rep.insert(produto);
			
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
			Produto entity = new Produto();
			
			entity.setDescricao(produto.getDescricao());
			entity.setDataCriacao(produto.getDataCriacao());
			entity.setPreco(produto.getPreco());
			
			rep.update(produto);
			
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
		return rep.listAll();
	}
 
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getProduto/{id}")
	public Produto findById(@PathParam("codigo") Long id){
 
		Produto produto = rep.findById(id);
 
		if(produto != null)
			return produto;
 
		return null;
	}
 
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("codigo") Long codigo){
 
		try {
 
			rep.remove(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}
}
