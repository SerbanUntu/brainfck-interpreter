import java.util.Scanner;

public class Executer {
    final static short MEMORY_CAPACITY = 30_000;

    public static void execute(String fileContent) throws Error {
        int currentLine = 1;
        int currentCol = 1;
        int index = 0;
        int positionOfLastOpenBracket = 0;
        byte[] arr = new byte[MEMORY_CAPACITY];
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        for (int pos = 0; pos < fileContent.length(); pos++) {
            switch (fileContent.charAt(pos)) {
                case '\n':
                    currentLine++;
                    currentCol = 0;
                    break;
                case '<':
                    if (index == 0) {
                        throw new Error("[Line " + currentLine + ":" + currentCol
                                + "] Out of range: index -1.\nCan only access memory between locations 0 and "
                                + (MEMORY_CAPACITY - 1) + ".");
                    }
                    index--;
                    break;
                case '>':
                    index++;
                    if (index == MEMORY_CAPACITY) {
                        throw new Error("[Line " + currentLine + ":" + currentCol
                                + "] Out of range: index " + MEMORY_CAPACITY
                                + ".\nCan only access memory between locations 0 and "
                                + (MEMORY_CAPACITY - 1) + ".");
                    }
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
            }
            currentCol++;
        }
        System.out.print('\n');
        scanner.close();
    }
}
