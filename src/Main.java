import java.util.List;
import algorithms.*;

class Main{
    public static void main(String[] args) {
        System.out.println("oi queridas");
        List<Integer> ref = List.of(7, 0, 1, 2, 0, 3, 0, 4);
        FIFO fifoAlgorithm = new FIFO(ref, 5);
        System.out.println("primeiro valor físico da memoria ram: " + fifoAlgorithm.mainMemory.searchItemByAbsoluteIndex(0));
    }
}