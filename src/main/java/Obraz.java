import  java.util.Random;

class Obraz {

    private int size_n;
    private int size_m;
    private char[][] tab;
    private char[] tab_symb;
    private int[] histogram;
    private int[] histogran_threads;
    private int[] histogram_runners;

    public Obraz(int n, int m) {

        this.size_n = n;
        this.size_m = m;
        tab = new char[n][m];
        tab_symb = new char[94];

        final Random random = new Random();

        for(int k=0;k<94;k++) {
            tab_symb[k] = (char)(k+33);
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tab[i][j] = tab_symb[random.nextInt(94)];  // ascii 33-127
                //tab[i][j] = (char)(random.nextInt(94)+33);  // ascii 33-127
                System.out.print(tab[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");

        histogram = new int[94];
        histogram_runners = new int[94];
        histogran_threads = new int[94];
        clear_histogram();
    }

    public void clear_histogram(){

        for(int i=0;i<94;i++)
        {
            histogram[i] = 0;
            histogran_threads[i] = 0;
            histogram_runners[i] = 0;
        }

    }

    public void calculate_histogram() {

        for(int i=0;i<size_n;i++) {
            for(int j=0;j<size_m;j++) {
                for(int k=0;k<94;k++) {
                    if(tab[i][j] == tab_symb[k]) histogram[k]++;
                }

            }
        }
    }

    public void print_histogram(){

        System.out.print("Wynik kodu sekwencyjnego\n");

        for(int i=0;i<94;i++) {
            System.out.print(tab_symb[i]+" "+histogram[i]+"\n");
        }

    }

    public synchronized void calculate_histogram_threads(int k, char c) {

        for (int i = 0; i < size_n; i++) {
            for (int j = 0; j < size_m; j++) {

                if (tab[i][j] == c)
                {
                    histogran_threads[k]++;
                }
            }
        }
    }

    public void calculate_histogram_runners(int start, int end, int stride) {

        for (int i = 0; i < size_n; i++) {
            for (int j = 0; j < size_m; j++) {

                for(int k = start; k < end; k+=stride) {
                    if (tab[i][j] == tab_symb[k]) {
                        histogram_runners[k]++;
                    }
                }
            }
        }
    }

    public synchronized  void print_histogram_threads(int i, char c){
            System.out.print("Watek "+i +" ( "+ c + " ): "+histogran_threads[i]+"\n");
    }

    public void print_histogram_runners(int id, int start, int end, int stride){
        for(int k = start; k < end; k+=stride) {
            System.out.print("Watek " + id + " ( " + tab_symb[k] + " ): " + histogram_runners[k] + "\n");
        }
    }
}