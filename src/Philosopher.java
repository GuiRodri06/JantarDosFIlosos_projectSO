public class Philosopher implements Runnable {

    private Integer id;
    private Garfo rightFork;
    private Garfo leftFork;

    public Philosopher(Integer id) {
        this.id = id;
    }

    public Philosopher(Integer id, Garfo rightFork, Garfo leftFork) {
        this.id = id;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    public void eat(Integer id) throws InterruptedException {
        System.out.println("O filosofo " + id + "estas a comer");
        Thread.sleep(1000);
    }

    public void think(Integer id) throws InterruptedException {
        System.out.println("O filosofo " + id + "esta a pensar");
        Thread.sleep(1000);
    }

    public Integer getId() {
        return id;
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
