public class CounterThread  extends Thread {
    private Contador contador;

    public CounterThread(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            contador.increment();
        }
    }
}
