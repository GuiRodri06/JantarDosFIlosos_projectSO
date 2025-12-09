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
        synchronized (System.out) {
            System.out.println("O filosofo " + (id+1) + " esta a comer a " +refeicoes+ "ª refeição");
            Thread.sleep(1000);
        }
    }

    public void think() throws InterruptedException {
        setStatus(0); // Estado = pensar
        synchronized (System.out) {
            System.out.println("O filosofo " + (id+1) + " esta a pensar");
            Thread.sleep(1000);
        }
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
            while (refeicoes < 3) { // Limita a 5 refeições para vermos o fim da execução

                think();

                // TENTA PEGAR GARFOS (A thread bloqueia se necessário)
                controleGarfos.pegar(this);

                // COMER (Se pegou com sucesso)
                eat();

                // LIBERAR GARFOS
                controleGarfos.liberar(this);

                if(refeicoes == 3) {
                    synchronized (System.out) {
                        System.out.println("o filosofo " + (id+1) +" foi embora satisfeito!");
                        Thread.sleep(1000);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
