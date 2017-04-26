package eoghan.projectv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/** DatabaseHelper class which creates the tables and inserts some hardcoded data into them
 *
 * <p>
 * Citation
 * This class is based on code from
 * https://github.com/aporter/coursera-android/tree/master/Examples/DataManagementSQL
 * Retrieved on 10/11/2016
 *</p>
 *
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "GOLFERS.DB";

    // Table Name
    public static final String TABLE_NEWGOLFER = "NEWGOLFERS";
    public static final String TABLE_NEWCOURSE = "COURSES";
    public static final String TABLE_RESULTS = "RESULTS";

    // database version
    static final int DB_VERSION = 14;

    // Golfer Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String HANDICAP = "handicap";
    public static final String PHONENO = "phone";

    // Courses Table columns
    public static final String COURSE_ID = "_id";
    public static final String COURSE_NAME = "coursename";
    public static final String COURSE_ADDRESS = "courseaddress";
    public static final String HOLE1 = "hole1";
    public static final String HOLE2 = "hole2";
    public static final String HOLE3 = "hole3";
    public static final String HOLE4 = "hole4";
    public static final String HOLE5 = "hole5";
    public static final String HOLE6 = "hole6";
    public static final String HOLE7 = "hole7";
    public static final String HOLE8 = "hole8";
    public static final String HOLE9 = "hole9";
    public static final String HOLE10 = "hole10";
    public static final String HOLE11 = "hole11";
    public static final String HOLE12 = "hole12";
    public static final String HOLE13 = "hole13";
    public static final String HOLE14 = "hole14";
    public static final String HOLE15 = "hole15";
    public static final String HOLE16 = "hole16";
    public static final String HOLE17 = "hole17";
    public static final String HOLE18 = "hole18";

    // Results Table Columns
    public static final String RESULT_ID = "_id";
    public static final String RESNAME = "resname";
    public static final String SCORE = "score";
    public static final String RESULT1 = "result1";
    public static final String RESULT2 = "result2";
    public static final String RESULT3 = "result3";
    public static final String RESULT4 = "result4";
    public static final String RESULT5 = "result5";
    public static final String RESULT6 = "result6";
    public static final String RESULT7 = "result7";
    public static final String RESULT8 = "result8";
    public static final String RESULT9 = "result9";
    public static final String RESULT10 = "result10";
    public static final String RESULT11 = "result11";
    public static final String RESULT12 = "result12";
    public static final String RESULT13 = "result13";
    public static final String RESULT14 = "result14";
    public static final String RESULT15 = "result15";
    public static final String RESULT16 = "result16";
    public static final String RESULT17 = "result17";
    public static final String RESULT18 = "result18";


    // Creating NEWGOLFER table
    private static final String CREATE_TABLE_NEWGOLFER =
            "create table " + TABLE_NEWGOLFER + "(" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + HANDICAP
                    + " TEXT NOT NULL, " + PHONENO + " TEXT NOT NULL);";

    // Create NEWCOURSE table
    private static final String CREATE_TABLE_NEWCOURSE =
            "create table " + TABLE_NEWCOURSE + "(" + COURSE_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COURSE_NAME + " TEXT NOT NULL, " + COURSE_ADDRESS
                    + " TEXT NOT NULL, " + HOLE1 + " TEXT NOT NULL, " + HOLE2 + " TEXT NOT NULL, " + HOLE3 + " TEXT NOT NULL, " + HOLE4
                    + " TEXT NOT NULL, " + HOLE5 + " TEXT NOT NULL, " + HOLE6 + " TEXT NOT NULL, " + HOLE7 + " TEXT NOT NULL, " + HOLE8
                    + " TEXT NOT NULL, " + HOLE9 + " TEXT NOT NULL, " + HOLE10 + " TEXT NOT NULL, " + HOLE11 + " TEXT NOT NULL, " + HOLE12
                    + " TEXT NOT NULL, " + HOLE13 + " TEXT NOT NULL, " + HOLE14 + " TEXT NOT NULL, " + HOLE15 + " TEXT NOT NULL, " + HOLE16
                    + " TEXT NOT NULL, " + HOLE17 + " TEXT NOT NULL, " + HOLE18 + " TEXT NOT NULL);";

    // Create RESULTS table
    private static final String CREATE_TABLE_RESULTS =
            "create table " + TABLE_RESULTS + "(" + RESULT_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RESNAME + " TEXT NOT NULL, " + SCORE
                    + " TEXT NOT NULL, " + RESULT1 + " TEXT NOT NULL, " + RESULT2 + " TEXT NOT NULL, " + RESULT3 + " TEXT NOT NULL, " + RESULT4
                    + " TEXT NOT NULL, " + RESULT5 + " TEXT NOT NULL, " + RESULT6 + " TEXT NOT NULL, " + RESULT7 + " TEXT NOT NULL, " + RESULT8
                    + " TEXT NOT NULL, " + RESULT9 + " TEXT NOT NULL, " + RESULT10 + " TEXT NOT NULL, " + RESULT11 + " TEXT NOT NULL, " + RESULT12
                    + " TEXT NOT NULL, " + RESULT13 + " TEXT NOT NULL, " + RESULT14 + " TEXT NOT NULL, " + RESULT15 + " TEXT NOT NULL, " + RESULT16
                    + " TEXT NOT NULL, " + RESULT17 + " TEXT NOT NULL, " + RESULT18 + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     *
     * @param db Creates  new SQLiteDatabase which creates the three tables and inserts some hardcoded data into them.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NEWGOLFER);
        db.execSQL(CREATE_TABLE_NEWCOURSE);
        db.execSQL(CREATE_TABLE_RESULTS);

        db.execSQL("INSERT INTO " + TABLE_NEWCOURSE + "(coursename, courseaddress, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18)" +
                " VALUES ('Beech Park Golf Club', 'Rathcoole' , 4, 4, 4, 3, 4, 4, 5, 3, 5, 4, 4, 3, 5, 4, 3, 4, 5, 4)");
        db.execSQL("INSERT INTO " + TABLE_NEWCOURSE + "(coursename, courseaddress, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18)" +
                " VALUES ('Castleknock Golf Club', 'Castleknock' , 4, 5, 4, 3, 4, 3, 5, 3, 5, 4, 4, 5, 5, 4, 3, 4, 5, 4)");
        db.execSQL("INSERT INTO " + TABLE_NEWCOURSE + "(coursename, courseaddress, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18)" +
                " VALUES ('Grange Castle Golf Club', 'Clondalkin' , 4, 5, 4, 4, 3, 4, 3, 5, 5, 4, 4, 3, 5, 4, 3, 4, 4, 4)");

        db.execSQL("INSERT INTO " + TABLE_NEWGOLFER + "(name, handicap, phone)" + " VALUES ('Eoghan Nolan', 9, 0851297842), ('John Burke', 16, 0874567433), ('Lorcan Timoney', 21, 0854399033)");

        db.execSQL("INSERT INTO " + TABLE_RESULTS + "(resname, score, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15, result16, result17, result18)" +
                " VALUES ('Brian Lawlor', 88, 4, 6, 7, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4)");
        db.execSQL("INSERT INTO " + TABLE_RESULTS + "(resname, score, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15, result16, result17, result18)" +
                " VALUES ('John Burke', 94, 4, 6, 7, 4, 4, 4, 4, 11, 4, 4, 4, 15, 4, 4, 4, 4, 4, 4)");
    }

    /** Creates new versions of the tables if the database version changes.
     *
     * @param db SQLiteDatabase
     * @param oldVersion Int which returns the stored value of the table version
     * @param newVersion Int which returns the current value of the table version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWGOLFER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWCOURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }
}
