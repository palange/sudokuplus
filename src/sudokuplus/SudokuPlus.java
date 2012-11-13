package sudokuplus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Sudoku Plus programming test
 * SudokuPlus.java
 *
 * @author Mark Palange
 * @version 1.0 11/12/2012
 */
public class SudokuPlus {
    private static final String usage = "Usage: java SudokuPlus <file1> <file2> <file...>";

    /**
     * Main entry point - takes a list of args, one for each file to read.
     *
     * @param args An array of puzzle files to test.
     */
    public static void main(String[] args) {
        for (String s: args) {
            System.out.format("Reading file \"%s\"\n", s);
            try {
                Puzzle p = new Puzzle(readCSV(s));
                System.out.format("Puzzle \"%s\" is %s.\n", s, (p.isComplete()) ? "solved" : "not solved");
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
            catch (PuzzleException e) {
                System.err.println(e.getReason());
            }
        }
    }

    /**
     * Read a CSV file and return an ArrayList<String[]>.
     *
     * @param filename Name of a CSV file to load.
     * @return A list of String[] containing the contents of the csv file.
     * @throws IOException - for cases when the file can not be read.
     */
    public static ArrayList<String[]> readCSV(String filename) throws IOException {
        ArrayList<String[]> row_list = new ArrayList<String[]>();
        BufferedReader csvFile =
                new BufferedReader(new FileReader(filename));

        String line = csvFile.readLine();
        while (line != null) {
            String[] row_data = line.split(",");
            row_list.add(row_data);
            line = csvFile.readLine();
        }

        csvFile.close();
        return row_list;
    }
}

/**
 * Exception override for Puzzle specific exceptions.
 */
class PuzzleException extends Exception {
    String m_reason;
    PuzzleException(String reason) { m_reason = reason; }
    public String getReason() { return m_reason; }
}

/**
 * Representation of a sudoku puzzle, designed for validation and not much else.
 */
class Puzzle {
    private int m_size;
    private boolean[][] m_rows;
    private boolean[][] m_columns;
    private boolean[][] m_boxes;

    /**
     * Construct a puzzle from a list of String[]s.
     * The list length sets the dimension (N) of the puzzle.
     * N must be a perfect square.
     * Each String[] length must equal N.
     * The values of each string must be in the range 1 to N.
     *
     * @param rows_list A list of String[]s describing the puzzle state.
     *
     * @throws PuzzleException on any puzzle related error.
     */
    Puzzle(ArrayList<String[]> rows_list) throws PuzzleException {
        m_size = rows_list.size();
        int m_size_sqrt = (int) Math.sqrt(m_size);

        // *NOTE: Assuming booleans are initialized to false.
        m_rows = new boolean[m_size][m_size];
        m_columns = new boolean[m_size][m_size];
        m_boxes = new boolean[m_size][m_size];

        if(m_size_sqrt * m_size_sqrt  != m_size)
            throw new PuzzleException("Size of puzzle needs to be perfect square.");
        int row_index = 0;
        for(String [] row_data: rows_list) {
            if(row_data.length != m_size)
                throw new PuzzleException(String.format("Puzzle row %d length is not consistent.", row_index));

            int col_index = 0;
            for(String value_string: row_data) {
                // *TODO: Provide a function for string to internal repr conversion.
                int value = Integer.parseInt(value_string) - 1;
                if(value < 0 || value >= m_size)
                    throw new PuzzleException(
                            String.format("Found invalid cell value at row  %d, column %d", row_index, col_index));
                m_rows[row_index][value] = true;
                m_columns[col_index][value] = true;
                m_boxes[col_index/m_size_sqrt + (m_size_sqrt*(row_index/m_size_sqrt))][value] = true;
                ++col_index;
            }
            ++row_index;
        }
    }

    /**
     * Return whether the current puzzle state is complete and correct.
     *
     * @return true if puzzle state is complete and correct.
     */
    public boolean isComplete() {
        for(int set_index = 0; set_index < m_size; ++set_index)
        {
            for(int value = 0; value < m_size; ++value)
            {
                if(!m_rows[set_index][value] || !m_columns[set_index][value] || !m_boxes[set_index][value])
                    return false;
            }
        }
        return true;
    }
}
