package models;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        //Create an instance of RaffleCup.
        for (int i = 0; i < dice.length; i++) { // For hver indeks i dice arrayet laves en terning
            dice[i] = new Die();
        }
    }

    public void throwDice() {
        //implement throwDice method.
        for (Die die : dice) { //Ruller hver terning i dice arrayet
            die.roll();
        }
    }

    public Die[] getDice() {
        return dice;
    }
}
