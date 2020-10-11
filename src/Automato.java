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
    private int[][] tabelaEstados;
    private List<String[]> listaTransicoes;

    public int[][] getTabelaEstados() {
        return tabelaEstados;
    }

    public void setTabelaEstados(int[][] tabelaEstados) {
        this.tabelaEstados = tabelaEstados;
    }

    public List<String[]> getListaTransicoes() {
        return listaTransicoes;
    }

    public void setListaTransicoes(List<String[]> listaTransicoes) {
        this.listaTransicoes = listaTransicoes;
    }

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
        this.tabelaEstados = criaTabelaEstados(numeroDeEstados, tamanhoAlfabeto);
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
        String[] transicoes = teste.split("\\s+");
        for (String transicao: transicoes) {
            int simbolo = Integer.valueOf(transicao);
            if(estadoAtual.existeTransicao(simbolo)){
                estadoAtual = estados.get(estadoAtual.fazTransicao(simbolo));
            }
        }
        return estadoAtual.isEhAceito();
    }

    public int[][] criaTabelaEstados(int numeroDeEstados, int tamanhoAlfabeto){
        //vou inicializar todos os espaços da tabela com -1
        int[][]resultado =  new int[(int) Math.pow(2,numeroDeEstados)][tamanhoAlfabeto];
        for (int i = 0; i < (int) Math.pow(2,numeroDeEstados); i++) {
            for (int j = 0; j < tamanhoAlfabeto; j++) {
                resultado[i][j] = -1;
            }
        }
        return resultado;
    }

    // Pego o estado inicial
    // Pego os estados em que posso chegar partindo do inicial
    //pra isso preciso de uma lista de todas as transições
    public void populaTabelaEstados(int[][] tabelaEstados, List<String[]> listaTransicoes){
        Estado estadoAtual = this.estadoAtual;
        for (Transicao tran: estadoAtual.getTransicoes()) {
            if(posicaoEstaVazia(tabelaEstados[estadoAtual.getIdEstado()][tran.getSimboloAlfabeto()])) {
                tabelaEstados[estadoAtual.getIdEstado()][tran.getSimboloAlfabeto()] = tran.getIndiceFinal();
            }
            else{
                Estado novoEstado = estadoAtual;
                this.numeroDeEstados++;
                estados.add(novoEstado);
            }
        }


    }

    private boolean posicaoEstaVazia(int i) {
        return (i == -1) ? true : false;
    }
}
