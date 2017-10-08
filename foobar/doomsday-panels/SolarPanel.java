import java.util.*;

public class SolarPanel {

    /**
     * This function demonstrates dynamic programming
     * using an iterative approach in which we solve a series
     * of recurring subproblems to solve for a global optimum.
     *
     * The idea behind this optimization is to continuously
     * extract the largest perfect squares from
     * the remaining area until that remaining area is 0.
     *
     * @param area total squared area.
     * @return a list containing largest squares.
     */
    public static List<Integer> solarOptimization(int area) {
        List<Integer> aL = new ArrayList<>();
        int remainingArea = area;
        while (remainingArea > 0) {
            int sqrtArea = (int) Math.sqrt((double) remainingArea);
            int perfectSquare = sqrtArea * sqrtArea;
            aL.add(perfectSquare);
            remainingArea -= perfectSquare;
        }

        return aL;
    }

    // version 2: int array return type
    public static int[] solarInt(int area) {
        List<Integer> aL = new ArrayList<>();
        int remainingArea = area;
        while (remainingArea > 0) {
            int sqrtArea = (int) Math.sqrt((double) remainingArea);
            int perfectSquare = sqrtArea * sqrtArea;
            aL.add(perfectSquare);
            remainingArea -= perfectSquare;
        }

        int idx = 0;
        int arr[] = new int[aL.size()];
        for (int i : aL) arr[idx++] = i;

        // Java8 arraylist to int[]
        // return aL.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }    

    public static void main(String[] args) {
        int area = 15324;
        List<Integer> aList = solarOptimization(area);
        System.out.println(aList);

        int [] arr = solarInt(area);
        System.out.println(Arrays.toString(arr));
    }
}
