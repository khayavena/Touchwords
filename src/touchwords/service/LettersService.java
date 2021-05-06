package touchwords.service;

import touchwords.controller.model.ScrallableWord;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LettersService {


    public List<Map.Entry<String, Long>> getHighest(File myObj) throws FileNotFoundException {
        List<Map.Entry<String, Long>> words = new ArrayList();
        readFile(myObj, words);

        long sum = 0;
        for (Map.Entry<String, Long> word : words
        ) {
            sum = sum + word.getValue();
        }
        long count = words.stream().count();
        double avg = Double.parseDouble(String.valueOf(sum)) / Double.parseDouble(String.valueOf(count));

        List<Map.Entry<String, Long>> result = words.stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(w -> w.getValue() > avg)
                .collect(Collectors.toList());
        if (result != null && !result.isEmpty())
            return result;
        return null;
    }

    public List<ScrallableWord> getHighest2(List<Map.Entry<String, Long>> scrales, List<String> words) throws FileNotFoundException {
        List<ScrallableWord> scrallableWords = new ArrayList();

        for (String word : words) {
            List<Map.Entry<String, Long>> list = new ArrayList();
            for (char ch : word.toCharArray()) {
                List<Map.Entry<String, Long>> entry = scrales.stream().filter(s -> s.getKey().contains(String.valueOf(ch))).collect(Collectors.toList());
                if (entry != null && !entry.isEmpty()) {
                    list.addAll(entry);
                }
                scrallableWords.add(new ScrallableWord(word, list));
            }
        }

        long count = scrallableWords.size();
        long sum = 0;
        for (ScrallableWord entry : scrallableWords) {
            sum = sum + entry.getSum();
        }
        double avg = Double.parseDouble(String.valueOf(sum)) / Double.parseDouble(String.valueOf(count));
        //Collections.sort(scrallableWords, Collections.reverseOrder());

        ScrallableWord result = scrallableWords.stream()
                .max(Comparator.comparing(ScrallableWord::getSum))
                .get();
        scrallableWords = new ArrayList<>();

        if (result != null) {
            scrallableWords.add(result);
            return scrallableWords;
        }
        return null;
    }


    public static void readFile(File myObj, List<Map.Entry<String, Long>> words) {
        try (BufferedReader reader = new BufferedReader(new FileReader(myObj))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitWords = line.split(",");
                Map.Entry<String, Long> entry = new Map.Entry<String, Long>() {
                    @Override
                    public String getKey() {
                        return splitWords[0];
                    }

                    @Override
                    public Long getValue() {
                        return Long.parseLong(splitWords[1]);
                    }

                    @Override
                    public Long setValue(Long value) {
                        return value;
                    }
                };
                words.add(entry);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}







