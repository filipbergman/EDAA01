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
		ArrayList<ArrayList<OneNumberTextField>> textFields = new ArrayList<ArrayList<OneNumberTextField>>();

		solve.setOnAction(event -> {
			// Clean board object old numbers did not disapear before
			board.clear();

			getVisibleBoard(textFields, board);
			System.out.println(board);
			Boolean solved = board.solve();
			if (!solved) {
				Alert alert = new Alert(AlertType.ERROR, "The sudoku is unsolvable!");
				alert.showAndWait();
			}
			plotBoard(textFields, board);

			System.out.println(board);
		});

		clear.setOnAction(event -> {
			board.clear();
			clearVisibleBoard(textFields);
		});

		ArrayList<TilePane> sections = new ArrayList<TilePane>();
		tile.setPrefColumns(3);
		tile.setHgap(2);
		tile.setVgap(2);

		for (int i = 0; i < 9; i++) {
			ArrayList<OneNumberTextField> tempTextFields = new ArrayList<OneNumberTextField>();
			textFields.add(tempTextFields);
			TilePane t = new TilePane();
			sections.add(t);
			t.setPrefColumns(3);

			tile.getChildren().addAll(sections.get(i));
			for (int k = 0; k < 9; k++) {
				tempTextFields.add(new OneNumberTextField());
				tempTextFields.get(k).setPrefHeight(30);
				tempTextFields.get(k).setPrefWidth(30);
				// Ändra färger varannan tilepane
				if (i % 2 == 0) {
					tempTextFields.get(k).setStyle("-fx-background-color: red;");
				}

				sections.get(i).getChildren().addAll(tempTextFields.get(k));
			}
		}
	}

	private void getVisibleBoard(ArrayList<ArrayList<OneNumberTextField>> textFields, Board board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int num = 0;

				// row conversion start
				int section = (col / 3) + 3* (row / 3);
				// row conversion end

				// Col conversion start
				int place = row % 3 * 3 + col % 3;
				// Col conversion end

				String temp = textFields.get(section).get(place).getCharacters().toString();

				if (!temp.equals("")) {
					num = Integer.parseInt(temp);
				}

				board.set(row, col, num);
			}
		}
	}

	private void plotBoard(ArrayList<ArrayList<OneNumberTextField>> textFields, Board board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int num = board.getNum(row, col);

				int tempCol;
				int tempRow;

				// row conversion start
				tempRow = (col / 3) + 3 * (row / 3);
				// row conversion end

				// Col conversion start
				tempCol = row % 3 * 3 + col % 3;
				// Col conversion end

				String tempString;
				if (num == 0)
					tempString = "";
				else
					tempString = Integer.toString(num);
				textFields.get(tempRow).get(tempCol).setText(tempString);

			}
		}
	}

	private void clearVisibleBoard(ArrayList<ArrayList<OneNumberTextField>> textFields) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				textFields.get(row).get(col).setText("");
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}