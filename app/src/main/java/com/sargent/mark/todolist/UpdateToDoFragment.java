package com.sargent.mark.todolist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.sargent.mark.todolist.R.id.completed;

/**
 * Created by mark on 7/5/17.
 */

public class UpdateToDoFragment extends DialogFragment {

    private EditText toDo;
    private DatePicker dp;
    private Button add;
    private CheckBox checked;
    private Spinner category;
    private final String TAG = "updatetodofragment";
    private long id;
    private boolean comp;
    private String categ;

    public UpdateToDoFragment(){}

    public static UpdateToDoFragment newInstance(int year, int month, int day, String descrpition, long id,boolean complete,String category) {
        UpdateToDoFragment f = new UpdateToDoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        args.putLong("id", id);
        args.putString("description", descrpition);
        args.putBoolean("complete",complete);
        args.putString("category",category);

        f.setArguments(args);

        return f;
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnUpdateDialogCloseListener {
        void closeUpdateDialog(int year, int month, int day, String description, long id,boolean complete,String category);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);

   // Variable for the checkbox  which is present on  the fragment
        checked = (CheckBox) view.findViewById(R.id.checkbox);

        // Variable for the spinner   which is present on  the fragment
        category = (Spinner) view.findViewById(R.id.category);

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        id = getArguments().getLong("id");

        String description = getArguments().getString("description");
        dp.updateDate(year, month, day);

        toDo.setText(description);


        // Retrieving value from the arguments for the checkbox
        comp = getArguments().getBoolean("complete");
      // Setting the value of the checkbox, if task is already completed then click the checkbox or else keep it unclick
        if(comp)
            checked.setChecked(true);
        else
            checked.setChecked(false);

        final Context context = getContext();

        // Setting the on click Listener for the Checkbox

        checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     /*Setting the value of the checked when the checkbox get clicked, we will use the same value
         to pass from the fragment to the main activity. */
                comp = ((CheckBox) v).isChecked();

               // Toast.makeText(context,String.valueOf(checked),Toast.LENGTH_LONG).show();
            }
        });

        // Retrieving value for the arguments for the spinner
         categ = getArguments().getString("category");

        // Setting the value for the Spinner menu option

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        if (!categ.equals(null)) {
            int spinnerPosition = adapter.getPosition(categ);
            category.setSelection(spinnerPosition);
        }

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                categ =  parent.getItemAtPosition(pos).toString();
                // Toast.makeText(context,category,Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        add.setText("Update");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateToDoFragment.OnUpdateDialogCloseListener activity = (UpdateToDoFragment.OnUpdateDialogCloseListener) getActivity();
                Log.d(TAG, "id: " + id);
                activity.closeUpdateDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(), id,comp,categ);
                UpdateToDoFragment.this.dismiss();
            }
        });

        return view;
    }
}