import java.util.List;

/**
 * Group class represents each group.
 */

public class Group
{
    private int id; // id = 1 through 95 excluding 68 in our case
    private int size; // group size
    private List<Integer> prefs; // prefered events in order; events represented here as integers (1-10 in our case)
    private int priority; // priority for scheduling; higher value = scheduled earlier
    
    public Group(int id, int size, List<Integer> prefs) {
        this.id = id;
        this.size = size;
        this.prefs = prefs;
        this.priority = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getPref(int rank) {
        if (rank < this.prefs.size()) {
            return this.prefs.get(rank);
        }
        return -1;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
}
