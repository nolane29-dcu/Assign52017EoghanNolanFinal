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
/** Opens the database before retrieving the values relating to the result that was selected by the user. Allows the user to either
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
public class EditResult extends Activity implements OnClickListener {

    private DatabaseManager dbManager;
    private EditText name;
    private EditText newscore1, newscore2, newscore3, newscore4, newscore5, newscore6, newscore7, newscore8, newscore9, newscore10, newscore11, newscore12, newscore13, newscore14, newscore15, newscore16, newscore17, newscore18;
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
        setContentView(R.layout.activity_edit_result);
        setTitle("Edit Result");

        // Creates and opens a new DatabaseManager to be used in this activity.
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Links the new variables to the appropriate edittext boxes in the xml file
        name = (EditText) findViewById(R.id.upplayername);
        newscore1 = (EditText) findViewById(R.id.upscore1);
        newscore2 = (EditText) findViewById(R.id.upscore2);
        newscore3 = (EditText) findViewById(R.id.upscore3);
        newscore4 = (EditText) findViewById(R.id.upscore4);
        newscore5 = (EditText) findViewById(R.id.upscore5);
        newscore6 = (EditText) findViewById(R.id.upscore6);
        newscore7 = (EditText) findViewById(R.id.upscore7);
        newscore8 = (EditText) findViewById(R.id.upscore8);
        newscore9 = (EditText) findViewById(R.id.upscore9);
        newscore10 = (EditText) findViewById(R.id.upscore10);
        newscore11 = (EditText) findViewById(R.id.upscore11);
        newscore12 = (EditText) findViewById(R.id.upscore12);
        newscore13 = (EditText) findViewById(R.id.upscore13);
        newscore14 = (EditText) findViewById(R.id.upscore14);
        newscore15 = (EditText) findViewById(R.id.upscore15);
        newscore16 = (EditText) findViewById(R.id.upscore16);
        newscore17 = (EditText) findViewById(R.id.upscore17);
        newscore18 = (EditText) findViewById(R.id.upscore18);

        // Links the new Button with the button in the XML file.
        updatebtn = (Button) findViewById(R.id.updateresultbtn);
        deletebtn = (Button) findViewById(R.id.deleteresultbtn);

        // Creates a new intent which retrieves the values from the database and puts them in new Strings.
        Intent intent = getIntent();
        String oldresultid = intent.getStringExtra("id");
        String oldresultname = intent.getStringExtra("resname");
        String oldscore = intent.getStringExtra("score");
        String old1 = intent.getStringExtra("result1");
        String oldres2 = intent.getStringExtra("result2");
        String oldres3 = intent.getStringExtra("result3");
        String oldres4 = intent.getStringExtra("result4");
        String oldres5 = intent.getStringExtra("result5");
        String oldres6 = intent.getStringExtra("result6");
        String oldres7 = intent.getStringExtra("result7");
        String oldres8 = intent.getStringExtra("result8");
        String oldres9 = intent.getStringExtra("result9");
        String oldres10 = intent.getStringExtra("result10");
        String oldres11 = intent.getStringExtra("result11");
        String oldres12 = intent.getStringExtra("result12");
        String oldres13 = intent.getStringExtra("result13");
        String oldres14 = intent.getStringExtra("result14");
        String oldres15 = intent.getStringExtra("result15");
        String oldres16 = intent.getStringExtra("result16");
        String oldres17 = intent.getStringExtra("result17");
        String oldres18 = intent.getStringExtra("result18");

        // Sets the edittext variables to the values stored in the strings retrieved from the database.
        _id = Long.parseLong(oldresultid);
        name.setText(oldresultname);
        newscore1.setText(old1);
        newscore2.setText(oldres2);
        newscore3.setText(oldres3);
        newscore4.setText(oldres4);
        newscore5.setText(oldres5);
        newscore6.setText(oldres6);
        newscore7.setText(oldres7);
        newscore8.setText(oldres8);
        newscore9.setText(oldres9);
        newscore10.setText(oldres10);
        newscore11.setText(oldres11);
        newscore12.setText(oldres12);
        newscore13.setText(oldres13);
        newscore14.setText(oldres14);
        newscore15.setText(oldres15);
        newscore16.setText(oldres16);
        newscore17.setText(oldres17);
        newscore18.setText(oldres18);

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
            case R.id.updateresultbtn:
                /*Code adapted from Check if edittext is empty described at
                * http://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
                * Error check to make sure none of the edittext boxes are empty.
                */
                if (name.getText().toString().trim().length() <= 0 || newscore1.getText().toString().trim().length() <= 0 ||
                        newscore2.getText().toString().trim().length() <= 0 || newscore3.getText().toString().trim().length() <= 0 ||
                        newscore4.getText().toString().trim().length() <= 0 || newscore5.getText().toString().trim().length() <= 0 ||
                        newscore6.getText().toString().trim().length() <= 0 || newscore7.getText().toString().trim().length() <= 0 ||
                        newscore8.getText().toString().trim().length() <= 0 || newscore9.getText().toString().trim().length() <= 0 ||
                        newscore10.getText().toString().trim().length() <= 0 || newscore11.getText().toString().trim().length() <= 0 ||
                        newscore12.getText().toString().trim().length() <= 0 || newscore13.getText().toString().trim().length() <= 0 ||
                        newscore14.getText().toString().trim().length() <= 0 || newscore15.getText().toString().trim().length() <= 0 ||
                        newscore16.getText().toString().trim().length() <= 0 || newscore17.getText().toString().trim().length() <= 0 ||
                        newscore18.getText().toString().trim().length() <= 0)
                {
                    // If any of them are, display a toast to tell the user.
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                    break;
                }
                else {

                    // Create new Integers and store the int value of the value entered in the edittext box.
                    int val1 = Integer.valueOf(newscore1.getText().toString());
                    int val2 = Integer.valueOf(newscore2.getText().toString());
                    int val3 = Integer.valueOf(newscore3.getText().toString());
                    int val4 = Integer.valueOf(newscore4.getText().toString());
                    int val5 = Integer.valueOf(newscore5.getText().toString());
                    int val6 = Integer.valueOf(newscore6.getText().toString());
                    int val7 = Integer.valueOf(newscore7.getText().toString());
                    int val8 = Integer.valueOf(newscore8.getText().toString());
                    int val9 = Integer.valueOf(newscore9.getText().toString());
                    int val10 = Integer.valueOf(newscore10.getText().toString());
                    int val11 = Integer.valueOf(newscore11.getText().toString());
                    int val12 = Integer.valueOf(newscore12.getText().toString());
                    int val13 = Integer.valueOf(newscore13.getText().toString());
                    int val14 = Integer.valueOf(newscore14.getText().toString());
                    int val15 = Integer.valueOf(newscore15.getText().toString());
                    int val16 = Integer.valueOf(newscore16.getText().toString());
                    int val17 = Integer.valueOf(newscore17.getText().toString());
                    int val18 = Integer.valueOf(newscore18.getText().toString());

                    // Create a new Integer that stores the total value of the 18 new integers.
                    int total = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10 + val11 + val12 + val13 + val14 + val15 + val16 + val17 + val18;

                    // Creates a new string that stores the value of 'total'
                    String newscore = String.valueOf(total);

                    // Creates new Strings that store the values entered into the edittext.
                    String newresname = name.getText().toString();
                    String newres1 = newscore1.getText().toString();
                    String newres2 = newscore2.getText().toString();
                    String newres3 = newscore3.getText().toString();
                    String newres4 = newscore4.getText().toString();
                    String newres5 = newscore5.getText().toString();
                    String newres6 = newscore6.getText().toString();
                    String newres7 = newscore7.getText().toString();
                    String newres8 = newscore8.getText().toString();
                    String newres9 = newscore9.getText().toString();
                    String newres10 = newscore10.getText().toString();
                    String newres11 = newscore11.getText().toString();
                    String newres12 = newscore12.getText().toString();
                    String newres13 = newscore13.getText().toString();
                    String newres14 = newscore14.getText().toString();
                    String newres15 = newscore15.getText().toString();
                    String newres16 = newscore16.getText().toString();
                    String newres17 = newscore17.getText().toString();
                    String newres18 = newscore18.getText().toString();

                    // Enters the values stored in the strings into the database.
                    dbManager.updateresult(_id, newresname, newscore, newres1, newres2, newres3, newres4, newres5, newres6, newres7, newres8, newres9,
                            newres10, newres11, newres12, newres13, newres14, newres15, newres16, newres17, newres18);

                    // Starts the returnHome intent.
                    this.returnHome();
                    break;
                }

            case R.id.deleteresultbtn:

                /* Code from 'Android confirmation message for delete' described at
                * http://stackoverflow.com/questions/11740311/android-confirmation-message-for-delete
                */
                new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this result from the database?")
                        .setCancelable(false).setPositiveButton
                        // If they click yes, the result is deleted from the database.
                                ("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dbManager.deleteresult(_id);
                                        /* Code adapted from 'What does the activity clear top do' described at
                                        * http:skillgun.com/question/318/android/activities/what-does-the-flag-flag_activity_clear_top-do-here-in-below-android-code-assume-that-activities-in-the-stack-are-abcd-code-is-currently-running-in-activity-d-now-what-will-happen-in-this-scenario-intent-in-new-intentthis-bclass-insetflagsintentflag
                                        * Creates and starts a new activity which returns the user to the Results activity.
                                        */
                                        Intent home = new Intent(getApplicationContext(), Results.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(home);
                                    }
                                })
                        // If they click no, they are brought back to the Results activity.
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
    }
    public void returnHome() {
        // Creates and starts a new intent which returns the user to the Results activity.
        Intent home_intent = new Intent(getApplicationContext(), Results.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
