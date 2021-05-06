package touchwords.service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WordsService {


    public List<String> getWords(File file) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitWords = line.split(" ");

                for (String word : splitWords) {
                    words.add(word);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public Map.Entry<String, Long> getMostEntry(List<String> words) {
        Map<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        List<Map.Entry<String, Long>> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .collect(Collectors.toList());
        if (result != null && !result.isEmpty())
            return result.get(0);
        return null;
    }


    public Map.Entry<String, Long> getMost7Entry(List<String> words) {
        Map<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        List<Map.Entry<String, Long>> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(w -> w.getKey().length() == 7)
                .collect(Collectors.toList());
        if (result != null && !result.isEmpty())
            return result.get(0);
        return null;
    }


}







