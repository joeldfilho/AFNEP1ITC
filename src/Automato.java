import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Automato {
    int numeroDeEstados;             //q
    int tamanhoAlfabeto;             //s
    int numeroDeTransicoes ;         //t
    int estadoInicial;               //q0
    int numeroDestadosDeAceitacao ;  //a
    List<Integer> estadosAceitacao = new ArrayList<>();
    List<Estado> tabelaTransicao = new ArrayList<>();
    List<Set> reach;
    Set estados;

    /* Criação de um Automato a partir dos dados do cabeçalho (q, s, t, q0, a) */
    public Automato(int[] cabecalhoAutomato) {
        this.numeroDeEstados = cabecalhoAutomato[0];
        this.tamanhoAlfabeto = cabecalhoAutomato[1];
        this.numeroDeTransicoes = cabecalhoAutomato[2];
        this.estadoInicial = cabecalhoAutomato[3];
        this.numeroDestadosDeAceitacao = cabecalhoAutomato[4];
        Set estados = new Set();

        /* adiciona todos os estados em uma lista */
        List<Integer> listaEstados = new ArrayList<>();
        for (int i = 0; i < numeroDeEstados; i++) {
            listaEstados.add(i);
        }

        /* insere a lista de estados em um tipo List para montar a lista de transições */
        estados.insere(listaEstados);
        this.estados = estados;

        /* Para o número de estados recebido na entrada eu crio um novo Estado, e pra cada símbolo esse estado recebe uma transição */
        for (int i = 0; i < numeroDeEstados; i++) {
            Estado estado = new Estado();
            for (int j = 0; j < tamanhoAlfabeto; j++) {
                Transicao transicao = new Transicao();
                estado.transicoes.add(transicao);
            }

            /* Todos os estados podem, por definição, chegar a si mesmos com o símbolo vazio (representado aqui por 0) */
            estado.transicoes.get(0).chegada.add(i);
            tabelaTransicao.add(estado);
        }
    }

    public void determinaAlcancaveisComZero(){

        /* Crio uma matriz qxq, e, pra cada estado i, se o numero de estados que ele alcança com um determinado símbolo for maior que zero, coloco na matriz na posição [i][estado de chegada] o valor 1, criando a matriz de adjacência para o grafo dirigido */
        int[][] matriz = matriz(numeroDeEstados,numeroDeEstados);
        for (int i = 0; i < numeroDeEstados; i++) {
            if(tabelaTransicao.get(i).transicoes.get(0).chegada.size() > 0){
                for (int chegada: tabelaTransicao.get(i).transicoes.get(0).chegada) {
                    matriz[i][chegada] = 1;
                }
            }
        }

        /* instancia e monta o grafo de acordo com a matriz de adjacência */
        Grafo grafo = new Grafo();
        grafo.newGrafo(matriz);
        List<Set> saida = new ArrayList<>();

        /* busca quais estados são alcançáveis por meio de transições vazias */
        for (int i = 0; i < numeroDeEstados ; i++) {
            Set alcancaveisParaI = new Set();
            alcancaveisParaI.insere(Collections.singletonList(i));
            Set alcancaveis = grafo.search(i);
            if (alcancaveis.length() > 0){
                alcancaveisParaI.insere(alcancaveis.elementos);
            }
            saida.add(alcancaveisParaI);
        }
        this.reach = saida;
    }

    /* Cria uma matriz e inicialmente salva todas as posições com 0 */
    private int[][] matriz(int linhas, int colunas) {
        int[][]matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    /* adiciona uma nova transição à tabela de transições */
    public void adicionaTransicao(int estadoInicial, int simbolo, int estadoFinal) {
        tabelaTransicao.get(estadoInicial).transicoes.get(simbolo).chegada.add(estadoFinal);
    }

    public boolean verificaCadeia(int[] cadeiaTeste){

        /* Se o Automato não possuir estados de aceitação, retornamos falso imediatamente */
        if (this.numeroDestadosDeAceitacao == 0){
            return false;
        }

        /* Se a cadeia de testes for vazia verifico se há interseção entre o conjunto de estados de aceitação e o conjunto de estados alcançáveis com o símbolo vazio */
        if(cadeiaTeste.length == 0){
            Set intersecaoEstadosFinaisEstadosAceitacao = new Set();
            intersecaoEstadosFinaisEstadosAceitacao.intersect(reach.get(0), estadosAceitacao);
            return (intersecaoEstadosFinaisEstadosAceitacao.length() > 0) ? true:false;
        }

        /* instancia uma fila e inicia ela com todos os elementos alcançáveis com o símbolo vazio */
        Queue estadosPossiveis = new Queue();
        estadosPossiveis.newQueue(reach.get(0).elementos);

        /* Faço um laço que vai rodar pelo tamanho da cadeia de testes */
        for (int i = 0; i < cadeiaTeste.length; i++) {

            /* Se a fila de estados possíveis for vazia, retorno falso pois o autômato rejeitou a cadeia de testes */
            int tamanhoFila = estadosPossiveis.length();
            if(tamanhoFila == 0){
                return false;
            }

            /*  */
            int atual = cadeiaTeste[i];
            for (int j = 0; j < tamanhoFila; j++) {
                int simbolo = estadosPossiveis.dequeue();
                if (tabelaTransicao.get(simbolo).transicoes.get(atual).chegada.size() > 0){
                    for (int chegada: tabelaTransicao.get(simbolo).transicoes.get(atual).chegada
                    ) {
                        estadosPossiveis.adicionar(chegada);
                        estadosPossiveis.enqueue(reach.get(chegada).elementos);
                    }
                }
            }
        }

        /* verifica se a Queue não está vazia */
        if (estadosPossiveis.length() == 0){
            return false;
        }

        /* instancia e atribui elementos para os Sets */
        Set intersecaoEstadosFinaisEstadosAceitacao = new Set();
        Set fila = new Set();
        for (Integer elemento: estadosPossiveis.elementosFila){
            fila.elementos.add(elemento);
        }

        /* compara os estados em que as cadeias encerraram com o estado de aceitação do autômato e retorna a sequencia de quais foram ou não aprovados */
        intersecaoEstadosFinaisEstadosAceitacao = intersecaoEstadosFinaisEstadosAceitacao.intersect(fila,estadosAceitacao);
        return (intersecaoEstadosFinaisEstadosAceitacao.length() > 0) ? true:false;
    }
}
