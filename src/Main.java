import java.util.Scanner;

public class Main {
    static int numProdutos = 0, numPaginas = 0;
    static final int MAX_PAGINAS = 10;
    static Pagina[] paginas = new Pagina[MAX_PAGINAS];

    public static void main(String[] args) {

        adicionarProduto("1Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("2Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("3Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("4Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("5Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("6Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");
        adicionarProduto("7Leite Condensado Piracanjuba 395g", 6.47, "src/imagens/piracanjuba.png");

        Produto p = new Produto("teste", 1.0, "src/imagens/itambe.png");
        p.salvarInformacoes("pagina2");

        //mostrarImagem(1);

        salvarPaginas();


    }

    public static void adicionarProduto(String d, double p, String i){
        if(numProdutos >= numPaginas * Pagina.getMaximoProdutos() && !criarPagina())
            System.out.println("Máximo de produtos atingido!");
        else
            try {
                paginas[numPaginas - 1].adicionarProduto(new Produto(d, p, i));
                numProdutos++;
            }catch (IllegalArgumentException ae){
                System.out.println("Produto inválido: " + ae.getMessage());
            }
    }

    private static void alterarPreco(){
        double preco;
        int pagina, produto;

        if(numPaginas == 0) {
            System.out.println("Ainda não há produtos.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        if(numPaginas > 1)
            System.out.printf("Página do produto (disponíveis: 01 ~ %02d): ", numPaginas);
        else
            System.out.printf("Página do produto (disponível: 01): ");
        pagina = scanner.nextInt();

        if(pagina <  1 || pagina > numPaginas) {
            System.out.println("Indice de página inválido!");
            return;
        }

        if(paginas[pagina - 1].getNumProdutos() == 0) {
            System.out.println("Essa página está vazia.");
            return;
        }

        if(paginas[pagina - 1].getNumProdutos() > 1)
            System.out.printf("Indice do produto (disponíveis: 01 ~ %02d): ", paginas[pagina - 1].getNumProdutos());
        else
            System.out.printf("Indice do produto (disponível: 01): ");
        produto = scanner.nextInt();

        if(produto < 1 || produto > paginas[pagina - 1].getNumProdutos()){
            System.out.println("Indice de produto inválido!");
            return;
        }

        if(paginas[pagina - 1].getPromocaoProduto(produto - 1)) {
            scanner.nextLine();
            System.out.println("Produto em promoção, deseja encerrá-la? (s/n) ");
            char input = scanner.nextLine().charAt(0);

            if(input == 's')
                paginas[pagina-1].desativarPromocaoProduto(produto - 1);
        }else{
            scanner.nextLine();
            System.out.println("Iniciar uma promoção? (s/n) ");
            char input = scanner.nextLine().charAt(0);

            if(input == 's')
                paginas[pagina-1].ativarPromocaoProduto(produto - 1);
        }

        try{
            System.out.println("Novo preço: ");
            preco = scanner.nextDouble();
            paginas[pagina - 1].alterarPrecoProduto(produto - 1, preco);

            System.out.println("\nAlterado com sucesso!");
        }catch (IllegalArgumentException ae){
            System.out.println("Erro! " + ae.getMessage());
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