package songpyeon;

public class PrettyStylePolicy implements SongpyeonStylePolicy {
    @Override
    public int pieceCount() {
        return 3;
    }

    @Override
    public int shapeScore() {
        return 90;
    }

    @Override
    public String label() {
        return "예쁘게";
    }
    

}
