package gui;

import javafx.event.Event;
import javafx.scene.control.TextField;
import models.Die;
import models.RaffleCup;
import models.YatzyResultCalculator;

public class YatzyController {

    private final RaffleCup raffleCup = new RaffleCup();
    private int rollsLeft = 3;
    private ScorePane scorePane; // reference til GUI for opdatering

    public void setScorePane(ScorePane scorePane) {
        this.scorePane = scorePane;
    }

    public void rollDice(boolean[] held) {
        if (rollsLeft <= 0) return;

        Die[] dice = raffleCup.getDice();
        for (int i = 0; i < dice.length; i++) {
            if (!held[i]) dice[i].roll();
        }
        rollsLeft--;

        // Vis mulige scores
        if (scorePane != null) {
            YatzyResultCalculator calc = new YatzyResultCalculator(getDice());
            scorePane.showPossibleScores(calc.getPossibleScores());
        }
    }

    public int getRollsLeft() {
        return rollsLeft;
    }

    public Die[] getDice() {
        return raffleCup.getDice();
    }

    public void chooseFieldAction(Event event) {
        TextField field = (TextField) event.getSource();

        // Hvis feltet allerede er udfyldt, ignorer klik
        if (!field.getText().isEmpty()) return;

        // Beregn points baseret på nuværende terninger
        YatzyResultCalculator calc = new YatzyResultCalculator(getDice());
        String id = field.getId();

        int points = switch (id) {
            case "1'ere" -> calc.upperSectionScore(1);
            case "2'ere" -> calc.upperSectionScore(2);
            case "3'ere" -> calc.upperSectionScore(3);
            case "4'ere" -> calc.upperSectionScore(4);
            case "5'ere" -> calc.upperSectionScore(5);
            case "6'ere" -> calc.upperSectionScore(6);
            case "Et par" -> calc.onePairScore();
            case "To par" -> calc.twoPairScore();
            case "3 ens" -> calc.threeOfAKindScore();
            case "4 ens" -> calc.fourOfAKindScore();
            case "Lille straight" -> calc.smallStraightScore();
            case "Store straight" -> calc.largeStraightScore();
            case "Fuldt hus" -> calc.fullHouseScore();
            case "Chance" -> calc.chanceScore();
            case "Yatzy" -> calc.yatzyScore();
            default -> 0;
        };

        field.setText(String.valueOf(points));
        field.setEditable(false);

        // Opdater Sum, Bonus og Total
        if (scorePane != null) {
            scorePane.updateTotals();
            scorePane.clearPossibleScores();
        }

        // Nulstil kast
        rollsLeft = 3;
    }
}
