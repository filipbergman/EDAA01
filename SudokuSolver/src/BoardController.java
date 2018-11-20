import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class BoardController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Board board = new Board();
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root, 700, 600);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

		HBox hbox = new HBox();
	    Button solve = new Button("Solve");
	    Button clear = new Button("Clear");
	    
	    solve.setDefaultButton(true);
	    
	    hbox.getChildren().addAll(solve, clear);
	    root.setBottom(hbox);
	    
	    solve.setOnAction(event -> board.solve());
	    clear.setOnAction(event -> board.clear());
	    
	    TilePane tile = new TilePane();
	    tile.setHgap(8);
	    ArrayList<TilePane> sections = new ArrayList<TilePane>();
	    tile.setPrefColumns(3);
	    for(int i = 0; i < 9; i++) {
	    	sections.add(new TilePane());
	    	tile.getChildren().addAll(sections.get(i));
	    }
	    
	    root.getChildren().addAll(tile);
	    
	    
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
