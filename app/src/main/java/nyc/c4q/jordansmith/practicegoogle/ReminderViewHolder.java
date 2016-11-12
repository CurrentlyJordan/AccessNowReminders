package nyc.c4q.jordansmith.practicegoogle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jordansmith on 10/31/16.
 */

public class ReminderViewHolder extends RecyclerView.ViewHolder {


    public EditText remindersEditText;
    public Button doneButton;
    public RecyclerView innerRecyclerView;

    public ReminderViewHolder(View itemView) {
        super(itemView);
        remindersEditText = (EditText) itemView.findViewById(R.id.reminder_edit_text);
        doneButton = (Button) itemView.findViewById(R.id.done_button);
        innerRecyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recycler_view);
        innerRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

    }
}
