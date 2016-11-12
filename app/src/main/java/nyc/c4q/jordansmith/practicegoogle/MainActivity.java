package nyc.c4q.jordansmith.practicegoogle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.practicegoogle.innerRecyclerView.Reminder;

public class MainActivity extends AppCompatActivity {

    RecyclerView outsideRecyclerView;
   public static List<Reminder> reminderList = new ArrayList<Reminder>();
    public final String REMINDERS_PREFS_TAG = "prefs tag";
    public final String DEFAULT_TEXT = "no reminders";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reminderList = getDataFromSharedPreferences();

        setContentView(R.layout.activity_main);
        outsideRecyclerView = (RecyclerView) findViewById(R.id.outside_recycler_view);
        outsideRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        outsideRecyclerView.setAdapter(new ReminderAdapter());

    }

    public List<Reminder> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<Reminder> foundReminders = new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(REMINDERS_PREFS_TAG, Context.MODE_PRIVATE);
        String retrievedReminders = sharedPref.getString(REMINDERS_PREFS_TAG, DEFAULT_TEXT);

        if(retrievedReminders.equals(DEFAULT_TEXT)){
            foundReminders = reminderList;
            return foundReminders;
        }

        Type type = new TypeToken<List<Reminder>>() {}.getType();
        foundReminders = gson.fromJson(retrievedReminders, type);
        return foundReminders;

    }

    public void putDataInSharedPreferences(){
        Gson gson = new Gson();
        String savedRemindersString = gson.toJson(reminderList);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(REMINDERS_PREFS_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(REMINDERS_PREFS_TAG, savedRemindersString);
        editor.apply();
    }

    @Override
    protected void onStop(){
        super.onStop();
        putDataInSharedPreferences();
    }
}