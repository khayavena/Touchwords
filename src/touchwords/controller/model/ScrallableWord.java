package touchwords.controller.model;

import java.util.List;
import java.util.Map;

public class ScrallableWord implements Comparable<ScrallableWord> {
    String word;
    List<Map.Entry<String, Long>> entries;
    int sum = 0;

    public ScrallableWord(String word, List<Map.Entry<String, Long>> entries) {
        this.word = word;
        this.entries = entries;
    }


    public int getSum() {
        for (Map.Entry<String, Long> entry : entries) {
            sum = (int) (sum + entry.getValue());
        }
        return sum;
    }

    @Override
    public String toString() {
        return "\nScrallableWord{" +
                "word='" + word + '\'' +
                ", sum=" + sum +
                ", \nscrales=" + getEntries() +
                '}';
    }

    private String getEntries() {
        String value = "";
        for (Map.Entry<String, Long> entry : entries
        ) {
            value = value + "," + entry.getKey();
        }
        return value;
    }

    @Override
    public int compareTo(ScrallableWord o) {
        return String.valueOf(getSum()).compareTo(String.valueOf(o.getSum()));
    }
}
