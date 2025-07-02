import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pagina {
    private int paginaID;
    private static int proximoID = 1;
    private static final int MAX_PRODUTOS = 5;
    private int numProdutos;
    private Produto[] produtos;

    public Pagina(){
        numProdutos = 0;
        paginaID = proximoID++;
        produtos = new Produto[MAX_PRODUTOS];
    }

    public static int getMaximoProdutos(){
        return MAX_PRODUTOS;
    }

    public void salvarProdutos(){
        if(numProdutos == 0)
            return;

        for(int i = 0; i < numProdutos; i++)
        {
            try {
                File arquivo = new File(String.format("pagina%02d.txt", paginaID));
                FileWriter escrever = new FileWriter(String.format("pagina%02d.txt", paginaID), true);
                escrever.write(stringProduto(i));
                escrever.close();
            } catch (IOException e) {
                System.out.println("Erro ao salvar as operações: " + e.getMessage());
            }
        }

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

    private String stringProduto(int indice){
        String descricao;
        Produto p = produtos[indice];

        descricao = p.getDescricao();

        if(descricao.length() > 100)
            descricao = descricao.substring(0, 96) + "...";

        String s = String.format("___________________________\n\n" + descricao + "\n");

        if (p.getPromocao()) {
            s += String.format("PROMOÇÃO! [R$ %.2f] -> R$ %.2f", p.getPreco(), p.getPrecoPromocional());
            s += String.format(" (%.0f%% off)\n\n", (p.getPreco() - p.getPrecoPromocional())/p.getPreco()*100);
        }
        else
            s += String.format("Preço: R$ %.2f\n\n", p.getPreco());

        if (p.getQntdAvaliacoes() == 0) {
            s += "(sem avaliações)";
        }
        else{
            if (p.getQntdAvaliacoes() == 1)
                s += String.format("(%d avaliação) ", p.getQntdAvaliacoes());
            else
                s += String.format("(%d avaliações) ", p.getQntdAvaliacoes());

            for (int i = 0; i < 5; i++)
                if (i < (int) Math.round(p.getMediaAvaliacoes()))
                    s += "★ ";
                else
                    s += "☆ ";
        }

        s += "\n\n";

        return s;
    }

    public void mostrarImagens(){
        for(int i = 0; i < numProdutos; i++){
            BufferedImage imagem = produtos[i].getImagem();
            String nomeImagem = produtos[i].getNomeImagem();
            String extensao = nomeImagem.substring(nomeImagem.length() - 3, nomeImagem.length());

            if (imagem == null || (!extensao.equals("png") && !extensao.equals("jpg"))) {
                System.out.println("Nenhuma imagem carregada.");
                return;
            }

            ImageIcon icone = new ImageIcon(imagem);
            JLabel label = new JLabel(icone);
            JFrame frame = new JFrame(produtos[i].getDescricao());

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

}