package models;

import java.util.Random;

public class Die {
    private int eyes;
    private static final Random random = new Random();

    public Die(int eyes) {
        this.eyes = eyes;
    }

    public Die() {
        this.eyes = 6;
    }

    public void roll() {
        this.eyes = random.nextInt(6) + 1; // Giver et tilfældigt tal mellem 1 og 6
    }

    public int getEyes() {
        return eyes;
    }
}
