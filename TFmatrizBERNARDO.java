import java.util.Random;
import java.util.Scanner;

public class TFmatrizBERNARDO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomico = new Random();

        // pede o tamanho da matriz ao usuário
        System.out.print("Informe a quantidade de linhas: ");
        int totalLinhas = scanner.nextInt();

        System.out.print("Informe a quantidade de colunas: ");
        int totalColunas = scanner.nextInt();

        // validação do menor tamanho possivel
        if (totalLinhas < 3 || totalColunas < 3) {
            System.out.println("A matriz deve ter no mínimo 3 linhas e 3 colunas.");
            return;
        }

        // Cria a matriz e preenche
        char[][] matriz = new char[totalLinhas][totalColunas];
        for (int linha = 0; linha < totalLinhas; linha++) {
            for (int coluna = 0; coluna < totalColunas; coluna++) {
                matriz[linha][coluna] = '.';
            }
        }

        // armazena as coordenadas dos 4 pontos A, B, C e D
        int[][] coordenadas = new int[4][2];
        String[] nomePONTOS = {"A", "B", "C", "D"};

        // sorteia 4 coordenadas unicas
        int pontosSorteados = 0;
        while (pontosSorteados < 4) {
            int linhaSorteada = randomico.nextInt(totalLinhas);
            int colunaSorteada = randomico.nextInt(totalColunas);
            boolean repetido = false;

            //verificacao pra nao repetir o ponto
            for (int i = 0; i < pontosSorteados; i++) {
                if (coordenadas[i][0] == linhaSorteada && coordenadas[i][1] == colunaSorteada) {
                    repetido = true;
                    break;
                }
            }

            if (!repetido) {
                coordenadas[pontosSorteados][0] = linhaSorteada;
                coordenadas[pontosSorteados][1] = colunaSorteada;
                pontosSorteados++;
            }
        }

        // exibe os pontos sorteados
        System.out.println("\nSorteando os pontos...");
        for (int i = 0; i < 4; i++) {
            System.out.printf("::: Ponto%s: L=%d, C=%d%n", nomePONTOS[i], coordenadas[i][0], coordenadas[i][1]);
        }
        System.out.println("::: >>> Nenhum ponto com coordenadas repetidas! <<<\n");

        // marca os pontos na matriz com A, B, C, D
        matriz[coordenadas[0][0]][coordenadas[0][1]] = 'A';
        matriz[coordenadas[1][0]][coordenadas[1][1]] = 'B';
        matriz[coordenadas[2][0]][coordenadas[2][1]] = 'C';
        matriz[coordenadas[3][0]][coordenadas[3][1]] = 'D';

        // conecta A com B usando '+'
        conectarPontos(matriz, coordenadas[0], coordenadas[1], '+');

        // conecta C com D usando '*'
        conectarPontos(matriz, coordenadas[2], coordenadas[3], '*');

        // exibe a matriz final na tela
        for (int linha = 0; linha < totalLinhas; linha++) {
            for (int coluna = 0; coluna < totalColunas; coluna++) {
                System.out.print(matriz[linha][coluna] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
    // conecta dois pontos com um caractere, fazendo um "caminho em L"
    private static void conectarPontos(char[][] matriz, int[] pontoInicial, int[] pontoFinal, char simbolo) {
        int linha1 = pontoInicial[0], coluna1 = pontoInicial[1];
        int linha2 = pontoFinal[0], coluna2 = pontoFinal[1];

        // caminho vertical
        int menorLinha = Math.min(linha1, linha2);
        int maiorLinha = Math.max(linha1, linha2);
        for (int i = menorLinha + 1; i < maiorLinha; i++) {
            if (matriz[i][coluna1] == '.') {
                matriz[i][coluna1] = simbolo;
            }
        }

        // caminho horizontal
        int menorColuna = Math.min(coluna1, coluna2);
        int maiorColuna = Math.max(coluna1, coluna2);
        for (int j = menorColuna + 1; j < maiorColuna; j++) {
            if (matriz[linha2][j] == '.') {
                matriz[linha2][j] = simbolo;
            }
        }
    }
}
