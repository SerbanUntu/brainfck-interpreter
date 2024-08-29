public class InterpreterError extends Error {
    public FilePosition pos;
    public String head;
    public InterpreterErrorType type;

    public InterpreterError(FilePosition pos, InterpreterErrorType type) {
        super(switch (type) {
            case UNEXPECTED_CLOSING_BRACKET -> "Expected a preceding opening bracket.";
            case UNCLOSED_BRACKET -> "Expected a closing bracket \"]\" before the end of the file.";
            case OUT_OF_RANGE_LOWER, OUT_OF_RANGE_UPPER ->
                "Can only access memory between locations 0 and " + (Executer.MEMORY_CAPACITY - 1) + ".";
        });
        this.head = switch (type) {
            case UNEXPECTED_CLOSING_BRACKET -> "Unexpected character: \"]\".";
            case UNCLOSED_BRACKET -> "Unclosed bracket: \"[\".";
            case OUT_OF_RANGE_LOWER -> "Out of range: index -1.";
            case OUT_OF_RANGE_UPPER -> "Out of range: index " + Executer.MEMORY_CAPACITY + ".";
        };
        this.pos = pos;
        this.type = type;
    }

    public String getFullMessage() {
        return "[Line " + this.pos.line + ":" + this.pos.col
                + "] Error: " + this.head + "\n" + this.getMessage();
    }
}