package com.sargent.mark.todolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.R.attr.checked;
import static android.R.attr.value;

/**
 * Created by mark on 7/4/17.
 */

public class AddToDoFragment extends DialogFragment{

    private EditText toDo;
    private DatePicker dp;
    private Button add;
    private final String TAG = "addtodofragment";
  // Varibale for the Checkbox designed on the Fragment.
    private CheckBox completed;
 // String variable for the category of the Spinner
    private String category;
 // Declaration for the variable of the checkbox which will be saved in the database.
    boolean checked = false;

    public AddToDoFragment() {
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnDialogCloseListener {
  // changing the interface to handle complete task boolean variable and category string variable
        void closeDialog(int year, int month, int day, String description,boolean complete,String category);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);

// Getting the Application context
        final Context context = getContext();

    // Variable for the checkbox created in Fragment xml
        completed = (CheckBox) view.findViewById(R.id.checkbox);

    // Setting the on click Listener for the Checkbox

        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     /*Setting the value of the checked when the checkbox get clicked, we will use the same value
         to pass from the fragment to the main activity. */
                checked = ((CheckBox) v).isChecked();

                Toast.makeText(context,String.valueOf(checked),Toast.LENGTH_LONG).show();
            }
        });



        //Variable for the spinner designed in the xml and assigning using id category
        Spinner spinner = (Spinner) view.findViewById(R.id.category);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.category_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

       /* Setting the on Item Selected Listener to retrieve the vale of selected item by the user
           and then saving that value in the variable category*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                category =  parent.getItemAtPosition(pos).toString();
               // Toast.makeText(context,category,Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    // End of item select  listener for the spinner

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dp.updateDate(year, month, day);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogCloseListener activity = (OnDialogCloseListener) getActivity();

     /*Modifying the call to the close dialog by adding two parameters category of the task and
      to handle checkbox for the completed task */

                activity.closeDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(),checked,category);
                AddToDoFragment.this.dismiss();
            }
        });

        return view;
    }

}



