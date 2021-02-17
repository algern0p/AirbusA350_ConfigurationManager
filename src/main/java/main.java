import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        CommandProcessor commandProcessor = new CommandProcessor();

        while (true) {

            System.out.println("");
            System.out.print("> ");

            String command = reader.readLine();

            String[] evaluated = CommandEvaluator.EvaluateCommand(command);

            if (evaluated == null){
                System.out.println(String.format("%s, could not be processed", command));
                continue;
            }

            commandProcessor.ProcessCommand(evaluated);
        }
    }
}
