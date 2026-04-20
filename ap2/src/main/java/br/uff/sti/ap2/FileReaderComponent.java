package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileReaderComponent {
    @Loga
    public String read(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
