package algorithms;

import paging.Page;
import java.util.*;

public class LRU {
    private int pageFaultCounter;
    private int pageHitCounter;
    private int capacity;
    private List<Page> mainMemory;
    private int clockHand;

    public LRU(List<Integer> referenceString, int capacity) {
        this.pageFaultCounter = 0;
        this.pageHitCounter = 0;
        this.capacity = capacity;
        this.mainMemory = new ArrayList<>(capacity);
        this.clockHand = 0;
        simulate(referenceString);
        printStats();
    }

    private void simulate(List<Integer> referenceList) {
        for (int pageNumber : referenceList) {
            Page page = findPage(pageNumber);

            if (page != null) { // Page hit
                pageHitCounter++;
                page.counter = 1; // Usa 1 para marcado, 0 para não marcado
            } else { // Page fault
                pageFaultCounter++;
                if (mainMemory.size() < capacity) {
                    mainMemory.add(new Page(pageNumber));
                    mainMemory.get(mainMemory.size()-1).counter = 1;
                } else {
                    replacePage(pageNumber);
                }
            }
        }
    }

    private Page findPage(int pageNumber) {
        for (Page page : mainMemory) {
            if (page.number == pageNumber) {
                return page;
            }
        }
        return null;
    }

    private void replacePage(int newPageNumber) {
        while (true) {
            Page current = mainMemory.get(clockHand);

            if (current.counter == 0) {
                current.number = newPageNumber;
                current.counter = 1;
                clockHand = (clockHand + 1) % capacity;
                return;
            } else {
                current.counter = 0;
                clockHand = (clockHand + 1) % capacity;
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
        System.out.println("ALGORITMO LRU COM BIT DE REFERÊNCIA");
        System.out.printf("Page Faults: %d\n", pageFaultCounter);
        System.out.printf("Page Hits: %d\n", pageHitCounter);
        System.out.printf("Taxa de Hits: %.2f%%\n", getPageHitRate());
        System.out.printf("Taxa de Page Faults: %.2f%%\n", getPageFaultRate());
        System.out.println("-".repeat(20));
    }
}