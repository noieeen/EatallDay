package com.example.eatall;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emergencyphone.R;
import com.example.eatall.db.DatabaseHelper;

import static com.example.eatall.db.DatabaseHelper.COL_CAL;
import static com.example.eatall.db.DatabaseHelper.COL_ID;
import static com.example.eatall.db.DatabaseHelper.COL_TITLE;
import static com.example.eatall.db.DatabaseHelper.TABLE_NAME;

public class EditFoodItemActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mCalEditText;
    private Button mSaveButton;

    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_item);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String number = intent.getStringExtra("number");
        mId = intent.getLongExtra("id", 0);

        mTitleEditText = findViewById(R.id.title_edit_food);
        mCalEditText = findViewById(R.id.title_edit_cal);
        mSaveButton = findViewById(R.id.save_button);

        mTitleEditText.setText(title);
        mCalEditText.setText(number);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: บันทึกข้อมูลใหม่ลง db
                DatabaseHelper helper = new DatabaseHelper(EditFoodItemActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                String newTitle = mTitleEditText.getText().toString().trim();
                String newNumber = mCalEditText.getText().toString().trim();

                ContentValues cv = new ContentValues();
                cv.put(COL_TITLE, newTitle);
                cv.put(COL_CAL, newNumber);

                db.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(mId)}
                );
                finish();
            }
        });
    }
}
