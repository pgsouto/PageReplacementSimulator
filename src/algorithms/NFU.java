package algorithms;

import java.util.*;

public class NFU {
    private int pageFaultCounter;
    private int pageHitCounter;
    private int capacity;
    private Map<Integer, Integer> frequencyTable;
    private Set<Integer> memory;

    public NFU(List<Integer> referenceString, int capacity) {
        this.pageFaultCounter = 0;
        this.pageHitCounter = 0;
        this.capacity = capacity;
        this.frequencyTable = new HashMap<>();
        this.memory = new LinkedHashSet<>();
        simulate(referenceString);
        printStats();
    }

    private void simulate(List<Integer> referenceList) {
        for (int page : referenceList) {
            if (memory.contains(page)) {
                pageHitCounter++;
                frequencyTable.put(page, frequencyTable.get(page) + 1);
            } else {
                pageFaultCounter++;
                if (memory.size() == capacity) {
                    int victim = findLeastFrequentlyUsed();
                    memory.remove(victim);
                    frequencyTable.remove(victim);
                }
                memory.add(page);
                frequencyTable.put(page, 1);
            }
        }
    }

    private int findLeastFrequentlyUsed() {
        return memory.stream()
                .min(Comparator.comparingInt(frequencyTable::get))
                .orElseThrow();
    }

    public void printStats() {
        System.out.println("ALGORITMO NFU");
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
