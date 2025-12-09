public class Philosopher implements Runnable {

    private Integer id;
    private Garfo controleGarfos;
    private int status; // 0=Pensando, 1=Comendo
    private int refeicoes = 0; // UTILIZAR PARA CONTAR

    public Philosopher(Integer id, Garfo controleGarfos) {
        this.id = id;
        this.controleGarfos = controleGarfos;
        this.status = 0;
    }

    public void eat() throws InterruptedException {
        setStatus(1);// Estado = comer
        this.refeicoes++;
        System.out.println("O filosofo " + id + " esta a comer a " +refeicoes+ "ª refeição");
        Thread.sleep(1000);
    }

    public void think() throws InterruptedException {
        setStatus(0); // Estado = pensar
        System.out.println("O filosofo " + id + " esta a pensar");
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
            while (refeicoes < 5) { // Limita a 5 refeições para vermos o fim da execução

                think();
                // 2. TENTA PEGAR GARFOS (A thread bloqueia se necessário)
                controleGarfos.pegar(this);

                // 3. COMER (Se pegou com sucesso)
                eat();

                // 4. LIBERAR GARFOS
                controleGarfos.liberar(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
