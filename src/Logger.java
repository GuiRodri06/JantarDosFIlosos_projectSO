import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Logger {

    private static ConcurrentLinkedQueue<LogEvento> eventos = new ConcurrentLinkedQueue<>();
    private static AtomicLong sequencia = new AtomicLong(0);

    // Registar evento com ordem absoluta
    public static void registar(String msg) {
        long ordem = sequencia.incrementAndGet();  
        eventos.add(new LogEvento(ordem, msg));
    }

    // Imprimir no ecrã na ordem real
    public static void imprimirOrdenado() {
        List<LogEvento> lista = new ArrayList<>(eventos);
        lista.sort(Comparator.comparingLong(e -> e.ordem));

        for (LogEvento e : lista) {
            System.out.println(e.msg);
        }
    }

    // Gravar num ficheiro .txt
    public static void gravarTXT(String nomeFicheiro) throws IOException {
        List<LogEvento> lista = new ArrayList<>(eventos);
        lista.sort(Comparator.comparing(e -> e.ordem));

        FileWriter fw = new FileWriter(nomeFicheiro);

        for (LogEvento e : lista) {
            fw.write(e.msg + "\n");
        }
        fw.close();
    }

    private static class LogEvento {
        long ordem;   // Sequência absoluta e crescente
        String msg;

        LogEvento(long ordem, String msg) {
            this.ordem = ordem;
            this.msg = msg;
        }
    }
}
