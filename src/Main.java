import java.util.List;
import algorithms.*;

class Main{
    public static void main(String[] args) {
        System.out.println("-".repeat(55));
        System.out.println("Simulador: algoritmos de substituição de páginas");
        System.out.println("-".repeat(55));
        //List<Integer> ref = List.of(7, 0, 1, 2, 0, 3, 0, 4);
        //nos exemplos abaixo mudar capacidade para 3
        //List<Integer> ref = List.of(1, 2, 3, 1, 4, 5);
        List<Integer> ref = List.of(
            7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1
        );
        int capacidade = 3;
        

        FIFO fifoAlgorithm = new FIFO(ref, capacidade);
        LRU lruAlgorithm = new LRU(ref, capacidade);
        NFU nfuAlgorithm = new NFU(ref, capacidade);
        Aging agingAlgorithm = new Aging(ref, capacidade);

    }
}