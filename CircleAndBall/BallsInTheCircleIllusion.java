/*
 * Created by Toleubay Alikhan (StarLigthNova)
 * 28/07/2020
 * Spinning circles
 * JavaFX 11
 * Way to start: https://openjfx.io/openjfx-docs/#install-javafx
 * Try many variation of delay and amount of balls with speed, you will even see 3D model of warm if you can :) Good luck
*/


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.animation.PathTransition; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
import javafx.util.Duration;  
import javafx.scene.shape.LineTo; 
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.animation.Timeline;

import java.util.ArrayList;



public class BallsInTheCircleIllusion extends Application{
	private final double Screen_Width = 750.00; //you may change it to 1280.00 also
	private final double Screen_Height = 500.00; //you may change it to 720.00 also
	private final double circle_radius = (Screen_Width / 2 + Screen_Height / 2) / 4; //dynamic radius depending on width and height
	private final double circle_x_center = Screen_Width / 2;
	private final double circle_y_center = Screen_Height / 2;
	private int amount = 16;
	private int counter = 0;
	private double spd = 1000.0;
	private double del = 121.0;
	private HBox container;
	private VBox UI_container;
	private StackPane main_pane;
	private Circle main_circle;
	private Button addBalls;
	private Button clearCircle;
	private Button StartCircle;
	private Button hideRedLines;
	private Button showRedLines;
	private Button amountIncreaser;
	private Button amountDecreaser;
	private HBox amount_container;
	private Text text_amount = new Text(": " + amount + " (360 / n)");
	private Button speedIn;
	private Button speedDe;
	private HBox speed_container;
	private Text text_speed = new Text(": " + spd + " millis");
	private Button delayIn;
	private Button delayDe;
	private HBox delay_container;
	private Text text_delay = new Text(": " + del + " delay millis");
	private ArrayList<Line> list_of_lines = new ArrayList<>();

	@Override
	public void start(Stage primaryStage){
		container = new HBox();
		UI_container = new VBox();
		main_pane = new StackPane();
		main_circle = new Circle(circle_x_center, circle_y_center, circle_radius);


		//new Buttons
		addBalls = new Button("ADD BALL");
		clearCircle = new Button("CLEAR");
		StartCircle = new Button("START");
		hideRedLines = new Button("HIDE RED");
		showRedLines = new Button("SHOW RED");
		amountIncreaser = new Button("+");
		amountDecreaser = new Button("-");
		speedIn = new Button("+");
		speedDe = new Button("-");
		delayIn = new Button("+");
		delayDe = new Button("-");

		delay_container  = new HBox(delayDe, delayIn, text_delay);
		amount_container = new HBox(amountDecreaser, amountIncreaser, text_amount);
		speed_container  = new HBox(speedDe, speedIn, text_speed);



		main_circle.setFill(Color.WHITE);
		main_circle.setStroke(Color.BLACK);



		addBalls.setOnAction(e -> {
			addBall();
		});
		clearCircle.setOnAction(e -> {
			cliearEverythin();
		});
		StartCircle.setOnAction(e ->{
			startAppearance();
			text_amount.setText(": " + amount + " (360 / n)");
			text_speed.setText(": " + spd + " millis");
			text_delay.setText(": " + del + " delay millis");

		});

		hideRedLines.setOnAction(e -> {
			hideRed();
		});

		showRedLines.setOnAction(e -> {
			showRed();
		});

		amountIncreaser.setOnAction(e -> {
			amount++;
			text_amount.setText(": " + amount + " (360 / n)");
		});

		amountDecreaser.setOnAction(e -> {
			amount--;
			text_amount.setText(": " + amount + " (360 / n)");
		});

		speedIn.setOnAction(e -> {
			spd += 100.0;
			text_speed.setText(": " + spd + " millis");
		});

		speedDe.setOnAction(e -> {
			spd -= 100.0;
			text_speed.setText(": " + spd + " millis");
		});

		delayIn.setOnAction(e -> {
			del++;
			text_delay.setText(": " + del + " delay millis");
		});

		delayDe.setOnAction(e -> {
			del--;
			text_delay.setText(": " + del + " delay millis");
		});


		//main_pane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));


		main_pane.getChildren().add(main_circle);
		main_pane.setStyle("-fx-padding: 50px;");
		startAppearance();

		UI_container.getChildren().addAll(addBalls, clearCircle, StartCircle, hideRedLines, showRedLines, amount_container, speed_container, delay_container);
		UI_container.setStyle("-fx-padding: 10px;");
		UI_container.setAlignment(Pos.CENTER);
		UI_container.setSpacing(10);
		amount_container.setSpacing(10);
		speed_container.setSpacing(10);
		delay_container.setSpacing(10);

		container.getChildren().addAll(main_pane, UI_container);
		container.setSpacing(50);

		Scene scene = new Scene(container, Screen_Width, Screen_Height);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circles and Balls");
		primaryStage.show();

	}

	public void addBall(){
		double calc_x = circle_x_center + circle_radius * Math.cos(Math.toRadians(counter * (360 / amount)));
		double calc_y = circle_y_center + circle_radius * Math.sin(Math.toRadians(counter * (360 / amount)));
		Line newLine = new Line(circle_x_center, circle_y_center, calc_x, calc_y);
		list_of_lines.add(newLine);
		Circle newCircle = new Circle(circle_x_center, circle_y_center, 12.5);
		newCircle.setFill(Color.BLUE);
		newCircle.setStroke(Color.BLACK);

		Path path = new Path();
		MoveTo moveTo = moveTo = new MoveTo(circle_x_center - circle_radius * Math.cos(Math.toRadians(counter * (360 / amount))), circle_y_center - circle_radius * Math.sin(Math.toRadians(counter * (360 / 16))));
		LineTo line1 = new LineTo(calc_x, calc_y);
		//CubicCurveTo cubicCurveTo = new CubicCurveTo(400, 40, 175, 250, 500, 150); 

		path.getElements().add(moveTo);
		//path.getElements().add(cubicCurveTo);
		path.getElements().add(line1);

		PathTransition pathTransition = new PathTransition(); 
		pathTransition.setDelay(Duration.millis(del * (counter + 1)));

		pathTransition.setDuration(Duration.millis(spd));
        
        pathTransition.setNode(newCircle);
        pathTransition.setPath(path);  
        //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TAN_GENT); 
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

		newLine.setStroke(Color.RED);
		counter++;
		main_pane.getChildren().addAll(newLine, newCircle);
	}

	private void startAppearance(){
		amount = 16;
		counter = 0;
		addBall();
		addBall();
		addBall();
		addBall();
		addBall();
		addBall();
		addBall();
		addBall();
	}

	private void cliearEverythin(){
		main_pane.getChildren().clear();
		main_pane.getChildren().add(main_circle);
		main_pane.setStyle("-fx-padding: 50px;");
	}

	private void hideRed(){
		for(Line l : list_of_lines){
			l.setVisible(false);
		}
	}

	private void showRed(){
		for(Line l : list_of_lines){
			l.setVisible(true);
		}	
	}

	public static void main(String[] args) {
		Application.launch();
	}
}
