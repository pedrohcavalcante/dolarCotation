package br.ufrn.imd.DolarCotation.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.DolarCotation.domain.Dolar;
import br.ufrn.imd.DolarCotation.service.DollarService;

@RestController
@RequestMapping("/cotacao")
public class DollarRequest {

	@Autowired
	private DollarService dollarService;
	
	@GetMapping("/dolar")
	public Dolar getCotacaoDolar() {
		Dolar dolar = new Dolar();
		Calendar dia = Calendar.getInstance();
		
		return dollarService.getDolarDia(dia.get(Calendar.DAY_OF_MONTH), dia.get(Calendar.MONTH), dia.get(Calendar.YEAR), "USD");
	}
	
	@GetMapping("{moeda}")
	public Dolar getCotacaoMoeda(@PathVariable String moeda) {
		return dollarService.getMoedaFechamentoDoDia(moeda);
	}
	
	@GetMapping("{dia}/{mes}/{ano}")
	public Dolar getCotacaoDia(@PathVariable String dia, @PathVariable String mes, @PathVariable String ano) {
		return null;
	}
}
