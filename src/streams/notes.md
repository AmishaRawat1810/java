You can build a simple mental model with three questions: **binary vs text**, need for **decoding**, and need for **buffering / convenience**. Once you answer those, the “right” stream/reader usually falls out naturally. [w3schools](https://www.w3schools.com/java/java_io_streams.asp)

***

## Step 1: Byte vs character

First decide what kind of data you have.

- Binary data (images, PDFs, zips, any non-text): use **byte streams**: `InputStream` / `OutputStream` and their subclasses like `FileInputStream`, `FileOutputStream`. [scribd](https://www.scribd.com/document/841443101/8-Introduction-and-Implementation-of-Byte-stream-Character-stream-Buffered-strea)
- Text data (JSON, XML, logs, CSV, source code): use **character streams**: `Reader` / `Writer` and their subclasses like `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`. [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)

**Rule of thumb**:
- If you ever care about encoding (UTF‑8, etc.), you are in **text** land → readers/writers.
- If encoding is irrelevant and content is opaque bytes → input/output streams.

Example: reading an image:

```java
try (InputStream in = new FileInputStream("logo.png")) { ... } // binary
```

Example: reading a config file:

```java
try (Reader reader = new FileReader("config.txt")) { ... } // text
```

***

## Step 2: Basic vs “bridging” vs buffered

Once you pick byte vs character, choose the right “layer”. Java I/O classes are meant to be **stacked**. [reddit](https://www.reddit.com/r/java/comments/z7x3c/there_are_so_many_reader_classes_which_one_should/)

### Core “source/sink” classes

These talk directly to the underlying resource.

- Files (binary): `FileInputStream`, `FileOutputStream`. [scribd](https://www.scribd.com/document/841443101/8-Introduction-and-Implementation-of-Byte-stream-Character-stream-Buffered-strea)
- Files (text): `FileReader`, `FileWriter` (character-based, but not buffered). [jcodebook](https://jcodebook.com/courses/complete-java-programming-course/53087/)
- Sockets: usually provide `InputStream` / `OutputStream` (then you wrap them).
- In-memory: `ByteArrayInputStream`, `ByteArrayOutputStream`, `StringReader`, `StringWriter`. [omkare](http://www.omkare.in/Content/TopicMaterial/0525c731230d4309ba2e6ad202320526.pdf)

Use these as your **base**; you rarely use them completely bare except for simple or very small I/O.

### Bridging: bytes ↔ characters

These convert between byte streams and character streams, handling **encoding**. [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)

- `InputStreamReader` – `InputStream` → `Reader` (bytes to chars).  
  Example: socket or file `InputStream` where you want text:

  ```java
  Reader r = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
  ```

- `OutputStreamWriter` – `OutputStream` → `Writer` (chars to bytes).

**Mental model**:  
You already have `InputStream`/`OutputStream` but you want to treat it as **text** → wrap in `InputStreamReader` / `OutputStreamWriter`.

### Buffering: performance and convenience

Anything “Buffered” adds an internal buffer and often convenience methods. [stackoverflow](https://stackoverflow.com/questions/1160050/java-i-o-streams-what-are-the-differences)

- `BufferedInputStream` / `BufferedOutputStream` – for **byte** streams.
- `BufferedReader` / `BufferedWriter` – for **character** streams. [jcodebook](https://jcodebook.com/courses/complete-java-programming-course/53087/)

Effects:

- Improves performance by reading/writing larger chunks instead of single bytes/chars. [campus.datacamp](https://campus.datacamp.com/courses/importing-data-in-java/introduction-to-data-management-in-java?ex=4)
- `BufferedReader` gives you `readLine()`, very convenient for text. [studocu](https://www.studocu.com/in/document/apj-abdul-kalam-technological-university/object-oriented-programming/java-file-reading-input-classes-methods-overview/142556591)

**Rule of thumb**:
- For non-trivial I/O, wrap your base stream/reader in a buffered one.

Example (text file, line by line, with encoding):

```java
try (BufferedReader br = new BufferedReader(
        new InputStreamReader(
            new FileInputStream("data.txt"), StandardCharsets.UTF_8))) {
    String line;
    while ((line = br.readLine()) != null) {
        ...
    }
}
```

***

## Step 3: Practiced combinations (what people actually use)

Here’s a compact “when to use what” table.

### Common file and console patterns

| Scenario                                    | Typical construct                                                                                           | Why this is used in practice                                                                                 |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| Small text file (don’t care about encoding)| `new FileReader("file.txt")`                                                                               | Simple, but platform default encoding and no buffering; fine for quick demos.  [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)                |
| Text file, large or performance‑sensitive  | `new BufferedReader(new FileReader("file.txt"))`                                                           | Buffering + `readLine()`, still uses default encoding.  [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)                               |
| Text file with explicit encoding           | `new BufferedReader(new InputStreamReader(new FileInputStream("file.txt"), UTF_8))`                        | Correct encoding + buffering + `readLine()`. This is the **idiomatic** pattern.  [w3schools](https://www.w3schools.com/java/java_io_streams.asp)            |
| Writing text file, large                   | `new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file.txt"), UTF_8))`                      | Buffered + explicit encoding for output.  [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)                                                     |
| Binary file (image, PDF) read              | `new BufferedInputStream(new FileInputStream("image.png"))`                                                | Binary + buffering; no decoding.  [w3schools](https://www.w3schools.com/java/java_io_streams.asp)                                                      |
| Binary file write                          | `new BufferedOutputStream(new FileOutputStream("image.png"))`                                              | Efficient binary output.  [w3schools](https://www.w3schools.com/java/java_io_streams.asp)                                                                     |
| Parsing simple text input (console, small) | `new Scanner(System.in)` or `new Scanner(new File("data.txt"))`                                            | Easy token-based parsing but slower; great for quick utilities / coding problems.  [studocu](https://www.studocu.com/in/document/apj-abdul-kalam-technological-university/object-oriented-programming/java-file-reading-input-classes-methods-overview/142556591)                  |
| Network text protocol (e.g., HTTP headers) | `new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8))` and similar for output writer | You start from socket streams (bytes), then bridge to chars, then buffer.  [omkare](http://www.omkare.in/Content/TopicMaterial/0525c731230d4309ba2e6ad202320526.pdf)           |

***

## How to reason in your head

You can phrase your mental decision tree like this:

1. **What is my data?**
    - Binary → `InputStream`/`OutputStream` plus buffered if non-trivial.
    - Text → `Reader`/`Writer` or bridge `InputStream` with `InputStreamReader`.

2. **Where is it coming from?**
    - File → `FileInputStream`/`FileOutputStream` (binary) or `FileReader`/`FileWriter` (text) as base. [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)
    - Socket → `InputStream`/`OutputStream` from socket, then decide if you wrap in reader/writer.
    - In-memory → `ByteArray*` or `String*`.

3. **Do I care about encoding?**
    - Yes → use `InputStreamReader`/`OutputStreamWriter` with charset (often UTF‑8). [w3schools](https://www.w3schools.com/java/java_io_streams.asp)
    - No / binary → stay in byte world.

4. **Is performance / convenience a concern?**
    - Yes or file/network not tiny → wrap in `Buffered*`. [stackoverflow](https://stackoverflow.com/questions/1160050/java-i-o-streams-what-are-the-differences)
    - Need `readLine()` → `BufferedReader`.

If you follow that, the class stack essentially chooses itself.

***

## Quick cheat sheet for your brain

When you see each name, think this:

- `InputStream` / `OutputStream`: **Low-level bytes**, base abstraction. [oreilly](https://www.oreilly.com/library/view/java-a-beginners/9780071606325/ch10lev1sec2.html)
- `FileInputStream` / `FileOutputStream`: bytes from/to **file**. [scribd](https://www.scribd.com/document/841443101/8-Introduction-and-Implementation-of-Byte-stream-Character-stream-Buffered-strea)
- `Reader` / `Writer`: **characters** abstraction. [omkare](http://www.omkare.in/Content/TopicMaterial/0525c731230d4309ba2e6ad202320526.pdf)
- `FileReader` / `FileWriter`: characters from/to **file**, default encoding. [jcodebook](https://jcodebook.com/courses/complete-java-programming-course/53087/)
- `InputStreamReader`: **bytes → chars**; attach a charset (UTF‑8). [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)
- `OutputStreamWriter`: **chars → bytes** with charset. [geeksforgeeks](https://www.geeksforgeeks.org/java/java-io-input-output-in-java-with-examples/)
- `BufferedInputStream` / `BufferedOutputStream`: **buffered bytes**, faster I/O. [campus.datacamp](https://campus.datacamp.com/courses/importing-data-in-java/introduction-to-data-management-in-java?ex=4)
- `BufferedReader` / `BufferedWriter`: **buffered chars**, `readLine()` etc. [studocu](https://www.studocu.com/in/document/apj-abdul-kalam-technological-university/object-oriented-programming/java-file-reading-input-classes-methods-overview/142556591)
- `Scanner`: higher-level text parser over `InputStream` or file; convenient, slower. [studocu](https://www.studocu.com/in/document/apj-abdul-kalam-technological-university/object-oriented-programming/java-file-reading-input-classes-methods-overview/142556591)

If you want, I can also map a few concrete scenarios you encounter (e.g., reading logs, streaming JSON over HTTP, etc.) to exact stream stacks, so the model becomes muscle memory.