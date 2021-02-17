import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        CommandProcessor commandProcessor = new CommandProcessor();

        while (true) {
            String command = reader.readLine();

            String[] evaluated = CommandEvaluator.EvaluateCommand(command);

            commandProcessor.ProcessCommand(evaluated);
        }
    }
}
