import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public void main() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while (true) {
            String command = reader.readLine();

            switch (command) {
                case "show current configuration":
                    break;

                case "show groups":
                    break;

                case "show missing configuration":
                    break;

                case "build":
                    break;

                default:

            }
        }
    }
}
