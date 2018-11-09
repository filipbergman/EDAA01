package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setMinWidth(400);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();

		Set<String> stopwords = readStopWords();
		
		GeneralWordCounter generalWordCounter = new GeneralWordCounter(stopwords);
		
		readFile(generalWordCounter);	// Lägger till orden i generalWordCounter.
		
		// Skapar en mängd av alla ord med map entries som typ på elementen i mängden. 
		Set<Map.Entry<String, Integer>> wordSet = generalWordCounter.getWords();
		
		// Skapar en observable list från orden i boken. En observable list lyssnar hela tiden på förändringar.
		ObservableList<Map.Entry<String, Integer>> wordList = FXCollections.observableArrayList(wordSet);
		// Lägger till den observerbara listan i ett ListView objekt.
		ListView<Map.Entry<String, Integer>> listView = new ListView<>(wordList);
		// Lägger till den observerbara listan i gränssnittet.
		root.setCenter(listView);
		
		// Skapar knapparna och sätter in dem längst ner i gränssnittet och innehåller logiken för 
		createButtons(root, wordList, listView);
	}
	
	// Skapar knapparna och sätter in dem längst ner i gränssnittet.
	private void createButtons(BorderPane root, ObservableList<Map.Entry<String, Integer>> wordList, ListView<Map.Entry<String, Integer>> listView) {
		// Skapar ett hbox objekt som man sedan anväder för att lägga in knappar, textfält och sökfält.
		HBox hbox = new HBox();
	    Button alphabeticButton = new Button("Alphabetic");
	    Button frequenzyButton = new Button("Frequency");
	    TextField textField = new TextField();
	    Button searchButton = new Button("Search");
	    
	    searchButton.setDefaultButton(true); // Knappen "Search" anropas när man trycker på enter på tangentbordet.
	    HBox.setHgrow(textField, Priority.ALWAYS); // Textfältet täcker upp förstret och förändras i storlek när man förstorar/förminskar.
	    
	    SelectionModel<Map.Entry<String, Integer>> selectList = listView.getSelectionModel();
	    Alert alert = new Alert(AlertType.CONFIRMATION, "The text does not contain this word.");
	    
	    // Lägger till alla knappar och lägger dem längst ner i fönstret.
	    hbox.getChildren().addAll(alphabeticButton, frequenzyButton, textField, searchButton);
	    root.setBottom(hbox);
	    
	    // Sorterar på alfabetisk ordning
	    alphabeticButton.setOnAction(event -> {
	    	wordList.sort((entry1, entry2) -> {
	    		listView.scrollTo(0);
	    		return entry1.getKey().compareTo(entry2.getKey());
	    	});
	    });
	    
	    // Sorterar på antal förekomster
	    frequenzyButton.setOnAction(event -> {
	    	wordList.sort((entry1, entry2) -> {
	    		listView.scrollTo(0);
	    		return entry2.getValue().compareTo(entry1.getValue());
	    	});
	    });
	    
	    // Sorterar på sökord
	    searchButton.setOnAction(event -> {
	    	String text = textField.getCharacters().toString();
	    	String trimmedText = text.trim();
	    	for (int i = 0; i < wordList.size(); i++) {
	    		if(wordList.get(i).getKey().equalsIgnoreCase(trimmedText)) {
	    			listView.scrollTo(i);
	    			selectList.clearAndSelect(i);
		    		return; // Return gör så att man hoppar över alert.show() ifall ordet hittas.
	    		}
			}
	    	alert.show();
	    });
	}
	
	// Lägger till alla undantagsord i stopwords.
	private Set<String> readStopWords() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>(); // en lämplig mängd skapas
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		scan.close();
		return stopwords;
	}
	
	// Lägger till orden i generalWordCounter.
	private void readFile(GeneralWordCounter generalWordCounter) throws FileNotFoundException {
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			generalWordCounter.process(word);
		}
		s.close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
