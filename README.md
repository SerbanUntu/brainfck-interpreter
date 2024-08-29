# Brainf\*ck interpreter

## How to use

### Prerequisites

- You must have Java installed on your machine. You can test this by using the command `java --version` in the terminal.

### Instructions

1. Download the repository.
1. Navigate to the repository and create a file ending in `.bf` in the root of the repository[^1].
1. Write the desired Brainf\*ck program in the `.bf` file.
1. Run `javac Main.java` from the terminal. Make sure you run this command from the root folder of the repository[^2].
1. Run `java Main <name>.bf`, replacing `<name>` with the actual name of the `.bf` file you created previously.

[^1]: The file can be anywhere in the repository as long as a valid path to it is entered when running step 5.
[^2]: This step can be skipped when rerunning the program. Only step 5 needs to be followed again.

## My approach

### Interpreter

The `Main.java` file is concerned with processing the target `.bf` file and coordinating the steps of the interpreting process.

It will also catch any errors related to the executing command or the Brainf\*ck program itself.

```java
// Main.java
Path pathToFile = Path.of(args[0]);
String fileContent;
try {
	fileContent = Files.readString(pathToFile);
} catch (IOException _err) {
	System.err.println("The file at path \"" + args[0]
		    + "\" does not exist.\nPlease enter a valid path to a target .bf file.");
	return;
}
```

The `Analyzer.java` file ensures the target file is not evaluated if it has any syntax errors.

It will check for the only two possible types of syntax errors, both related to brackets.

```java
// Analyzer.java
switch (ch) {
    // ...
    case ']':
        if (bracketsCount == 0)
            throw new InterpreterError(cursor, InterpreterErrorType.UNEXPECTED_CLOSING_BRACKET);
        bracketsCount--;
}
```

The `Evaluator.java` file processes each valid Brainf\*ck character, printing to the console and throwing runtime errors (if needed).

```java
// Evaluator.java
for (int fileIndex = 0; fileIndex < fileContent.length(); fileIndex++) {
    // An index is used instead of looping over chars so the index can jump back and forth when evaluating loops.
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
        // ...
    }
}
```

### Error handling

The `InterpreterError` inherits from the `Error` type, storing additional data. It is used for error handling throughout the program.

To report errors exactly where they occur, the current position in the file is stored in a `FilePosition` object with the `line` and `col` (column) fields respectively.

The `line` property increases each time a line feed character `'\n'` is encountered.

Each `InterpreterError` also stores a `FilePosition` object which is printed alongside the error message.

```java
// InterpreterError.java
public String getFullMessage() {
    return this.pos.toString() + " Error: " + this.head + "\n" + this.getMessage();
}
```
