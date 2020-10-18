/* Classe que representa uma transição. Ela não guarda o estado inicial porque estará guardada dentro do estado incial.
* */

public class Transicao {

    private int simboloAlfabeto;
    private String indiceFinal;

    public int getSimboloAlfabeto() {
        return simboloAlfabeto;
    }

    public void setSimboloAlfabeto(int simboloAlfabeto) {
        this.simboloAlfabeto = simboloAlfabeto;
    }

    public String getIndiceFinal() {
        return indiceFinal;
    }

    public void setIndiceFinal(String indiceFinal) {
        this.indiceFinal = indiceFinal;
    }

}
