package br.com.denis.helper;

import java.util.ArrayList;
import java.util.List;

import br.com.denis.entity.ProdutoORM;
import br.com.denis.http.Produto;

public final class RootElementHelper {
	
		private RootElementHelper() {
			
		}
		
		public static Produto converterOrmParaHttp(ProdutoORM orm) {
			Produto produto = new Produto();
			produto.setId(orm.getId());
			produto.setDescricao(orm.getDescricao());
			produto.setDataCriacao(orm.getDataCriacao());
			produto.setPreco(orm.getPreco());
			
			return produto;
		}
		
		public static List<Produto> converterOrmParaHttp(List<ProdutoORM> ormList) {
			List<Produto> listProduto = new ArrayList<Produto>();
			for (ProdutoORM orm : ormList) {
				listProduto.add(converterOrmParaHttp(orm));
			}
			return listProduto;
		}
		
		public static List<ProdutoORM> converterHttpParaOrm(List<Produto> listProduto) {
			List<ProdutoORM> listProdutoORM = new ArrayList<ProdutoORM>();
			for (Produto produto : listProduto) {
				listProdutoORM.add(converterHttpParaOrm(produto));
			}
			return listProdutoORM;
		}
		
		public static ProdutoORM converterHttpParaOrm(Produto produtoHttp) {
			ProdutoORM produto = new ProdutoORM();
			produto.setId(produtoHttp.getId());
			produto.setDescricao(produtoHttp.getDescricao());
			produto.setDataCriacao(produtoHttp.getDataCriacao());
			produto.setPreco(produtoHttp.getPreco());
			
			return produto;
		}

}
