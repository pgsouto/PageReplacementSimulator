package algorithms;

import java.util.LinkedList;
import java.util.List;

public class LRU {
    private int pageFaultCounter;
    private int pageHitCounter;
    private int capacity;
    private LinkedList<Integer> mainMemory;

    public LRU(List<Integer> referenceString,int capacity) {
        this.pageFaultCounter = 0;
        this.pageHitCounter = 0;
        this.capacity = capacity;
        this.mainMemory = new LinkedList<>();
        simulate(referenceString);
        printStats();
    }

    private void simulate(List<Integer> referenceList) {
        for(int page : referenceList){
            if(this.mainMemory.contains(page)){ //simula o page hit (encontrou um valor repetido)
                this.pageHitCounter++;
                this.mainMemory.remove((Integer) page);
                this.mainMemory.addFirst(page);
            } else{ // simula o page fault (não encontrou um valor)
                this.pageFaultCounter++;
                if(this.mainMemory.size() == this.capacity){
                    this.mainMemory.removeLast();
                }
                this.mainMemory.addFirst(page);
            }
        }
    }

    public int getPageFaultCounter() {
        return this.pageFaultCounter;
    }

    public int getPageHitCounter() {
        return this.pageHitCounter;
    }

    public float getPageHitRate() {
        int total = this.pageHitCounter + this.pageFaultCounter;
        if (total == 0) return 0;
        return (float) this.pageHitCounter / total * 100;
    }

    public float getPageFaultRate() {
        int total = this.pageHitCounter + this.pageFaultCounter;
        if (total == 0) return 0;
        return (float) this.pageFaultCounter / total * 100;
    }

    public void printStats() {
        System.out.println("ALGORITMO LRU");
        System.out.printf("Page Faults: %d\n", pageFaultCounter);
        System.out.printf("Page Hits: %d\n", pageHitCounter);
        System.out.printf("Taxa de Hits: %.2f%%\n", getPageHitRate());
        System.out.printf("Taxa de Page Faults: %.2f%%\n", getPageFaultRate());
        System.out.println("-".repeat(20));
    }
}

