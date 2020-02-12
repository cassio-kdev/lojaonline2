package br.com.lojaonline.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lojaonline.model.Endereco;
import br.com.lojaonline.model.Pessoa;
import br.com.lojaonline.service.EnderecoService;

public class TestePessoaDAO {
	private Pessoa pessoa;
	private List<Endereco> enderecos;
	private PessoaDAO dao = new PessoaDAO();

	@Before
	public void setUp() {
		pessoa = new Pessoa("Santana", "B",enderecos);
		enderecos = Arrays.asList(new Endereco("74005699", "Rua", "Centro", "Brasilia", "DF", pessoa));
		pessoa.getEndereco().addAll(enderecos);
	}
	@After
	public void limpa() {
		pessoa = null;
		enderecos = null;
	}
	
	@Test
	public void salvaOuAtualiza() throws Exception {
		boolean sucesso = dao.salvaOuAtualiza(pessoa);
		assertTrue(sucesso);
	}
	
	@Test
	public void testeListaPessoa() {
		List<Pessoa> lis = dao.lista();
		assertNotNull(lis);
	}
	
	@Test
	public void testeCep() {
		try {
		Endereco end = EnderecoService.consultaCep("745534809");
		System.out.println(end);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	@Test
	public void limpaString() {
		String str = "75-5-5-a".replaceAll("\\D", "");
		System.out.println(str);
	}
}
