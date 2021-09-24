package pages;

import static config.Driver.getDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;
import beans.ValuesHelper;

public class OrderPage extends BasePage {
	public OrderPage() {
        PageFactory.initElements(getDriver(), this);
    }
	
	Evidence evidencia = new Evidence();
	
	static ValuesHelper values = new ValuesHelper();
	

    @FindBy(id = "total_product")
    private WebElement lblTotalProdutos;

    @FindBy(id = "total_price")
    private WebElement lblValorTotal;

    @FindBy(id = "total_shipping")
    private WebElement lblValorFrete;

    @FindBy(xpath = "(//span[contains(text(), 'Proceed to checkout')])[2]")
    private WebElement btnProcederCheckout;

    @FindBy(xpath = "//h3[text() = 'Your delivery address']")
    private WebElement titEnderecoEntrega;

    @FindBy(xpath = "//ul[@id='address_delivery']//li")
    private List<WebElement> listaEnderecoEntrega;

    @FindBy(id = "id_address_delivery")
    private WebElement selectEndereco;

    @FindBy(xpath = "//p[@class='carrier_title' and contains(text(), 'address')]")
    private WebElement tituloTransporte;

    @FindBy(id = "cgv")
    private WebElement checkboxTermosEntrega;

    @FindBy(xpath = "//div[@class = 'delivery_option_price']")
    private WebElement valorEntregaShipping;

    @FindBy(className = "page-heading")
    private WebElement tituloPedidoPage;

    @FindBy(xpath = "//div[@class='box cheque-box']//p/span[@class='price']")
    private WebElement spanValorTotalConfirmacaoPedido;

    @FindBy(xpath = "//span[text()='I confirm my order']/../../button")
    private WebElement buttonConfirmarPedido;

    @FindBy(className = "cheque-indent")
    private WebElement textoCompraComSucessoTransferencia;

    @FindBy(xpath = "//p[contains(text(), 'Your order on My Store is complete.')]")
    private WebElement textoCompraComSucessoCheque;

    // >>>> Variaveis globais <<<<

    private static String metodoPagamento = "";

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void validarValoresPedido() throws DocumentException {
    	System.out.println("======\n Validando valores do pedido \n======");
    	evidencia.evidenciaPorPagina("Validação do valores do Pedido ");
        assertEquals(values.getValorProduto(), lblTotalProdutos.getText().replace("$", ""));
        assertEquals(values.getValorTotal(), lblValorTotal.getText().replace("$", "").replace(".", ","));
    }

    public boolean validadorEnderecoEntrega() {
        List<String> enderecoEntregaEsperado = new ArrayList<String>();
        int cont = 0;

        enderecoEntregaEsperado.add("YOUR DELIVERY ADDRESS");
        enderecoEntregaEsperado.add("Helen Nascimento");
        enderecoEntregaEsperado.add("PRIMARY 445");
        enderecoEntregaEsperado.add("Texas, Texas 75201");
        enderecoEntregaEsperado.add("United States");
        enderecoEntregaEsperado.add("(11)985898899");
        enderecoEntregaEsperado.add("(11)985999899");

        for (int i = 0; i < listaEnderecoEntrega.size() - 1; i++) {
            if (listaEnderecoEntrega.get(i).getText().equals(enderecoEntregaEsperado.get(i))) {
                cont++;
            }
        }
        boolean validador = (cont == 7) ? true : false;
        return validador;
    }

    public void clicarProcederCheckout() throws DocumentException {
    	System.out.println("======\n Checkout \n======");
    	evidencia.evidenciaPorPagina("Checkout ");
        scrollDown();
        btnProcederCheckout.click();
    }

    public void validarEnderecoEntrega() {
        assertTrue(validadorEnderecoEntrega());
    }

    public void passoValidarCarrinho() throws DocumentException {
    	System.out.println("======\n Validação do carrinho \n======");
    	evidencia.evidenciaPorPagina("Validação do Carrinho ");
        validarValoresPedido();
    }

    public void passoValidarEndereco() throws DocumentException {
    	System.out.println("======\n Validação do Endereço \n======");
    	evidencia.evidenciaPorPagina("Validação do endereço ");
        validarEnderecoEntrega();
    }

    public void selecionarEndereco() throws DocumentException {
        selecionarPrimeiro(selectEndereco);
        evidencia.evidenciaPorPagina("Seleção de endereço ");
        WebElement enderecoSelecionado = selectEndereco.findElement(By.xpath("//option[@selected='selected']"));
        values.setNomeEnderecoEntrega(enderecoSelecionado.getText());
        System.out.println("======\n Seleção de endereço: "+ enderecoSelecionado + "\n======");
    }

    public void validarEntrega() throws DocumentException {
    	System.out.println("======\n Validação de Entrega \n======");
        assertEquals("desafios", tituloTransporte.getText().substring(43, 51));
        values.setValorFrete(valorEntregaShipping.getText().trim().replace("$", ""));
        evidencia.evidenciaPorPagina("Validando Entrega");
        aceitarTermosEnvio();
        
        
    }

    public void aceitarTermosEnvio() throws DocumentException {
    	evidencia.evidenciaPorPagina("Termos de aceite");
        checkboxTermosEntrega.click();
    }

    public void validarPagamento() {
        assertEquals(values.getValorProduto(), lblTotalProdutos.getText().trim().replace("$", ""));
        assertEquals(values.getValorFrete(), lblValorFrete.getText().trim().replace("$", ""));
        assertEquals(values.somarValorTotal(values.getValorProduto(), values.getValorFrete()), Float.parseFloat(lblValorTotal.getText().trim().replace("$", "")), 0.0f);
    }

    public void escolherMetodoDePagamento(String pagamento) {
        metodoPagamento = pagamento;

        System.out.println("PAGAMENTO SALVO: " + metodoPagamento);

        System.out.println("======\n Entrando na escolha de metodo de pagamento \n======");
        WebElement elemento = null;

        if (pagamento.equals("bankwire")) {
            System.out.println("======\n Escolhido transferencia bancaria \n======");
            elemento = getDriver().findElement(By.xpath("//a[@class='" + pagamento + "']"));
            elemento.click();
        } else {
            System.out.println("======\n Escolhido cheque \n======");
            elemento = getDriver().findElement(By.xpath("//a[@class='" + pagamento + "']"));
            elemento.click();
        }
    }


    public void confirmarPedido() throws DocumentException {
        Assert.assertEquals("ORDER SUMMARY".trim(), tituloPedidoPage.getText().trim());
        Assert.assertEquals(String.valueOf(values.getValorTotal().replace(",", ".")), spanValorTotalConfirmacaoPedido.getText().replace("$", "").trim());
        evidencia.evidenciaPorPagina("Confirmação do pedido");
        System.out.println("======\n Confirmação do Pedido \n======");
        buttonConfirmarPedido.click();
    }

    public void confirmarFinalizacaoDaCompra() throws DocumentException {
    	evidencia.evidenciaPorPagina("Finalização da Compra");
        Assert.assertEquals("ORDER CONFIRMATION".trim(), tituloPedidoPage.getText().trim());

        if (metodoPagamento.equals("bankwire")) {
            Assert.assertEquals("Your order on My Store is complete.".trim(), textoCompraComSucessoTransferencia.getText().trim());
        } else {
            Assert.assertEquals("Your order on My Store is complete.".trim(), textoCompraComSucessoCheque.getText().trim());
        }
        System.out.println("======\n Finalização da compra com Sucesso \n======");

    }

}
