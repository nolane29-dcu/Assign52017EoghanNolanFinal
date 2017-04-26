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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
/** Opens the database before retrieving the values relating to the course that was selected by the user. Allows the user to either
 *  update the values or delete the record entirely.
 *
 * <p>
 * Citations
 * Class contains code adapted from
 *
 * http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
 * Retrieved on 28/3/17
 *
 * http://stackoverflow.com/questions/11740311/android-confirmation-message-for-delete
 * Retrieved 18/4/2017
 *
 * http://skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
 * Retrieved on 12/2/2017
 *</p>
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class EditGolfer extends Activity implements OnClickListener {

    private EditText nameText;
    private EditText handicapText;
    private EditText phoneText;
    private Button updateBtn, deleteBtn;
    private long _id;

    private DatabaseManager dbManager;

    /** Initalizes the activity and sets the layout and title.
     *
     * @param savedInstanceState Bundle object that is passed into the onCreate method which allows activities to restore themselves
     *        to a previous state using the data stored in the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_edit_golfer);

        // Sets the new GraphView to the GraphView in the xml file
        GraphView graph = (GraphView) findViewById(R.id.handicapgraph);
        // Creates new DataPoints and inserts values.
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 16),
                new DataPoint(2, 17),
                new DataPoint(3, 12),
                new DataPoint(4, 13),
                new DataPoint(5, 14),
                new DataPoint(6, 15),
                new DataPoint(7, 16),
                new DataPoint(8, 11),
        });
        // Adds the datapoints to the graph
        graph.addSeries(series);

        // Creates and opens a new DatabaseManager to be used in this activity.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Links the new variables to the appropriate edittext boxes in the xml file.
        nameText = (EditText) findViewById(R.id.name_editTV);
        handicapText = (EditText) findViewById(R.id.handicap_editTV);
        phoneText = (EditText) findViewById(R.id.phone_editTV);

        // Links the new Button variables to the appropriate buttons in the xml file.
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        // Creates a new intent which gets the values from the database and stores them in new strings.
        Intent intent = getIntent();
        String golfid = intent.getStringExtra("id");
        String golfname = intent.getStringExtra("name");
        String golfhandicap = intent.getStringExtra("handicap");
        String golfphone = intent.getStringExtra("phone");

        // Uses the values in the new strings to store in the edittext boxes.
        _id = Long.parseLong(golfid);
        nameText.setText(golfname);
        handicapText.setText(golfhandicap);
        phoneText.setText(golfphone);

        // Sets the on click listener to the buttons.
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }
    /**
     *
     * @param v Switches case depending on which button was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                /*Code adapted from Check if edittext is empty described at
                * http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                * Error check to make sure none of the edittext boxes are empty.
                */
                if (nameText.getText().toString().trim().length() <= 0 || handicapText.getText().toString().trim().length() <= 0 ||
                        phoneText.getText().toString().trim().length() <= 0) {
                    // If any of the edittext boxes are empty, displays a toast.
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    // Retrieves the values from the edittext variables and stores them as string values.
                    String name = nameText.getText().toString();
                    String handicap = handicapText.getText().toString();
                    String phone = phoneText.getText().toString();

                    // Enters the values from the Strings into the database and starts the returnHome intent.
                    dbManager.update(_id, name, handicap, phone);
                    this.returnHome();
                    break;
                }

            case R.id.btn_delete:

                /* Code from 'Android confirmation message for delete' described at
                * http://stackoverflow.com/questions/11740311/android-confirmation-message-for-delete
                */
                new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this golfer from the database?")
                        .setCancelable(false).setPositiveButton
                        // If they click yes, the golfer is deleted from the database.
                                ("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dbManager.delete(_id);
                                        /* Code adapted from 'What does the activity clear top do' described at
                                        * http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                                        * Creates and starts a new activity which returns the user to the Golfers activity.
                                        */
                                        Intent home = new Intent(getApplicationContext(), Golfers.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(home);
                                    }
                                })
                        // If they click no, they are brought back to the Golfers activity.
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
    }

    public void returnHome() {
        // Creates and starts a new intent which returns the user to the Golfers activity.
        Intent home_intent = new Intent(getApplicationContext(), Golfers.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}

