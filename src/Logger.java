import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Logger {

    private static ConcurrentLinkedQueue<LogEvento> eventos = new ConcurrentLinkedQueue<>();

    // Registar evento pelos filósofos
    public static void registar(String msg) {
        eventos.add(new LogEvento(LocalTime.now(), msg));
    }

    // Imprimir no ecrã na ordem temporal
    public static void imprimirOrdenado() {
        List<LogEvento> lista = new ArrayList<>(eventos);
        lista.sort(Comparator.comparing(e -> e.tempo));

        for (LogEvento e : lista) {
            System.out.println(e.msg);
        }
    }

    // Gravar num ficheiro .txt
    public static void gravarTXT(String nomeFicheiro) throws IOException {
        List<LogEvento> lista = new ArrayList<>(eventos);
        lista.sort(Comparator.comparing(e -> e.tempo));

        FileWriter fw = new FileWriter(nomeFicheiro);

        for (LogEvento e : lista) {
            fw.write(e.msg + "\n");
        }

        fw.close();
    }

    // Classe interna para guardar tempo + mensagem
    private static class LogEvento {
        LocalTime tempo;
        String msg;

        LogEvento(LocalTime tempo, String msg) {
            this.tempo = tempo;
            this.msg = msg;
        }
    }
}