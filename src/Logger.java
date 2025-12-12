import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static final StringBuilder logBuffer = new StringBuilder();

    // 1. Anexar mensagem (chamado durante a simulação)
    public static synchronized void appendLog(String msg) {
        System.out.println(msg);
        logBuffer.append(msg).append("\n");
    }

    // NOVO MÉTODO: Registra o tempo de execução
    public static synchronized void registrarTempoExecucao(long tempoMs) {
        double tempoSegundos = tempoMs / 1000.0;
        String msgTempo = "\n[RESUMO] Tempo Total de Execução: " + tempoSegundos + " segundos.";

        // Adiciona ao final do log buffer
        logBuffer.append(msgTempo).append("\n");
    }

    // 2. Gravar o buffer no arquivo (chamado no final)
    public static void gravarTXT(String nomeFicheiro) throws IOException {
        try (FileWriter fw = new FileWriter(nomeFicheiro)) {
            fw.write(logBuffer.toString());
        }
    }
}