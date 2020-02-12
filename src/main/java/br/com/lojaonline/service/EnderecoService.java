package br.com.lojaonline.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.lojaonline.model.Endereco;

public class EnderecoService {

	public static Endereco consultaCep(String cep) {
		String param = String.format("%s/json", cep);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://viacep.com.br/ws/").path(param);
		Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);

		Response response = builder.get();

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			throw new RuntimeException("Erro ao consultar o CEP");
		}
		return response.readEntity(Endereco.class);

	}
}
