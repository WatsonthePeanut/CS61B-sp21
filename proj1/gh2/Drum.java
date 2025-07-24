package gh2;

public class Drum extends GuitarString {
    private static final double DECAY = 1.0;

    public Drum(double frequency) {
        super(frequency);
    }

    @Override
    public void tic() {
        double num1 = buffer.removeFirst();
        double num2 = buffer.get(0);
        double newDouble = (num1 + num2) / 2 * DECAY;

        if (Math.random() > 0.5) {
            newDouble = -newDouble;
        }

        buffer.addLast(newDouble);
    }
}
