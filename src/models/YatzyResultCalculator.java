package models;

import java.util.Arrays;

public class YatzyResultCalculator {
    private final Die[] dice;
    private final int[] counts = new int[7]; // indeks 1–6 bruges til at tælle øjne

    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice;
        countEyes();
    }

    // hjælpemethod tæl hvor mange der er af hver øjenværdi
    private void countEyes() {
        Arrays.fill(counts, 0); //Nulstil counts arrayet
        for (Die die : dice) {
            counts[die.getEyes()]++;
        }
    }


    //Sum af alle terninger med den givne værdi
    public int upperSectionScore(int eyes) {
        if (eyes < 1 || eyes > 6) return 0;
        return counts[eyes] * eyes;
    }


    //Enkelt par – højeste par giver point (f.eks. 3,3,5,5 -> 10)
    public int onePairScore() {
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2) return i * 2;
        }
        return 0;
    }


    // par – summen af begge par, ellers 0
    public int twoPairScore() {
        int pairsFound = 0;
        int score = 0;
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2) {
                pairsFound++;
                score += i * 2;
                if (pairsFound == 2) return score;
            }
        }
        return 0;
    }


    //Tre ens – summen af tre ens (kun tre af dem, selvom der er flere)
    public int threeOfAKindScore() {
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 3) return i * 3;
        }
        return 0;
    }


    //Fire ens – summen af fire ens (kun fire af dem, selvom der er flere)
    public int fourOfAKindScore() {
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 4) return i * 4;
        }
        return 0;
    }


    //Lille straight (1,2,3,4,5) = 15 point
    public int smallStraightScore() {
        for (int i = 1; i <= 5; i++) {
            if (counts[i] != 1) return 0;
        }
        return 15;
    }


    //Stor straight (2,3,4,5,6) = 20 point
    public int largeStraightScore() {
        for (int i = 2; i <= 6; i++) {
            if (counts[i] != 1) return 0;
        }
        return 20;
    }


    //Fuldt hus: 3 ens + 2 ens
    public int fullHouseScore() {
        int threeValue = 0;
        int twoValue = 0;
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 3) threeValue = i;
            else if (counts[i] == 2) twoValue = i;
        }
        if (threeValue > 0 && twoValue > 0) {
            return threeValue * 3 + twoValue * 2;
        }
        return 0;
    }

    //Chance: summen af alle øjne
    public int chanceScore() {
        int sum = 0;
        for (int i = 1; i <= 6; i++) {
            sum += counts[i] * i;
        }
        return sum;
    }

    //Yatzy: 5 ens = 50 point
    public int yatzyScore() {
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 5) return 50;
        }
        return 0;
    }

    public java.util.Map<String, Integer> getPossibleScores() {
        java.util.Map<String, Integer> scores = new java.util.LinkedHashMap<>();
        scores.put("1'ere", upperSectionScore(1));
        scores.put("2'ere", upperSectionScore(2));
        scores.put("3'ere", upperSectionScore(3));
        scores.put("4'ere", upperSectionScore(4));
        scores.put("5'ere", upperSectionScore(5));
        scores.put("6'ere", upperSectionScore(6));
        scores.put("Et par", onePairScore());
        scores.put("To par", twoPairScore());
        scores.put("3 ens", threeOfAKindScore());
        scores.put("4 ens", fourOfAKindScore());
        scores.put("Lille straight", smallStraightScore());
        scores.put("Store straight", largeStraightScore());
        scores.put("Fuldt hus", fullHouseScore());
        scores.put("Chance", chanceScore());
        scores.put("Yatzy", yatzyScore());
        return scores;
    }
}
