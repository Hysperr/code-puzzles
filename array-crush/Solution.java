import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/crush

public class Solution {
    public static void main (String [] args) {

        /*  Simple Sample Input from the problem page.
            5 3
            1 2 100
            2 5 100
            3 4 100

            output should be 200
         */
        Scanner k = new Scanner(System.in);

        // size of our array which holds the numbers
        int nSize = 5;

        // create array of size n
        int [] array = new int[nSize];

        // initialize array with zeros
        Arrays.fill(array, 0);

        // arraylist to hold a, b, and k
        ArrayList<Integer> tmp = new ArrayList<>();

        // var prep
        int max = -1000;
        int numOps = 0;

        // Prepare scanner
        System.out.println("You will determine the number of operations. For now let's begin.");
        String val = "Yes";

        // Begin
        while (val.equalsIgnoreCase("yes")) {
            // gather input
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Enter value for A");
                        break;
                    case 1:
                        System.out.println("Enter value for B");
                        break;
                    case 2:
                        System.out.println("Enter value for K");
                        break;
                    default:
                        // do nothing
                }
                // add a,b,k to arraylist
                int mInput = k.nextInt();
                tmp.add(mInput);
            }

            // perform operation, 0 = a, 1 = b, 2 = k; diff is how much to increment from tmp.get(0)
            for (int i = tmp.get(0); i <= tmp.get(0) + (tmp.get(1) - tmp.get(0)); i++) {
                array[i-1] += tmp.get(2);
                max = (array[i - 1] > max) ? array[i - 1] : max;
            }

            System.out.println();
            System.out.println("The max is: " + max);
            System.out.println(Arrays.toString(array));
            System.out.println("Number of operations completed thus far " + ++numOps);
            System.out.println("Continue Current run? Enter YES to continue or NO to quit.");
            tmp.clear();

            // must consume scanner object for the try/catch
            k.next();
            // Decide for next round.
            while (true) {
                try {
                    val = k.nextLine();
                    if (val.equalsIgnoreCase("yes") || val.equalsIgnoreCase("no")) {
                        break;
                    }
                    else
                        System.out.println("Invalid input. Enter Yes or No.");
                }
                catch (InputMismatchException e) {
                    System.out.println("How did you even manage to break this part?!");
                    k.next();   // flush scanner object
                }
            }

        }   // end while

    }   // end main

}   // end Solution.java
