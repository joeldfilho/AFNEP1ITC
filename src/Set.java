import java.util.ArrayList;
import java.util.List;

public class Set {

    List<Integer> elementos = new ArrayList<>();

    /* Criamos um novo conjunto */
    public Set newSet(){
        return new Set();
    }

    /* instancia um novo Set com uma lista de inteiros como entrada inicial. */
    public Set newSet(List<Integer> listaDeElementosConjunto){
        Set novoSet = new Set();
        novoSet.insere(listaDeElementosConjunto);
        return novoSet;
    }

    /* insere um conjunto de elementos no Set */
    public void insere(List<Integer> elementosInserir) {
        elementos.addAll(elementosInserir);
    }

    /* retorna o número de elementos do Set */
    public int length(){
        return elementos.size();
    }

    /* compara cada elemento da lista com os do Set recebido e se forem iguais os adiciona e retorna o novo Set */
    public Set intersect(Set estadosEntrada, List<Integer> listaEstadosAceitacao){
        Set intersecao = new Set();
        for (Integer numero: listaEstadosAceitacao
             ) {
            if(estadosEntrada.elementos.contains(numero)){
                intersecao.elementos.add(numero);
            }
        }
        return intersecao;
    }
}
