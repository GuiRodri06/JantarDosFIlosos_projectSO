import java.util.concurrent.Semaphore;

public class Garfo {

    private final Semaphore[] semaforosGarfos;

    public Garfo (int n) {
        
        semaforosGarfos = new Semaphore[n];

        //Inicializa os semaforos dos garfos como livres (1)
        for (int i = 0; i < n; i++) {
            semaforosGarfos[i] = new Semaphore(1); //cada garfo começa disponivel

        }
    }

    //Metodo que tenta pegar os dois garfos
    public void pegar(Philosopher f) throws InterruptedException {

        int id = f.getId();
        int esquerdo = id;
        int direito = (id +1) % semaforosGarfos.length; // (id + 1, com loop)

        if (id == semaforosGarfos.length -1) {

            semaforosGarfos[direito].acquire();
            semaforosGarfos[esquerdo].acquire();
        }
        else {
            semaforosGarfos[esquerdo].acquire();
            semaforosGarfos[direito].acquire();
    }

        Logger.registar("O filosofo " + (f.getId() + 1)  + " pegou os garfos " + esquerdo + " e " + direito);

        
    }

    public void liberar (Philosopher f) {

        int id = f.getId();
        int esquerda = id;
        int direita = (id + 1) % semaforosGarfos.length;

        //Soltar os garfos

        semaforosGarfos[esquerda].release();
        semaforosGarfos[direita].release();


        Logger.registar("O filosofo " + (f.getId() + 1)  + " soltou os garfos " + esquerda + " e " + direita);
    }
}
