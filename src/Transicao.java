/* Classe que representa uma transição. Ela não guarda o estado inicial porque estará guardada dentro do estado incial.
* */

public class Transicao {

    private int simboloAlfabeto;
    private int indiceFinal;

    public int getSimboloAlfabeto() {
        return simboloAlfabeto;
    }

    public void setSimboloAlfabeto(int simboloAlfabeto) {
        this.simboloAlfabeto = simboloAlfabeto;
    }

    public int getIndiceFinal() {
        return indiceFinal;
    }

    public void setIndiceFinal(int indiceFinal) {
        this.indiceFinal = indiceFinal;
    }

}
