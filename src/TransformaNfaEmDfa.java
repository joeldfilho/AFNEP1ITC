import java.util.ArrayList;
import java.util.List;

public class TransformaNfaEmDfa {

    Automato automatoDeterministico;

    Automato transformaNfaParaDfa(Automato automatoNd){
        automatoDeterministico = new Automato();

        List<Estado> estados = new ArrayList<>();
        automatoDeterministico.setEstados(estados);
        for (Estado estado:automatoNd.getEstados()
             ) {
            estados.add(estado);

            if(estado.getTransicoes().size() != 0) {
                int simboloAtual = estado.getTransicoes().get(0).getSimboloAlfabeto();
                String idEstadosAlcancaveis = "";

                for (Transicao tran : estado.getTransicoes()
                ) {
                    if (tran.getSimboloAlfabeto() != simboloAtual) {

                        if (!existeEstado(idEstadosAlcancaveis)) {
                            Estado novoEstado = new Estado();
                            novoEstado.setIdEstado(idEstadosAlcancaveis);
                            setTransicoes(novoEstado, automatoNd);
                            //pego todas as transicoes do estado atual que tem o simbolo atual e coloco como destino delas o id do estado novo
                            acertaTransicoes(novoEstado, simboloAtual, idEstadosAlcancaveis);
                            estados.add(novoEstado);
                        }
                        simboloAtual = tran.getSimboloAlfabeto();
                    } else {
                        idEstadosAlcancaveis += " " + tran.getIndiceFinal();
                    }
                }
            }
        }
        automatoDeterministico.setEstados(estados);
        automatoDeterministico.setEstadoAtual(automatoNd.getEstadoAtual());
        automatoDeterministico.setEstadoInicial(automatoNd.getEstadoInicial());

//        for (Estado estado: automatoDeterministico.getEstados()
//             ) {
//            estado.apagaTransicoesRedundantes();
//        }
        return automatoDeterministico;
    }

    private void acertaTransicoes(Estado novoEstado, int simboloAtual, String idEstadosAlcancaveis) {
        for (Transicao tran: novoEstado.getTransicoes()
             ) {
            if(tran.getSimboloAlfabeto() == simboloAtual){
                tran.setIndiceFinal(idEstadosAlcancaveis);
            }
        }
    }

    private void setTransicoes(Estado novoEstado, Automato automatoNd) {
        List<String> estadosAlcancaveis = new ArrayList<>();
        String[] estadosLista = novoEstado.getIdEstado().split("\\s+");
        for (String estado:estadosLista
             ) {
            estadosAlcancaveis.add(estado);
        }
        for (Estado estado:automatoNd.getEstados()
             ) {
            for (String id: estadosAlcancaveis
            ) {if(!id.equals("")) {

                    if (id.equals(estado.getIdEstado())) {
                        novoEstado.getTransicoes().addAll(estado.getTransicoes());

                        if (estado.isEhAceito()) {
                            novoEstado.setEhAceito(true);
                        }
                    }
                }
            }
        }
    }

    private boolean existeEstado(String idEstadosAlcancaveis) {
        for (Estado estado: automatoDeterministico.getEstados()
             ) {
            if(estado.getIdEstado() == idEstadosAlcancaveis) return true;
        }
        return false;
    }
}
