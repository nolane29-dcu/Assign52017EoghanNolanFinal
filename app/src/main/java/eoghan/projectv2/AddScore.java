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
 * Citations
 * Class contains code adapted from
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
public class AddScore extends Activity implements OnClickListener {

    private Button add_result;
    private EditText resname;
    private EditText hole1res, hole2res, hole3res, hole4res, hole5res, hole6res, hole7res, hole8res, hole9res, hole10res, hole11res, hole12res, hole13res, hole14res, hole15res, hole16res, hole17res, hole18res;

    private DatabaseManager dbManager;
    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addscore);
        setTitle("Enter Result");

        // Links the new variables to the appropriate edittext boxes in the xml file.
        resname = (EditText) findViewById(R.id.playername);
        hole1res = (EditText) findViewById(R.id.reshole1);
        hole2res = (EditText) findViewById(R.id.reshole2);
        hole3res = (EditText) findViewById(R.id.reshole3);
        hole4res = (EditText) findViewById(R.id.reshole4);
        hole5res = (EditText) findViewById(R.id.reshole5);
        hole6res = (EditText) findViewById(R.id.reshole6);
        hole7res = (EditText) findViewById(R.id.reshole7);
        hole8res = (EditText) findViewById(R.id.reshole8);
        hole9res = (EditText) findViewById(R.id.reshole9);
        hole10res = (EditText) findViewById(R.id.reshole10);
        hole11res = (EditText) findViewById(R.id.reshole11);
        hole12res = (EditText) findViewById(R.id.reshole12);
        hole13res = (EditText) findViewById(R.id.reshole13);
        hole14res = (EditText) findViewById(R.id.reshole14);
        hole15res = (EditText) findViewById(R.id.reshole15);
        hole16res = (EditText) findViewById(R.id.reshole16);
        hole17res = (EditText) findViewById(R.id.reshole17);
        hole18res = (EditText) findViewById(R.id.reshole18);

        // Links the new Button to the button in the XML file.
        add_result = (Button) findViewById(R.id.addresultbtn);

        // Creates and opens a new DatabaseManager to be used in this activity.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Sets the on click listener to the Button.
        add_result.setOnClickListener(this);

    }
    /**
     *
     * @param v Switches to the add_result button click scenario because its the only button.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addresultbtn:

                //Code adapted from Check if edittext is empty described at
                //http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                // Error check to make sure none of the edittext boxes are empty.
                if (resname.getText().toString().trim().length() <= 0 || hole1res.getText().toString().trim().length() <= 0 ||
                    hole2res.getText().toString().trim().length() <= 0 || hole3res.getText().toString().trim().length() <= 0 ||
                    hole4res.getText().toString().trim().length() <= 0 || hole5res.getText().toString().trim().length() <= 0 ||
                    hole6res.getText().toString().trim().length() <= 0 || hole7res.getText().toString().trim().length() <= 0 ||
                    hole8res.getText().toString().trim().length() <= 0 || hole9res.getText().toString().trim().length() <= 0 ||
                    hole10res.getText().toString().trim().length() <= 0 || hole11res.getText().toString().trim().length() <= 0 ||
                    hole12res.getText().toString().trim().length() <= 0 || hole13res.getText().toString().trim().length() <= 0 ||
                    hole14res.getText().toString().trim().length() <= 0 || hole15res.getText().toString().trim().length() <= 0 ||
                    hole16res.getText().toString().trim().length() <= 0 || hole17res.getText().toString().trim().length() <= 0 ||
                    hole18res.getText().toString().trim().length() <= 0)
            {
                // If there are errors, display a message prompting the user to fill in all fields.
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                break;
            }
            else {
                    // Assigns the values entered in the edittext boxes to final integers, ready to be entered in the database.
                    int val1 = Integer.valueOf(hole1res.getText().toString());
                    int val2 = Integer.valueOf(hole2res.getText().toString());
                    int val3 = Integer.valueOf(hole3res.getText().toString());
                    int val4 = Integer.valueOf(hole4res.getText().toString());
                    int val5 = Integer.valueOf(hole5res.getText().toString());
                    int val6 = Integer.valueOf(hole6res.getText().toString());
                    int val7 = Integer.valueOf(hole7res.getText().toString());
                    int val8 = Integer.valueOf(hole8res.getText().toString());
                    int val9 = Integer.valueOf(hole9res.getText().toString());
                    int val10 = Integer.valueOf(hole10res.getText().toString());
                    int val11 = Integer.valueOf(hole11res.getText().toString());
                    int val12 = Integer.valueOf(hole12res.getText().toString());
                    int val13 = Integer.valueOf(hole13res.getText().toString());
                    int val14 = Integer.valueOf(hole14res.getText().toString());
                    int val15 = Integer.valueOf(hole15res.getText().toString());
                    int val16 = Integer.valueOf(hole16res.getText().toString());
                    int val17 = Integer.valueOf(hole17res.getText().toString());
                    int val18 = Integer.valueOf(hole18res.getText().toString());

                    // Creates a new final integer that is equal to the total of the values entered in the 18 edittext boxes.
                    int total = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10 + val11 + val12 + val13 + val14 + val15 + val16 + val17 + val18;

                    // Creates a new final string to store the value on total as a string value.
                    final String score = String.valueOf(total);
                    // Assigns the values entered in the edittext boxes to final strings, ready to be entered in the database.
                    final String name = resname.getText().toString();
                    final String hole1 = hole1res.getText().toString();
                    final String hole2 = hole2res.getText().toString();
                    final String hole3 = hole3res.getText().toString();
                    final String hole4 = hole4res.getText().toString();
                    final String hole5 = hole5res.getText().toString();
                    final String hole6 = hole6res.getText().toString();
                    final String hole7 = hole7res.getText().toString();
                    final String hole8 = hole8res.getText().toString();
                    final String hole9 = hole9res.getText().toString();
                    final String hole10 = hole10res.getText().toString();
                    final String hole11 = hole11res.getText().toString();
                    final String hole12 = hole12res.getText().toString();
                    final String hole13 = hole13res.getText().toString();
                    final String hole14 = hole14res.getText().toString();
                    final String hole15 = hole15res.getText().toString();
                    final String hole16 = hole16res.getText().toString();
                    final String hole17 = hole17res.getText().toString();
                    final String hole18 = hole18res.getText().toString();

                    // Inserts the values into the database.
                    dbManager.insertscore(name, score, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18);

                    // Code adapted from 'What does the activity clear top do' described at
                    //http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                    // Creates and starts an intent that brings the user back to the Results activity.
                    Intent addresult = new Intent(AddScore.this, Results.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(addresult);
                    break;
                }
        }
    }
}

