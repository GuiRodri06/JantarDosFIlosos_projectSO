import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    // Usamos o StringBuilder dentro de um bloco synchronized para garantir a segurança da thread.
    private static final StringBuilder logBuffer = new StringBuilder();

    // Métodos estáticos para controle:

    // 1. Anexar mensagem (chamado durante a simulação)
    public static synchronized void appendLog(String msg) {
        // Captura a mensagem e anexa ao buffer, garantindo que o System.out também funcione
        System.out.println(msg); // Imprime imediatamente no terminal
        logBuffer.append(msg).append("\n"); // Anexa ao buffer para gravação futura
    }

    // 2. Gravar o buffer no arquivo (chamado no final)
    public static void gravarTXT(String nomeFicheiro) throws IOException {
        try (FileWriter fw = new FileWriter(nomeFicheiro)) {
            fw.write(logBuffer.toString());
        }
    }
}
