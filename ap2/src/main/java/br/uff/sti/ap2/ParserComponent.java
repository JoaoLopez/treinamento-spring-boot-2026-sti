package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParserComponent {
    public String jsonToCsv(String json) {

        // Remove outer [ ]
        json = json.trim();
        if (json.startsWith("[")) json = json.substring(1);
        if (json.endsWith("]")) json = json.substring(0, json.length() - 1);

        // Split objects: },{ 
        String[] objects = json.split("\\},\\s*\\{");

        List<Map<String, String>> rows = new ArrayList<>();

        for (String obj : objects) {
            obj = obj.replaceAll("[{}\"]", "");

            Map<String, String> map = new LinkedHashMap<>();
            String[] pairs = obj.split(",");

            for (String pair : pairs) {
                String[] kv = pair.split(":");
                if (kv.length == 2) {
                    map.put(kv[0].trim(), kv[1].trim());
                }
            }

            rows.add(map);
        }

        if (rows.isEmpty()) return "";

        // Build CSV
        StringBuilder sb = new StringBuilder();

        // Header
        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        sb.append(String.join(",", headers)).append("\n");

        // Rows
        for (Map<String, String> row : rows) {
            List<String> values = new ArrayList<>();
            for (String h : headers) {
                values.add(row.getOrDefault(h, ""));
            }
            sb.append(String.join(",", values)).append("\n");
        }

        return sb.toString();
    }
}
