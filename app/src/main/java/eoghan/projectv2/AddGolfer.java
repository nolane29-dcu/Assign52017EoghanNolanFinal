package eoghan.projectv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/** Opens the database before allowing the user to enter information about a golfer and then stores the data in the database.
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
public class AddGolfer extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText handicapEditText;

    private DatabaseManager dbManager;

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");
        setContentView(R.layout.activity_add_golfer);

        // Links the new variables to the appropriate edittext boxes in the xml file.
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        handicapEditText = (EditText) findViewById(R.id.handicap_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);

        // Links the new button to the appropriate edittext boxes in the xml file.
        addTodoBtn = (Button) findViewById(R.id.add_record);

        // Creates and opens a new DatabaseManager.
        dbManager = new DatabaseManager(this);
        dbManager.open();
        // Sets the on click listener to the button
        addTodoBtn.setOnClickListener(this);
    }
    /**
     *
     * @param v Switches to the add_record button click scenario because its the only button.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                //Code adapted from Check if edittext is empty described at
                //http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                // Error check to make sure there are no empty edittext boxes.
                if (nameEditText.getText().toString().trim().length() <= 0 || handicapEditText.getText().toString().trim().length() <= 0 ||
                        phoneEditText.getText().toString().trim().length() <= 0)
                    {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    break;
                    }
                else
                    {
                        // Assigns the values entered in the edittext boxes to final strings, ready to be entered in the database.
                    final String name = nameEditText.getText().toString();
                    final String handicap = handicapEditText.getText().toString();
                    final String phone = phoneEditText.getText().toString();

                        // Enters the values into the database.
                    dbManager.insert(name, handicap, phone);

                        // Code adapted from 'What does the activity clear top do' described at
                        //http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                        // Creates and starts a new intent which bring the user to the Golfers activity.
                    Intent main = new Intent(AddGolfer.this, Golfers.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);
                    break;
                }
        }

    }
}