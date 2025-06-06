package org.example;

public class TestStatistics {
    int total = 0;
    int passed = 0;
    int failed = 0;

    public void printSummary() {
        System.out.println("\nTest Statistics:");
        System.out.println("Total tests: " + total);
        System.out.println("Passed     : " + passed);
        System.out.println("Failed     : " + failed);
    }
}
