import java.util.ArrayList;
import java.util.List;

public class Queue {

    /* instancia List de elementos da fila */
    List<Integer> elementosFila = new ArrayList<>();

    /* instancia a Queue recebendo como entrada uma lista de inteiros. Utilizamos essa função especialmente para criar a fila com todos os alcançáveis com o símbolo 0 a partir do estado inicial */
    public Queue newQueue(List<Integer> listaDeElementos){
        Queue queue = new Queue();
        queue.enqueue(listaDeElementos);
        enqueue(listaDeElementos);
        return queue;
    }

    /* adiciona um elemento no final da fila */
    public void adicionar(int elemento){
        elementosFila.add(elementosFila.size(), elemento);
    }

    /* adiciona um conjunto de elementos no final da fila */
    public void enqueue(List<Integer> elementos) {
        for (Integer elemento: elementos
             ) {
            elementosFila.add(elementosFila.size(), elemento);
        }
    }

    /* retira o primeiro elemento da fila (função pop())*/
    public int dequeue(){
        int devolver = elementosFila.get(0);
        elementosFila.remove(0);
        return devolver;
    }

    
    /* retorna o número de elementos na fila. Usamos essa função para verificar fila de estados vazia */
    public int length() {
        return elementosFila.size();
    }
}
