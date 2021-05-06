package touchwords.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import touchwords.controller.model.ScrallableWord;
import touchwords.service.LettersService;
import touchwords.service.WordsService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class WordsFormController {
    @FXML
    private TextField nameField;

    @FXML
    private Button lettersButton;
    @FXML
    private Button wordsButton;
    @FXML
    private Label outputLbl;

    @FXML
    private TextArea outputTxt;
    private File file;
    private File lettersFile;

    private WordsService service;
    private LettersService lettersService;
    private List<String> words;

    public WordsFormController() {
        service = new WordsService();
        lettersService = new LettersService();
    }

    @FXML
    protected void handleLettersFile(ActionEvent event) throws FileNotFoundException {
        Window owner = lettersButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        new FileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        this.lettersFile = fileChooser.showOpenDialog(owner);
        List<Map.Entry<String, Long>> entries = this.lettersService.getHighest(lettersFile);
        for (Map.Entry<String, Long> entry : entries) {
            outputTxt.appendText("\nHighest Score" + entry.getKey() + "-:-" + entry.getValue().toString());
        }

        List<ScrallableWord> value = this.lettersService.getHighest2(entries, words);
        if (words != null) {
            for (ScrallableWord entry : value) {
                outputTxt.appendText(entry.toString());
            }
        }
    }

    @FXML
    protected void handleBrowseInput(ActionEvent event) throws FileNotFoundException {
        Window owner = wordsButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        new FileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        this.file = fileChooser.showOpenDialog(owner);
        this.words = service.getWords(file);
        outputLbl.setText(words.size() + "");
        Map.Entry<String, Long> entry = service.getMostEntry(words);
        Map.Entry<String, Long> entry7 = service.getMost7Entry(words);
        outputTxt.setText("Most common:" + entry.getKey().toString() + ":" + entry.getValue().toString());
        outputTxt.appendText("\nMost 7common:" + entry7.getKey().toString() + ":" + entry7.getValue().toString());

    }
}
