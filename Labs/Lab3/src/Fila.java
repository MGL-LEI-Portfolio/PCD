import java.util.ArrayList;

public class Fila {
    private ArrayList<Integer> fila;
    int last;

    public Fila() {
        fila = new ArrayList<Integer>();
        last = 0;
    }

    public boolean empty() {
        return fila.isEmpty();
    }

    public synchronized int peek() {
        return fila.getFirst();

    }

    public synchronized int poll() {
        int intToReturn = fila.getFirst();
        fila.remove(0);
        return intToReturn;
    }

    public synchronized void offer(int item) {
        fila.add(item);
    }

    public int size() {
        return fila.size();
    }
}