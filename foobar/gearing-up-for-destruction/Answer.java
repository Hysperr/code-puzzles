import java.util.Arrays;

public class Answer {
    public static int[] answerOld(int[] pegs) {
        int[] tmp = new int[pegs.length];
        int lastIndex = pegs.length - 1;
        int maxR0 = pegs[1] - pegs[0] - 1;
        for (int possibleR0 = 1; possibleR0 <= maxR0; ++possibleR0) {
            tmp[0] = possibleR0; // incr radius R0
            for (int idx = 0; idx < lastIndex; ++idx) {
                tmp[idx + 1] = (pegs[idx + 1] - pegs[idx]) - tmp[idx];
            }
            // if condition... return the a/b ratio
            int mysteryDistance = (pegs[lastIndex] - pegs[lastIndex - 1]) - tmp[lastIndex - 1];
            if (2 * mysteryDistance == tmp[0]) {
                return new int[]{tmp[0], 1};
            }
            if (2 * mysteryDistance == tmp[0] + 1) {
                return new int[]{tmp[0] * 3 + 1, 3};
            }
            if (2 * mysteryDistance == tmp[0] + 2) {
                return new int[]{tmp[0] * 3 + 2, 3};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * My solution solves every array of pegs given, however, the expression
     * of the solution is not 100%.
     *
     * I approached this problem by setting up a system of
     * equations for the unknowns r1, r2...rn-1. From there I created a matrix
     * and for each difference between pegs, and performed Gaussian Elimination
     * to diagonalize the matrix. I then could extrapolate the y term. This is
     * achieved via alternating addition and subtraction through the differences
     * in the matrix. any even or 2x2 matrix would result in an result that must
     * be divided by 3. From there, arithmetic is used to determine a divisor and
     * afterwards the entire beam is fitted with gears starting with our known x
     * gear radius. A simple check completes the process.
     *
     * This solution approaches the problem by setting up
     * a system of equations. From there
     * @param pegs
     * @return
     */
    public static int[] answer(int[] pegs) {
        boolean nEven = pegs.length % 2 == 0 ? true : false;    // even / 3, odd / 1
        int diff[] = new int[pegs.length - 1];
        for (int i = 0; i < pegs.length - 1; ++i) {
            diff[i] = pegs[i + 1] - pegs[i];
        }
        int y = 0;
        // alternate diff in spaces for y's radius
        for (int i = 0; i < diff.length; ++i) {
            if (i % 2 == 0)
                y += diff[i];
            else
                y -= diff[i];
        }
        // x = 2y
        int x = y * 2;
//        System.out.println("y is: " + y);       // sanity check

        // divide or not, determined via matrix
        int div = 1;
        if (nEven) {
            if (x % 3 == 0) x /= 3; // div = 1
            else div = 3;
        }
        // insert all gears from solved radius0 'x'
        int[] radii = new int[pegs.length];
        radii[0] = x;
        for (int idx = 0; idx < radii.length - 1; ++idx) {
            radii[idx + 1] = (pegs[idx + 1] - pegs[idx]) - radii[idx];
        }

        // check that last 2 gears are touching
        if (radii[radii.length - 1] + radii[radii.length - 2] == diff[diff.length - 1]) {
            return new int[]{radii[0], div};
        }
        else {
            return new int[]{-1, -1};
        }
    }


    public static void main(String[] args) {
        int[] pegs = {4, 30, 50};
        int[] pegs2 = {8, 36, 51, 68};
        int[] pegs3 = {8, 36, 51};

        int[] solution = answer(pegs2);
        System.out.println(Arrays.toString(solution));
    }
}
