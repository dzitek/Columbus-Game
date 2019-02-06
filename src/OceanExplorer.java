import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.awt.Point;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	
	Pane root;
	final int dimensions = 20;
	final int islandCount = 20;
	final int scale = 50;
	boolean GameOver = false;
	Text WinText;
	Text LoseText;
	Image shipImage;
	Image pirateImage;
	Image islandImage;
	Image pirateLand;
	Image seaMonsterImage;
	Image submarineImage;
	Image treasureImage;

	ImageView treasureImageView;
	ImageView SubmarineImageView;
	ImageView shipImageView; 
	ImageView seaMonsterImageView;
	ImageView[] pirateImageView; 
	ImageView[] islandImageView;

	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	PirateShip pirateShips;
	SeaMonster seaMonster;
	FasterSpeed fasterSpeed;
	Stage stage;

	void startGame(Stage mapStage) {
		oceanMap = OceanMap.createOceanMap(dimensions, islandCount); 
		islandImageView = new ImageView[oceanMap.getIslands().length];
		pirateImageView = new ImageView[oceanMap.getPirateShips().length];
		root = new AnchorPane();
		drawMap();
		stage = mapStage;

		
		ship = new Ship(oceanMap);
		pirateShips = new PirateShip(oceanMap);
		seaMonster = new SeaMonster(oceanMap);
		fasterSpeed = new FasterSpeed(oceanMap);
		loadShipImage();
		ship.addObserver(pirateShips);
		ship.addObserver(seaMonster);
		ship.addObserver(fasterSpeed);
		scene = new Scene(root, 1200, 1000);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.setScene(scene);
		mapStage.show();
		startSailing();
		Button();
	}
	@Override
	public void start(Stage mapStage) throws Exception {
		startGame(mapStage);

	}

	private void loadShipImage() {
		Image shipImage = new Image("ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x * scale);
		shipImageView.setY(oceanMap.getShipLocation().y * scale);
		root.getChildren().add(shipImageView);

		Image treasureImage = new Image("Treasure.jpg", 50, 50, true, true);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(oceanMap.getTreasureLocation().x * scale);
		treasureImageView.setY(oceanMap.getTreasureLocation().y * scale);
		root.getChildren().add(treasureImageView);

		Image seaMonsterImage = new Image("seamonster1.jpg", 50, 50, true, true);
		seaMonsterImageView = new ImageView(seaMonsterImage);
		seaMonsterImageView.setX(oceanMap.getSeaMonsterLocation().x * scale);
		seaMonsterImageView.setY(oceanMap.getSeaMonsterLocation().y * scale);
		root.getChildren().add(seaMonsterImageView);

		Image SubmarineImage = new Image("submarine.jpg", 50, 50, true, true);
		SubmarineImageView = new ImageView(SubmarineImage);
		SubmarineImageView.setX(oceanMap.getSpeed().x * scale);
		SubmarineImageView.setY(oceanMap.getSpeed().y * scale);
		root.getChildren().add(SubmarineImageView);

		Image pirateImage = new Image("pirateShip.png", 50, 50, true, true);
		for (int i = 0; i < oceanMap.getPirateShips().length; i++) {
			pirateImageView[i] = new ImageView(pirateImage);
			pirateImageView[i].setX(oceanMap.getPirateShips()[i].x * scale);
			pirateImageView[i].setY(oceanMap.getPirateShips()[i].y * scale);
			root.getChildren().add(pirateImageView[i]);
		}

		
		Image islandImage = new Image("island.jpg", 50, 50, true, true);
		for (int i = 0; i < 20; i++) {
			islandImageView[i] = new ImageView(islandImage);
			islandImageView[i].setX(oceanMap.getIslands()[i].x * scale);
			islandImageView[i].setY(oceanMap.getIslands()[i].y * scale);
			root.getChildren().add(islandImageView[i]);
		}
	}
	private void Button() {
		Button reset = new Button("Reset");
		reset.setLayoutY(0);
		reset.setLayoutX(1000);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        reset.setText("reset");
		        stage.close();
		        restart();
		    }
		});
		root.getChildren().add(reset);
	}

	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
				
				case RIGHT:
					ship.goRight();
					break;
				case LEFT:
					ship.goLeft();
					break;
				case UP:
					ship.goUp();
					break;
				case DOWN:
					ship.goDown();
					break;
				default:
					break;
				}
				Point p = ship.getShipLocation();
				shipImageView.setX(p.x * scale);
				shipImageView.setY(p.y * scale);
				
				for (int i = 0; i < oceanMap.getPirateShips().length; i++) {
					pirateImageView[i].setX(pirateShips.getShipLocation()[i].x * scale);
					pirateImageView[i].setY(pirateShips.getShipLocation()[i].y * scale);

				}
				seaMonsterImageView.setX(seaMonster.getSeaMonsterLocation().x * scale);
				seaMonsterImageView.setY(seaMonster.getSeaMonsterLocation().y * scale);

				SubmarineImageView.setX(fasterSpeed.getFasterSpeed().x * scale);
				SubmarineImageView.setY(fasterSpeed.getFasterSpeed().y * scale);
				if (GameOver == false) {
				WinLoseCondition();
				}
			}
		});
	}

	public void drawMap() {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
	}

	void restart() {
		stage.close();
		Stage mapStage = new Stage();
		startGame(mapStage);
	}
	

	public void WinLoseCondition() {
		if (oceanMap.checkLose() == false) {
			LoseText = new Text(500, 500, "Sorry, you lost!");
			LoseText.setFont(Font.font("Times New Roman", 20));
			root.getChildren().add(LoseText);
			GameOver = true;

		} else if (oceanMap.checkWin() == true) {
			WinText = new Text(500, 500, "Good job, you won!");
			WinText.setFont(Font.font("Times New Roman", 20));
			root.getChildren().add(WinText);
			GameOver = true;

		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
