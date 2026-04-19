package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileWriterComponent {
    public void write(String path, String content) throws IOException {
        Files.writeString(Path.of(path), content);
    }
}
