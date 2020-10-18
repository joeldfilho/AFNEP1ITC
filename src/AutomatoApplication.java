import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomatoApplication {

    public static void main(String[] args) throws IOException {

        /* Lê o arquivo de entrada*/
        String caminho = System.getProperty("user.dir");
        String arquivoHardCoded = caminho + "\\entrada.txt";
        FileReader fileReader = new FileReader(arquivoHardCoded);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner arquivoEntrada = new Scanner(bufferedReader);


        /* primeira linha do arquivo de entrada tem o número de automatos*/
        int numeroDeAutomatos = arquivoEntrada.nextInt();   //n

        /*pra cada automato vou ter que fazer os próximos passos*/

        /* O arquivo de saída deverá ser um só para todos os autômatos, então vou criar antes de criá-los */
        String saida = "";

        /* Vou guardar os automatos criados em uma lista pra poder ler os testes depois*/
        List<Automato> automatos = new ArrayList<>();

        for (int i = 0; i < numeroDeAutomatos; i++) {

            /* primeira linha do automato atual é no formato q s  t q0 a */
            int[] cabecalhoAutomato = leCabecalho(arquivoEntrada);

            /* Crio o automato com os dados já passados*/
            Automato automato = criaAutomato(cabecalhoAutomato);

            /* Como tratei os primeiros casoss com o nextInt, preciso pular uma linha aqui*/
            arquivoEntrada.nextLine();

            /* Próxima linha tem os estados de aceitação. Como não especifica quantos podem ser, vou ler a linha inteira
             *  Depois vou separar a linha em um array de Strings e, para cada elemento desse array, adicionar o
             *  índice ao Array de estados de aceitação do automato*/
            String estadosAceitacao = arquivoEntrada.nextLine();

            /* Vou usar uma Expressão Regular para separar essa string pelo caracter " " (espaço)*/
            String[] estadosAceitacaoSeparados = estadosAceitacao.split("\\s+");
            automato.setEstadosAceitacao(estadosAceitacaoSeparados);

            /* Laço para adicionar cada estado à lista de estados de aceitação*/
            for (String estado : estadosAceitacaoSeparados) {
                automato.getEstados().get(Integer.valueOf(estado)).setEhAceito(true);
            }

            /* Na terceira linha das especificações começa a leitura das transicoes */
            for (int j = 0; j < automato.getNumeroDeTransicoes(); j++) {

                // Leitura da linha de transição
                String transicao = arquivoEntrada.nextLine();

                // RegEx para separar os valores
                String[] transicoesSeparadas = transicao.split("\\s+");
                Transicao tran = new Transicao();
                tran.setSimboloAlfabeto(Integer.valueOf(transicoesSeparadas[1]));
                tran.setIndiceFinal(transicoesSeparadas[2]);

                automato.getEstados().get(Integer.valueOf(transicoesSeparadas[0]))
                        .getTransicoes().add(tran);
            }

            /* A próxima linha tem o número de casos de teste para o autômato */
            int numeroCadeiaTestes = arquivoEntrada.nextInt();

            /*Quando uso o nextInt antes de um nextLine preciso pualr uma linha para não ler a expressão vazia*/
            arquivoEntrada.nextLine();

            /*Transformo o NFA em DFA*/
            TransformaNfaEmDfa transformador = new TransformaNfaEmDfa();
            Automato deterministico = transformador.transformaNfaParaDfa(automato);

            /* Para cada cadeia de teste verificar se o autômato é válido ou não*/
            for (int j = 0; j < numeroCadeiaTestes; j++) {
                String teste = arquivoEntrada.nextLine();
                String resultado = (deterministico.verifica(teste)) ? "1" : "0";
                saida+= resultado + " ";
            }
         saida+= "\n";
        }

        System.out.println(saida);
    }

    private static int[] leCabecalho(Scanner arquivoEntrada) {
        int[] dadosAutomato = new int[5];
        //String cabecalho = arquivoEntrada.nextLine();

        int numeroDeEstados = arquivoEntrada.nextInt();             //q
        int tamanhoAlfabeto = arquivoEntrada.nextInt();             //s
        int numeroDeTransicoes = arquivoEntrada.nextInt();          //t
        int estadoInicial = arquivoEntrada.nextInt();               //q0
        int numeroDestadosDeAceitacao = arquivoEntrada.nextInt();   //a

        dadosAutomato[0] = numeroDeEstados;
        dadosAutomato[1] = tamanhoAlfabeto;
        dadosAutomato[2] = numeroDeTransicoes;
        dadosAutomato[3] = estadoInicial;
        dadosAutomato[4] = numeroDestadosDeAceitacao;

        return dadosAutomato;
    }

    private static Automato criaAutomato(int[] cabecalhoAutomato) {
        return new Automato(cabecalhoAutomato);
    }
}
