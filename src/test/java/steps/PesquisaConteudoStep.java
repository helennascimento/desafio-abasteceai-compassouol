package steps;

import com.itextpdf.text.DocumentException;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.HomePage;
import pages.PesquisaPage;

public class PesquisaConteudoStep {
	
	HomePage home = new HomePage();
	PesquisaPage pesquisar = new PesquisaPage();
	
	@Dado("que estou na pagina de compra")
	public void que_estou_na_pagina_de_compra() {
		home.acessarUrl();
		home.validarAcesso();
	   
	}
	
	
	@Quando("desejo fazer uma pesquisa pelo produto {string}")
	public void desejo_fazer_uma_pesquisa_pelo_produto(String procuraDeProduto) throws DocumentException {
		home.pesquisarProduto(procuraDeProduto);
		home.botaoBuscar();
	    
	}

	@Entao("a pesquisa e feito com sucesso")
	public void a_pesquisa_e_feito_com_sucesso() throws DocumentException {
		pesquisar.validacaoDePesquisa("Faded Short Sleeve T-shirts");
	    
	}



}
