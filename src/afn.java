/* Joel Dias do Amaral Filho 9761486    Vinícius Martiniano Oliveira de Moraes 11207748 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class afn {

    public static void main(String[] args) throws IOException {

        /* Lê o arquivo de entrada*/
        String caminho = System.getProperty("user.dir");
        String arquivoHardCoded = caminho + "\\entrada.txt";
        FileReader fileReader = new FileReader(arquivoHardCoded);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner arquivoEntrada = new Scanner(bufferedReader);


        /* primeira linha do arquivo de entrada tem o número de automatos */
        int numeroDeAutomatos = arquivoEntrada.nextInt();   //

        /* O arquivo de saída deverá ser um só para todos os autômatos, então vou criar antes de criá-los */
        String saida = "";

        /*pra cada automato vou ter que fazer os próximos passos*/
        for (int i = 0; i < numeroDeAutomatos; i++) {

            /* primeira linha do automato atual é no formato q s t q0 a */
            int[] cabecalhoAutomato = leCabecalho(arquivoEntrada);
            Automato automato = new Automato(cabecalhoAutomato);

            /* lê a linha seguinte de estados de aceitação e a armazena */
            List<Integer> estadosAceitacao = new ArrayList<>();
            for (int j = 0; j < automato.numeroDestadosDeAceitacao; j++) {
                estadosAceitacao.add(arquivoEntrada.nextInt());
            }
            automato.estadosAceitacao = estadosAceitacao;

            /* lê as linhas seguintes de transições aceitas e as armazena */
            for (int j = 0; j < automato.numeroDeTransicoes; j++) {
                automato.adicionaTransicao(arquivoEntrada.nextInt(), arquivoEntrada.nextInt(), arquivoEntrada.nextInt());
            }

            /* lê a linha seguinte de quantidade de cadeias e as proximas de cadeias de transição */
            int numeroTestes = arquivoEntrada.nextInt();

            /* Como antes tratamos uma entrada de inteiro e a seguir trataremos uma String é preciso ler o espaço vazio para pular para a próxima linha */
            arquivoEntrada.nextLine();


            for (int j = 0; j < numeroTestes; j++) {
                String cadeia = arquivoEntrada.nextLine();
                String[] arrayCadeia = cadeia.split("\\s+");
                int[] arrayVerificar = new int[arrayCadeia.length];
                for (int k = 0; k < arrayCadeia.length; k++) {
                    arrayVerificar[k] = Integer.valueOf(arrayCadeia[k]);
                }
                /* chama a função 'verificaCadeia()' para verificar se as cadeias estabelecidas passam no autômato */
                automato.determinaAlcancaveisComZero();
                String adicionarSaida = (automato.verificaCadeia(arrayVerificar)) ? "1 " : "0 ";
                saida += adicionarSaida;
            }
            /* Pula uma linha na saída*/
            saida += "\n";
        }

        /* escreve no arquivo de saida o resultado */
        String arquivoSaida = caminho + "\\saida.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivoSaida));
        bufferedWriter.write(saida);
        bufferedWriter.flush();
        bufferedWriter.close();
        fileReader.close();
    }

    /* Função que recebe de entrada o arquivo e a partir da linha cabeçalho de um determinado Automato define os dados que serão usados para criá-lo */
    private static int[] leCabecalho(Scanner arquivoEntrada) {
        int[] dadosAutomato = new int[5];

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
}
