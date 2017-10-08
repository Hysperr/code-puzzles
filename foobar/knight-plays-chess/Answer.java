import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Queue;
import java.util.List;

public class Answer {

    /**
     * Generates all valid knight "L" moves on an 8x8 board. The
     * formal parameter <code>loc</code> corresponds to a square location
     * within the 2D chess board. Using 1D array syntax, we can extract
     * from the 2D array, the individual row and column that combine
     * to form the 2D location. With some arithmetic we can manipulate the row
     * and column as rvalues to correspond to a knight's "L" moves and place them
     * within tmpRow and tmpCol, respectively. If both tmpRow and tmpCol are
     * valid indices within the 2D board (checked using  {@code isValid})
     * then we add to the arraylist, the corresponding location within the 2D array
     * via 1D syntax reconstruction such that {@code board[tmpRow][tmpCol]} is
     * equivalent to {@code board[width*tmpRow+tmpCol]}.
     * <p>
     * A slightly more compact way of writing the permutations
     * of "L" moves can be achieved but for visual clarity I chose this pattern.
     * Furthermore, this can easily be expanded to an MxM board by passing an
     * additional parameter {@code int M} and setting variable {@code width = M}.
     * Alternatively, assuming there's an <code>int[][] board</code> member variable, set
     * <code>width = board[0].length;</code>
     *
     * @param loc current location on 2D board (not index!)
     * @return a list containing each location's valid "L" destinations as an integer
     * (adjacency list)
     */
    public static List<Integer> generateEdges(int loc) {
        int width = 8;
        int row = loc / width;
        int col = loc - (width * row);
        List<Integer> aL = new ArrayList<>();
        int tmpRow, tmpCol;

        // up2L
        tmpRow = row - 2;
        tmpCol = col - 1;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);
        // up2R
        tmpCol = col + 1;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);

        // up1L
        tmpRow = row - 1;
        tmpCol = col - 2;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);
        // up1R
        tmpCol = col + 2;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);

        // dw2L
        tmpRow = row + 2;
        tmpCol = col - 1;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);
        // dw2R
        tmpCol = col + 1;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);

        // dw1L
        tmpRow = row + 1;
        tmpCol = col - 2;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);
        // dw1R
        tmpCol = col + 2;
        if (isValid(tmpRow, tmpCol)) aL.add(width * tmpRow + tmpCol);

        return aL;
    }

    /**
     * Ensures row and col parameters are valid indices in the 8x8 board,
     * because in {@code generateEdges} we perform 2D access of our board
     * using 1D array syntax i.e. {@code board[row * width + col]}.
     * Values are hard-coded for 8x8 board. Trivial to expand for MxM board.
     *
     * @param row row index in board
     * @param col column index in board
     */
    public static boolean isValid(int row, int col) {
        return (row >= 0 && row <= 7 && col >= 0 && col <= 7);
    }


    /**
     * The function is best approached as a directional graph with edges
     * connecting to indices at an "L" shape. These edges can be represented
     * using an adjacency list {@code aL}. Since the edges of the graph
     * are not weighted in any way, Dijkstra's search algorithm isn't necessary,
     * so we use Breadth First Search as the most reasonable alternative to traverse
     * the board. A FIFO queue holds the current square whose value is checked for the
     * destination's value. If not our destination square, we place its adjacent squares
     * (reachable only by "L" moves), into the back of a the queue only if said squares
     * have NOT been visited yet (otherwise you begin looping). Once you have placed a
     * square into the queue, mark its corresponding visited index as seen (true).
     * <p>
     * Now, the return trip: Using a hashmap, place the adjacent child squares into the
     * map as a key, and the single parent that led to said child, as the corresponding value.
     * This pair mapping is crucial as it allows us to efficiently return to our starting
     * square once we have found our destination. Specifically, once we reach our
     * destination, we break out of our while-loop and enter a final loop over the
     * hashmap to count exactly how far away we are from our original starting square.
     * By starting the return trip from our destination (which is a child) and 'jumping' back to
     * the corresponding parent of our current child square, we will eventually return to our original
     * starting square. This is exactly why each pair in the hashmap is a child (key) and parent (value).
     * At each jump back to a parent, we add the routed square to the linked-list to obtain the route
     * information in case we would like to later print/analyze it. Alternatively, simply incrementing
     * an integer value can achieve the route number.
     * <p>
     * Lastly, we return the size of this
     * linked-list representing the minimum number of moves required to reach any destination
     * on the chess board from a given starting point using a knight's "L" move set.
     *
     * @param src  starting square on 2D board
     * @param dest destination square on 2D board
     * @return minimum number of "L"-shaped knight moves required to reach
     * dest from src
     */
    public static int answer(int src, int dest) {
        HashMap<Integer, Integer> returnRoute = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> aL;   // initialized later via function return(technically obj reference points to initialized aL)

        boolean[] visited = new boolean[64];
        Arrays.fill(visited, false);
        visited[src] = true;
        queue.add(src);

        while (queue.size() != 0) {
            int front = queue.peek();
            System.out.println("queue front: " + front);
            if (front == dest) break;
            else {
                queue.poll();
                aL = generateEdges(front);
                System.out.println(aL);
                for (int i : aL) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                        returnRoute.put(i, front);
                    }
                }
            }
            System.out.println("---------------------");
        }

        List<Integer> routeList = new LinkedList<>();
        // go back home
        for (int n = dest; n != src; n = returnRoute.get(n)) {
            routeList.add(0, n);
        }

        return routeList.size();
    }


    public static void main(String[] args) {
        int start = 0;
        int finish = 1;

        int minKnightMoves = answer(start, finish);

        System.out.println("\nSEARCHING COMPLETE...");
        System.out.println("Start: " + start + "\nFinish: " + finish);
        System.out.println("Minimum knight moves: " + minKnightMoves);

    }
}
