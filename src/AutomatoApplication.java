import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomatoApplication {

    public static void main(String[] args) throws IOException {

        Scanner arquivoEntrada = new Scanner(System.in);
        /* primeira linha do arquivo de entrada tem o número de automatos*/
        int numeroDeAutomatos = arquivoEntrada.nextInt();   //n

        while (numeroDeAutomatos > 0) {
            /* segunda linha do arquivo de entrada é no formato q s  t q0 a */
            int numeroDeEstados = arquivoEntrada.nextInt();             //q
            int tamanhoAlfabeto = arquivoEntrada.nextInt();             //s
            int numeroDeTransicoes = arquivoEntrada.nextInt();          //t
            int estadoInicial = arquivoEntrada.nextInt();               //q0
            int numeroDestadosDeAceitacao = arquivoEntrada.nextInt();   //a


            Automato automato = criaAutomato(numeroDeEstados, tamanhoAlfabeto, numeroDeTransicoes, estadoInicial, numeroDestadosDeAceitacao);
        }
    }

    private static Automato criaAutomato(int numeroDeEstados, int tamanhoAlfabeto, int numeroDeTransicoes, int estadoInicial, int numeroDestadosDeAceitacao) {
        return new Automato(numeroDeEstados, tamanhoAlfabeto, numeroDeTransicoes, estadoInicial, numeroDestadosDeAceitacao);
    }

}
