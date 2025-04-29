package algorithms;

import paging.Page;

import java.util.*;

public class Aging {
    /*
    private static class Page {
        int number;
        int counter;

        Page(int number) {
            this.number = number;
            this.counter = 0;
        }
    }*/

    public int getPageHitCounter() {
        return pageHitCounter;
    }

    public int getPageFaultCounter() {
        return pageFaultCounter;
    }

    private int pageFaultCounter;
    private int pageHitCounter;
    private int capacity;
    private List<Page> memory;

    public Aging(List<Integer> referenceString, int capacity) {
        this.pageFaultCounter = 0;
        this.pageHitCounter = 0;
        this.capacity = capacity;
        this.memory = new ArrayList<>();
        simulate(referenceString);
        printStats();
    }

    private void simulate(List<Integer> referenceList) {
        for (int ref : referenceList) {
            boolean hit = false;
            for (Page p : memory) {
                p.counter >>= 1;
                if (p.number == ref) {
                    p.counter |= 1 << 7;
                    hit = true;
                    pageHitCounter++;
                }
            }

            if (!hit) {
                pageFaultCounter++;
                if (memory.size() < capacity) {
                    Page newPage = new Page(ref);
                    newPage.counter |= 1 << 7;
                    memory.add(newPage);
                } else {
                    Page victim = Collections.min(memory, Comparator.comparingInt(p -> p.counter));
                    memory.remove(victim);
                    Page newPage = new Page(ref);
                    newPage.counter |= 1 << 7;
                    memory.add(newPage);
                }
            }
        }
    }

    public void printStats() {
        System.out.println("ALGORITMO AGING");
        System.out.printf("Page Faults: %d\n", pageFaultCounter);
        System.out.printf("Page Hits: %d\n", pageHitCounter);
        System.out.printf("Taxa de Hits: %.2f%%\n", getPageHitRate());
        System.out.printf("Taxa de Page Faults: %.2f%%\n", getPageFaultRate());
        System.out.println("-".repeat(20));
    }

    private float getPageHitRate() {
        int total = pageHitCounter + pageFaultCounter;
        return total == 0 ? 0 : (float) pageHitCounter / total * 100;
    }

    private float getPageFaultRate() {
        int total = pageHitCounter + pageFaultCounter;
        return total == 0 ? 0 : (float) pageFaultCounter / total * 100;
    }
}
