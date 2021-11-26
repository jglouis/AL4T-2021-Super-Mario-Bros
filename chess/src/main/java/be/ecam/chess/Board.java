package be.ecam.chess;

import be.ecam.chess.piece.Piece;

/**
 * A 8x8 chess board. It acts like a simple container for {@link Piece}s.
 * One cell can only contain 0 or 1 {@link Piece}. It does not implement other chess rules.
 */
public class Board implements IBoard {
    private final Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Add a {@link Piece} to the board.
     * @param piece the {@link Piece} to add
     * @param x x coordinate
     * @param y y coordinate
     * @throws CellIsNotEmptyException if the cell is not empty
     * @throws OutOfBoundException if the cell is out of bound
     */
    @Override
    public void addPiece(Piece piece, int x, int y) throws CellIsNotEmptyException, OutOfBoundException {
        assertCellInbound(x, y);
        if (board[x][y] != null) {
            throw new CellIsNotEmptyException(x, y);
        }
        board[x][y] = piece;
    }

    /**
     * Return the piece at given coordinates.
     * @param x x coordinate
     * @param y y coordinate
     * @return the Piece or null if cell is empty
     */
    @Override
    public Piece getPiece(int x, int y) throws OutOfBoundException {
        assertCellInbound(x, y);
        return board[x][y];
    }

    /**
     * Move a {@link Piece} from one cell to another.
     * @param fromX origin x coordinate
     * @param fromY origin y coordinate
     * @param toX destination x coordinate
     * @param toY destination y coordinate
     * @throws CellIsEmptyException if the origin cell is empty
     * @throws CellIsNotEmptyException if the destination cell is not empty
     * @throws OutOfBoundException if either of the coordinate set is out of bound
     *
     */
    @Override
    public void move(int fromX, int fromY, int toX, int toY) throws CellIsEmptyException, CellIsNotEmptyException, OutOfBoundException {
        assertCellInbound(fromX, fromY);
        assertCellInbound(toX, toY);
        if (board[fromX][fromY] == null) {
            throw new CellIsEmptyException(fromX, fromY);
        } else if (board[toX][toY] != null) {
            throw new CellIsNotEmptyException(toX, toY);
        }
        board[toX][toY] = board[fromX][fromY];
        board[fromX][fromY] = null;
    }

    /**
     * Remove {@link Piece} from the board.
     * @param x x coordinate
     * @param y y coordinate
     * @return the removed {@link Piece} or null if cell was empty
     * @throws OutOfBoundException if the cell is out of bound
     */
    @Override
    public Piece remove(int x, int y) throws OutOfBoundException {
        assertCellInbound(x, y);
        Piece piece = board[x][y];
        board[x][y] = null;
        return piece;
    }

    private void assertCellInbound(int x, int y) throws OutOfBoundException {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new OutOfBoundException(x, y);
        }
    }
}
