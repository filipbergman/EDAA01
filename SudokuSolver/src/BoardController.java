import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
		Scene scene = new Scene(root, 400, 350);
		HBox hbox1 = new HBox(); // För tilen som ska innehålla brädet
		HBox hbox2 = new HBox(); // För knapparna
		
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		
		// Lägger hboxen som ska innehålla sudokut längst upp och hboxen som ska ha knapparna längst ner.
		root.setTop(hbox1);
		root.setBottom(hbox2);
		
		tile.setPrefColumns(3);
		tile.setHgap(2);
		tile.setVgap(2);
		
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(tile);
		hbox2.getChildren().addAll(solve, clear);
		
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

		solve.setDefaultButton(true);
		
		// En lista som innehåller listor för varje enskild sektion.
		ArrayList<ArrayList<OneNumberTextField>> textFields = new ArrayList<ArrayList<OneNumberTextField>>();

		// Yttre loopen lägger till de 9 sektionerna
		for (int i = 0; i < 9; i++) {
			ArrayList<OneNumberTextField> tempTextFields = new ArrayList<OneNumberTextField>();
			TilePane t = new TilePane();
			textFields.add(tempTextFields);
			t.setPrefColumns(3);
			tile.getChildren().addAll(t);
			
			// Inre loopen lägger till 9 textfields i varje sektion
			for (int k = 0; k < 9; k++) {
				tempTextFields.add(new OneNumberTextField());
				tempTextFields.get(k).setPrefHeight(30);
				tempTextFields.get(k).setPrefWidth(30);
				// Ändra färger varannan tilepane
				if (i % 2 == 0) {
					tempTextFields.get(k).setStyle("-fx-background-color: red;");
				}
				// Lägger till varje textfield i sektionen i gränssnittet
				t.getChildren().addAll(tempTextFields.get(k));
			}
		}
		
		//--------------------------------------------------------------
		// Knapparna
		//--------------------------------------------------------------
		
		// Löser sudokut 
		solve.setOnAction(event -> {
			// Rensar modellens bräde då detta är kvar från förra lösningen
			board.clear();
			
			// Hämtar alla siffror som skrivits in i vyns bräde till modellens bräde.
			addVisibleBoard(textFields, board);
			
			System.out.println(board); // Skriver ut det som skrevs i vyn i konsollen.
			
			// Löser brädet ifall detta går.
			Boolean solved = board.solve();
			if (!solved) {
				Alert alert = new Alert(AlertType.ERROR, "The sudoku is unsolvable!");
				alert.showAndWait();
			}
			// Skriver ut lösningen på brädet ifall det gick.
			plotBoard(textFields, board);

			System.out.println(board); // Skriver ut lösningen på brädet i konsollen.
		});

		// Rensar både brädet i board-klassen och det synliga brädet.
		clear.setOnAction(event -> {
			board.clear();
			clearVisibleBoard(textFields);
		});
	}

	private void addVisibleBoard(ArrayList<ArrayList<OneNumberTextField>> textFields, Board board) {
		// Nestlad loop som går igenom alla textrutor.
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int num = 0;

				// Beräknar vilken av de nio sektionerna textrutan tillhör (mellan 0 och 0). 
				int section = (col / 3) + 3* (row / 3);

				// Beräknar vilken plats textrutan ligger i sektionen (mellan 0 och 0).
				int place = row % 3 * 3 + col % 3;
				
				// Omvandlar det som står i rutan till en String.
				String temp = textFields.get(section).get(place).getCharacters().toString();

				// Om rutan inte är tom ska num få ett värde och sedan läggas till i kontrollerns bräde
				if (!temp.equals("")) {
					num = Integer.parseInt(temp);
				}
				board.set(row, col, num);
			}
		}
	}

	private void plotBoard(ArrayList<ArrayList<OneNumberTextField>> textFields, Board board) {
		// Nestlad loop som går igenom alla textrutor i brädet, fungerar liknande addVisibleBoard
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				// Hämtar värdet  som ligger på raden row och kolumnen col.
				int num = board.getNum(row, col);

				int tempRow = (col / 3) + 3 * (row / 3);
				int tempCol = row % 3 * 3 + col % 3;
				
				String tempString;
				if (num == 0) {
					tempString = "";
				} else {
					tempString = Integer.toString(num);
				}
				// Lägger till värdet från kontrollerns board till vyn.
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