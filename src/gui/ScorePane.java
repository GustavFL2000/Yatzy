package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ScorePane extends GridPane {
    private final YatzyController controller;

    public ScorePane(YatzyController controller) {
        this.controller = controller;

        setPadding(new Insets(10));
        setVgap(6);
        setHgap(25);
        setStyle("-fx-border-color: lightgray; -fx-border-radius: 4; -fx-border-width: 1;");

        // Venstre sektion (1'ere–Yatzy)
        String[] leftLabels = {
                "1'ere", "2'ere", "3'ere", "4'ere", "5'ere", "6'ere",
                "Et par", "To par", "3 ens", "4 ens",
                "Lille straight", "Store straight",
                "Fuldt hus", "Chance", "Yatzy"
        };

        int leftRow = 0;
        for (String label : leftLabels) {
            Text text = new Text(label);
            TextField field = new TextField();
            field.setPrefWidth(60);
            field.setEditable(false);
            field.setId(label);
            field.setOnMouseClicked(event -> controller.chooseFieldAction(event));
            add(text, 0, leftRow);
            add(field, 1, leftRow);
            leftRow++;
        }

        // Lodret separator mellem venstre og højre kolonne
        Separator verticalLine = new Separator();
        verticalLine.setOrientation(javafx.geometry.Orientation.VERTICAL);
        add(verticalLine, 2, 0, 1, leftRow);

        // Højre sektion (Sum, Bonus, Total)
        int rightRow = 0;

        Text sumLabel = new Text("Sum");
        TextField sumField = new TextField();
        sumField.setPrefWidth(60);
        sumField.setEditable(false);
        sumField.setId("Sum");

        Text bonusLabel = new Text("Bonus");
        TextField bonusField = new TextField();
        bonusField.setPrefWidth(60);
        bonusField.setEditable(false);
        bonusField.setId("Bonus");

        Text totalLabel = new Text("Total");
        TextField totalField = new TextField();
        totalField.setPrefWidth(60);
        totalField.setEditable(false);
        totalField.setId("Total");

        add(sumLabel, 3, rightRow);
        add(sumField, 4, rightRow++);
        add(bonusLabel, 3, rightRow);
        add(bonusField, 4, rightRow++);
        add(new Separator(), 3, rightRow++, 2, 1);
        add(totalLabel, 3, rightRow);
        add(totalField, 4, rightRow);
    }

    public void updateTotals() {
        final int[] sum = {0};
        final int[] total = {0};

        // Gennemløb alle felter i gridpane
        getChildren().forEach(node -> {
            if (node instanceof TextField field) {
                String id = field.getId();
                if (id != null && !field.getText().isEmpty()) {
                    try {
                        int value = Integer.parseInt(field.getText());
                        if (id.matches("[1-6]'ere")) sum[0] += value; // øverste sektion
                        total[0] += value; // hele kortet
                    } catch (NumberFormatException ignored) {}
                }
            }
        });

        //Til bonus feltet, hvis man når 63 i sum før bonusen får ,man 50 ekstra point
        int bonus = (sum[0] >= 63) ? 50 : 0;
        total[0] += bonus;

        // Find felterne
        TextField sumField = getFieldById("Sum");
        TextField bonusField = getFieldById("Bonus");
        TextField totalField = getFieldById("Total");

        if (sumField != null) sumField.setText(String.valueOf(sum[0]));
        if (bonusField != null) bonusField.setText(String.valueOf(bonus));
        if (totalField != null) totalField.setText(String.valueOf(total[0]));
    }

    private TextField getFieldById(String id) {
        return getChildren().stream()
                .filter(n -> n instanceof TextField f && id.equals(f.getId()))
                .map(n -> (TextField) n)
                .findFirst()
                .orElse(null);
    }

    public void showPossibleScores(java.util.Map<String, Integer> scores) {
        getChildren().forEach(node -> {
            if (node instanceof TextField field) {
                String id = field.getId();
                if (scores.containsKey(id)) {
                    field.setPromptText(String.valueOf(scores.get(id)));
                }
            }
        });
    }

    public void clearPossibleScores() {
        getChildren().forEach(node -> {
            if (node instanceof TextField field) {
                field.setPromptText("");
            }
        });
    }
}
