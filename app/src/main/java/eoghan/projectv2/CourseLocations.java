package eoghan.projectv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/** Gets the listview from the xml file and attachs an adapter to it which is filled with the course names from an array.
 * It then allows the user to click on one of the courses. When they select a course, Google maps is launched and the user is given
 * directions to the selected golf club from their current location. It also has a menubar which allows the user to view all courses
 * or return to the homescreen.
 *
 * <p>
 * Citation
 * Class contains code adapted from
 *
 * http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
 * Retrieved on 25/2/2017
 *</p>
 *
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class CourseLocations extends AppCompatActivity {

    ListView listView;


    @Override
    /**onCreate
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_locations);
        // Sets the title bar to 'Select a course'
        setTitle("Select a course");

        // Links the new ListView to the one in the xml file.
        listView = (ListView) findViewById(R.id.list);
        // Creates new string array called courses which will be filled with 'coursenames'
        String[] courses = getResources().getStringArray(R.array.coursenames);

        // Code adapted from 'Array adapter in android to create a simple listview' described at
        // http://stackoverflow.com/questions/19079400/arrayadapter-in-android-to-create-simple-listview
        // Sets up 'adapter' with standard layout and text from courses string array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, courses);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             *
             * @param parent  Is an AdapterView that chooses the activity in which the click happened
             * @param view selects the view in AdapterView that was clicked (acivity_course_locations)
             * @param position points to the position of the view in the adapter
             * @param id points to the id of the row that was clicked
             * @return Depending on what row of the listview is clicked, it will take the user to google maps and give directions
             *         from their current location
             */

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    // Starts a new intent which displays data to the user
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            // Uri identifies the Url and enables interaction with it over the internet.
                            Uri.parse("http://maps.google.com/maps?daddr=53.1465, -7.2381"));
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.2600, -6.4981"));
                    startActivity(intent);
                }
                else if (position == 2)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.3066, -6.6253"));
                    startActivity(intent);
                }
                else if (position == 3)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.1244, -6.5403"));
                    startActivity(intent);
                }
                else if (position == 4)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.1894, -6.9333"));
                    startActivity(intent);
                }
                else if (position == 5)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.3658, -6.3954"));
                    startActivity(intent);
                }
                else if (position == 6)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.4298, -6.9208"));
                    startActivity(intent);
                }
                else if (position == 7)
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=53.3153, -6.4326"));
                    startActivity(intent);
                }
            }

        });
    }

    /**
     *
      * @param menu Overrides standard onCreateOptionsMenu()
     * @return Inflates the view to be used, in this case 'viewcoursemenu'
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewcoursemenu, menu);
        return true;
    }

    /**
     *
     * @param item defines the items in the xml files
     * @return Returns whichever menu item was clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // if id is equal to viewcourse_menu, start all_courses intent
        if (id == R.id.viewcourse_menu) {

            Intent all_courses = new Intent(this, MapsActivity.class);
            startActivity(all_courses);
        }
        else
        if (id == R.id.home_menu) {
            Intent add_score = new Intent(this, HomeScreen.class);
            startActivity(add_score);
        }
        // Calls the parent class method in the event of a click of an undefined menu item.
        return super.onOptionsItemSelected(item);
    }
}


