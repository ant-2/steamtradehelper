package eu.mosov.steamTradeHelper.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class RequestSender {
	static Client client;
	Response response;

	public RequestSender() {
		client = ClientBuilder.newClient();
	}

	public void sendRequest() {
		response = client.target("http://127.0.0.1:8080/rest/hello").request("text/plain").get();
		System.out.println(response);
	}
}
