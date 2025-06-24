import java.lang.IllegalArgumentException;

public class Avaliacao {
    private String autor;
    private String analise;
    private double nota;

    public Avaliacao(String autor, String analise, double nota){
        this.autor = autor;
        this.analise = analise;
        setNota(nota);
    }

    public String getAutor(){
        return autor;
    }

    public String getAnalise(){
        return analise;
    }

    public double getNota(){
        return nota;
    }

    public void setNota(double nota) {
        if(nota < 0 || nota > 5)
            throw new IllegalArgumentException("Nota inválida");
        if(autor.isEmpty() || analise.isEmpty())
            throw new IllegalArgumentException("Texto vazio");

        this.nota = nota;
    }

    public String toString() {
        return String.format("Autor: %s\nAvaliação: %s\nNota: %.2f\n\n", autor, analise, nota);
    }
}