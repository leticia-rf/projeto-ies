import java.util.Scanner;

public class Main {
    static int numProdutos = 0, numPaginas = 0;
    static final int MAX_PAGINAS = 10;
    static Pagina[] paginas = new Pagina[MAX_PAGINAS];

    public static void main(String[] args) {
        int input;

        imprimirMenu();
        do{
            input = lerOpcao();

            switch(input){
                case 01:
                    adicionarProduto();
                    break;

                case 02:
                    alterarPreco();
                    break;

                case 03:
                    salvarPagina();
                    break;

                case 04:
                    System.out.println("\nfinalizando...");
                    break;

                default:
                    System.out.println("opção inválida!\n");
                    break;
            }
        }while(input != 4);
    }

    private static void imprimirMenu(){
        System.out.println("Menu:\n");

        System.out.println("1. Adicionar produto;");
        System.out.println("2. Alterar preço;");
        System.out.println("3. Salvar página;");
        System.out.println("4. Finalizar;");

        System.out.println();
    }

    private static int lerOpcao(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        return scanner.nextInt();
    }

    public static void adicionarProduto(){
        if(numProdutos >= numPaginas * Pagina.getMaximoProdutos() && !criarPagina())
            System.out.println("Máximo de produtos atingido!");
        else
            adicionarProdutoPrivado();
    }

    private static void alterarPreco(){
    }

    private static void salvarPagina(){
    }

    private static boolean criarPagina(){
        if(numPaginas >= MAX_PAGINAS)
            return false;

        paginas[numPaginas++] = new Pagina();
        return true;
    }

    private static void adicionarProdutoPrivado(){
        Scanner scanner = new Scanner(System.in);
        String descricao, imagem;
        double preco;

        System.out.println("Descrição do produto: ");
        descricao = scanner.nextLine();

        System.out.println("Imagem do produto: ");
        imagem = scanner.nextLine();

        System.out.println("Preço: ");
        preco = scanner.nextDouble();

        try {
            paginas[numPaginas - 1].adicionarProduto(new Produto(descricao, preco, imagem));
        }catch (IllegalArgumentException ae){
            System.out.println("Produto inválido: " + ae.getMessage());
        }
    }
}