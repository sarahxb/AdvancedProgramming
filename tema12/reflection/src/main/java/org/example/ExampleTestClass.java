package org.example;

public class ExampleTestClass {

    @Test
    public void testNoArgs() {
        System.out.println("Test without args");
    }

    @Test
    public void testWithArgs(int a, String b) {
        System.out.println("Arguments: " + a + ", " + b);
    }
}
