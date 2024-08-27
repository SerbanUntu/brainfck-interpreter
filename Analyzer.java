public class Analyzer {
    public static void analyze(String fileContent) throws Error {
        int currentLine = 1;
        int bracketsCount = 0;
        int lineOfLastOpenBracket = 0;
        for (char ch : fileContent.toCharArray()) {
            switch (ch) {
                case '\n':
                    currentLine++;
                    break;
                case '[':
                    bracketsCount++;
                    lineOfLastOpenBracket = currentLine;
                    break;
                case ']':
                    if (bracketsCount == 0) {
                        throw new Error("[Line " + currentLine
                                + "] Unexpected character: \"]\".\nExpected a preceding opening bracket.");
                    }
                    bracketsCount--;
                default:
                    continue;
            }
        }
        if (bracketsCount > 0) {
            throw new Error("[Line " + lineOfLastOpenBracket + "] Unclosed bracket: \"[\".");
        }
    }
}
