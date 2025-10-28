package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class YatzyGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Opret controller og GUI-komponenter
        YatzyController controller = new YatzyController();
        DicePane dicePane = new DicePane(controller);
        ScorePane scorePane = new ScorePane(controller);

        //Forbind controller med ScorePane
        controller.setScorePane(scorePane);

        root.setTop(dicePane);
        root.setCenter(scorePane);

        Scene scene = new Scene(root, 500, 800);
        primaryStage.setTitle("Yatzy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
