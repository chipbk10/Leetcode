package lib;

public class Printer {

    public static void print(int[] A) {
        print(A, " ");
    }

    public static void print(int[] A, String separate) {
        for (int a : A)
            print(a, separate);
    }

    public static void print(int a) {
        print(a, "");
    }

    public static void print(int a, String s) {
        System.out.print(a + s);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void println(int a) {
        println(a, "");
    }

    public static void println(int a, String s) {
        System.out.println(a + s);
    }

    public static void println(int[] A) {
        print(A);
        newLine();
    }

    public static void println(int[] A, String separate) {
        for (int a : A)
            print(a, separate);
        newLine();
    }
}
