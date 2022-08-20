// 314712563
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Houri
 * This class include multiple utils functions that used by the main classes.
 */
public class Utils {

    public static final int  FIRST_ITERATION = -1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER = 30;

    /**
     * The methode check the validity of the parameters.
     * @param args the arguments from the user
     * @return valid - an array of integers, else null.
     */
    public static List<Integer> checkArgs(String[] args) {
        int temp;
        List<Integer> list = new ArrayList<>();
        for (String arg : args) {
            //If the the string is not an integer the function return an exception
            try {
                temp = Integer.parseInt(arg);
                if (temp < 0) {
                    list.add(-temp);
                } else {
                    list.add(temp);
                }
            } catch (Exception e) {
                System.out.println(arg);
            }
        }
        return list;
    }

    /**
     * The methode checks if the variables are approximately equal.
     * @param a the first number
     * @param b the second number
     * @return true - it's approximately equal, else false
     */
    public static boolean round(double a, double b) {
        double epsilon = Math.pow(10, -5);
        // this checks if a-b is closer to 0 than epsilon, if true, then a probably equals b
        return (Math.abs(a - b) < epsilon);
    }
}