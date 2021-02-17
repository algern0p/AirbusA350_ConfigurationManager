import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandEvaluator {

    public static String[] EvaluateCommand(String command) {
        var singleCommands = new String[]{"show current configuration", "show groups", "show missing configuration", "build"};

        if (Arrays.asList(singleCommands).contains(command)) {
            return new String[]{command};
        }

        var extracted = ExtractArguments(command);
        return extracted;
    }

    private static String[] ExtractArguments(String command) {
        String showComponentsRegex = "(show components) (like|in group) (\\w*)";
        String addQuantityRegex = "(set quantity for component) (\\w*) (\\d*)";

        String[] extracted = getRegexGroups(showComponentsRegex, command);

        return extracted == null ? extracted : getRegexGroups(addQuantityRegex, command);
    }

    private static String[] getRegexGroups(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            List<String> groups = new ArrayList<String>();
            for (int i = 0; i < matcher.groupCount() + 1; i++) {
                groups.add(matcher.group(i));
            }
            return groups.toArray(new String[0]);
        }
        return null;
    }

}
