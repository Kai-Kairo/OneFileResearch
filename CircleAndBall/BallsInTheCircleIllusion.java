import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.transform.Scale; 

public class BallsInTheCircleIllusion extends Application{
	private final double Screen_Width = 640.00; //you may change it to 1280.00 also
	private final double Screen_Height = 480.00; //you may change it to 720.00 also
	private final double circle_radius = (Screen_Width / 2 + Screen_Height / 2) / 4; //dynamic radius depending on width and height
	private final double circle_x_center = Screen_Width / 2;
	private final double circle_y_center = Screen_Height / 2;
	private int counter = 0;
	private StackPane main_pane;
	private Circle main_circle;

	@Override
	public void start(Stage primaryStage){
		main_pane = new StackPane();
		main_circle = new Circle(circle_x_center, circle_y_center, circle_radius);
		main_circle.setFill(Color.WHITE);
		main_circle.setStroke(Color.BLACK);


		//main_pane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

		main_pane.getChildren().add(main_circle);
		// addBall();
		// addBall();
		// addBall();
		// addBall();
		// addBall();
		// addBall();
		// addBall();
		// addBall();

		Scene scene = new Scene(main_pane, Screen_Width, Screen_Height);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circles and Balls");
		primaryStage.show();

	}

	public void addBall(){
		Line newLine = new Line(circle_x_center, circle_y_center, circle_x_center + 2 * circle_radius * Math.cos(Math.toRadians(counter * (360 / 16))), circle_y_center + 1.9944444 * circle_radius * Math.sin(Math.toRadians(counter * (360 / 16))));
		
		
		newLine.setStroke(Color.RED);
		counter++;
		main_pane.getChildren().add(newLine);
	}

	public static void main(String[] args) {
		Application.launch();
	}
}
