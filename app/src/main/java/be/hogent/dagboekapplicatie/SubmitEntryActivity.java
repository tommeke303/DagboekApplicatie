package be.hogent.dagboekapplicatie;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import be.hogent.dagboekapplicatie.persistence.Constants;
import be.hogent.dagboekapplicatie.persistence.MyDB;


public class SubmitEntryActivity extends Activity {
    private Button submit_button;
    private EditText editText_diary_title;
    private EditText editText_content;
    private MyDB db;

    /**
     * Creates this activity. Make sure that (i) you generate the db, (ii) open it, (iii) initialise
     * the references for the view and (iv) you add the code for the button listener.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_entry);
        editText_content = (EditText)findViewById(R.id.editText_content);
        editText_diary_title = (EditText)findViewById(R.id.editText_diary_title);
        db = new MyDB(this);
        submit_button = (Button)findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDB();
            }

        });
    }

    /**
     * Saves the information provided in the UI to the database. Check for null or empty strings
     * before entering into the DBA. Make toast messages in case of empty strings.
     */
    private void saveToDB(){
        ContentValues values = new ContentValues();
        values.put(Constants.TITLE, editText_diary_title.getText().toString());
        values.put(Constants.CONTENT, editText_content.getText().toString());
        Date date = new Date();
        values.put(Constants.DATE, date.getTime());
        db.open();
        db.insertDiary(values);
        db.close();

        Intent i = new Intent(this, DisplayDiaries.class);


    }

}
