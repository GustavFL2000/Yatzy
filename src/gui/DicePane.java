package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Die;

public class DicePane extends VBox {
    private final Text[] diceTexts = new Text[5];
    private final CheckBox[] holds = new CheckBox[5];
    private final Button rollButton = new Button("Kast terningerne");
    private final Text rollsLeftText = new Text("Antal kast tilbage: 3");

    private final YatzyController controller;

    public DicePane(YatzyController controller) {
        this.controller = controller;

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.CENTER);

        HBox diceBox = new HBox(15);
        diceBox.setAlignment(Pos.CENTER);

        HBox holdBox = new HBox(25);
        holdBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            diceTexts[i] = new Text("-");
            diceTexts[i].setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
            holds[i] = new CheckBox("Hold");
            diceBox.getChildren().add(diceTexts[i]);
            holdBox.getChildren().add(holds[i]);
        }

        HBox controlBox = new HBox(15);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.getChildren().addAll(rollsLeftText, rollButton);

        rollButton.setOnAction(e -> {
            boolean[] held = new boolean[5];
            for (int i = 0; i < 5; i++) {
                held[i] = holds[i].isSelected();
            }
            controller.rollDice(held);
            updateDiceDisplay();
        });

        getChildren().addAll(diceBox, holdBox, controlBox);
    }

    public void updateDiceDisplay() {
        Die[] dice = controller.getDice();
        for (int i = 0; i < 5; i++) {
            diceTexts[i].setText(String.valueOf(dice[i].getEyes()));
        }
        rollsLeftText.setText("Antal kast tilbage: " + controller.getRollsLeft());
    }
}
