import java.util.ArrayList;

public class Board {
    private Square[][] board = new Square[8][8];

    public Board(char whiteGap, char blackGap) {
        int whiteGapCol = ((int) whiteGap) - ((int) 'a');
        int blackGapCol = ((int) blackGap) - ((int) 'a');
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Square square = new Square(x, y);
                board[y][x] = square;
                if (y == 1 && x != whiteGapCol) {
                    square.setOccupier(Color.BLACK);
                } else if (y == 6 && x != blackGapCol) {
                    square.setOccupier(Color.WHITE);
                }
            }
        }
    }

    public Square getSquare(int x, int y) {
        return board[y][x];
    }

    public void applyMove(Move move) {
        Square moveTo = move.getTo();
        Square moveFrom = move.getFrom();
        Square to = getSquare(moveTo.getX(), moveTo.getY());
        Square from = getSquare(moveFrom.getX(), moveFrom.getY());
        to.setOccupier(from.occupiedBy());
        from.setOccupier(Color.NONE);
    }

    public void unapplyMove(Move move) {
        Square moveTo = move.getTo();
        Square moveFrom = move.getFrom();
        Square to = getSquare(moveTo.getX(), moveTo.getY());
        Square from = getSquare(moveFrom.getX(), moveFrom.getY());
        from.setOccupier(to.occupiedBy());
        to.setOccupier(Color.NONE);
    }

    public void display() {
        String output = "   A B C D E F G H   \n";
        for (int y = 0; y < 8; y++) {
            output += " " + (8 - y) + " ";
            for (int x = 0; x < 8; x++) {
                Square square = board[y][x];
                output += displayHelper(square.occupiedBy()) + " ";
            }
            output += (8 - y) + "\n";
        }
        System.out.print(output + "   A B C D E F G H   \n");
    }

    private String displayHelper(Color color) {
        switch (color) {
            case BLACK:
                return "B";
            case WHITE:
                return "W";
            case NONE:
                return ".";
            default:
                return "";
        }
    }

}
