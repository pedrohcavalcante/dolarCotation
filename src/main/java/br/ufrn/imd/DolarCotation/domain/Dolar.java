package br.ufrn.imd.DolarCotation.domain;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Classe de domínio referente a moeda utilizada pelas consultas
 * @author pedrohbcavalcante
 *
 */
public class Dolar {
	
	private String moeda;
	private Double cotacaoCompra;
	private Double cotacaoVenda;
	private String dataHoraCotacao;
	
	public String getMoeda() {
		return moeda;
	}
	public Double getCotacaoCompra() {
		return cotacaoCompra;
	}
	public Double getCotacaoVenda() {
		return cotacaoVenda;
	}
	public void setMoeda(String dollar) {
		this.moeda = dollar;
	}
	public void setCotacaoCompra(Double cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}
	public void setCotacaoVenda(Double cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}
	public String getDataHoraCotacao() {
		return dataHoraCotacao;
	}
	public void setDataHoraCotacao(String dataHoraCotacao) {
		this.dataHoraCotacao = dataHoraCotacao;
	}
	
	

	
	

}
