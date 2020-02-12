package br.com.lojaonline.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.lojaonline.dao.PessoaDAO;
import br.com.lojaonline.model.Endereco;
import br.com.lojaonline.model.Pessoa;
import br.com.lojaonline.service.EnderecoService;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private PessoaDAO dao = new PessoaDAO();
	private Pessoa pessoaSelected;
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	private Endereco end;
	
	public void salva(Pessoa pessoa) {
		try {
			boolean sucesso = dao.salvaOuAtualiza(pessoa);
			System.out.println(sucesso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void instanciaPessoa() {
		pessoaSelected = new Pessoa();
	}
	
	public Endereco consultaCep(String cep) {
		String cepFormatado = cep.replaceAll("\\D", "");
		return EnderecoService.consultaCep(cepFormatado);
		
	}
	
	@PostConstruct
	public void pessoas() {
		listaPessoas = dao.lista();
	}

	public List<Pessoa> getListaPessoas() {

		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public PessoaDAO getDao() {
		return dao;
	}

	public void setDao(PessoaDAO dao) {
		this.dao = dao;
	}

	public Pessoa getPessoaSelected() {
		return pessoaSelected;
	}

	public void setPessoaSelected(Pessoa pessoaSelected) {
		this.pessoaSelected = pessoaSelected;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}
}
