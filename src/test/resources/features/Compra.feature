#language: pt
Funcionalidade: Realizar uma compra
  Como potencial ou atual cliente da loja
  Quero realizar uma compra
  Para ficar no estilo

  @AC-002
  Cenario: Comprar uma peca logado
    Dado que esteja logado na pagina inicial
    E adicione "Blouse" ao carrinho
    Quando prosseguir com a compra ate o checkout
    Entao a compra e realizada com sucesso