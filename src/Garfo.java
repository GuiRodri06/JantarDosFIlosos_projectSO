import java.util.concurrent.Semaphore;

public class Garfo {

    private final Semaphore[] semaforosGarfos;

    public Garfo(int n) {

        semaforosGarfos = new Semaphore[n];

        //Inicializa os semaforos dos garfos como livres (1)
        for (int i = 0; i < n; i++) {
            semaforosGarfos[i] = new Semaphore(1); //cada garfo começa disponivel

        }
    }


    public void pegar(Philosopher f) throws InterruptedException {
        int id = f.getId();
        int esquerdo = id;
        int direito = (id + 1) % semaforosGarfos.length;

        // Ordem global: pega sempre primeiro o menor ID
        int primeiro = Math.min(esquerdo, direito);
        int segundo = Math.max(esquerdo, direito);

        semaforosGarfos[primeiro].acquire();
        semaforosGarfos[segundo].acquire();

        Logger.appendLog("O filosofo " + (id + 1) + " pegou os garfos " + primeiro + " e " + segundo);

    }

    public void liberar(Philosopher f) {
        int id = f.getId();
        int esquerdo = id;
        int direito = (id + 1) % semaforosGarfos.length;

        int primeiro = Math.min(esquerdo, direito);
        int segundo = Math.max(esquerdo, direito);

        Logger.appendLog("O filosofo " + (id + 1) + " soltou os garfos " + primeiro + " e " + segundo);

        semaforosGarfos[primeiro].release();
        semaforosGarfos[segundo].release();
    }
}
