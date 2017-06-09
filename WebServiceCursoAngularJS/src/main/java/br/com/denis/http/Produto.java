package br.com.denis.http;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
	private Long id;
	
	private String descricao;

	private Date dataCriacao;
	
	private String preco;

}
