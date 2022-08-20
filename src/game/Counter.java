// 314712563
package game;

/**
 * Counter is a simple class that is used for counting things.
 * - Add number to current count
 * - Subtract number from current count
 * - Get current count
 */
public class Counter {

    //Fields
    private int counter = 0;

    /**
     * The constructor of the class 'Counter'.
     */
    public Counter() {
    }

    /**
     * The constructor of the class 'Counter'.
     * @param counter counter
     */
    public Counter(int counter) {
        if (counter >= 0) {
            this.counter = counter;
        }
    }

    /**
     * Add number to current count.
     * @param number number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     * @param number number
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * @return Get current count
     */
    public int getValue() {
        return this.counter;
    }
}