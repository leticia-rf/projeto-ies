import java.io.File;

public class GerenciadorPaginas {
    private static int numProdutos, numPaginas;
    private static final int MAX_PAGINAS = 10;
    private static Pagina[] paginas;

    public GerenciadorPaginas(){
        this.numProdutos = 0;
        this.numPaginas = 0;
        this.paginas = new Pagina[MAX_PAGINAS];
    }

    public Pagina getPagina(int indice) {
        if(indice >= 0 && indice < numPaginas){
            return paginas[indice];
        }
        return null;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void inserirProduto(Produto p){
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

    private static boolean criarPagina(){
        if(numPaginas >= MAX_PAGINAS)
            return false;

        paginas[numPaginas++] = new Pagina();
        return true;
    }

    public void salvarPaginas(){
        if(numPaginas == 0)
            System.out.println("Ainda não há páginas!");
        else
            for(int i = 0; i < numPaginas; i++)
                paginas[i].salvarProdutos();
    }

    public void limparPaginas(){
        File f;

        if(numPaginas == 0)
            System.out.println("Ainda não há páginas!");
        else
            for(int i = 1; i <= numPaginas; i++){
                f = new File(String.format("pagina" + "%02d" + ".txt", i));
                f.delete();
            }

    }
}