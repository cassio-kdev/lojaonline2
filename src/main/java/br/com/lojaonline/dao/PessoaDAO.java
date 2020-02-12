package br.com.lojaonline.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.lojaonline.model.Endereco;
import br.com.lojaonline.model.Pessoa;
import br.com.lojaonline.util.JpaFactory;

public class PessoaDAO {

	private EntityManager em;
	private boolean sucesso;

	public PessoaDAO() {
		em = JpaFactory.getEntityManager();
	}

	public boolean salvaOuAtualiza(Pessoa pessoa) throws Exception {
		sucesso = false;
		try {
			if (pessoa.getIdPessoa() == null) {
				em.getTransaction().begin();
				em.persist(pessoa);
			} else {
				em.merge(pessoa);
			}
			em.getTransaction().commit();

			sucesso = true;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
		return sucesso;
	}

	public boolean deletePessoa(Long idPessoa) throws Exception {
		sucesso = false;
		try {
			em.getTransaction().begin();
			em.remove(idPessoa);
			em.getTransaction().commit();
			sucesso = true;
		} catch (Exception ex) {
			throw ex;
		}
		return sucesso;
	}

	public Pessoa buscaPorId(Long id) throws Exception {
		try {
			return em.find(Pessoa.class, id);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public List<Pessoa> lista(){
		return em.createQuery("from Pessoa order by nome").getResultList();
	}
	
	public Endereco buscaEnderecoPorId(Long id){
		String hql = "from Endereco  where pessoa =  :id ";
		Endereco end = (Endereco) em.createQuery(hql, Endereco.class).setParameter("id", id).getSingleResult();
		return end;
	}
}
