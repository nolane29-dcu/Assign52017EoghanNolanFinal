package eoghan.projectv2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** Opens the database before retrieving the values relating to the course that was selected by the user. Allows the user to either
 *  update the values or delete the record entirely.
 *
 * <p>
 * Citations
 * Class contains code adapted from
 *
 * http://stackoverflow.com/questions/11740311/android-confirmation-message-for-delete
 * Retrieved 18/4/2017
 *
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
public class EditCourse extends Activity implements OnClickListener {

    private DatabaseManager dbManager;
    private EditText name, address;
    private EditText hole1ET, hole2ET, hole3ET, hole4ET, hole5ET, hole6ET, hole7ET, hole8ET, hole9ET, hole10ET, hole11ET, hole12ET, hole13ET, hole14ET, hole15ET, hole16ET, hole17ET, hole18ET;
    private Button updatebtn, deletebtn;
    private long _id;

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Edit Course");
        setContentView(R.layout.activity_edit_course);

        // Creates and opens a new DatabaseManager to be used in this activity.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Links the new variables to the appropriate edittext boxes in the xml file.
        name = (EditText) findViewById(R.id.editcoursename);
        address = (EditText) findViewById(R.id.editcourseaddress);
        hole1ET = (EditText) findViewById(R.id.edithole1);
        hole2ET = (EditText) findViewById(R.id.edithole2);
        hole3ET = (EditText) findViewById(R.id.edithole3);
        hole4ET = (EditText) findViewById(R.id.edithole4);
        hole5ET = (EditText) findViewById(R.id.edithole5);
        hole6ET = (EditText) findViewById(R.id.edithole6);
        hole7ET = (EditText) findViewById(R.id.edithole7);
        hole8ET = (EditText) findViewById(R.id.edithole8);
        hole9ET = (EditText) findViewById(R.id.edithole9);
        hole10ET = (EditText) findViewById(R.id.edithole10);
        hole11ET = (EditText) findViewById(R.id.edithole11);
        hole12ET = (EditText) findViewById(R.id.edithole12);
        hole13ET = (EditText) findViewById(R.id.edithole13);
        hole14ET = (EditText) findViewById(R.id.edithole14);
        hole15ET = (EditText) findViewById(R.id.edithole15);
        hole16ET = (EditText) findViewById(R.id.edithole16);
        hole17ET = (EditText) findViewById(R.id.edithole17);
        hole18ET = (EditText) findViewById(R.id.edithole18);


        // Links the new Button with the button in the XML file.
        updatebtn = (Button) findViewById(R.id.updatecoursebtn);
        deletebtn = (Button) findViewById(R.id.deletecoursebtn);

        // Creates a new intent which retrieves the values from the database and puts them in new Strings.
        Intent intent = getIntent();
        String oldcourseid = intent.getStringExtra("id");
        String oldcoursename = intent.getStringExtra("coursename");
        String oldcourseaddress = intent.getStringExtra("courseaddress");
        String oldhole1 = intent.getStringExtra("hole1");
        String oldhole2 = intent.getStringExtra("hole2");
        String oldhole3 = intent.getStringExtra("hole3");
        String oldhole4 = intent.getStringExtra("hole4");
        String oldhole5 = intent.getStringExtra("hole5");
        String oldhole6 = intent.getStringExtra("hole6");
        String oldhole7 = intent.getStringExtra("hole7");
        String oldhole8 = intent.getStringExtra("hole8");
        String oldhole9 = intent.getStringExtra("hole9");
        String oldhole10 = intent.getStringExtra("hole10");
        String oldhole11 = intent.getStringExtra("hole11");
        String oldhole12 = intent.getStringExtra("hole12");
        String oldhole13 = intent.getStringExtra("hole13");
        String oldhole14 = intent.getStringExtra("hole14");
        String oldhole15 = intent.getStringExtra("hole15");
        String oldhole16 = intent.getStringExtra("hole16");
        String oldhole17 = intent.getStringExtra("hole17");
        String oldhole18 = intent.getStringExtra("hole18");

        // Sets the edittext variables to the values stored in the strings retrieved from the database.
        _id = Long.parseLong(oldcourseid);
        name.setText(oldcoursename);
        address.setText(oldcourseaddress);
        hole1ET.setText(oldhole1);
        hole2ET.setText(oldhole2);
        hole3ET.setText(oldhole3);
        hole4ET.setText(oldhole4);
        hole5ET.setText(oldhole5);
        hole6ET.setText(oldhole6);
        hole7ET.setText(oldhole7);
        hole8ET.setText(oldhole8);
        hole9ET.setText(oldhole9);
        hole10ET.setText(oldhole10);
        hole11ET.setText(oldhole11);
        hole12ET.setText(oldhole12);
        hole13ET.setText(oldhole13);
        hole14ET.setText(oldhole14);
        hole15ET.setText(oldhole15);
        hole16ET.setText(oldhole16);
        hole17ET.setText(oldhole17);
        hole18ET.setText(oldhole18);

        // Sets the on click listener to the new button variables.
        updatebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
    }

    /**
     *
     * @param v Switches case depending on which button was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updatecoursebtn:
                /*Code adapted from Check if edittext is empty described at
                * http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                * Error check to make sure none of the edittext boxes are empty.
                */
                if (name.getText().toString().trim().length() <= 0 || address.getText().toString().trim().length() <= 0 ||
                        hole1ET.getText().toString().trim().length() <= 0 || hole2ET.getText().toString().trim().length() <= 0 ||
                        hole3ET.getText().toString().trim().length() <= 0 || hole4ET.getText().toString().trim().length() <= 0 ||
                        hole5ET.getText().toString().trim().length() <= 0 || hole6ET.getText().toString().trim().length() <= 0 ||
                        hole7ET.getText().toString().trim().length() <= 0 || hole8ET.getText().toString().trim().length() <= 0 ||
                        hole9ET.getText().toString().trim().length() <= 0 || hole10ET.getText().toString().trim().length() <= 0 ||
                        hole11ET.getText().toString().trim().length() <= 0 || hole12ET.getText().toString().trim().length() <= 0 ||
                        hole13ET.getText().toString().trim().length() <= 0 || hole14ET.getText().toString().trim().length() <= 0 ||
                        hole15ET.getText().toString().trim().length() <= 0 || hole16ET.getText().toString().trim().length() <= 0 ||
                        hole17ET.getText().toString().trim().length() <= 0 || hole18ET.getText().toString().trim().length() <= 0) {
                    // If there are errors, display a message prompting the user to fill in all fields.
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    break;
                } else {

                    // Retrieves the values from the edittext variables and stores them as string values.
                    String newcoursename = name.getText().toString();
                    String newcourseaddress = address.getText().toString();
                    String newhole1 = hole1ET.getText().toString();
                    String newhole2 = hole2ET.getText().toString();
                    String newhole3 = hole3ET.getText().toString();
                    String newhole4 = hole4ET.getText().toString();
                    String newhole5 = hole5ET.getText().toString();
                    String newhole6 = hole6ET.getText().toString();
                    String newhole7 = hole7ET.getText().toString();
                    String newhole8 = hole8ET.getText().toString();
                    String newhole9 = hole9ET.getText().toString();
                    String newhole10 = hole10ET.getText().toString();
                    String newhole11 = hole11ET.getText().toString();
                    String newhole12 = hole12ET.getText().toString();
                    String newhole13 = hole13ET.getText().toString();
                    String newhole14 = hole14ET.getText().toString();
                    String newhole15 = hole15ET.getText().toString();
                    String newhole16 = hole16ET.getText().toString();
                    String newhole17 = hole17ET.getText().toString();
                    String newhole18 = hole18ET.getText().toString();

                    // Stores the new string values in the database.
                    dbManager.updatecourse(_id, newcoursename, newcourseaddress, newhole1, newhole2, newhole3, newhole4, newhole5, newhole6, newhole7, newhole8, newhole9, newhole10,
                            newhole11, newhole12, newhole13, newhole14, newhole15, newhole16, newhole17, newhole18);
                    // Starts an intent that returns the user to the Courses activity.
                    this.returnHome();
                    break;
                }
            case R.id.deletecoursebtn:


                /* Code from 'Android confirmation message for delete' described at
                * http://stackoverflow.com/questions/11740311/android-confirmation-message-for-delete
                */
                new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this course from the database?")
                        .setCancelable(false).setPositiveButton
                        // If they click yes, the course is deleted from the database.
                                ("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dbManager.deletecourse(_id);
                                        /* Code adapted from 'What does the activity clear top do' described at
                                        * http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                                        * Creates and starts a new activity which returns the user to the Courses activity.
                                        */
                                        Intent home = new Intent(getApplicationContext(), Courses.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(home);
                                    }
                                })
                        // If they click no, they are brought back to the Courses activity.
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), Courses.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
