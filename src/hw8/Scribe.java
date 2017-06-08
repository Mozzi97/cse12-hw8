package hw8;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Scribe {
    public static void main(String[] args) throws IOException
    {
        try (BufferedWriter scribe = Files.newBufferedWriter(Paths.get("hello.txt"), Charset.defaultCharset()))
        {
            scribe.write("Hello! Is it me you're looking for?");
        }
    }
}