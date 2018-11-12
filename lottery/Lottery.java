import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Lottery class takes a list of groups and a schedule (itself containing a list of events) and tries to randomly add groups
 * to the schedule according to their preferences and, if a group is unlucky when scheduled for an earlier session, they get
 * priority when scheduled for a later session.
 */


public class Lottery
{
    private Schedule schedule; // schedule with events
    private List<Group> groups; // list of groups
    private Random random;
    private int eventIds; // how many different event ids there are; for us this is 10
    private int sessions; // how many sessions; for us this is 2

    public Lottery(Schedule schedule, List<Group> groups, int eventIds) {
        this.schedule = schedule;
        this.groups = groups;
        this.random = new Random();
        this.eventIds = eventIds;
        this.sessions = this.schedule.getSessions();
    }
    
    // generates a random permutation of a given length
    public int[] randPerm(int length) {
        List<Integer> ints = new ArrayList<Integer>();
        for (int n = 0; n < length; n++) {
            ints.add(n);
        }
        int[] perm = new int[length];
        for (int n = 0; n < length; n++) {
            int m = this.random.nextInt(ints.size());
            perm[n] = ints.remove(m);
        }
        return perm;
    }
    
    // makes the schedule
    public void setSchedule() {
        for (int session = 0; session < this.sessions; session++) {
            setSession(session);
        }
    }
    
    // makes the schedule for a given session
    public void setSession(int session) {
        // first schedule with respect to priorities
        for (int priority = this.eventIds - 1; priority >= 0; priority--) {
            List<Group> unassigned = this.getPriorityGroups(priority);
            // then schedule with respectg to preference (rank)
            for (int rank = 0; rank < this.eventIds; rank++) { 
                int[] perm = this.randPerm(unassigned.size()); // generate random permutation to schedule groups
                List<Group> toRemove = new ArrayList<Group>(); // keep track of which groups have been scheduled
                for (int n : perm) {
                    Group group = unassigned.get(n);
                    int id = group.getPref(rank);
                    // try to add a group to an event
                    if (id != -1 && this.schedule.addGroup(group, id, session)) {
                        toRemove.add(group);
                        group.setPriority(rank);
                        /**
                         * If a group is successfully scheduled, their priority is set equal to the rank of their preference.
                         * So if a group gets their first (rank = 0) choice, their priority is 0, which is as low as possible.
                         * If a group gets their last (in our case, rank = 4) choice, their priority is then as high as
                         * possible.
                         */
                    }
                }
                for (Group group: toRemove) {
                    unassigned.remove(group); // remove everyone who's been assigned;
                }
            }
        }       
    }

    // gets a list of groups of a give priority
    public List<Group> getPriorityGroups(int priority) {
        List<Group> priorityGroups = new ArrayList<Group>();
        for (Group group : this.groups) {
            if (group.getPriority() == priority) {
                priorityGroups.add(group);
            }
        }
        return priorityGroups;
    }
   
}
