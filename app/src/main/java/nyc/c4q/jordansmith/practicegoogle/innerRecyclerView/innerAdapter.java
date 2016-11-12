package nyc.c4q.jordansmith.practicegoogle.innerRecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.jordansmith.practicegoogle.R;

/**
 * Created by jordansmith on 11/1/16.
 */

public class innerAdapter extends RecyclerView.Adapter<innerAdapter.innerViewHolder> {

    public static List<Reminder> reminderList;

    public innerAdapter(List<Reminder> reminderList){
        this.reminderList = reminderList;
    }



    @Override
    public innerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_item_view, parent, false);

       return new innerViewHolder(childView);
    }



    @Override
    public void onBindViewHolder(final innerViewHolder holder, final int position) {
        holder.deleteLinearLayout.setVisibility(View.INVISIBLE);
        holder.reminderText.setText(reminderList.get(position).getReminderText());
        holder.reminderText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d((String)holder.reminderText.getText(), "Clicked");
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Add reminder to calendar")
                        .setMessage("do you want to add this reminder to your calendar?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                                intent.putExtra(CalendarContract.Events.TITLE, holder.reminderText.getText());
                                holder.itemView.getContext().startActivity(intent);
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
        holder.reminderText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.deleteLinearLayout.setVisibility(View.VISIBLE);
                return true;
            }
        });

        holder.deleteYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, reminderList.size());
                holder.deleteLinearLayout.setVisibility(View.INVISIBLE);

            }
        });
        holder.deleteNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.deleteLinearLayout.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }



    public class innerViewHolder extends RecyclerView.ViewHolder{

        TextView reminderText;
        LinearLayout deleteLinearLayout;
        Button deleteYes;
        Button deleteNo;

        public innerViewHolder(View itemView) {
            super(itemView);
            reminderText = (TextView) itemView.findViewById(R.id.inner_reminder_text);
            deleteLinearLayout = (LinearLayout) itemView.findViewById(R.id.delete_layer);
            deleteYes = (Button) itemView.findViewById(R.id.delete_button_yes);
            deleteNo = (Button) itemView.findViewById(R.id.delete_button_no);

        }
    }
}
