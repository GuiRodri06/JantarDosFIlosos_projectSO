import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        long tempoInicial = System.currentTimeMillis(); // MARCA O TEMPO INICIAL

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira quantos filósofos vão comer (N >= 2): ");
        int numPhi = scanner.nextInt();
        scanner.close();

        // ... (criação de Garfo e Threads) ...
        Garfo controleGarfos = new Garfo(numPhi);

        Thread[] threads = new Thread[numPhi];
        for (int i = 0; i < numPhi; i++) {
            Philosopher philosopher = new Philosopher(i, controleGarfos);
            threads[i] = new Thread(philosopher, "Filosofo-" + i);
            threads[i].start();
        }

        // 1. Esperar todas as threads terminarem (essencial para log completo)
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long tempoFinal = System.currentTimeMillis(); // MARCA O TEMPO FINAL
        long tempoExecucaoMs = tempoFinal - tempoInicial;

        // 2. Registra o tempo total de execução no log buffer
        Logger.registrarTempoExecucao(tempoExecucaoMs);

        // 2. Gravar o buffer no arquivo após o término da simulação
        try {
            Logger.gravarTXT("filosofos_log.txt");
            System.out.println("\n--- SIMULAÇÃO FINALIZADA ---");
            System.out.println("Tempo total de execução: " + (tempoExecucaoMs / 1000.0) + " segundos.");
            System.out.println("O log foi salvo em: filosofos_log.txt");

        } catch (IOException e) {
            System.err.println("Erro ao gravar log: " + e.getMessage());
        }
    }
}
