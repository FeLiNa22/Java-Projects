public class Move {
    private Square from;
    private Square to;
    private boolean isCapture;
    private boolean isEnPassantCapture;

    public Move (Square from, Square to, boolean isCapture, boolean isEnPassantCapture){
        this.from = from;
        this.to = to;
        this.isCapture = isCapture;
        this.isEnPassantCapture = isEnPassantCapture;
    }

    public Square getFrom(){
        return this.from;
    }

    public Square getTo(){
        return this.to;
    }
    public boolean isCapture(){
        return this.isCapture;
    }
    public boolean isEnPassantCapture(){
        return this.isEnPassantCapture;
    }

    public String getSAN(){
        String output = "";
        output += from.getX() + ((int) 'a');
        output += from.getY();
        output += "-";
        output += to.getX() + ((int) 'a');
        output += to.getX();
        return output;
    }
}
