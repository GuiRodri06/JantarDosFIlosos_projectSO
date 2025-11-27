import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira quantos filosofos vao comer: ");
        int numPhi = scanner.nextInt();

        List<Philosopher> philosopherList = new ArrayList<>();
        for (int i = 0; i <= numPhi; i++) {
            Philosopher philosopher = new Philosopher(i); // ADICIONAR OS OUTROS PARAMETROS DO CONSTRUTOR
            philosopherList.add(philosopher);
        }


        scanner.close();

    }
}