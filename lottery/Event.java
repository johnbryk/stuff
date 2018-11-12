/**
 * Event class represents each event.
 */

public class Event
{
    private int id; // id = 1 through 10 in our case
    private int session; // 0 or 1 in our case for first or second session
    private int capacity;
    private int spotsLeft; // keeps track of open spots
    
    public Event(int id, int session, int capacity) {
        this.id = id;
        this.session = session;
        this.capacity = capacity;
        this.spotsLeft = this.capacity;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getSession() {
        return this.session;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    public int getSpotsLeft() {
        return this.spotsLeft;
    }

    public void setSpotsLeft(int spotsLeft) {
        this.spotsLeft = spotsLeft;
    }
    
}
