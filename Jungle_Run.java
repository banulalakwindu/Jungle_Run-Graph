// Import Scanner class
import java.util.Scanner;

// Create point class
class point {
    int i; // x-coordinate
    int j; // y-coordinate
    char val; // char value of point
    int dist; // distance from start point

    // Constructor
    point(int i, int j, char val, int dist) {
        this.i = i; // x-coordinate
        this.j = j; // y-coordinate
        this.val = val; // char value of point
        this.dist = dist; // distance from start point
    }
}

// Create jungleRun class
class jungleRun {

    // Declare variables
    point[][] map; // map of points
    int n; // size of map
    boolean[][] visited; // visited points

    // Constructor
    jungleRun(point[][] map, int n) {
        this.map = map; // map of points
        this.n = n; // size of map
        visited = new boolean[n][n]; // visited points
    }

    // Method to find the start point
    void findS() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].val == 'S') {
                    visited[i][j] = true; // mark start point as visited
                    map[i][j].dist = 1; // set distance from start point
                }
            }
        }
    }

    // Method to find the end point
    void findE() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].val == 'E') {
                    if (map[i][j].dist == 0)
                        System.out.println("No Path"); // no path
                    else
                        System.out.println("Shortest Path = " + (map[i][j].dist - 1)); // shortest path
                }
            }
        }
    }

    // Method to mark near points
    void markNear(point p) {

        if (p.i > 0 && map[p.i - 1][p.j].val != 'T' && !visited[p.i - 1][p.j]) { // up
            map[p.i - 1][p.j].dist = p.dist + 1; // set distance from start point
            visited[p.i - 1][p.j] = true; // mark point as visited
        }

        if (p.j > 0 && map[p.i][p.j - 1].val != 'T' && !visited[p.i][p.j - 1]) { // left
            map[p.i][p.j - 1].dist = p.dist + 1; // set distance from start point
            visited[p.i][p.j - 1] = true; // mark point as visited
        }

        if (p.i < n - 1 && map[p.i + 1][p.j].val != 'T' && !visited[p.i + 1][p.j]) { // down
            map[p.i + 1][p.j].dist = p.dist + 1; // set distance from start point
            visited[p.i + 1][p.j] = true; // mark point as visited
        }

        if (p.j < n - 1 && map[p.i][p.j + 1].val != 'T' && !visited[p.i][p.j + 1]) { // right
            map[p.i][p.j + 1].dist = p.dist + 1; // set distance from start point
            visited[p.i][p.j + 1] = true; // mark point as visited
        }
    }

    // Method to find the shortest path
    void calc() {

        // Find start point
        findS();

        // declare variables
        int p = 1;

        // loop until all points are visited
        while (n * 2 > p) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].dist == p) {
                        if (map[i][j].val == 'E') {
                            findE(); // find end point
                            return; // return
                        }
                        markNear(map[i][j]); // mark near points
                    }
                }
            }
            p++; // increment p
        }
        findE(); // find end point
    }
}

// Main Class
public class Jungle_Run {

    // Main Method
    public static void main(String[] args) {

        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // Get size of map from user
        System.out.print("Enter the size of matrix: ");
        int size = input.nextInt();
        input.nextLine();

        // Create map of points
        point[][] matrix = new point[size][size];

        // Loop to get char value of each point
        System.out.println("Enter the matrix: ");

        for (int i = 0; i < size; i++) {
            String Line = input.nextLine(); // get line of char values
            Line = Line.replaceAll("\\s", ""); // remove spaces
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new point(i, j, Line.charAt(j), 0); // create points
            }
        }
        
        // Create jungleRun object
        jungleRun jr = new jungleRun(matrix, size);

        // Find the shortest path
        jr.calc();
        
    }
}
