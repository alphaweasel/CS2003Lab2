package labtwo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Garrison Henkle
 * @version jre1.8.0_144
 * @since 9-7-17
 *
 */
public class SeatMap {

	private String seatMapAr[][];
	private String inputAr[][];
	private String name;
	private String loc;
	private int row = 5;
	private int column;
	private int count;
	private int rowLoc = 0;
	private int columnLoc = 0;
	private int flag = 0;

	public SeatMap() {
		readFile();
		populateArray();
	}

	/**
	 * Reads the file and stores it in the temporary array, inputAr
	 */
	public void readFile() {
		try {
			// import the file and set up the scanner
			File inputFile = new File("src/names.dat");
			Scanner input = new Scanner(inputFile);

			// set count variable to 0 and count the total number of names
			count = 0;

			while (input.hasNextLine()) {
				System.out.println(input.nextLine());
				count++;
			} // end while

			// divide the number of names by 5 and find the number of columns
			column = (int) (Math.ceil((double) count / (double) row));

			// reset count to 0 for the next loop and reset the location variables
			count = 0;
			rowLoc = 0;
			columnLoc = 0;

			while (input.hasNextLine()) {
				inputAr[rowLoc][columnLoc] = input.nextLine();
				rowLoc++;
				if (rowLoc == row) {
					rowLoc = 0;
					columnLoc++;
				}
			} // end while

			input.close();

		} catch (

		IOException ex) {
			System.err.println("IOException in reading input file!!!");
		} // end try catch
	} // end readFile method

	/**
	 * Fills the array seatMapAr with data from the temporary array inputAr
	 */
	public void populateArray() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (inputAr[i][j] != null)
					seatMapAr[i][j] = inputAr[i][j];
				else {
					flag = 1;
					break;
				}
			} // end of column for loop
			if (flag == 1)
				break;
		} // end of row for loop
	} // end of populateArray method

	/**
	 * asks user to input the row and column then returns the name of that column or
	 * gives an error if no name is in that location
	 * 
	 * @return name
	 */
	public String getSeat() {
		Scanner userIn = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Enter a valid row and column location (in integers) of the desired name in the format \"row,column\":");
			loc = userIn.nextLine();
			StringTokenizer st = new StringTokenizer(loc, ",");
			rowLoc = Integer.parseInt(st.nextToken());
			columnLoc = Integer.parseInt(st.nextToken());

			if (!(rowLoc >= row) || !(columnLoc >= column) || seatMapAr[rowLoc][columnLoc] != null) {
				name = seatMapAr[rowLoc][columnLoc];
				break;
			} else if (rowLoc >= row)
				System.out.println("Please enter a valid row number");
			else if (columnLoc >= column)
				System.out.println("Please enter a valid column number");
			else if (seatMapAr[rowLoc][columnLoc] == null)
				System.out.println("There is no student in this seat.  Please enter another seat location.");
		} // end while loop
		userIn.close();
		return name;
	} // end of getSeat method

	public static void main(String[] args) {
		SeatMap seat = new SeatMap();
		seat.getSeat();
	}
} // end of SeatMap class
