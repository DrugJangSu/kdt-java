package chapter02.lesson03;

public class PinnedPost extends Post {
    private boolean pinned;

    public PinnedPost(String title, String body) {
        super(title, body);
        this.pinned = false;
    }

    public void pin() {
        this.pinned = true;
    }

    public boolean isPinned() {
        return this.pinned;
    }

    @Override
    public void changeTitle(String next) {
        if (this.pinned) {
            System.out.println("reject=pinned");
            return;
        }
        super.changeTitle(next);
    }

    @Override
    public void print() {
        System.out.println("pinned=" + pinned);
        super.print();
    }
}
