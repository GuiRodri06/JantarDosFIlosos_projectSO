import java.util.concurrent.Semaphore;

public class Garfo {
   
    private final Semaphore mutex;
    private final Semaphore[] semaforosGarfos; 

    public Garfo (int n) {
        mutex = new Semaphore(1); //Apenas um filosofo pode acessar a seçao critica de cada vez
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

        //Seção crítica para pegar os garfos
        mutex.acquire(); //Adquire o mutex para acesso exclusivo

        semaforosGarfos[esquerdo].acquire(); 
        semaforosGarfos[direito].acquire(); 

        mutex.release(); //Libera o mutex após pegar os garfos

        System.out.println("O filosofo " + (f.getId() + 1) + " pegou os garfos " + esquerdo + " e " + direito);
        f.setStatus(1);

    }

    public void liberar (Philosopher f) {

        int id = f.getId();
        int esquerda = id; 
        int direita = (id + 1) % semaforosGarfos.length;

        //Soltar os garfos

        semaforosGarfos[esquerda].release(); 
        semaforosGarfos[direita].release();

        System.out.println("O filosofo " + (f.getId() + 1) + " soltou os garfos " + esquerda + " e " + direita);
    }
}