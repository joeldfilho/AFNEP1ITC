import java.util.ArrayList;
import java.util.List;

public class Grafo {

    /* instancia List de nós para o grafo */
    List<No> nos = new ArrayList<>();


    public Grafo newGrafo(int[][]matrizAdjacencia){

        /* instancia o grafo, cria os nós necessários e os adiciona como filhos */
        Grafo grafo = new Grafo();
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            No no = new No();
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if(matrizAdjacencia[i][j] == 1){
                    no.filhos.add(j);
                }
            }
            /* adiciona os nós na lista de nós*/
            grafo.nos.add(no);
        }
        this.nos = grafo.nos;
        return grafo;
    }

    /* função que realiza uma busca em profundidade */
    public Set search(int no){

        /* Cria um vetor com o tamanho dos nós para salvar quando algum deles já foi visitado */
        boolean[] visitados = new boolean[nos.size()];

        /* Cria um conjunto para salvar os resultados da busca */
        Set busca = new Set();
        
        /* Salva o primeiro nó na lista de visitados */
        visitados[no] = true;

        /* Coloca o elemento inicial no conjunto de saída */
        busca.elementos.add(no);

        /* Cria uma fila de elementos para salvar a ordem de busca */
        Queue aBuscar = new Queue();

        /* Adiciona o primeiro nó na fila */
        aBuscar.adicionar(no);

        /* Enquanto a fila de elementos que ainda precisa buscar não estiver vazia entra no loop */
        while(aBuscar.elementosFila.size() != 0){

            /* Pega o primeiro elemento da fila */
            int atual = aBuscar.dequeue();

            /* Pra cada elemento nos filhos do nó atual */
            for (int filho: nos.get(atual).filhos
                 ) {
                     /* Se ele ainda não foi visitado eu salvo ele como visitado, coloco na fila de busca e no conjunto de saída */
                if (visitados[filho] == false){
                    visitados[filho] = true;
                    aBuscar.adicionar(filho);
                    busca.elementos.add(filho);
                }
            }
        }

        /* Retorna todos os elementos que podem ser visitados a partir do nó inicial do grafo */
        return busca;
    }

}
