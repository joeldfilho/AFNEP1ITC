import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
*  Classe responsável por representar um estado dentro do autômato.
*  Ela possui um id para mostrar qual estado é dentro do autômato, um boolean para representar se é um estado de aceitação
*  e uma lista contendo as transições possíveis a partir dela
* */
public class Estado {

    private String idEstado;
    private boolean ehAceito = false;
    private List<Transicao> transicoes = new ArrayList<>();
    private int[] estadosAlcancaveis;

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public boolean isEhAceito() {
        return ehAceito;
    }

    public void setEhAceito(boolean ehAceito) {
        this.ehAceito = ehAceito;
    }

    public boolean existeTransicao(int simbolo){
        for (Transicao transicao: this.transicoes) {
            if (transicao.getSimboloAlfabeto() == simbolo){
                return true;
            }
        }
        return false;
    }

    public String fazTransicao(int simbolo) {
        for (Transicao transicao : this.transicoes) {
            if (transicao.getSimboloAlfabeto() == simbolo) {
                return transicao.getIndiceFinal();
            }
        }
        return "-1";
    }

    public void defineEstadosAlcancaveis(){
        for (Transicao transicao:
             this.transicoes) {
            int simboloAlfabetoAtual;
        }
    }

    //pego a lista de transicoes
    //pego o simbolo atual
    //pra cada transicao que tiver o alfabeto atual, adiciono seu indice à transicao e anterior e apago

    public void apagaTransicoesRedundantes() {
        if (transicoes.size() != 0) {
            int alfabetoAtual = transicoes.get(0).getSimboloAlfabeto();
            List<Transicao> transicoes = new ArrayList<>();
            Transicao transicaoSaida = new Transicao();
            String indiceSaida = "";
            for (Transicao tran : getTransicoes()
            ) {
                if (tran.getSimboloAlfabeto() != alfabetoAtual) {
                    transicaoSaida.setSimboloAlfabeto(alfabetoAtual);
                    transicaoSaida.setIndiceFinal(indiceSaida);
                    transicoes.add(transicaoSaida);
                } else {
                    indiceSaida.concat(" ").concat(tran.getIndiceFinal());
                }

            }
            this.transicoes = transicoes;
        }
    }
}
