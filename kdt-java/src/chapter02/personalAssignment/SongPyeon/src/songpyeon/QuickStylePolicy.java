package songpyeon;

public class QuickStylePolicy implements SongpyeonStylePolicy{
    @Override
    public int pieceCount() {
        return 10;
    }
    @Override
    public int shapeScore() {
        return 40;
    }
    @Override
    public String label() {
        return "빠르게";
    }
}
