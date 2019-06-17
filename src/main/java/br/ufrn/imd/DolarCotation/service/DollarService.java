package br.ufrn.imd.DolarCotation.service;

import java.io.IOException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import br.ufrn.imd.DolarCotation.domain.Dolar;
import springfox.documentation.spring.web.json.Json;

@Service
public class DollarService {
	

	
	public static final String BASE_URL = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='";
	public static final String FINAL_URL = "'&$top=100&$format=json";
	static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public Dolar getDolarDia(int dia, int mes, int ano) {
		JSONObject apiJSON = requestAPI(String.valueOf(dia), String.valueOf(mes), String.valueOf(ano));
		Dolar dolar = new Dolar();
		dolar.setCotacaoCompra(apiJSON.getString("cotacaoCompra"));
		dolar.setCotacaoVenda(apiJSON.getString("cotacaoVenda"));
		return dolar;
	}
	
	private JSONObject requestAPI(String dia, String mes, String ano) {
		HttpGet get = new HttpGet(BASE_URL + mes + "-" + dia + "-" + ano + FINAL_URL);
		
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			
			org.apache.http.HttpEntity entity = response.getEntity();
			
			JSONObject moeda = new JSONObject(EntityUtils.toString(entity));
			return moeda;
		} catch (ClientProtocolException e ) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
