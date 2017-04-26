package eoghan.projectv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** Opens the database before allowing the user to enter information about a course and then stores them in the database.
 *
 * <p>
 * Citation
 * Class contains code adapted from
 * http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
 * Retrieved on 28/3/17
 *
 * http://skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
 * Retrieved on 12/2/2017
 *</p>
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class AddCourse extends Activity implements OnClickListener {

    private Button addCourse;
    private EditText nameET;
    private EditText addressET;
    private EditText hole1ET, hole2ET, hole3ET, hole4ET, hole5ET, hole6ET, hole7ET, hole8ET, hole9ET, hole10ET, hole11ET, hole12ET, hole13ET, hole14ET, hole15ET, hole16ET, hole17ET, hole18ET;

    private DatabaseManager dbManager;

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add New Course");
        setContentView(R.layout.activity_add_course);

        // Links the new variables to the appropriate edittext boxes in the xml file.
        nameET = (EditText) findViewById(R.id.coursename);
        addressET = (EditText) findViewById(R.id.courseaddress);
        hole1ET = (EditText) findViewById(R.id.hole1ET);
        hole2ET = (EditText) findViewById(R.id.hole2ET);
        hole3ET = (EditText) findViewById(R.id.hole3ET);
        hole4ET = (EditText) findViewById(R.id.hole4ET);
        hole5ET = (EditText) findViewById(R.id.hole5ET);
        hole6ET = (EditText) findViewById(R.id.hole6ET);
        hole7ET = (EditText) findViewById(R.id.hole7ET);
        hole8ET = (EditText) findViewById(R.id.hole8ET);
        hole9ET = (EditText) findViewById(R.id.hole9ET);
        hole10ET = (EditText) findViewById(R.id.hole10ET);
        hole11ET = (EditText) findViewById(R.id.hole11ET);
        hole12ET = (EditText) findViewById(R.id.hole12ET);
        hole13ET = (EditText) findViewById(R.id.hole13ET);
        hole14ET = (EditText) findViewById(R.id.hole14ET);
        hole15ET = (EditText) findViewById(R.id.hole15ET);
        hole16ET = (EditText) findViewById(R.id.hole16ET);
        hole17ET = (EditText) findViewById(R.id.hole17ET);
        hole18ET = (EditText) findViewById(R.id.hole18ET);

        // Links the new Button to the button in the XML file.
        addCourse = (Button) findViewById(R.id.add_course);

        // Creates and opens a new DatabaseManager.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Sets the on click listener to the Button.
        addCourse.setOnClickListener(this);
    }

    /**
     *
     * @param v Switches to the add_course button click scenario because its the only button.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_course:

                //Code adapted from Check if edittext is empty described at
                //http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                //Error check to make sure none of the edittext boxes are empty.
                if (nameET.getText().toString().trim().length() <= 0 || addressET.getText().toString().trim().length() <= 0 ||
                        hole1ET.getText().toString().trim().length() <= 0 || hole2ET.getText().toString().trim().length() <= 0 ||
                        hole3ET.getText().toString().trim().length() <= 0 || hole4ET.getText().toString().trim().length() <= 0 ||
                        hole5ET.getText().toString().trim().length() <= 0 || hole6ET.getText().toString().trim().length() <= 0 ||
                        hole7ET.getText().toString().trim().length() <= 0 || hole8ET.getText().toString().trim().length() <= 0 ||
                        hole9ET.getText().toString().trim().length() <= 0 || hole10ET.getText().toString().trim().length() <= 0 ||
                        hole11ET.getText().toString().trim().length() <= 0 || hole12ET.getText().toString().trim().length() <= 0 ||
                        hole13ET.getText().toString().trim().length() <= 0 || hole14ET.getText().toString().trim().length() <= 0 ||
                        hole15ET.getText().toString().trim().length() <= 0 || hole16ET.getText().toString().trim().length() <= 0 ||
                        hole17ET.getText().toString().trim().length() <= 0|| hole18ET.getText().toString().trim().length() <= 0)
                {
                    // If there are errors, display a message prompting the user to fill in all fields.
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    // Assigns the values entered in the edittext boxes to final strings, ready to be entered in the database.
                    final String name = nameET.getText().toString();
                    final String address = addressET.getText().toString();
                    final String hole1 = hole1ET.getText().toString();
                    final String hole2 = hole2ET.getText().toString();
                    final String hole3 = hole3ET.getText().toString();
                    final String hole4 = hole4ET.getText().toString();
                    final String hole5 = hole5ET.getText().toString();
                    final String hole6 = hole6ET.getText().toString();
                    final String hole7 = hole7ET.getText().toString();
                    final String hole8 = hole8ET.getText().toString();
                    final String hole9 = hole9ET.getText().toString();
                    final String hole10 = hole10ET.getText().toString();
                    final String hole11 = hole11ET.getText().toString();
                    final String hole12 = hole12ET.getText().toString();
                    final String hole13 = hole13ET.getText().toString();
                    final String hole14 = hole14ET.getText().toString();
                    final String hole15 = hole15ET.getText().toString();
                    final String hole16 = hole16ET.getText().toString();
                    final String hole17 = hole17ET.getText().toString();
                    final String hole18 = hole18ET.getText().toString();

                    // Enters the values into the database.
                    dbManager.insertcourse(name, address, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18);

                    /* Code adapted from 'What does the activity clear top do' described at
                    * http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                    * Creates and starts a new activity which returns the user to the Courses activity.
                    */
                    Intent newcourse = new Intent(AddCourse.this, Courses.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(newcourse);
                    break;
                }
        }
    }


}