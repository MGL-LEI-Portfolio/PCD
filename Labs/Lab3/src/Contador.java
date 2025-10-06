import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Contador {

    private AtomicInteger count;


    public Contador() {
        count = new AtomicInteger(0);
    }

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }


    public static void main(String[] args) {
        Contador contador = new Contador();
        CounterThread t1 = new CounterThread(contador);
        CounterThread t2 = new CounterThread(contador);
        CounterThread t3 = new CounterThread(contador);
        Instant start = Instant.now();
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrupted ");
            return;
        }
        Instant end = Instant.now();
        // Calculate the difference in milliseconds
        long millisecondsBetween = ChronoUnit.MILLIS.between(start, end);
        System.out.println("O valor do Contador é " + contador.getCount() + " e demorei " + millisecondsBetween + "ms");


    }
}