import java.util.ArrayList;
import java.util.List;

public class Automato {

    private int numeroDeEstados;
    private int tamanhoAlfabeto;
    private int numeroDeTransicoes;
    private int estadoInicial;
    private int numeroDestadosDeAceitacao;
    private Estado estadoAtual;
    private List<Estado> estados;

    public Automato(int[] cabecalhoAutomato) {
        this.numeroDeEstados = cabecalhoAutomato[0];
        this.tamanhoAlfabeto = cabecalhoAutomato[1];
        this.numeroDeTransicoes = cabecalhoAutomato[2];
        this.estadoInicial = cabecalhoAutomato[3];
        this.numeroDestadosDeAceitacao = cabecalhoAutomato[4];
        List<Estado> estados = new ArrayList<>();
        for (int i = 0; i < numeroDeEstados; i++){
            Estado estado = new Estado();
            estados.add(estado);
        }
        this.estados = estados;
        this.estadoAtual = estados.get(estadoInicial);
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Estado estadoAtual) {
        this.estadoAtual = estadoAtual;
    }



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

    public boolean verifica(String teste) {
        return true;
    }
}
