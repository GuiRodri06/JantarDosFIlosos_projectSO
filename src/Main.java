import java.io.IOException;
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

        // Criar Garfo passando o logger (se quiseres que Garfo use logger)
        Garfo controleGarfos = new Garfo(numPhi);

        
        Thread[] threads = new Thread[numPhi];
        for (int i = 0; i < numPhi; i++) {
            Philosopher philosopher = new Philosopher(i, controleGarfos);
            threads[i] = new Thread(philosopher, "Filosofo-" + i);
            threads[i].start();
        }
       

        // Esperar todas as threads terminarem
        for (int i = 0; i < numPhi; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        
        // Imprimir no terminal na ordem temporal (ordenado)
        Logger.imprimirOrdenado();

        // Gravar no ficheiro (tratamento de erro)
        try {
            Logger.gravarTXT("filosofos_log.txt");
            System.out.println("Log gravado em filosofos_log.txt");
            
        } catch (IOException e) {
            System.err.println("Erro ao gravar log: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}
