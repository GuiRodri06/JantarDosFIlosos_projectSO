import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira quantos filósofos vão comer (N >= 2): ");
        int numPhi = scanner.nextInt();
        scanner.close();

        if (numPhi < 2) {
            System.err.println("São necessários pelo menos 2 filósofos.");
            return;
        }

        // 1. Instanciar o controle central (Garfo)
        Garfo controleGarfos = new Garfo(numPhi);

        // 2. Criar e conectar os Filósofos, e iniciar as Threads
        for (int i = 0; i < numPhi; i++) {
            // O filósofo recebe seu ID e a referência ao controle de garfos
            Philosopher philosopher = new Philosopher(i, controleGarfos);

            Thread thread = new Thread(philosopher, "Filosofo-" + i);
            thread.start();
        }

        System.out.println(numPhi + " Filósofos e suas Threads iniciadas. Observando...");

        scanner.close();
    }
}