package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.BookManagerView;

public class Main extends Application{
	 public static void main(String[] args) {
		 launch(args);
	 }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		new BookManagerView().start(arg0);
		
	}
}
