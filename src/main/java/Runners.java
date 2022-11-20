public class Runners implements Runnable
{
    private int id;
    private Obraz obraz;


    private int my_start;
    private int my_end;
    private int my_stride;

    public Runners(int id, int p, Obraz obraz) {
        this.id = id;
        this.obraz = obraz;
        this.my_end = p*(id+1);
        this.my_start = p*id;
        this.my_stride = 1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            obraz.calculate_histogram_runners(my_start, my_end, my_stride);
            obraz.print_histogram_runners(id, my_start, my_end, my_stride);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

