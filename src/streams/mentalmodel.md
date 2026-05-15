## Mental Model for Java I/O Streams

Java I/O streams follow a clear hierarchy that you can think of in three layers: **base type → source/destination → buffering**. Understanding this progression will help you choose the right stream for any situation. [docs.oracle](https://docs.oracle.com/javase/tutorial/essential/io/datastreams.html)

## Base Layer: Byte vs Character Streams

### Byte Streams (InputStream/OutputStream)
Byte streams handle raw 8-bit bytes and are the foundation of all stream I/O . All byte stream classes descend from `InputStream` and `OutputStream` . Use byte streams for:
- Non-text data: images, audio, video, binary files
- Low-level I/O operations where you need direct byte access

**Important**: Avoid byte streams for text files, as they don't handle character encoding properly .

### Character Streams (Reader/Writer)
Character streams handle Unicode characters and automatically translate between internal Unicode format and the local character set. All character stream classes descend from `Reader` and `Writer`. Use character streams for: [w3schools](https://www.w3schools.com/java/java_io_streams.asp)
- Text files and any character-based data
- Applications requiring internationalization support
- Processing text with proper encoding handling

Character streams are "wrappers" around byte streams—`FileReader` uses `FileInputStream` internally, while `FileWriter` uses `FileOutputStream`. [w3schools](https://www.w3schools.com/java/java_io_streams.asp)

## Middle Layer: Source/Destination Streams

These connect to actual data sources and are built on top of base streams:

### File-Based Streams
- `FileInputStream`/`FileOutputStream`: Read/write bytes from files
- `FileReader`/`FileWriter`: Read/write characters from files [w3schools](https://www.w3schools.com/java/java_io_streams.asp)

### Bridge Streams
- `InputStreamReader`/`OutputStreamWriter`: Convert between byte streams and character streams when no prepackaged character stream exists [w3schools](https://www.w3schools.com/java/java_io_streams.asp)

## Top Layer: Buffered Streams (Performance Wrapper)

Unbuffered I/O means each `read()` or `write()` call triggers direct OS interaction—disk access, network activity, etc.—which is expensive . Buffered streams solve this by reading/writing data to a memory buffer and only calling the native OS API when the buffer is empty (input) or full (output) .

### Four Buffered Stream Classes
- `BufferedInputStream`/`BufferedOutputStream`: Wrap byte streams
- `BufferedReader`/`BufferedWriter`: Wrap character streams

### Usage Pattern
Always wrap your source streams with buffered versions using the constructor pattern :

```java
// Instead of:
FileReader inputStream = new FileReader("file.txt");

// Use:
BufferedReader inputStream = new BufferedReader(new FileReader("file.txt"));
```

`BufferedReader` also provides line-oriented I/O through `readLine()`, which returns complete lines of text. [w3schools](https://www.w3schools.com/java/java_io_streams.asp)

## Practical Decision Tree

| Scenario | Use This |
|----------|----------|
| Reading text files efficiently | `BufferedReader` wrapping `FileReader` |
| Writing text files efficiently | `BufferedWriter` wrapping `FileWriter` |
| Reading binary files (images, PDFs) | `BufferedInputStream` wrapping `FileInputStream` |
| Writing binary files | `BufferedOutputStream` wrapping `FileOutputStream` |
| Single character at a time from text | `FileReader` (though buffering is still recommended) |
| Line-by-line text processing | `BufferedReader` with `readLine()` |
| Converting byte stream to character stream | `InputStreamReader`/`OutputStreamWriter` |

## Key Principles

1. **Start with the data type**: Text → Character streams; Binary → Byte streams [w3schools](https://www.w3schools.com/java/java_io_streams.asp)
2. **Add buffering almost always**: Wrap with `Buffered*` classes for performance
3. **Close streams in finally blocks**: Prevents resource leaks
4. **Character streams are built on byte streams**: All stream types ultimately use byte streams for physical I/O [w3schools](https://www.w3schools.com/java/java_io_streams.asp)