package eoghan.projectv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * @author Eoghan nolan
 * @version 1.0
 * @since 26/4/2017
 */

public class DatabaseManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Insert new golfer values
    public void insert(String name, String handicap, String phoneno) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.HANDICAP, handicap);
        contentValue.put(DatabaseHelper.PHONENO, phoneno);
        database.insert(DatabaseHelper.TABLE_NEWGOLFER, null, contentValue);
    }

    // Insert new course values
    public void insertcourse (String coursename, String courseaddress, String hole1, String hole2, String hole3, String hole4, String hole5, String hole6, String hole7, String hole8,
                              String hole9, String hole10,String hole11, String hole12,String hole13, String hole14,String hole15, String hole16,String hole17, String hole18) {
        ContentValues courseContent = new ContentValues();
        courseContent.put(DatabaseHelper.COURSE_NAME, coursename);
        courseContent.put(DatabaseHelper.COURSE_ADDRESS, courseaddress);
        courseContent.put(DatabaseHelper.HOLE1, hole1);
        courseContent.put(DatabaseHelper.HOLE2, hole2);
        courseContent.put(DatabaseHelper.HOLE3, hole3);
        courseContent.put(DatabaseHelper.HOLE4, hole4);
        courseContent.put(DatabaseHelper.HOLE5, hole5);
        courseContent.put(DatabaseHelper.HOLE6, hole6);
        courseContent.put(DatabaseHelper.HOLE7, hole7);
        courseContent.put(DatabaseHelper.HOLE8, hole8);
        courseContent.put(DatabaseHelper.HOLE9, hole9);
        courseContent.put(DatabaseHelper.HOLE10, hole10);
        courseContent.put(DatabaseHelper.HOLE11, hole11);
        courseContent.put(DatabaseHelper.HOLE12, hole12);
        courseContent.put(DatabaseHelper.HOLE13, hole13);
        courseContent.put(DatabaseHelper.HOLE14, hole14);
        courseContent.put(DatabaseHelper.HOLE15, hole15);
        courseContent.put(DatabaseHelper.HOLE16, hole16);
        courseContent.put(DatabaseHelper.HOLE17, hole17);
        courseContent.put(DatabaseHelper.HOLE18, hole18);
        database.insert(DatabaseHelper.TABLE_NEWCOURSE, null, courseContent);
    }

    // Method that when called, allows the user to entere scores into the database.
    public void insertscore (String resname, String score, String result1, String result2, String result3, String result4, String result5, String result6, String result7, String result8,
                              String result9, String result10 ,String result11, String result12,String result13, String result14,String result15, String result16,String result17, String result18) {
        ContentValues resultContent = new ContentValues();
        resultContent.put(DatabaseHelper.RESNAME, resname);
        resultContent.put(DatabaseHelper.SCORE, score);
        resultContent.put(DatabaseHelper.RESULT1, result1);
        resultContent.put(DatabaseHelper.RESULT2, result2);
        resultContent.put(DatabaseHelper.RESULT3, result3);
        resultContent.put(DatabaseHelper.RESULT4, result4);
        resultContent.put(DatabaseHelper.RESULT5, result5);
        resultContent.put(DatabaseHelper.RESULT6, result6);
        resultContent.put(DatabaseHelper.RESULT7, result7);
        resultContent.put(DatabaseHelper.RESULT8, result8);
        resultContent.put(DatabaseHelper.RESULT9, result9);
        resultContent.put(DatabaseHelper.RESULT10, result10);
        resultContent.put(DatabaseHelper.RESULT11, result11);
        resultContent.put(DatabaseHelper.RESULT12, result12);
        resultContent.put(DatabaseHelper.RESULT13, result13);
        resultContent.put(DatabaseHelper.RESULT14, result14);
        resultContent.put(DatabaseHelper.RESULT15, result15);
        resultContent.put(DatabaseHelper.RESULT16, result16);
        resultContent.put(DatabaseHelper.RESULT17, result17);
        resultContent.put(DatabaseHelper.RESULT18, result18);

        database.insert(DatabaseHelper.TABLE_RESULTS, null, resultContent);
    }

    /**
     *
     * @return Cursor which returns the values stored in the golfers database.
     */
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.HANDICAP, DatabaseHelper.PHONENO };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NEWGOLFER, columns, null, null, null, null, null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     *
     * @return Cursor which returns the values stored in teh golfers database.
     */

    public Cursor fetchcourse() {
        String[] columns = new String[] { DatabaseHelper.COURSE_ID, DatabaseHelper.COURSE_NAME, DatabaseHelper.COURSE_ADDRESS, DatabaseHelper.HOLE1,
                DatabaseHelper.HOLE2, DatabaseHelper.HOLE3, DatabaseHelper.HOLE4, DatabaseHelper.HOLE5, DatabaseHelper.HOLE6,
                DatabaseHelper.HOLE7, DatabaseHelper.HOLE8, DatabaseHelper.HOLE9, DatabaseHelper.HOLE10, DatabaseHelper.HOLE11,
                DatabaseHelper.HOLE12, DatabaseHelper.HOLE13, DatabaseHelper.HOLE14, DatabaseHelper.HOLE15, DatabaseHelper.HOLE16,
                DatabaseHelper.HOLE17, DatabaseHelper.HOLE18};
        Cursor coursecursor = database.query(DatabaseHelper.TABLE_NEWCOURSE, columns, null, null, null, null, null,null);
        if (coursecursor != null) {
            coursecursor.moveToFirst();
        }
        return coursecursor;
    }

    /**
     *
     * @return Cursor which returns the scores stored in the results table
     */
    public Cursor fetchresults() {
        String[] columns = new String[] { DatabaseHelper.RESULT_ID, DatabaseHelper.RESNAME, DatabaseHelper.SCORE, DatabaseHelper.RESULT1,
                DatabaseHelper.RESULT2, DatabaseHelper.RESULT3, DatabaseHelper.RESULT4, DatabaseHelper.RESULT5, DatabaseHelper.RESULT6,
                DatabaseHelper.RESULT7, DatabaseHelper.RESULT8, DatabaseHelper.RESULT9, DatabaseHelper.RESULT10, DatabaseHelper.RESULT11,
                DatabaseHelper.RESULT12, DatabaseHelper.RESULT13, DatabaseHelper.RESULT14, DatabaseHelper.RESULT15, DatabaseHelper.RESULT16,
                DatabaseHelper.RESULT17, DatabaseHelper.RESULT18};
        Cursor resultscursor = database.query(DatabaseHelper.TABLE_RESULTS, columns, null, null, null, null, DatabaseHelper.SCORE +" ASC", null );
        if (resultscursor != null) {
            resultscursor.moveToFirst();
        }
        return resultscursor;
    }

    /** Allows the user to update the values stored in a particular row of the golfers database.
     *
     * @param _id Int that identifies the golfer
     * @param name String that contains the golfers name
     * @param handicap String that contains the golfers handicap
     * @param phone String that contains the golfers phone number.
     * @return
     */
    public int update(long _id, String name, String handicap, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.HANDICAP, handicap);
        contentValues.put(DatabaseHelper.PHONENO, phone);
        int i = database.update(DatabaseHelper.TABLE_NEWGOLFER, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
// Allows the user to update the values stored in a particular row of the course database.
    public int updatecourse(long _id, String coursename, String courseaddress, String hole1, String hole2, String hole3, String hole4, String hole5, String hole6, String hole7, String hole8,
                            String hole9, String hole10,String hole11, String hole12,String hole13, String hole14,String hole15, String hole16,String hole17, String hole18) {
        ContentValues courseValues = new ContentValues();
        courseValues.put(DatabaseHelper.COURSE_NAME, coursename);
        courseValues.put(DatabaseHelper.COURSE_ADDRESS, courseaddress);
        courseValues.put(DatabaseHelper.HOLE1, hole1);
        courseValues.put(DatabaseHelper.HOLE2, hole2);
        courseValues.put(DatabaseHelper.HOLE3, hole3);
        courseValues.put(DatabaseHelper.HOLE4, hole4);
        courseValues.put(DatabaseHelper.HOLE5, hole5);
        courseValues.put(DatabaseHelper.HOLE6, hole6);
        courseValues.put(DatabaseHelper.HOLE7, hole7);
        courseValues.put(DatabaseHelper.HOLE8, hole8);
        courseValues.put(DatabaseHelper.HOLE9, hole9);
        courseValues.put(DatabaseHelper.HOLE10, hole10);
        courseValues.put(DatabaseHelper.HOLE11, hole11);
        courseValues.put(DatabaseHelper.HOLE12, hole12);
        courseValues.put(DatabaseHelper.HOLE13, hole13);
        courseValues.put(DatabaseHelper.HOLE14, hole14);
        courseValues.put(DatabaseHelper.HOLE15, hole15);
        courseValues.put(DatabaseHelper.HOLE16, hole16);
        courseValues.put(DatabaseHelper.HOLE17, hole17);
        courseValues.put(DatabaseHelper.HOLE18, hole18);
        int i = database.update(DatabaseHelper.TABLE_NEWCOURSE, courseValues, DatabaseHelper.COURSE_ID + " = " + _id, null);
        return i;
    }
    // Allows the user to update the values stored in a particular row of the results table.
    public int updateresult(long _id, String resname, String score, String result1, String result2, String result3, String result4, String result5, String result6, String result7, String result8,
                            String result9, String result10,String result11, String result12,String result13, String result14,String result15, String result16,String result17, String result18) {
        ContentValues newValues = new ContentValues();
        newValues.put(DatabaseHelper.RESNAME, resname);
        newValues.put(DatabaseHelper.SCORE, score);
        newValues.put(DatabaseHelper.RESULT1, result1);
        newValues.put(DatabaseHelper.RESULT2, result2);
        newValues.put(DatabaseHelper.RESULT3, result3);
        newValues.put(DatabaseHelper.RESULT4, result4);
        newValues.put(DatabaseHelper.RESULT5, result5);
        newValues.put(DatabaseHelper.RESULT6, result6);
        newValues.put(DatabaseHelper.RESULT7, result7);
        newValues.put(DatabaseHelper.RESULT8, result7);
        newValues.put(DatabaseHelper.RESULT9, result9);
        newValues.put(DatabaseHelper.RESULT10, result10);
        newValues.put(DatabaseHelper.RESULT11, result11);
        newValues.put(DatabaseHelper.RESULT12, result12);
        newValues.put(DatabaseHelper.RESULT13, result13);
        newValues.put(DatabaseHelper.RESULT14, result14);
        newValues.put(DatabaseHelper.RESULT15, result15);
        newValues.put(DatabaseHelper.RESULT16, result16);
        newValues.put(DatabaseHelper.RESULT17, result17);
        newValues.put(DatabaseHelper.RESULT18, result18);
        int x = database.update(DatabaseHelper.TABLE_RESULTS, newValues, DatabaseHelper.RESULT_ID + " = " + _id, null);
        return x;
    }

    /** Allows the user to delete a particular row of the golfer table
     *
     * @param _id The id of the row to be deleted.
     */
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NEWGOLFER, DatabaseHelper._ID + "=" + _id, null);
    }
    // Allows the user to delete a particular row of the courses table
    public void deletecourse(long _id) {
        database.delete(DatabaseHelper.TABLE_NEWCOURSE, DatabaseHelper.COURSE_ID + "=" + _id, null);
    }
    // Allows the user to delete a particular row of the results table.
    public void deleteresult(long _id) {
        database.delete(DatabaseHelper.TABLE_RESULTS, DatabaseHelper.RESULT_ID + "=" + _id, null);
    }
    // Allows the user to clear all of the results stored in the results table.
    public void clearresults() {
        database.delete(DatabaseHelper.TABLE_RESULTS, null, null);
    }
}
