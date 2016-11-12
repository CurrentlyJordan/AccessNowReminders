package nyc.c4q.jordansmith.practicegoogle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import nyc.c4q.jordansmith.practicegoogle.innerRecyclerView.Reminder;
import nyc.c4q.jordansmith.practicegoogle.innerRecyclerView.innerAdapter;

/**
 * Created by jordansmith on 10/31/16.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

    private List<Integer> intList = Arrays.asList(
            1
    );




    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_reminders,parent,false);

        return new ReminderViewHolder(childView);
    }


    @Override
    public void onBindViewHolder(final ReminderViewHolder holder, int position) {

        holder.innerRecyclerView.setAdapter(new innerAdapter(MainActivity.reminderList));
        holder.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.remindersEditText.getText().toString().equals("") || holder.remindersEditText.getText().toString().equals("")){
                    Toast.makeText(holder.innerRecyclerView.getContext(), "Please fill in a reminder", Toast.LENGTH_SHORT).show();
                }
                else {
                    Reminder reminder = new Reminder(holder.remindersEditText.getText().toString());
                    MainActivity.reminderList.add(reminder);
                    holder.innerRecyclerView.getAdapter().notifyDataSetChanged();
                    holder.remindersEditText.setText("");
                }

            }
        });

    }








    @Override
    public int getItemCount() {
        return intList.size();
    }
}
