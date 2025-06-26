import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Produto {
    private boolean promocao;
    private static final int MAX_AVALIACOES = 100;
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
        setImagem(nomeImagem);
        avaliacoes = new Avaliacao[MAX_AVALIACOES];
        qntdAvaliacoes = 0;
        precoPromocional = 0;
        promocao = false;
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

    public boolean getPromocao(){
        return promocao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty())
            throw new IllegalArgumentException("Descrição não pode ser nula.");
        if (descricao.length() > 100)
            throw new IllegalArgumentException("A descrição deve ter até 100 caracteres.");

        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        if(preco < 0)
            throw new IllegalArgumentException("Preço negativo.");
        this.preco = preco;
    }

    public void setPrecoPromocional(double preco) {
        if(preco < 0)
            throw new IllegalArgumentException("Preço negativo.");
        this.precoPromocional = preco;
    }

    public void setImagem(String nome) {
        try {
            imagem = ImageIO.read(new File(nome));
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem!");
            imagem = null;
        }
    }

    public void mostrarImagem() {
        if (imagem == null) {
            System.out.println("Nenhuma imagem carregada.");
            return;
        }

        ImageIcon icone = new ImageIcon(imagem);
        JLabel label = new JLabel(icone);
        JFrame frame = new JFrame(String.format("Produto %02d: %s", produtoID, descricao));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
            Avaliacao av = new Avaliacao(autor, analise, nota);
            avaliacoes[qntdAvaliacoes++] = av;
        } catch(IllegalArgumentException ae){
            System.out.println("Erro! " + ae.getMessage());
        }
        return true;
    }

    public void ativarPromocao(){
        this.promocao = true;
    }

    public void ativarPromocao(double valor){
        this.promocao = true;
        setPrecoPromocional(valor);
    }

    public void desativarPromocao(){
        this.promocao = false;
    }

    public double mediaAvaliacoes() {
        double soma = 0;
        for (int i = 0; i < qntdAvaliacoes; i++) {
            soma += avaliacoes[i].getNota();
        }
        return soma/qntdAvaliacoes;
    }

    public String toString () {
        String s = String.format("___________________________\n\n" +
                "PRODUTO %02d: %s\n", produtoID, descricao);

        if (promocao) {
            s += String.format("PROMOÇÃO! [R$ %.2f] -> R$ %.2f", preco, precoPromocional);
            s += String.format(" (%.0f%% off)\n\n", (preco - precoPromocional)/preco*100);
        }
        else
            s += String.format("Preço: R$ %.2f\n\n", preco);

        if (qntdAvaliacoes == 0) {
            s += "(sem avaliações)";
        }
        else{
            if (qntdAvaliacoes == 1)
                s += String.format("(%d avaliação) ", qntdAvaliacoes);
            else
                s += String.format("(%d avaliações) ", qntdAvaliacoes);

            for (int i = 0; i < 5; i++)
                if (i < (int) Math.round(mediaAvaliacoes()))
                    s += "★ ";
                else
                    s += "☆ ";
        }

        s += "\n\n";

        for (int i = 0; i < qntdAvaliacoes; i++)
            s += avaliacoes[i].toString();

        return s;
    }
}