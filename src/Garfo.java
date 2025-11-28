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

    /**
     * Retorna o Id do filosofo qu esta com o garfo se estiver livre retorna -1
     * @return ID do ocupante ou -1 se o garfo estiver livre
     */

    public synchronized int getOcupanteId() {
        return ocupante == null ? -1 : ocupante.getId();
    }


    /**
     * Metodo que permite um filosofo segurar o garfo 
     * A thread so prossegue quando o garfo estiver livre
     * @throws InterruptedException se a thread for interrompida enquanto espera
     */

    public synchronized void segurar (Philosopher f) throws InterruptedException {
        if (f == null) {
            throw new IllegalArgumentException("O filósofo não pode ser nulo.");
        }

    // Espera enquanto o garfo estiver ocupado
    while (ocupante != null) {
        wait();
    }

    ocupante = f;
    }


    /*
    *Libera o garfo caso o filosofo informado seja realmente aquele que ta com o garfo ~
    * depois de liberar o notifyAll e utilizado para evitar starvation
    */
    public synchronized void largar (Philosopher f) {
        if (ocupante == f) { // Apenas libera o garfo se o filósofo for realmente o ocupante.
            ocupante = null;
            notifyAll();  // Notifica todas as threads que estão possivelmente esperando.
        }
    }
}