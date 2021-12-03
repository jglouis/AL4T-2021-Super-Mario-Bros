package be.ecam.chess;

public class BoardUtils {
    /**
     * Computes (x,y) coordinates from its corresponding human representation.
     * It does not check for bounds.
     *
     * @param humanCoordinates a string representing a coordinate in the form "a1"
     * @return an array of two integers, the first one being the x coordinate,
     * the second one being the y coordinate
     * @throws IllegalArgumentException if the string is not in the correct format
     * @throws NullPointerException     if the string is null
     */
    public static int[] humanChessCoordinatesToXY(String humanCoordinates) {
        int[] xy = new int[2];
        xy[0] = humanCoordinates.charAt(0) - 97;
        xy[1] = (Integer.parseInt(humanCoordinates.substring(1))) - 1;
        return xy;
    }
}
