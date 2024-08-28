import java.util.Scanner;

public class Executer {
    public static void execute(String fileContent) throws Error {
        int currentLine = 1;
        int index = 0;
        int positionOfLastOpenBracket = 0;
        byte[] arr = new byte[30_000];
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        for (int pos = 0; pos < fileContent.length(); pos++) {
            switch (fileContent.charAt(pos)) {
                case '\n':
                    currentLine++;
                    break;
                case '<':
                    index--;
                    break;
                case '>':
                    index++;
                    break;
                case '+':
                    arr[index]++;
                    break;
                case '-':
                    arr[index]--;
                    break;
                case '.':
                    System.out.print((char) arr[index]);
                    break;
                case ',':
                    arr[index] = (byte) ((char) scanner.next().charAt(0));
                    break;
                case '[':
                    if (arr[index] == 0)
                        while (fileContent.charAt(pos) != ']')
                            pos++;
                    else
                        positionOfLastOpenBracket = pos;
                    break;
                case ']':
                    if (arr[index] != 0)
                        pos = positionOfLastOpenBracket;
                    break;
                default:
                    break;
            }
        }
        System.out.print('\n');
        scanner.close();
    }
}
