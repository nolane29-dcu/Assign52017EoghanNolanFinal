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

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
/** Displays the results entered by the user in the add score activity. It also allows the user to select a result and edit it if
 * required.
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
public class Results extends AppCompatActivity {

    private DatabaseManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    // String array that holds all the data retrieved from the database for a particular result
    final String[] from = new String[] { DatabaseHelper.RESULT_ID,
            DatabaseHelper.RESNAME, DatabaseHelper.SCORE, DatabaseHelper.RESULT1, DatabaseHelper.RESULT2, DatabaseHelper.RESULT3, DatabaseHelper.RESULT4, DatabaseHelper.RESULT5,
            DatabaseHelper.RESULT6, DatabaseHelper.RESULT7, DatabaseHelper.RESULT8, DatabaseHelper.RESULT9, DatabaseHelper.RESULT10, DatabaseHelper.RESULT11, DatabaseHelper.RESULT12,
            DatabaseHelper.RESULT13, DatabaseHelper.RESULT14, DatabaseHelper.RESULT15, DatabaseHelper.RESULT16, DatabaseHelper.RESULT17, DatabaseHelper.RESULT18};

    // Int array that holds the values from the edittext boxes associated with a particular result.
    final int[] to = new int[] { R.id.resultid, R.id.playername, R.id.playerscore, R.id.reshole1, R.id.reshole2, R.id.reshole3, R.id.reshole4, R.id.reshole5, R.id.reshole6, R.id.reshole7, R.id.reshole8,
            R.id.reshole9, R.id.reshole10, R.id.reshole11, R.id.reshole12, R.id.reshole13, R.id.reshole14, R.id.reshole15, R.id.reshole16, R.id.reshole17, R.id.reshole18};

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        // Sets the title bar to 'Results'
        setTitle("Results");

        // Creates new DatabaseManager to use in Results activity.
        dbManager = new DatabaseManager(this);
        // Opens the dbManager for access.
        dbManager.open();
        // Returns resultscursor from the fetchresults method in the database manager activity.
        Cursor resultscursor = dbManager.fetchresults();

        // Sets the listview depending on if there are any records or not.
        listView = (ListView) findViewById(R.id.results_list_view);
        listView.setEmptyView(findViewById(R.id.results_empty));

        /* Code adapted from 'Array adapter in android to create a simple listview' described at
        * http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
        * Sets up 'adapter' with layout from 'results_layout' and returns data from the database manager through the cursor.
        */
        adapter = new SimpleCursorAdapter(this, R.layout.results_layout, resultscursor, from, to, 0);
        // Notifies the adapter that the data has been changed and it should update the data.
        adapter.notifyDataSetChanged();
        // Sets the adapter with the updated data.
        listView.setAdapter(adapter);

        /** Code adapted from method onItemClick described at
         * https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.html
         *
         * @param parent  Is an AdapterView that chooses the activity in which the click happened
         * @param view selects the view in AdapterView that was clicked (acivity_results)
         * @param position points to the position of the view in the adapter
         * @param viewId points to the id of the row that was clicked
         * @return Depending on which result was clicked, it will bring the user to their results page and allow them to edit it.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Creates new TextViews and links them to the edittexts in the xml file.
                TextView playerid = (TextView) view.findViewById(R.id.resultid);
                TextView playernametv = (TextView) view.findViewById(R.id.playername);
                TextView score = (TextView) view.findViewById(R.id.playerscore);
                TextView hole1res = (TextView) view.findViewById(R.id.reshole1);
                TextView hole2res = (TextView) view.findViewById(R.id.reshole2);
                TextView hole3res = (TextView) view.findViewById(R.id.reshole3);
                TextView hole4res = (TextView) view.findViewById(R.id.reshole4);
                TextView hole5res = (TextView) view.findViewById(R.id.reshole5);
                TextView hole6res = (TextView) view.findViewById(R.id.reshole6);
                TextView hole7res = (TextView) view.findViewById(R.id.reshole7);
                TextView hole8res = (TextView) view.findViewById(R.id.reshole8);
                TextView hole9res = (TextView) view.findViewById(R.id.reshole9);
                TextView hole10res = (TextView) view.findViewById(R.id.reshole10);
                TextView hole11res = (TextView) view.findViewById(R.id.reshole11);
                TextView hole12res = (TextView) view.findViewById(R.id.reshole12);
                TextView hole13res = (TextView) view.findViewById(R.id.reshole13);
                TextView hole14res = (TextView) view.findViewById(R.id.reshole14);
                TextView hole15res = (TextView) view.findViewById(R.id.reshole15);
                TextView hole16res = (TextView) view.findViewById(R.id.reshole16);
                TextView hole17res = (TextView) view.findViewById(R.id.reshole17);
                TextView hole18res = (TextView) view.findViewById(R.id.reshole18);

                // Converts the value stored in the TextView to a String
                String id = playerid.getText().toString();
                String name = playernametv.getText().toString();
                String result = score.getText().toString();
                String hole1 = hole1res.getText().toString();
                String hole2 = hole2res.getText().toString();
                String hole3 = hole3res.getText().toString();
                String hole4 = hole4res.getText().toString();
                String hole5 = hole5res.getText().toString();
                String hole6 = hole6res.getText().toString();
                String hole7 = hole7res.getText().toString();
                String hole8 = hole8res.getText().toString();
                String hole9 = hole9res.getText().toString();
                String hole10 = hole10res.getText().toString();
                String hole11 = hole11res.getText().toString();
                String hole12 = hole12res.getText().toString();
                String hole13 = hole13res.getText().toString();
                String hole14 = hole14res.getText().toString();
                String hole15 = hole15res.getText().toString();
                String hole16 = hole16res.getText().toString();
                String hole17 = hole17res.getText().toString();
                String hole18 = hole18res.getText().toString();

                 /* Code adapted from 'How to pass and get an intent between activities' described at
                * http://stackoverflow.com/questions/16052291/how-to-pass-and-get-an-intent-between-activities
                *
                * Enters the data from the new strings into the EditResult activity.
                */
                Intent modifyresult_intent = new Intent(getApplicationContext(), EditResult.class);
                modifyresult_intent.putExtra("id", id);
                modifyresult_intent.putExtra("resname", name);
                modifyresult_intent.putExtra("playerscore", result);
                modifyresult_intent.putExtra("hole1", hole1);
                modifyresult_intent.putExtra("hole2", hole2);
                modifyresult_intent.putExtra("hole3", hole3);
                modifyresult_intent.putExtra("hole4", hole4);
                modifyresult_intent.putExtra("hole5", hole5);
                modifyresult_intent.putExtra("hole6", hole6);
                modifyresult_intent.putExtra("hole7", hole7);
                modifyresult_intent.putExtra("hole8", hole8);
                modifyresult_intent.putExtra("hole9", hole9);
                modifyresult_intent.putExtra("hole10", hole10);
                modifyresult_intent.putExtra("hole11", hole11);
                modifyresult_intent.putExtra("hole12", hole12);
                modifyresult_intent.putExtra("hole13", hole13);
                modifyresult_intent.putExtra("hole14", hole14);
                modifyresult_intent.putExtra("hole15", hole15);
                modifyresult_intent.putExtra("hole16", hole16);
                modifyresult_intent.putExtra("hole17", hole17);
                modifyresult_intent.putExtra("hole18", hole18);

                startActivity(modifyresult_intent);

            }
        });

    }

    /**
     *
     * @param menu Overrides standard onCreateOptionsMenu()
     * @return Inflates the view to be used, in this case 'resultsmenu'
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resultsmenu, menu);
        return true;
    }

    /**
     *
     * @param item defines the items in the associated xml files.
     * @return Returns which menu item has been clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // If id = addresultmenu creates and starts an intent to bring the user to the AddScore activity.
        if (id == R.id.add_resultmenu) {

            Intent add_score = new Intent(this, AddScore.class);
            startActivity(add_score);

        }
        else if
                // If id = new_competion, the user recieves an alert box asking them are they sure they want to delete all results.
                (id == R.id.new_competition) {
                new AlertDialog.Builder(this).setMessage("Are you sure you want to delete all existing results?").setCancelable(false).setPositiveButton
                        // If they click yes, the results database is cleared and the listview is emptied.
                        ("Yes", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id) {

                                dbManager.clearresults();
                                listView.setAdapter(null);
                                adapter.notifyDataSetChanged();
                                dbManager.close();
                            }
                        })
                        // If they click no, they are brought back to the Results activity.
                        .setNegativeButton("No", null)
                        .show();

                return true;
        }
        else if
                // If they click the home_menu button a new intent is created and started that returns them to the HomeScreen activity.
                (id == R.id.home_menu) {
                Intent home = new Intent(this, HomeScreen.class);
                startActivity(home);
                }
        // Calls the parent class method in the event of a click of an undefined menu item.
        return super.onOptionsItemSelected(item);
    }
}


