package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Book;

import controller.BookController;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookManagerView {
	private BookController controller = new BookController();
	
	
	public void start(Stage primaryStage) {
		TableView<Book> table = new TableView<>(controller.getBooks());
		
		TableColumn<Book, String> titleCol = new TableColumn<>("Titre");
		titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

		TableColumn<Book, String> authorCol = new TableColumn<>("Auteur");
		authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));

		TableColumn<Book, Number> priceCol = new TableColumn<>("Prix (€)");
		priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));

		TableColumn<Book, Number> stockCol = new TableColumn<>("Stock");
		stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()));

		table.getColumns().addAll(titleCol, authorCol, priceCol, stockCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TextField titleField = new TextField();
		titleField.setPromptText("Titre");

		TextField authorField = new TextField();
		authorField.setPromptText("Auteur");

		TextField priceField = new TextField();
		priceField.setPromptText("Prix");

		TextField stockField = new TextField();
		stockField.setPromptText("Stock");
		Button addButton = new Button("Ajouter");
		addButton.setOnAction(e -> {
		    try {
		        String title = titleField.getText();
		        String author = authorField.getText();
		        double price = Double.parseDouble(priceField.getText());
		        int stock = Integer.parseInt(stockField.getText());

		        Book book = new Book(title, author, price, stock);
		        controller.addBook(book);

		        titleField.clear();
		        authorField.clear();
		        priceField.clear();
		        stockField.clear();
		    } catch (Exception ex) {
		        showAlert("Erreur de saisie", "Vérifie les champs de prix et de stock.");
		    }
		});
		
		Button deleteButton = new Button("Supprimer");
		deleteButton.setOnAction(e -> {
		    Book selected = table.getSelectionModel().getSelectedItem();
		    if (selected != null) {
		        controller.removeBook(selected);
		    }else {
		    	showAlert("Pas de séléction", "Vérifie qu'un livre est séléctionné");
		    }
		});
		
		HBox form = new HBox(10, titleField, authorField, priceField, stockField, addButton, deleteButton);
		VBox layout = new VBox(10, table, form);
		layout.setPadding(new Insets(15));

		Scene scene = new Scene(layout, 800, 400);
		primaryStage.setTitle("Gestionnaire de Livres");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void showAlert(String title, String content) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	
	
}
