import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import algorithms.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-".repeat(55));
        System.out.println("Simulador: algoritmos de substituição de páginas");
        System.out.println("-".repeat(55));

        System.out.print("Digite o tamanho da memória (capacidade de frames): ");
        int capacity = scanner.nextInt();

        System.out.print("Digite o tamanho da string de referência: ");
        int refSize = scanner.nextInt();

        List<Integer> ref = new ArrayList<>();

        System.out.print("Deseja fazer inserção aleatória na string de referência? (S/N): ");
        char insertOption = scanner.next().charAt(0);

        if (insertOption == 'S' || insertOption == 's') {
            for (int i = 0; i < refSize; i++) {
                ref.add((int) (Math.random() * 100));
            }
        } else {
            for (int i = 0; i < refSize; i++) {
                System.out.print("Digite o valor da página (número inteiro) para a posição " + (i + 1) + ": ");
                int page = scanner.nextInt();
                ref.add(page);
            }
        }

        System.out.println("String de referência gerada: " + ref);

        FIFO fifoAlgorithm = new FIFO(ref, capacity);
        LRU lruAlgorithm = new LRU(ref, capacity);
        NFU nfuAlgorithm = new NFU(ref, capacity);
        Aging agingAlgorithm = new Aging(ref, capacity);
    }
}

/*
FIFO fifoAlgorithm = new FIFO(ref, capacidade);
LRU lruAlgorithm = new LRU(ref, capacidade);
NFU nfuAlgorithm = new NFU(ref, capacidade);
Aging agingAlgorithm = new Aging(ref, capacidade);*/