public class Analyzer {
    public static void analyze(String fileContent) throws Error {
        int currentLine = 1;
        int currentCol = 1;
        int bracketsCount = 0;
        int lineOfLastOpenBracket = 0;
        int colOfLastOpenBracket = 0;
        for (char ch : fileContent.toCharArray()) {
            switch (ch) {
                case '\n':
                    currentLine++;
                    currentCol = 0;
                    break;
                case '[':
                    bracketsCount++;
                    lineOfLastOpenBracket = currentLine;
                    colOfLastOpenBracket = currentCol;
                    break;
                case ']':
                    if (bracketsCount == 0) {
                        throw new Error("[Line " + currentLine + ":" + currentCol
                                + "] Unexpected character: \"]\".\nExpected a preceding opening bracket.");
                    }
                    bracketsCount--;
            }
            currentCol++;
        }
        if (bracketsCount > 0) {
            throw new Error(
                    "[Line " + lineOfLastOpenBracket + ":" + colOfLastOpenBracket + "] Unclosed bracket: \"[\".");
        }
    }
}
