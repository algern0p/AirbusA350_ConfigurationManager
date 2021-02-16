import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public void main(){

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while(true){

            System.out.print("> ");
                        
            try{
                String command = reader.readLine();
                CommandEvaluator.EvaluateCommand(command);
            }
            catch (Exception exception){
                break;
            }
        }
    }
}
