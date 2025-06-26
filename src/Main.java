import java.util.Scanner;

public class Main {
    static int numProdutos = 0, numPaginas = 0;
    static final int MAX_PAGINAS = 10;
    static Pagina[] paginas = new Pagina[MAX_PAGINAS];

    public static void main(String[] args) {

        Produto p1 = new Produto("Leite Condensado Piracanjuba", 6.47, "src/imagens/piracanjuba.png");
        Produto p2 = new Produto("Leite Condensado Frimesa", 6.79, "src/imagens/frimesa.png");
        Produto p3 = new Produto("Leite Condensado Italac", 6.65, "src/imagens/italac.png");
        Produto p4 = new Produto("Leite Condensado Camponesa", 8.49, "src/imagens/camponesa.png");
        Produto p5 = new Produto("Leite Condensado Moça", 8.98, "src/imagens/nestle.png");
        Produto p6 = new Produto("Leite Condensado São Lourenço", 36.00, "src/imagens/saolourenco.png");
        Produto p7 = new Produto("Leite Condensado Itambé", 7.89, "src/imagens/itambe.png");


        p1.adicionarAvaliacao("José da Silva", "Muito doce, adorei", 5);
        p1.adicionarAvaliacao("Mário", "Muito doce, odiei", 1);
        p1.adicionarAvaliacao("Luigi", "Meu pudim ficou torto, mas estava bom", 4);

        p2.adicionarAvaliacao("José da Silva", "Piracanjuba é melhor...", 3);

        p3.adicionarAvaliacao("Otávio Inácio", "Muito enjoativo, não gostei", 1);

        p4.ativarPromocao(4.49);
        p4.adicionarAvaliacao("Ash Ketchum", "Comprei na promoção e não me arrependo", 5);

        p6.adicionarAvaliacao("Anônimo", "Por esse preço, era de se esperar que mandassem o pudim", 3);

        p7.adicionarAvaliacao("Davi Leal", "Leite incomparável!", 5);

        adicionarProduto(p1);
        adicionarProduto(p2);
        adicionarProduto(p3);
        adicionarProduto(p4);
        adicionarProduto(p5);
        adicionarProduto(p6);
        adicionarProduto(p7);

        salvarPaginas();
    }

    public static void adicionarProduto(Produto p){
        if(numProdutos >= numPaginas * Pagina.getMaximoProdutos() && !criarPagina())
            System.out.println("Máximo de produtos atingido!");
        else
            try {
                paginas[numPaginas - 1].adicionarProduto(p);
                numProdutos++;
            }catch (IllegalArgumentException ae){
                System.out.println("Produto inválido: " + ae.getMessage());
            }
    }

    private static void salvarPaginas(){
        if(numPaginas == 0)
            System.out.println("Ainda não há páginas!");
        else
            for(int i = 0; i < numPaginas; i++)
                paginas[i].salvarProdutos();
    }

    private static boolean criarPagina(){
        if(numPaginas >= MAX_PAGINAS)
            return false;

        paginas[numPaginas++] = new Pagina();
        return true;
    }
}