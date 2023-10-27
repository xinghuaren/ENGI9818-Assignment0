import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        final char[][] maze = new char[][] {
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'E', ' ', ' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', 'W', 'X', ' ', 'W', ' ', 'W', ' ', 'W'},
                {'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', 'W'},
                {'W', ' ', 'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W'},
                {'W', ' ', ' ', 'W', ' ', 'W', ' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', 'W', ' ', 'W', ' ', 'W'},
                {'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W', ' ', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W'},
                {'W', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' ', ' ', ' ', 'W', ' ', 'W'},
                {'W', 'W', 'W', 'W', ' ', ' ', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', ' ', 'W', ' ', 'W', ' ', 'W', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W'},
        };

        final Escape escape = new EscapeImpl();
        final List<Pair<Integer, Integer>> result = escape.escape(maze);

        System.out.println(result.toString());

        for (Pair<Integer, Integer> pair : result) {
            System.out.println(
                "(" + pair.first + ", " + pair.second + ")"
            );
        }
    }
}