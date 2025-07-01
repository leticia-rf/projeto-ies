import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Produto {
    private boolean promocao;
    private String descricao;
    private int qntdAvaliacoes;
    private double preco;
    private double mediaAvaliacoes;
    private double precoPromocional;
    private BufferedImage imagem;

    public Produto(String descricao, double preco, String nomeImagem, int qntdAvaliacoes, double mediaAvaliacoes) {
        this.descricao = descricao;
        this.preco = preco;
        setImagem(nomeImagem);
        this.qntdAvaliacoes = qntdAvaliacoes;
        precoPromocional = 0;
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public Produto(String descricao, double preco, String nomeImagem, int qntdAvaliacoes, double mediaAvaliacoes, double promocao) {
        this.descricao = descricao;
        this.preco = preco;
        setImagem(nomeImagem);
        this.qntdAvaliacoes = qntdAvaliacoes;
        precoPromocional = 0;
        this.mediaAvaliacoes = mediaAvaliacoes;
        this.precoPromocional = promocao;
        this.promocao = true;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getQntdAvaliacoes() {
        return qntdAvaliacoes;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoPromocional() {
        return precoPromocional;
    }

    public boolean getPromocao() {
        return promocao;
    }

    public BufferedImage getImagem(){
        return imagem;
    }

    public void setImagem(String nome) {
        try {
            imagem = ImageIO.read(new File(nome));
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem!");
            imagem = null;
        }
    }

}