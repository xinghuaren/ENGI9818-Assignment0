import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EscapeImpl is a class to implement a graph traversal algorithm to find a path
 * from the entrance to the exit of a maze. It uses a recursive function to search
 * neighbor nodes along four directions (north, east, south, west). Once it encounters
 * an unvisited node, it will take one of its neighbor nodes (if exists) as the
 * next node on this branch. Recursively call the function to take the next node
 * as the 'entrance node' and solve the sub-problem. When reaching the end of a
 * branch, it backtracks to previous nodes and continue searching the next branches.
 *
 * @version 1.0 01 Oct 2023
 * @author Xinghua Ren
 */
public class EscapeImpl implements Escape {
    /**
     * escape is a method to find a path from the entrance to the exit in a maze.
     *
     * @param maze The maze to be searched, which is a 2-D matrix of character array.
     *             'E' represents the entrance; 'W' represents impassible walls;
     *             'X' represents the exit; ' ' represents reachable cell.
     * @requires   The maze is a 2-D character array with arbitrary number of rows
     *             and columns. Characters in maze is either 'E' or 'X' or 'W' or ' '.
     *             Entrance or exit or a valid path from entrance to exit may don't
     *             exist in the maze, but it is recommended that only one entrance
     *             and one exit should exist in it.
     * @ensures    If the returned list is not empty, cells corresponding to all
     *             items in the list can be connected to form a continuous path,
     *             which means there is no jump between neighbouring cells.
     * @return     A list of coordinates, from the exit point to the entrance point.
     *             The first item of the list is the coordinate of exit point
     *             while the last item is the coordinate of the entrance point.
     *             An empty list will be returned if no entrance or no exit exists
     *             or no valid path exists from entrance to exit.
     */
    @Override
    public List<Pair<Integer, Integer>> escape(final char[][] maze) {

        /* Store the number of rows and columns of maze. */
        int rows = maze.length;
        int cols = maze[0].length;

        /* Create a 2-D array to keep track of visited cells. */
        boolean[][] visit = new boolean[rows][cols];

        /* Create a list to include coordinates of valid cells on the path as result. */
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        /* Declare and initialize. */
        int[] entrance = new int[0];
        int[] exit = new int[0];

        /* Scan the whole maze to get coordinates of entrance and exit. */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'E') {
                    entrance = new int[] {i, j};
                } else if (maze[i][j] == 'X') {
                    exit = new int[] {i, j};
                }
            }
        }

        /* Return an empty list if no entrance or exit exists in maze. */
        if ( entrance.length == 0 || exit.length == 0) {
            return result;
        }

        /* Return result of the path if the search method find a valid path. */
        if (search(maze, entrance, exit, visit, result)) {

            /* Reverse the list to meet requirements of contract. */
            Collections.reverse((result));
            return result;
        }

        /* Return an empty list if no valid path exists. */
        result.clear();
        return result;
    }

    /**
     * search is a method that uses recursion to implement this algorithm:
     * 1. If current cell is out of bounds or at a visited cell or at a wall,
     *    return false.
     * 2. Else if current cell is at exit cell, return true.
     * 3. Else it adds current cell to list of path and recursively search in
     *    all four directions.
     * 4. If it returns false, the cell is removed from the path.
     *
     * @param maze   The maze to be searched.
     * @param curr   The coordinate of current cell.
     * @param exit   The coordinate of exit cell.
     * @param visit  The 2-D array to keep track of visited cells.
     * @param result The list of coordinates, from entrance cell to current cell.
     * @requires     Integer array curr and exit has 2 elements to store coordinates
     *               of cells. Boolean array visit is 2-D and has the same number
     *               of rows and columns as maze.
     * @ensures      It returns true if a valid path is found and returns false otherwise.
     * @return       A boolean indicating whether a valid path exists between the
     *               curr and exit cell.
     */
    private boolean search(char[][] maze, int[] curr, int[] exit, boolean[][] visit,
                           List<Pair<Integer, Integer>> result) {

        /* Coordinate of current cell is out of bounds. */
        if (curr[0] < 0 || curr[0] >= maze.length
                || curr[1] < 0 || curr[1] >= maze[0].length) {
            return false;
        }

        /* Current cell is already visited or current cell is wall. */
        if (visit[curr[0]][curr[1]] || maze[curr[0]][curr[1]] == 'W') {
            return false;
        }

       /* Exit is found. */
        if (curr[0] == exit[0] && curr[1] == exit[1]) {
            result.add(new Pair<>(curr[0], curr[1]));
            return true;
        }

        /* Mark current cell as visited. */
        visit[curr[0]][curr[1]] = true;

        /* Add coordinate of current cell to list of result. */
        result.add(new Pair<>(curr[0], curr[1]));

        /* Create two arrays to help us iterate over four directions. */
        int[] directionX = {-1, 0, 1, 0};
        int[] directionY = {0, 1, 0, -1};

        /*
         * Use loop to go through all four directions. Create two variables row and col
         * to move along a specific direction. Recursively call search from cell (row, col).
         * If any recursive call returns true, we return true from the main call as we
         * got a path to reach the exit cell.
         */
        for (int i = 0; i < 4; i++) {
            int row = curr[0] + directionX[i];
            int col = curr[1] + directionY[i];
                if (search(maze, new int[] {row, col}, exit, visit, result)) {
                    return true;
            }
        }

        /* Remove the cell from the path if false is returned. */
        result.remove(result.size() - 1);

        /* There is no path to reach the exit cell. */
        return false;
    }
}