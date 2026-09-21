public class RoomReservation {
    public void reserve(String name, String room) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("empty-name");
        }
        if (room == null || room.equals("")) {
            throw new IllegalArgumentException("empty-room");
        }
        System.out.println("reserved=" + name + "," + room);
    }

    public static void main(String[] args) {
        RoomReservation app = new RoomReservation();
        try {
            app.reserve("kim", "b201");
        } catch (IllegalArgumentException ex) {
            System.out.println("reject=" + ex.getMessage());
        }
        app.reserve("kim", "b201");

    }
}
