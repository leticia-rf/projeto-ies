import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pagina {
    private int paginaID;
    private static int proximoID = 0;
    private static final int MAX_PRODUTOS = 5;
    private int numProdutos;
    private Produto[] produtos;

    public Pagina(){
        numProdutos = 0;
        paginaID = proximoID++;
        produtos = new Produto[MAX_PRODUTOS];
    }

    public void salvarProdutos(){
        if(numProdutos == 0)
            return;

        for(int i = 0; i < numProdutos; i++)
            produtos[i].salvarInformacoes(String.format("pagina%02d.txt", paginaID));

        try {
            File f = new File(String.format("pagina%02d.txt", paginaID));
            FileWriter fw = new FileWriter(f, true);
            fw.write("___________________________");
            fw.close();
        }catch(IOException ioe){
            System.out.println(ioe.toString());
        }

    }

    public boolean adicionarProduto(Produto p){
        if(numProdutos >= MAX_PRODUTOS)
            return false;

        produtos[numProdutos++] = p;
        return true;
    }

    public int getNumProdutos(){
        return numProdutos;
    }

    public static int getMaximoProdutos(){
        return MAX_PRODUTOS;
    }

    public void alterarPrecoProduto(int indice, double preco){
        if(indice < 0 || indice >= numProdutos)
            throw new IllegalArgumentException("Indice de produto inválido.");

        produtos[indice].setPreco(preco);
    }

    public boolean getPromocaoProduto(int indice){
        if(indice < 0 || indice >= numProdutos)
            throw new IllegalArgumentException("Indice de produto inválido.");

        return produtos[indice].getPromocao();
    }

    public void ativarPromocaoProduto(int indice){
        if(indice < 0 || indice >= numProdutos)
            throw new IllegalArgumentException("Indice de produto inválido.");

        produtos[indice].ativarPromocao();
    }

    public void desativarPromocaoProduto(int indice){
        if(indice < 0 || indice >= numProdutos)
            throw new IllegalArgumentException("Indice de produto inválido.");

        produtos[indice].desativarPromocao();
    }
}