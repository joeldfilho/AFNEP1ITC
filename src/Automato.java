import java.util.ArrayList;
import java.util.List;

public class Automato {

    public Automato(int numeroDeEstados, int tamanhoAlfabeto, int numeroDeTransicoes, int estadoInicial, int numeroDestadosDeAceitacao) {
        this.numeroDeEstados = numeroDeEstados;
        this.tamanhoAlfabeto = tamanhoAlfabeto;
        this.numeroDeTransicoes = numeroDeTransicoes;
        this.estadoInicial = estadoInicial;
        this.numeroDestadosDeAceitacao = numeroDestadosDeAceitacao;
    }

    private int numeroDeEstados;
    private int tamanhoAlfabeto;
    private int numeroDeTransicoes;
    private int estadoInicial;
    private int numeroDestadosDeAceitacao;
    List<Estado> estados = new ArrayList<Estado>();

    public int getNumeroDeEstados() {
        return numeroDeEstados;
    }

    public void setNumeroDeEstados(int numeroDeEstados) {
        this.numeroDeEstados = numeroDeEstados;
    }

    public int getTamanhoAlfabeto() {
        return tamanhoAlfabeto;
    }

    public void setTamanhoAlfabeto(int tamanhoAlfabeto) {
        this.tamanhoAlfabeto = tamanhoAlfabeto;
    }

    public int getNumeroDeTransicoes() {
        return numeroDeTransicoes;
    }

    public void setNumeroDeTransicoes(int numeroDeTransicoes) {
        this.numeroDeTransicoes = numeroDeTransicoes;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public int getNumeroDestadosDeAceitacao() {
        return numeroDestadosDeAceitacao;
    }

    public void setNumeroDestadosDeAceitacao(int numeroDestadosDeAceitacao) {
        this.numeroDestadosDeAceitacao = numeroDestadosDeAceitacao;
    }


}
