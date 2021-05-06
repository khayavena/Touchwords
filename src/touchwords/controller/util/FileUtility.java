package touchwords.controller.util;

import touchwords.service.LettersService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileUtility {
    public static List<Map.Entry<String, Long>> getMapEntries(File file) {
        List<Map.Entry<String, Long>> entries = Arrays.asList();
        LettersService.readFile(file, entries);
        return entries;
    }
}

