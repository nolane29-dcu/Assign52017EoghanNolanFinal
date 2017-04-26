package eoghan.projectv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
 /**
 *  The homescreen allows the user to choose which activity they wish to enter.
 *
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class HomeScreen extends AppCompatActivity {



    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setTitle("Home");

    }

    /**
     *
     * @param view The onClick which has been assigned to gotoGolfers.
     */
    public void gotoGolfers (View view){
        // Creates and starts a new intent that opens the Golfers activity.
        Intent intent = new Intent(this, Golfers.class);
        startActivity(intent);
    }

    /**
     *
     * @param view The onClick which has been assigned to gotoCourses.
     */
    public void gotoCourses (View view) {
        // Creates and starts a new intent that opens the Courses activity.
        Intent coursesintent = new Intent(this, Courses.class);
        startActivity(coursesintent);
    }

    /**
     *
     * @param view the onClick which has been assigned to gotoResults.
     */
    public void gotoResults (View view){
        // Creates and starts a new intent that opens the Results activity.
        Intent resultsintent = new Intent(this, Results.class);
        startActivity(resultsintent);
    }

    /**
     *
     * @param view the onClick which has been assigned to gotoLocations.
     */
    public void gotoLocations (View view){
        // Creates and starts a new intent that opens the CourseLocations activity.
        Intent locationsintent = new Intent(this, CourseLocations.class);
        startActivity(locationsintent);
    }
}
