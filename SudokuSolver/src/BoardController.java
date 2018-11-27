import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class BoardController extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane tile = new TilePane();
		Board board = new Board();
		BorderPane root = new BorderPane();
		HBox hbox1 = new HBox();
		root.setCenter(hbox1);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(tile);
		
		
		Scene scene = new Scene(root, 400, 350);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

		HBox hbox2 = new HBox();
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");

		solve.setDefaultButton(true);

		hbox2.getChildren().addAll(solve, clear);
		root.setBottom(hbox2);
		
		solve.setOnAction(event -> {
			calculateBoard();

			board.solve();
		});

		clear.setOnAction(event -> board.clear());

		tile.setHgap(8);
		ArrayList<TilePane> sections = new ArrayList<TilePane>();
		tile.setPrefColumns(3);
		tile.setHgap(2);
		tile.setVgap(2);
		
		
		
		for (int i = 0; i < 9; i++) {
			ArrayList<OneNumberTextField> textFields = new ArrayList<OneNumberTextField>();
			TilePane t = new TilePane();
			sections.add(t);
			t.setPrefColumns(3);

			tile.getChildren().addAll(sections.get(i));
			for (int k = 0; k < 9; k++) {
				textFields.add(new OneNumberTextField());
				textFields.get(k).setPrefHeight(30);
				textFields.get(k).setPrefWidth(30);
				// Ändra färger varannan tilepane
				if (i % 2 == 0) {
					textFields.get(k).setStyle("-fx-background-color: red;");
				} else 
					textFields.get(k).setStyle("-fx-background-color: blue;");
				
				sections.get(i).getChildren().addAll(textFields.get(k));
			}
		}
	}

	private void calculateBoard() {

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
