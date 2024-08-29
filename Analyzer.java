public class Analyzer {
    public static void analyze(String fileContent) throws InterpreterError {
        FilePosition cursor = new FilePosition(1, 1);
        FilePosition lastOpenBracket = new FilePosition(0, 0);
        int bracketsCount = 0;
        for (char ch : fileContent.toCharArray()) {
            switch (ch) {
                case '\n':
                    cursor.line++;
                    cursor.col = 0;
                    break;
                case '[':
                    bracketsCount++;
                    lastOpenBracket.line = cursor.line;
                    lastOpenBracket.col = cursor.col;
                    break;
                case ']':
                    if (bracketsCount == 0)
                        throw new InterpreterError(cursor, InterpreterErrorType.UNEXPECTED_CLOSING_BRACKET);
                    bracketsCount--;
            }
            cursor.col++;
        }
        if (bracketsCount > 0)
            throw new InterpreterError(lastOpenBracket, InterpreterErrorType.UNCLOSED_BRACKET);
    }
}
