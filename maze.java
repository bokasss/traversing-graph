import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Maze {

    private static final String INPUT_FILE = "Maze.txt",
            WORK_FILE = "Work.txt";
    private static final int ROWS = 16,
            COLUMNS = 24;

    public static void main(String[] args) {

        // Read the file containing 0's and 1's into a matrix of numbers
        Scanner inFile = null;
        try {
            FileInputStream inStream = new FileInputStream(INPUT_FILE);
            inFile = new Scanner(inStream);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file " + INPUT_FILE);
            System.exit(0);
        }

        // Load into a matrix
        int[][] matrix = new int[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            String marks = inFile.nextLine();
            for (int column = 0; column < COLUMNS; column++) {
                matrix[row][column] = Integer.parseInt(marks.substring(column, column + 1));
            }
        }
        inFile.close() ;

        // Cut the chord to shortest path (part 2 of the project)
        // matrix[1][19] = 1 ;

        // Find all of the vertices and assign vertex numbers starting at 0
        int[][] vertices = new int[ROWS][COLUMNS];
        int nextVertex = 1;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                vertices[row][column] = -1;                    // Not a vertex
                if (matrix[row][column] == 0) {
                    boolean vertical = false;
                    boolean horizontal = false ;
                    if (((row > 0) && (matrix[row - 1][column] == 0)) |
                        ((row < ROWS - 1) && (matrix[row + 1][column] == 0))) {
                            vertical = true ;
                    }
                    if (((column > 0) && (matrix[row][column - 1] == 0)) |
                        ((column < COLUMNS - 1) && (matrix[row][column + 1] == 0))) {
                        horizontal = true ;
                    }
                    if (horizontal && vertical) {
                        vertices[row][column] = nextVertex++;
                    }
                }
            }
        }
        vertices[0][0] = 0 ;                                   // Starting vertex (corner)
        vertices[ROWS - 1][COLUMNS - 1] = nextVertex++ ;       // Finish vertex (corner)

        for (int i = 0 ; i < ROWS ; i++) {
            for (int j = 0 ; j < COLUMNS ; j++) {
                System.out.printf("%3d", vertices[i][j]) ;
            }
            System.out.println() ;
        }
        System.out.println() ;

        // Open a file to output column, row, distance combinations for input to the work file.
        PrintWriter writer = null;
        try {
            FileOutputStream outstream = new FileOutputStream(WORK_FILE);
            writer = new PrintWriter(outstream);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file " + WORK_FILE);
            System.exit(0);
        }
        
        /* Complete the program
        
        Write all horizontal paths onto the work file
            1. Scan each row until reaching a vertex number or the end of the row
            2. Continue scanning the row until reaching another vertex number
            3. Write the "from" and "to" vertices onto the work file plus the distance between the vertices
            4. Loop back to step 1 starting after the "to" vertex
        
        Do the same thing writing all of the vertical paths onto the work file
        
        Close the work file

        Open Scanner to the work file
        
        Create an undirected graph using (either matrix or array).  Use the "loadEdgesFromFile" method
        
        Use the DijkstrasAlgorithm class to compute the shortest path
        
        For each vertex, print the predecessor and the distance to the starting square.
        
        Uncomment the line which cuts the chord to the shortest path above and reprint
        
        */
    }
}
