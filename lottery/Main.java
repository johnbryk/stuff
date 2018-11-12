import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Main class to run from the command line. The input is a text file of form (without spaces; "events", "groups", and "end"
 * should be written out explicitly):
 * 
 * # of sessions, # of events per session
 * events
 * event id#, event capacity
 * ...
 * groups
 * group id#, group size, group preference 1, group preference 2, ..., group preference n
 * ...
 * end
 * 
 * so, for example:
 * 
 * 2,2
 * events
 * 1,10
 * 2,20
 * 3,30
 * groups
 * 1,5,1,2,3
 * 2,10,2,3,1
 * 3,15,3,1,2
 * end
 * 
 * Then run the command:
 * 
 * java Main < input.txt > output.txt
 * 
 * where input.txt is the text file above and output.txt is where you want the schedule to be written.
 */

public class Main
{

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        List<Event> events = new ArrayList<Event>();
        List<Group> groups = new ArrayList<Group>();
        
        int sessions = 0;
        int eventsPerSession = 0;
        int eventIds = 0;
        
        // reads how many sessions and events per session
        while (true) {
            String line = in.nextLine();
            if (line.equals("events")) break;
            String[] tokens = line.split(",");
            sessions = Integer.parseInt(tokens[0]);
            eventsPerSession = Integer.parseInt(tokens[1]);
        }
        
        // reads event data, making the appropriate number of events per session for each session
        while (true) {
            String line = in.nextLine();
            if (line.equals("groups")) break;
            for (int session = 0; session < sessions; session++) {
                for (int i = 0; i < eventsPerSession; i++) {
                    events.add(parseEvent(line, session));
                }
            }
            eventIds++;
        }
        
        // reads group data
        while (true) {
            String line = in.nextLine();
            if (line.equals("end")) break;
            groups.add(parseGroup(line));
        }
        
        // make the schedule and the lottery
        Schedule schedule = new Schedule(events, sessions);
        Lottery lottery = new Lottery(schedule, groups, eventIds);
        
        // set the schedule
        lottery.setSchedule();
        
        // print out the schedule
        for (int session = 0; session < sessions; session++) {
            List<Event> sessionEvents = schedule.getSessionEvents(session);
            for (Event event : sessionEvents) {
                List<Group> eventGroups = schedule.getEventGroups(event);
                String output = "";
                output += "session " + (session + 1);
                output += ", event " + event.getId();
                output += " groups ";
                for (Group group: eventGroups) {
                    output += group.getId() + ", ";
                }
                output += "spots left " + event.getSpotsLeft();
                System.out.println(output + "\n");
            }
        }
    }
    
    // reads group data
    public static Group parseGroup(String line) {
        String[] tokens = line.split(",");
        int id = Integer.parseInt(tokens[0]);
        int size = Integer.parseInt(tokens[1]);
        List<Integer> prefs = new ArrayList<Integer>();
        for (int i = 2; i < tokens.length; i++) {
            prefs.add(Integer.parseInt(tokens[i]));
        }
        return new Group(id, size, prefs);
        
    }
    
    // reads event data
    public static Event parseEvent(String line, int session) {
        String[] tokens = line.split(",");
        int id = Integer.parseInt(tokens[0]);
        int capacity = Integer.parseInt(tokens[1]);
        return new Event(id, session, capacity);
    }
        
}
