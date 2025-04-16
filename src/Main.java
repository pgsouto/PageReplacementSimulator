import java.util.List;
import algorithms.*;

class Main{
    public static void main(String[] args) {
        System.out.println("-".repeat(55));
        System.out.println("Simulador: algoritmos de substituição de páginas");
        System.out.println("-".repeat(55));
        //List<Integer> ref = List.of(7, 0, 1, 2, 0, 3, 0, 4);
        //nos exemplos abaixo mudar capacidade para 3
        List<Integer> ref = List.of(1, 2, 3, 1, 4, 5);
        //List<Integer> ref = List.of(1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5);
        FIFO fifoAlgorithm = new FIFO(ref, 3);
        LRU lruAlgorithm = new LRU(ref, 3);
    }
}