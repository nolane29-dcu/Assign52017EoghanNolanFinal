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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
/** Gets the listview from the xml file and attachs an adapter to it which is filled with the golfer names, handicaps and phone
 * numbers from the database.
 * It then allows the user to click on one of the golfers in order to update the stored information.
 * It also has a menubar which allows the user to add a new golfer or toreturn home.
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

public class Golfers extends AppCompatActivity {


    private DatabaseManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.HANDICAP, DatabaseHelper.PHONENO };

    final int[] to = new int[] { R.id.idTV, R.id.nameTV, R.id.handicapTV, R.id.phonenoTV };

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Golfers");
        setContentView(R.layout.activity_golfers);

        // Creates and opens new DatabaseManager to be used in this activity called dbManager
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Creates a new cursor called cursor which fetches the values in the golfer database.
        Cursor cursor = dbManager.fetch();

        // Assigns the listview to the ListView in the activity_golfers xml file or if there are no records sets it to the Textview empty
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        /* Code adapted from 'Array adapter in android to create a simple listview' described at
        * http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
        * Creates a new SimpleCursorAdapter to be used in this activity that fills the listview_layout with the values retrieved from
        * the coursecursor. It takes the string arrray 'from' and the int array 'to' as its arguements.
        */
        adapter = new SimpleCursorAdapter(this, R.layout.listview_layout, cursor, from, to, 0);
        // Updates the adapter to show the changes.
        adapter.notifyDataSetChanged();
        // Sets the adapter to the listview which will display the courses.
        listView.setAdapter(adapter);

        // OnCLickListener for the List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /** Code adapted from method onItemClick described at
             * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
             *
             * @param parent The AdapterView where the click happened.
             * @param view View that was selected
             * @param position The position of the selected item
             * @param viewId The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Creates a new TextView and assigns it to the edittexts in the xml file.
                TextView idtext = (TextView) view.findViewById(R.id.idTV);
                TextView nametext = (TextView) view.findViewById(R.id.nameTV);
                TextView handicaptext = (TextView) view.findViewById(R.id.handicapTV);
                TextView phonenotext = (TextView)view.findViewById(R.id.phonenoTV);

                // Converts the values in textboxes to strings
                String id = idtext.getText().toString();
                String name = nametext.getText().toString();
                String handicap = handicaptext.getText().toString();
                String phoneno = phonenotext.getText().toString();

               /* Code adapted from 'How to pass and get an intent between activities' described at
                * http://stackoverflow.com/questions/16052291/how-to-pass-and-get-an-intent-between-activities
                *
                * Creates an intent which sends the values to the EditGolfer class.
                */
                Intent modify_intent = new Intent(getApplicationContext(), EditGolfer.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("handicap", handicap);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("phone", phoneno);

                startActivity(modify_intent);
            }
        });
    }
    /**
     *
     * @param menu Overrides standard onCreateOptionsMenu()
     * @return Inflates the view to be used, in this case 'main'
     */
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

    /**
     *
     * @param item defines the items in the xml files
     * @return Returns whichever menu item was clicked.
     */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Creates a new int to store the values for items.
            int id = item.getItemId();
            // Depending on the value of id, it will create and start the appropriate intent.
            if (id == R.id.add_golfer) {

                Intent add_mem = new Intent(this, AddGolfer.class);
                startActivity(add_mem);
            }
            else if (id == R.id.golfers_home) {

                Intent add_mem = new Intent(this, HomeScreen.class);
                startActivity(add_mem);
            }
            // Calls the parent class method in the event of a click of an undefined menu item.
            return super.onOptionsItemSelected(item);
        }

    }