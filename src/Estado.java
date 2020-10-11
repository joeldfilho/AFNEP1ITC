import java.util.ArrayList;
import java.util.List;

/*
*  Classe responsável por representar um estado dentro do autômato.
*  Ela possui um id para mostrar qual estado é dentro do autômato, um boolean para representar se é um estado de aceitação
*  e uma lista contendo as transições possíveis a partir dela
* */
public class Estado {

    private int idEstado;
    private boolean ehAceito = false;
    private List<Transicao> transicoes = new ArrayList<>();

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
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

    public int fazTransicao(int simbolo) {
        for (Transicao transicao : this.transicoes) {
            if (transicao.getSimboloAlfabeto() == simbolo) {
                return transicao.getIndiceFinal();
            }
        }
        return -1;
    }
}
