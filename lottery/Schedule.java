import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Schedule class keeps a list of events and handles adding groups to the schedule.
 */

public class Schedule
{
    private Map<Event, List<Group>> grid; // roster for each event: each event is associated with a list of groups attending
    private List<Event> events; // list of all events; events are listed with multiplicity
    private int sessions; // sessions = 2 in our case
    
    public Schedule(List<Event> events, int sessions) {
        this.grid = new HashMap<Event, List<Group>>();
        this.events = events;
        this.sessions = sessions;
    }
    
    /**
     * Adds a group to an event of given id and session. First checks if the group is already scheduled for that session.
     * Then checks if the group is already taking that event. Then checks if there is an event with that id and session which
     * can fit the group. If so, great, add the group to the grid and return true. If not, return false.
     */
    public boolean addGroup(Group group, int id, int session) {
        if (this.isGroupBusy(group, session)) {
            return false;
        } else if (this.isGroupTaking(group, id)) {
            return false;
        }
        List<Event> events = this.getEvents(id, session);
        /**
         * In our case, every event occurs twice during each session. So if a group is free during this session and not taking
         * the specific event yet, we check each instance of the event in this session to see if they can accommodate the group.
         * If so, great! If not, too bad!
         */
        for (Event event: events) {
            if (event.getSpotsLeft() >= group.getSize()) {
                List<Group> groups = this.getEventGroups(event);
                groups.add(group);
                this.grid.put(event, groups);
                int spotsLeft = event.getSpotsLeft() - group.getSize();
                event.setSpotsLeft(spotsLeft);
                return true;
            }
        }
        return false;
    }
    
    // checks if group is already scheduled for a session
    public boolean isGroupBusy(Group group, int session) {
        for (Event event : this.events) {
            if (this.getEventGroups(event).contains(group) && event.getSession() == session) {
                return true;
            }
        }
        return false;
    }
    
    // checks if group is already taking an event of a given id
    public boolean isGroupTaking(Group group, int id) {
        for (Event event : this.events) {
            if (this.getEventGroups(event).contains(group) && event.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    // gets list of events a group is taking; I don't know that this is used anywhere
    public List<Event> getGroupEvents(Group group) {
        List<Event> groupEvents = new ArrayList<Event>();
        for (Event event : this.events) {
            if (grid.get(event).contains(group)) {
                groupEvents.add(event);
            }
        }
        return groupEvents;
    }
    
    // gets a list of groups takikng a specific instance of an event
    public List<Group> getEventGroups(Event event) {
        Set<Event> keys = this.grid.keySet();
        if (keys.contains(event)) {
            return this.grid.get(event);
        }
        return new ArrayList<Group>();
    }
    
    // gets a list of events with a given id during a given session
    public List<Event> getEvents(int id, int session) {
        List<Event> events = new ArrayList<Event>();
        for(Event event : this.events) {
            if (event.getId() == id && event.getSession() == session) {
                events.add(event);
            }
        }
        return events;
    }
    
    // gets a list of events scheduled for a given session; I don't know that this is used anywherre
    public List<Event> getSessionEvents(int session) {
    List<Event> sessionEvents = new ArrayList<Event>();
        for (Event event : this.events) {
            if (event.getSession() == session) {
                sessionEvents.add(event);
            }
        }
        return sessionEvents;
    }
    
    public int getSessions() {
        return this.sessions;
    }
    
}







