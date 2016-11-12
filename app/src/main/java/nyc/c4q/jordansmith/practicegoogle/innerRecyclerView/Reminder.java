package nyc.c4q.jordansmith.practicegoogle.innerRecyclerView;

/**
 * Created by jordansmith on 11/1/16.
 */

public class Reminder {

    String reminderText;

    public Reminder(String newText){
        setReminderText(newText);
    }

    public String getReminderText() {
        return reminderText;
    }

    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }
}
