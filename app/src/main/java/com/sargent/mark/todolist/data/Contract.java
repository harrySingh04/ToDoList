package com.sargent.mark.todolist.data;

import android.provider.BaseColumns;

/**
 * Created by mark on 7/4/17.
 */

public class Contract {

    public static class TABLE_TODO implements BaseColumns{
        public static final String TABLE_NAME = "todoitems";

        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DUE_DATE = "duedate";

   // Adding of two columns for the completed task and category option for the to do list
        public static final String COLUMN_NAME_COMPLETE="completed";
        public static final String COLUMN_NAME_CATEGORY="category";
    }
}
