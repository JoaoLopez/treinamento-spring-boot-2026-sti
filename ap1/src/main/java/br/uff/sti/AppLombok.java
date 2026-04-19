package br.uff.sti;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import lombok.val;
import tools.jackson.databind.ObjectMapper;

public class AppLombok {

    public static void main(String[] args){
        // 1. Read ALL input from terminal
        System.out.println("Digite o conteúdo CSV com cabeçalho (Para terminar, deixe uma linha em branco):");

        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) {
            if (!scanner.hasNextLine()) break; // just in case (EOF)
            String line = scanner.nextLine();

            if (line.trim().isEmpty()) break;  // <-- stop on blank line

            sb.append(line).append("\n");
        }

        String input = sb.toString();



        var records = CsvReader.builder().ofCsvRecord(input);

        Iterator<CsvRecord> it = records.iterator();

        if (!it.hasNext()) return;

        // 1. First row = headers
        CsvRecord headerRecord = it.next();
        List<String> headers = headerRecord.getFields();

        List<Map<String, String>> result = new ArrayList<>();

        // 2. Remaining rows
        while (it.hasNext()) {
            CsvRecord row = it.next();

            Map<String, String> map = new LinkedHashMap<>();

            for (int i = 0; i < headers.size(); i++) {
                String key = headers.get(i);
                String value = i < row.getFieldCount() ? row.getField(i) : "";
                map.put(key, value);
            }

            result.add(map);
        }

        // 3. JSON output
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
        );
    }
}
