public class Philosopher {

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

    public void eat(Integer id) {
        System.out.println("O filosofo " + id + "estas a comer");
    }

    public void think(Integer id) {
        System.out.println("O filosofo " + id + "esta a pensar");
    }

    public Integer getId() {
        return id;
    }


}
