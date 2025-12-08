public class Philosopher implements Runnable {

    private Integer id;
    private Garfo rightFork;
    private Garfo leftFork;
    private int status;

    public Philosopher(Integer id) {
        this.id = id;
        this.status = 0; //Estado inicial
    }

    public Philosopher(Integer id, Garfo rightFork, Garfo leftFork) {
        this.id = id;
        this.rightFork = rightFork;
        this.leftFork = leftFork;

        this.status = 0; //Estado inicial
    }

    public void eat(Integer id) throws InterruptedException {
        setStatus(1);// Estado = comer

        System.out.println("O filosofo " + id + "estas a comer");
        Thread.sleep(1000);
    }

    public void think(Integer id) throws InterruptedException {
        setStatus(0); // Estado = pensar

        System.out.println("O filosofo " + id + "esta a pensar");
        Thread.sleep(1000);
    }

    public Integer getId() {
        return id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() { // controlo/debug
        return status;
    }


    @Override
    public void run() {
        try {
            think(id);

            eat(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
