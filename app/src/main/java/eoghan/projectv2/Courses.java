/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied.
* See the License for the specific language governing permissions
and
* limitations under the License.
*/

package eoghan.projectv2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/** Gets the listview from the xml file and attachs an adapter to it which is filled with the course names, addresses and the
 *  par for each hole from the database.
 * It then allows the user to click on one of the courses in order to update any of this information and also allows the user to delete the
 *  entire record. It also has a menubar which allows the user to add a new golfer, view all stored courses on google maps or to
 *  return home.
 *
 * <p>
 * Citations
 * Class contains code adapted from
 *
 * http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
 * Retrieved on 25/2/2017
 *
 * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
 * Retrieved on 27/2/2017
 *
 * http://stackoverflow.com/questions/16052291/how-to-pass-and-get-an-intent-between-activities
 * Retrieved on 5/3/2017
 *</p>
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class Courses extends AppCompatActivity {

    private DatabaseManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COURSE_ID,
            DatabaseHelper.COURSE_NAME, DatabaseHelper.COURSE_ADDRESS, DatabaseHelper.HOLE1, DatabaseHelper.HOLE2, DatabaseHelper.HOLE3, DatabaseHelper.HOLE4, DatabaseHelper.HOLE5,
            DatabaseHelper.HOLE6, DatabaseHelper.HOLE7, DatabaseHelper.HOLE8, DatabaseHelper.HOLE9, DatabaseHelper.HOLE10, DatabaseHelper.HOLE11, DatabaseHelper.HOLE12,
            DatabaseHelper.HOLE13, DatabaseHelper.HOLE14, DatabaseHelper.HOLE15, DatabaseHelper.HOLE16, DatabaseHelper.HOLE17, DatabaseHelper.HOLE18,};

    final int[] to = new int[] { R.id.courseid, R.id.coursename, R.id.courseaddress, R.id.TVhole1, R.id.TVhole2, R.id.TVhole3, R.id.TVhole4, R.id.TVhole5, R.id.TVhole6, R.id.TVhole7, R.id.TVhole8,
            R.id.TVhole9, R.id.TVhole10, R.id.TVhole11, R.id.TVhole12, R.id.TVhole13, R.id.TVhole14, R.id.TVhole15, R.id.TVhole16, R.id.TVhole17, R.id.TVhole18,};

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Courses");
        setContentView(R.layout.activity_courses);

        // Creates and opens a new DatabaseManager to be used in this activity.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Sets up a cursor called coursecursor to fetch the courses from the database.
        Cursor coursecursor = dbManager.fetchcourse();

        // Assigns the listview to the ListView in the activity_courses xml file or if there are no records sets it to the Textview course_empty
        listView = (ListView) findViewById(R.id.courses_list_view);
        listView.setEmptyView(findViewById(R.id.course_empty));

        /* Code adapted from 'Array adapter in android to create a simple listview' described at
         http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
         Creates a new SimpleCursorAdapter to be used in this activity that fills the courses_listview with the values retrieved from
         the coursecursor. It takes the string arrray 'from' and the int array 'to' as its arguements.
        */
        adapter = new SimpleCursorAdapter(this, R.layout.courses_listview, coursecursor, from, to, 0);
        // Updates the adapter to show the changes
        adapter.notifyDataSetChanged();
        // Sets the adapter to the listview which will display the courses.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /** Code adapted from method onItemClick described at
             * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
             *
             * @param parent The AdapterView where the click happened.
             * @param view View that was selected
             * @param position The position of the selected item
             * @param viewId The row id of the item in the listview that was clicked
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Creates new TextViews and links them to the ones in the xml file.
                TextView courseidTV = (TextView) view.findViewById(R.id.courseid);
                TextView coursenameTV = (TextView) view.findViewById(R.id.coursename);
                TextView courseaddressTV = (TextView) view.findViewById(R.id.courseaddress);
                TextView hole1TV = (TextView) view.findViewById(R.id.TVhole1);
                TextView hole2TV = (TextView) view.findViewById(R.id.TVhole2);
                TextView hole3TV = (TextView) view.findViewById(R.id.TVhole3);
                TextView hole4TV = (TextView) view.findViewById(R.id.TVhole4);
                TextView hole5TV = (TextView) view.findViewById(R.id.TVhole5);
                TextView hole6TV = (TextView) view.findViewById(R.id.TVhole6);
                TextView hole7TV = (TextView) view.findViewById(R.id.TVhole7);
                TextView hole8TV = (TextView) view.findViewById(R.id.TVhole8);
                TextView hole9TV = (TextView) view.findViewById(R.id.TVhole9);
                TextView hole10TV = (TextView) view.findViewById(R.id.TVhole10);
                TextView hole11TV = (TextView) view.findViewById(R.id.TVhole11);
                TextView hole12TV = (TextView) view.findViewById(R.id.TVhole12);
                TextView hole13TV = (TextView) view.findViewById(R.id.TVhole13);
                TextView hole14TV = (TextView) view.findViewById(R.id.TVhole14);
                TextView hole15TV = (TextView) view.findViewById(R.id.TVhole15);
                TextView hole16TV = (TextView) view.findViewById(R.id.TVhole16);
                TextView hole17TV = (TextView) view.findViewById(R.id.TVhole17);
                TextView hole18TV = (TextView) view.findViewById(R.id.TVhole18);

                // Converts the value stored in the TextView to a String
                String id = courseidTV.getText().toString();
                String name = coursenameTV.getText().toString();
                String address = courseaddressTV.getText().toString();
                String newhole1 = hole1TV.getText().toString();
                String newhole2 = hole2TV.getText().toString();
                String newhole3 = hole3TV.getText().toString();
                String newhole4 = hole4TV.getText().toString();
                String newhole5 = hole5TV.getText().toString();
                String newhole6 = hole6TV.getText().toString();
                String newhole7 = hole7TV.getText().toString();
                String newhole8 = hole8TV.getText().toString();
                String newhole9 = hole9TV.getText().toString();
                String newhole10 = hole10TV.getText().toString();
                String newhole11 = hole11TV.getText().toString();
                String newhole12 = hole12TV.getText().toString();
                String newhole13 = hole13TV.getText().toString();
                String newhole14 = hole14TV.getText().toString();
                String newhole15 = hole15TV.getText().toString();
                String newhole16 = hole16TV.getText().toString();
                String newhole17 = hole17TV.getText().toString();
                String newhole18 = hole18TV.getText().toString();

                /* Code adapted from 'How to pass and get an intent between activities' described at
                * http://stackoverflow.com/questions/16052291/how-to-pass-and-get-an-intent-between-activities
                *
                * Creates a new intent called modifycourse_intent which Returns the context for the entire application and puts
                * them into the EditCourse class.
                */
                Intent modifycourse_intent = new Intent(getApplicationContext(), EditCourse.class);
                // Sends the Strings to the EditCourse class.
                modifycourse_intent.putExtra("id", id);
                modifycourse_intent.putExtra("coursename", name);
                modifycourse_intent.putExtra("courseaddress",address);
                modifycourse_intent.putExtra("hole1", newhole1);
                modifycourse_intent.putExtra("hole2", newhole2);
                modifycourse_intent.putExtra("hole3", newhole3);
                modifycourse_intent.putExtra("hole4", newhole4);
                modifycourse_intent.putExtra("hole5", newhole5);
                modifycourse_intent.putExtra("hole6", newhole6);
                modifycourse_intent.putExtra("hole7", newhole7);
                modifycourse_intent.putExtra("hole8", newhole8);
                modifycourse_intent.putExtra("hole9", newhole9);
                modifycourse_intent.putExtra("hole10", newhole10);
                modifycourse_intent.putExtra("hole11", newhole11);
                modifycourse_intent.putExtra("hole12", newhole12);
                modifycourse_intent.putExtra("hole13", newhole13);
                modifycourse_intent.putExtra("hole14", newhole14);
                modifycourse_intent.putExtra("hole15", newhole15);
                modifycourse_intent.putExtra("hole16", newhole16);
                modifycourse_intent.putExtra("hole17", newhole17);
                modifycourse_intent.putExtra("hole18", newhole18);
                // Runs the startActivity method of the intent
                startActivity(modifycourse_intent);

            }
        });

            }
    /**
     *
     * @param menu Overrides standard onCreateOptionsMenu()
     * @return Inflates the view to be used, in this case 'coursemenu'
     */
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.coursemenu, menu);
                return true;
            }

    /**
     *
     * @param item defines the items in the associated xml files.
     * @return Returns which menu item has been clicked
     */
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Creates an int to store the value of the items
                int id = item.getItemId();
                // Depending on the value of id, it will create and start the appropriate intent.
                if (id == R.id.add_coursemenu) {
                    Intent add_course = new Intent(this, AddCourse.class);
                    startActivity(add_course);
                }
                else
                if (id == R.id.course_location) {
                    Intent show_map = new Intent(this, MapsActivity.class);
                    startActivity(show_map);
                }
                else
                if (id == R.id.course_home) {
                    Intent show_map = new Intent(this, HomeScreen.class);
                    startActivity(show_map);
                }
                // Calls the parent class method in the event of a click of an undefined menu item.
                return super.onOptionsItemSelected(item);
            }
}