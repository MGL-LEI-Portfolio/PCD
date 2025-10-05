package Ex3;

import java.util.Observable;

public class Car extends Observable implements Runnable {
    private int id;
    private int limit;
    private int position=0;

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public Car(int id, int limit) {
        super();
        this.id = id;
        this.limit = limit;
    }

    @Override
    public void run(){
        while(true){
            position++;
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
