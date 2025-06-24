public class Main {
    public static void main(String[] args) {
        Produto p1 = new Produto("Leite Condensado Moça",  8.98, "mocateste.png");

        Pagina pg1 = new Pagina();
        pg1.adicionarProduto(p1);

        p1.adicionarAvaliacao("Gabriel", "Muito bom!", 4.3);

        p1.ativarPromocao(5.99);
        pg1.salvarProdutos();

        p1.mostrarImagem();
    }

}