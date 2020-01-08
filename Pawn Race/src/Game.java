import java.util.ArrayList;

public class Game {
    private ArrayList<Move> moves;
    private int pointer;
    private Board board;
    private Color currentPlayer;

    public Game(Board board) {
        this.moves = new ArrayList<Move>();
        this.pointer = 0;
        this.board = board;
        this.currentPlayer = Color.WHITE;
    }

    public Color getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Move getLastMove() {
        if (this.moves.isEmpty()) {
            return null;
        } else {
            return this.moves.get(this.pointer);
        }
    }

    public void applyMove(Move move) {
        this.moves.add(move);
        this.pointer++;
        playerSwitch();
    }

    private void playerSwitch() {
        switch (this.currentPlayer) {
            case WHITE:
                this.currentPlayer = Color.BLACK;
                break;
            case BLACK:
                this.currentPlayer = Color.WHITE;
                break;
        }
    }

    public void unapplyMove() {
        if (!this.moves.isEmpty()){
            Move lastMove = getLastMove();
            this.board.unapplyMove(lastMove);
            this.pointer--;
        }
    }
    public boolean isFinished(){
        int oppsLeft = 0;
        for(int y = 0; y < 8; y ++){
            for(int x = 0; x < 8; x++){
                Square square = this.board.getSquare(x,y);
                    switch (this.currentPlayer){
                        case WHITE:
                            if (square.occupiedBy() == Color.WHITE && y == 0) {
                                return true;
                            }else if(square.occupiedBy() == Color.BLACK){
                                oppsLeft++;
                            }
                        case BLACK:
                            if (square.occupiedBy() == Color.BLACK && y == 7){
                                return true;
                            }else if(square.occupiedBy() == Color.WHITE){
                                oppsLeft++;
                            }
                }
            }
        }
    if(oppsLeft == 0) {
        return true;
    }
        return false;
    }

    }
