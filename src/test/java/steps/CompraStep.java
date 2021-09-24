package steps;

import com.itextpdf.text.DocumentException;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.CompraPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MinhaContaPage;
import pages.OrderPage;

public class CompraStep {
	
	HomePage home = new HomePage();
	LoginPage login = new LoginPage();
	MinhaContaPage minhaConta = new MinhaContaPage();
	CompraPage compra = new CompraPage();
	OrderPage order = new OrderPage();
	
	
	
	@Dado("que esteja logado na pagina inicial")
	public void que_esteja_logado_na_pagina_inicial() throws DocumentException, InterruptedException {
		home.acessarUrl();
		home.validarAcesso();
		home.acessarLogin();
		login.validarSignInPage();
		login.loginComDadosValidos("desafioautomacao@gmail.com", "1234567");
		minhaConta.validarMensagemWelcome();
		home.voltarParaHome();
		
	}

	@E("adicione {string} ao carrinho")
	public void adicione_ao_carrinho(String nomeDoProduto) throws Exception {
		home.clickBtnAddToCart(nomeDoProduto);
		compra.validarInformacoesProduto(nomeDoProduto);
		compra.inserirInformacoesDeCompra("1", "M");
		compra.validarProdutoAdicionadoAoCarrinho(nomeDoProduto);
		
	}

	@Quando("prosseguir com a compra ate o checkout")
	public void prosseguir_com_a_compra_ate_o_checkout() throws DocumentException {
		order.passoValidarCarrinho();
		order.clicarProcederCheckout();
		order.passoValidarEndereco();
		order.clicarProcederCheckout();
		order.validarEntrega();
		order.clicarProcederCheckout();
	    
	}

	@Entao("a compra e realizada com sucesso")
	public void a_compra_e_realizada_com_sucesso() throws DocumentException {
		order.validarPagamento();
		order.escolherMetodoDePagamento("bankwire");
		order.confirmarPedido();
		order.confirmarFinalizacaoDaCompra();
		
	    
	}


}
