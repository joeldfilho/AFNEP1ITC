
arquivoEntrada = open("entrada.txt")
numeroAutomatos = arquivoEntrada.readline()
# primeira linha do automato atual é no formato q s  t q0 a 
for numIteracoes in range(numeroAutomatos):

    definicaoAutomato = arquivoEntrada.readline().split()
    quantidadeEstados = definicaoAutomato[0]
    quantidadeSimbolos = definicaoAutomato[1]
    quantidadeTransicoes = definicaoAutomato[2]
    estadoInicial = definicaoAutomato[3]
    quantidadeEstadosAceitacao = definicaoAutomato[4]

    estadosAceitacao[quantidadeEstadosAceitacao] = arquivoEntrada.readline().split()
    transicoes = []


    for transicao in transicoes:
        transicoes.append(arquivoEntrada.readline())


    numeroCadeiasTeste = arquivoEntrada.readline()

    for iteracao in numeroCadeiasTeste:
        teste = arquivoEntrada.readline()

arquivoSaida = open("saida.txt", 'x')
arquivoSaida.write(saida)
arquivoSaida.close()
arquivoEntrada.close()