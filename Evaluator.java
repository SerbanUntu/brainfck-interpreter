import java.util.Scanner;

public class Evaluator {
    public final static short MEMORY_CAPACITY = 30_000;

    public static void evaluate(String fileContent) throws InterpreterError {
        byte[] arr = new byte[MEMORY_CAPACITY];
        int arrIndex = 0;
        int fileIndexOfLastOpenBracket = 0;
        FilePosition cursor = new FilePosition(1, 1);
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        for (int fileIndex = 0; fileIndex < fileContent.length(); fileIndex++) {
            switch (fileContent.charAt(fileIndex)) {
                case '\n':
                    cursor.line++;
                    cursor.col = 0;
                    break;
                case '<':
                    if (arrIndex == 0) {
                        throw new InterpreterError(cursor, InterpreterErrorType.OUT_OF_RANGE_LOWER);
                    }
                    arrIndex--;
                    break;
                case '>':
                    arrIndex++;
                    if (arrIndex == MEMORY_CAPACITY)
                        throw new InterpreterError(cursor, InterpreterErrorType.OUT_OF_RANGE_UPPER);
                    break;
                case '+':
                    arr[arrIndex]++;
                    break;
                case '-':
                    arr[arrIndex]--;
                    break;
                case '.':
                    System.out.print((char) arr[arrIndex]);
                    break;
                case ',':
                    arr[arrIndex] = (byte) scanner.next().charAt(0);
                    break;
                case '[':
                    if (arr[arrIndex] == 0)
                        while (fileContent.charAt(fileIndex) != ']')
                            fileIndex++;
                    else
                        fileIndexOfLastOpenBracket = fileIndex;
                    break;
                case ']':
                    if (arr[arrIndex] != 0)
                        fileIndex = fileIndexOfLastOpenBracket;
            }
            cursor.col++;
        }
        System.out.print('\n');
        scanner.close();
    }
}
