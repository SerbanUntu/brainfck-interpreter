import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println(
                    "Please provide a path to the target .bf file.\nThe correct format is `java Main <path-to-file>`.");
            return;
        }
        Path pathToFile = Path.of(args[0]);
        String fileContent;
        try {
            fileContent = Files.readString(pathToFile);
        } catch (IOException err) {
            System.err.println("The file at path \"" + args[0]
                    + "\" does not exist.\nPlease enter a valid path to a target .bf file.");
            return;
        }
        if (!args[0].endsWith(".bf")) {
            System.err.println("The file at path \"" + args[0]
                    + "\" is not a valid Brainf*ck file.\nOnly files ending with .bf are valid targets for the interpreter.");
            return;
        }
        try {
            Analyzer.analyze(fileContent);
            Executer.execute(fileContent);
        } catch (Error err) {
            System.err.println(err.getMessage());
        }
    }
}