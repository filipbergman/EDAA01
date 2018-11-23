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
	TilePane tile = new TilePane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Board board = new Board();
		BorderPane root = new BorderPane();
		root.getChildren().addAll(tile);
		
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
//		root.setCenter(tile);
		solve.setOnAction(event -> {
			calculateBoard();

			board.solve();
		});

		clear.setOnAction(event -> board.clear());

		tile.setHgap(8);
		ArrayList<TilePane> sections = new ArrayList<TilePane>();
		tile.setPrefColumns(3);
		
		
		for (int i = 0; i < 9; i++) {
			ArrayList<OneNumberTextField> textFields = new ArrayList<OneNumberTextField>();
			sections.add(new TilePane());
			
			// Ändra färger varannan tilepane
			if (i % 2 == 0) {

			}

			tile.getChildren().addAll(sections.get(i));
			for (int k = 0; k < 9; k++) {
				textFields.add(new OneNumberTextField());
				textFields.get(k).setPrefHeight(1);
				textFields.get(k).setPrefWidth(1);

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
