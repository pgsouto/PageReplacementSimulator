package algorithms;

import structures.CircularQueue;

import java.util.List;

public class FIFO{

    private int pageFaultCounter;
    private int pageHitCounter;
    public CircularQueue mainMemory;

    public FIFO(List<Integer> referenceString, int capacity) {
        this.mainMemory = new CircularQueue(capacity);
        this.pageFaultCounter = 0;
        this.pageHitCounter = 0;
        simulate(referenceString);
        printStats();
    }

    public int getPageFaultCounter(){
        return this.pageFaultCounter;
    }
    public int getPageHitCounter(){
        return this.pageHitCounter;
    }
    public float getPageHitRate(){
        int total = this.pageHitCounter + this.pageFaultCounter;
        if(total == 0) return 0;
        return (float) this.pageHitCounter /total * 100;
    }
    public float getPageFaultRate(){
        int total = this.pageHitCounter + this.pageFaultCounter;
        if(total == 0) return 0;
        return (float) this.pageFaultCounter /total * 100;
    }


    private void simulate(List<Integer> referenceList){
        for(int page : referenceList){
            if(!mainMemory.isInQueue(page)){ //caso não encontre um valor é o mesmo que page fault
                pageFaultCounter++;
                if(this.mainMemory.isFull()){
                    this.mainMemory.dequeue();
                }
                this.mainMemory.enqueue(page);
            }else{// caso encontre é o mesmo que page hit
                pageHitCounter++;
            }
        }
    }

    public void printStats() {
        System.out.println("ALGORITMO FIFO: ");
        System.out.printf("Page Faults: %d\n", pageFaultCounter);
        System.out.printf("Page Hits: %d\n", pageHitCounter);
        System.out.printf("Taxa de Hits: %.2f%%\n", getPageHitRate());
        System.out.printf("Taxa de Page Faults: %.2f%%\n", getPageFaultRate());
        System.out.println("-".repeat(20));
    }
}
