import java.util.Scanner;


class Histogram_test{

    static int n, m;
    static int p, stride;
    static char symbol;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Obraz obraz_1 = new Obraz(n, m);

        System.out.println("Set number of threads");
        int num_threads_runnable = scanner.nextInt();
        int num_threads_threads = 94;

        p = 94/num_threads_runnable;


        Threads[] threads = new Threads[num_threads_threads];
        Runners[] threads_r = new Runners[num_threads_runnable];

        System.out.print("Wynik kodu rownoleglego\n");

        for(int i = 0; i< num_threads_threads; i++) {
            symbol = (char)(i+33);
            threads[i] = new Threads(i, symbol, obraz_1);
            threads[i].start();
        }


        for(int i = 0; i < num_threads_runnable; i++) {
            threads_r[i] = new Runners(i, p, obraz_1);
        }

        for (int i = 0; i < num_threads_threads; i++) {
             try {
         	 threads[i].join();
             } catch (InterruptedException e) {}
        }

        for(int i = 0; i < num_threads_runnable; i++) {
            threads_r[i].run();
        }

        obraz_1.calculate_histogram();
        obraz_1.print_histogram();
    }

}