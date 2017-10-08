import java.util.*;

public class BinaryMaze {

    private int[][] board;
    private int startLoc = 0;
    private int endLoc;

    public BinaryMaze(int[][] bluePrint) {
        board = bluePrint;
        endLoc = (board.length * board[0].length) - 1;
    }

    public int[][] getBoard() {
        return board;
    }

    void printBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isValid(int row, int col) {
        if (row >= 0 && row <= 3 && col >= 0 && col <= 3) {
            System.out.println("row: " + row);
            System.out.println("col: " + col);
            System.out.println("yo");
            return true;
        }
        return false;
    }


    List<Integer> retriveN4(int loc) {
        int row = loc / board.length;
        int col = loc - (board.length * row);
        List<Integer> aL = new ArrayList<>();

        // up
        if (isValid(row - board.length, col)) aL.add((row - board.length) * board.length + col);
        // down
        if (isValid(row + board.length, col)) aL.add((row + board.length) * board.length + col);
        // left
        if (isValid(row, col - 1)) aL.add(row * board.length + col - 1);
        // right
        if (isValid(row, col + 1)) aL.add(row * board.length + col + 1);

        return aL;
    }

    int bunnyEscapeRoute() {
        boolean[] visited = new boolean[board.length * board[0].length];
        Arrays.fill(visited, false);
        visited[0] = true;

        List<Integer> movesAL;
        Map<Integer, Integer> hashMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startLoc);
        while (!queue.isEmpty()) {
            int frontLoc = queue.peek();
            if (frontLoc == endLoc) break;
            else {
                System.out.println("mayy");
                queue.poll();
                movesAL = retriveN4(frontLoc);
                System.out.println(movesAL);
                for (int a : movesAL) {
                    if (!visited[a]) {
                        visited[a] = true;
                        queue.add(a);
                        hashMap.put(a, frontLoc);
                        System.out.println("hi");
                    }
                }
            }
        }
        List<Integer> routeList = new LinkedList<>();
        for (int n = endLoc; n != startLoc; n = hashMap.get(n)) {
            routeList.add(0, n);
        }
        return routeList.size();
    }

    public static void main(String[] args) {

        int[][] bluePrint1 = {{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}};

        BinaryMaze binaryMaze = new BinaryMaze(bluePrint1);
        binaryMaze.printBoard();
        binaryMaze.bunnyEscapeRoute();
    }
}











