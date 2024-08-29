public class FilePosition {
    public int line;
    public int col;

    public FilePosition(int line, int col) {
        this.line = line;
        this.col = col;
    }

    @Override
    public String toString() {
        return "[Line " + this.line + ":" + this.col + "]";
    }
}