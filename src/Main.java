import java.io.File;

public class Main {
    static int numProdutos = 0, numPaginas = 0;
    static final int MAX_PAGINAS = 10;
    static Pagina[] paginas = new Pagina[MAX_PAGINAS];

    public static void main(String[] args) {

        Produto p1 = new Produto("Leite Condensado Piracanjuba", 6.47, "src/imagens/piracanjuba.png", 4674, 5);
        Produto p2 = new Produto("Leite Condensado Frimesa", 6.79, "src/imagens/frimesa.png", 1418, 4.76);
        Produto p3 = new Produto("Leite Condensado Italac", 6.65, "src/imagens/italac.png", 462, 2.98);
        Produto p4 = new Produto("Leite Condensado Camponesa", 8.49, "src/imagens/camponesa.png", 27, 4.4);
        Produto p5 = new Produto("Leite Condensado Moça", 8.98, "src/imagens/nestle.png", 148, 4.7, 4.98);
        Produto p6 = new Produto("Leite Condensado São Lourenço", 36.00, "src/imagens/saolourenco.png", 967, 2.3,29.99);
        Produto p7 = new Produto("Leite Condensado Itambé", 7.89, "src/imagens/itambe.png", 0, 0);

        inserirProduto(p1);
        inserirProduto(p2);
        inserirProduto(p3);
        inserirProduto(p4);
        inserirProduto(p5);
        inserirProduto(p6);
        inserirProduto(p7);

        for(int i = 0; i < numPaginas; i++)
            paginas[i].mostrarImagens();

        limparPaginas();
        salvarPaginas();
    }

    public static void inserirProduto(Produto p){
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

    private static void limparPaginas(){
        File f;

        if(numPaginas == 0)
            System.out.println("Ainda não há páginas!");
        else
            for(int i = 1; i <= numPaginas; i++){
                f = new File(String.format("pagina" + "%02d" + ".txt", i));
                f.delete();
            }

    }

    private static boolean criarPagina(){
        if(numPaginas >= MAX_PAGINAS)
            return false;

        paginas[numPaginas++] = new Pagina();
        return true;
    }
}