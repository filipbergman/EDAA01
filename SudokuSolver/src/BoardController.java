import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
			
//			plotBoard(textFields, board);
			System.out.println(board);
			Boolean solved = board.solve();
			if (!solved) {
				Alert alert = new Alert(AlertType.ERROR, "The sudoku is unsolvable!");
				 alert.showAndWait();
			}
			System.out.println(board);
		});

		clear.setOnAction(event -> board.clear());

		ArrayList<TilePane> sections = new ArrayList<TilePane>();
		tile.setPrefColumns(9);
		tile.setHgap(2);
		tile.setVgap(2);
		
		
		int counter = 0;
		
		for (int i = 0; i < 9; i++) {
			ArrayList<OneNumberTextField> textFields = new ArrayList<OneNumberTextField>();
			
			if(i > 2 && i < 6) counter = 3;
			else counter = 0;
			
			
			textFields.addAll(textFields);
			TilePane t = new TilePane();
			sections.add(t);
			t.setPrefColumns(9);

			tile.getChildren().addAll(sections.get(i));
			for (int k = 0; k < 9; k++) {
				textFields.add(new OneNumberTextField());
				textFields.get(k).setPrefHeight(30);
				textFields.get(k).setPrefWidth(30);
				
				// Ändra färger varannan tilepane
				if (counter < 3) textFields.get(k).setStyle("-fx-background-color: red;");
				counter++;
				if(counter == 6) counter = 0;
				
				sections.get(i).getChildren().addAll(textFields.get(k));
			}
		}
	}

	private void plotBoard(ArrayList<OneNumberTextField> textFields, Board board) {
//		for(int row = 0; row < 9; row++) {
//			for(int col = 0; col < 9; col++) {
//				int num = 0;
//				
//				int tempRow;
//				if (row < 3) tempRow = 0;
//				else if (row < 6) tempRow = 1;
//				else tempRow = 2;
//				
//				int tempCol;
//				if (col < 3) tempCol = 0;
//				else if (col < 6) tempCol = 1;
//				else tempCol = 2;
//				
//				String temp = textFields.get(tempRow).get(tempCol).getCharacters().toString();
//				
//				if (!temp.equals("")) {
//					num = Integer.parseInt(temp);
//				}
//				
//				board.set(row, col, num);
//			}
//		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
