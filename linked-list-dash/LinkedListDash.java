import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class LinkedListDash {
    public static String solution(String S, int K) {

        // place string into char array, uppercase
        char[] c = S.toUpperCase().toCharArray();

        // add chars to linked list
        List<Character> linked = new LinkedList<>();
        for (char aC : c) {
            if (aC != '-') linked.add(aC);
        }

        // add dashes
        int count = 0;
        for (int i = linked.size() - 1; i > 0; i--) {
            if (count == K) {
                linked.add(i + 1, '-');
                count = 0;
            }
            count++;
        }

        // remove '-' from first index
        if (linked.get(0) == '-') linked.remove(0);

        return linked.toString();
    }

    public static void main(String[] args) {

        // Static test cases
        System.out.println(solution("G", 1));
        System.out.println(solution("2-4A0r7-4k", 2));
        System.out.println(solution("5a37D-1--b", 3));
        System.out.println(solution("ABCDE---", 5));
        System.out.println(solution("s48-23-f-Jwesa64fadjf*8--s", 9));
        System.out.println(solution("sf--dsaf-j3-8ujf---daskfjf3fu--8-79-u-f-np---13-92r", 7));
    }
}
