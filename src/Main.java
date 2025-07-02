public class Main {
    public static void main(String[] args) {

        GerenciadorPaginas gerenciador = new GerenciadorPaginas();

        Produto p1 = new Produto("Leite Condensado Piracanjuba", 6.47, "src/imagens/piracanjuba.png", 4674, 5);
        Produto p2 = new Produto("Leite Condensado Frimesa", 6.79, "src/imagens/frimesa.png", 1418, 4.76);
        Produto p3 = new Produto("Leite Condensado Italac", 6.65, "src/imagens/italac.png", 462, 2.98);
        Produto p4 = new Produto("Leite Condensado Camponesa", 8.49, "src/imagens/camponesa.png", 27, 4.4);
        Produto p5 = new Produto("Leite Condensado Moça", 8.98, "src/imagens/nestle.png", 148, 4.7, 4.98);
        Produto p6 = new Produto("Leite Condensado São Lourenço", 36.00, "src/imagens/saolourenco.png", 967, 2.3,29.99);
        Produto p7 = new Produto("Leite Condensado Itambé - 395 gramas - Consistente e saboroso, ótimo para pudim, brigadeiro e outras sobremesas", 7.89, "src/imagens/itambe.png", 0, 0);

        gerenciador.inserirProduto(p1);
        gerenciador.inserirProduto(p2);
        gerenciador.inserirProduto(p3);
        gerenciador.inserirProduto(p4);
        gerenciador.inserirProduto(p5);
        gerenciador.inserirProduto(p6);
        gerenciador.inserirProduto(p7);

        for(int i = 0; i < gerenciador.getNumPaginas(); i++)
            gerenciador.getPagina(i).mostrarImagens();

        gerenciador.limparPaginas();
        gerenciador.salvarPaginas();
    }
}
