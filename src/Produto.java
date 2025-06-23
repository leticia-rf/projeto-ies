import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Produto {
    private boolean promocao;
    private static final int MAX_AVALIACOES = 10;
    private int produtoID;
    private static int proximoID = 0;
    private String descricao;
    private Avaliacao[] avaliacoes;
    private int qntdAvaliacoes;
    private double preco;
    private double precoPromocional;
    private BufferedImage imagem;

    public Produto(String descricao, double preco, String nomeImagem) {
        produtoID = proximoID++;
        setDescricao(descricao);
        setPreco(preco);
        imagem = carregarImagem(nomeImagem);
        avaliacoes = new Avaliacao[MAX_AVALIACOES];
        qntdAvaliacoes = 0;
        precoPromocional = 0;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQntdAvaliacoes() {
        return qntdAvaliacoes;
    }

    public double getPreco() {
        return preco;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public BufferedImage carregarImagem(String nome) {
        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(new File(nome));
            return imagem;
        } catch (IOException e) {
            System.err.println("Erro ao carregar a imagem. " + e.getMessage());
            return null;
        }
    }

    public void salvarInformacoes(String nome) {
        try {
            File arquivo = new File(nome);
            FileWriter escrever = new FileWriter(nome, true);
            escrever.write(toString());
            escrever.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar as operações: " + e.getMessage());
        }

    }

    public boolean adicionarAvaliacao(String autor, String analise, double nota){
        if(qntdAvaliacoes >= MAX_AVALIACOES)
            return false;

        try {
            avaliacoes[qntdAvaliacoes++] = new Avaliacao(autor, analise, nota);
        } catch(IllegalArgumentException ae){
            System.out.println("Erro! " + ae.getMessage());
        }

        return true;
    }

    public void setPromocao(boolean promocao, double valor){
        if(!promocao)
            return;

        precoPromocional = valor;
        this.promocao = true;
    }

    public void setPromocao(boolean promocao){
        if(promocao)
            return;

        this.promocao = false;
    }

    public String toString () {
        String s = String.format("PRODUTO %02d:\n%s\nR$ %.2f\n\n", produtoID, descricao, preco);
        for (int i = 0; i < qntdAvaliacoes; i++) {
            s+= avaliacoes[i].toString();
        }
        return s;
    }
}