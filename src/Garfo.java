public class Garfo {
    
    private final int id;
    private Philosopher ocupante;

    public Garfo(int id) {
        this.id = id;
        this.ocupante = null;
    }

    public int getId() {
        return id;
    }

    public synchronized int getOcupanteId() {
        return ocupante == null ? -1 : ocupante.getId();
    }

    public synchronized void segurar (Philosopher f) throws InterruptedException {
        if (f == null) {
            throw new IllegalArgumentException("");
        }


    public synchronized void largar (Philosopher f) {
        if (ocupante == f) {
            ocupante = null;
            notifyAll();
        }
    }
}