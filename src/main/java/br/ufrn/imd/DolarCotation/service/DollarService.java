package br.ufrn.imd.DolarCotation.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

	public static final String BASE_URL = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/";
	public static final String FINAL_URL = "'&$top=100&$format=json";
	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public Dolar getDolarDia(int dia, int mes, int ano, String moeda) {
		JSONObject apiJSON = requestAPI(String.valueOf(dia), String.valueOf(mes + 1), String.valueOf(ano));
		Dolar dolar = new Dolar();
		// apiJSON.getJSONObject("value").getJSONArray("0").getDouble("cotacaoCompra");
		System.out.println(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoCompra"));
		System.out.println(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoVenda"));
		dolar.setMoeda(moeda);
		dolar.setCotacaoCompra(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoCompra"));
		dolar.setCotacaoVenda(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoVenda"));
		dolar.setDataHoraCotacao(apiJSON.getJSONArray("value").getJSONObject(0).getString(("dataHoraCotacao")));
		return dolar;
	}
	
	public Dolar getDolarDiaV2(int dia, int mes, int ano, String moeda) {
		JSONObject apiJSON = requestAPI(String.valueOf(dia), String.valueOf(mes), String.valueOf(ano));
		Dolar dolar = new Dolar();
		// apiJSON.getJSONObject("value").getJSONArray("0").getDouble("cotacaoCompra");
		System.out.println(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoCompra"));
		System.out.println(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoVenda"));
		dolar.setMoeda(moeda);
		dolar.setCotacaoCompra(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoCompra"));
		dolar.setCotacaoVenda(apiJSON.getJSONArray("value").getJSONObject(0).getDouble("cotacaoVenda"));
		dolar.setDataHoraCotacao(apiJSON.getJSONArray("value").getJSONObject(0).getString(("dataHoraCotacao")));
		return dolar;
	}

	public Dolar getMoedaFechamentoDoDia(String moeda) {
		Dolar dolar = new Dolar();

		JSONObject jsonObj = getByMoedaCode(moeda);
		dolar.setMoeda(moeda);

		dolar.setMoeda(moeda);
		dolar.setCotacaoCompra(jsonObj.getJSONArray("value").getJSONObject(4).getDouble("cotacaoCompra"));
		dolar.setCotacaoVenda(jsonObj.getJSONArray("value").getJSONObject(4).getDouble("cotacaoVenda"));
		dolar.setDataHoraCotacao(jsonObj.getJSONArray("value").getJSONObject(4).getString("dataHoraCotacao"));

		return dolar;

	}

	public JSONObject getByMoedaCode(String moeda) {
		
		Calendar dia = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		int mes = dia.get(Calendar.MONTH) + 1;
		System.out.println(mes);
		HttpGet get = new HttpGet(BASE_URL + "CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?@moeda='" + moeda
				+ "'&@dataCotacao='" + mes + "-" + dia.get(Calendar.DAY_OF_MONTH) + "-"
				+ dia.get(Calendar.YEAR) + FINAL_URL);
		System.out.println(get.toString());
		try {
			CloseableHttpResponse response = httpClient.execute(get);

			org.apache.http.HttpEntity entity = response.getEntity();

			JSONObject jsonMoeda = new JSONObject(EntityUtils.toString(entity));
			return jsonMoeda;
		} catch (ClientProtocolException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private JSONObject requestAPI(String dia, String mes, String ano) {
		HttpGet get = new HttpGet(BASE_URL + "CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='" + mes + "-"
				+ dia + "-" + ano + FINAL_URL);
		System.out.println(get.toString());
		try {
			CloseableHttpResponse response = httpClient.execute(get);

			org.apache.http.HttpEntity entity = response.getEntity();

			JSONObject moeda = new JSONObject(EntityUtils.toString(entity));
			return moeda;
		} catch (ClientProtocolException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
