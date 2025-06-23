public class Main {
    public static void main(String[] args) {
        Produto p1 = new Produto("Leite Condensado Piracanjuba",  6.47, "piracanjuba.png");
        Produto p2 = new Produto("Leite Condensado Moça",  8.98, "piracanjuba.png");

        Pagina pg1 = new Pagina();
        pg1.adicionarProduto(p1);
        pg1.adicionarProduto(p2);

        p1.adicionarAvaliacao("Gabriel", "Muito bom!!", 5);
        p2.adicionarAvaliacao("Letícia", "Melhor ainda!!!", 10);

        pg1.salvarProdutos();
    }

}