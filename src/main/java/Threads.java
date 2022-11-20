public class Threads extends Thread {

    private int id;
    private char symbol;
    private Obraz obraz;


    public Threads(int id, char symbol, Obraz obraz) {
        this.id = id;
        this.symbol = symbol;
        this.obraz = obraz;
    }

    @Override
    public void run() {
            obraz.calculate_histogram_threads(id, symbol);
            obraz.print_histogram_threads(id, symbol);
    }
}
