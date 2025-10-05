package Ex2;

public class NameThread extends Thread {

    private String name;

    public NameThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Eu sou a Thread " + name);
            long timeToSleep = (long) ((Math.random() + 1) * 1000);
            try {
                sleep(timeToSleep);
            } catch (InterruptedException e) {
                System.out.println("Sou a Thread " + name + " e fui interompida");
                return;
            }
        }

    }

    public static void main(String[] args) {
        NameThread Luis = new NameThread("Luis");
        NameThread Teresa = new NameThread("Teresa");
        NameThread Rodrigo = new NameThread("Rodrigo");

        Luis.start();
        Teresa.start();
        Rodrigo.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Sou O Main e fui interompido");
            return;
        }

        Luis.interrupt();
        Teresa.interrupt();
        Rodrigo.interrupt();

        try {
            Luis.join();
            Teresa.join();
            Rodrigo.join();
        } catch (InterruptedException e) {
            System.out.println("Sou O Main e fui interompido");
            return;
        }


        System.out.println("As Thread Acabaram e vou terminar");

    }
}