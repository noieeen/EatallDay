package com.example.eatall;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.eatall.db.DatabaseHelper;
import com.example.emergencyphone.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class AddFoodItemActivity extends AppCompatActivity {

    private static final String TAG = AddFoodItemActivity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private String mLogoFilename = "breakfast.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_item);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsertPhoneItem();
            }
        });

        ImageView logoImageView = findViewById(R.id.logo_image_view);
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openChooserWithGallery(AddFoodItemActivity.this, "เลือกรูปภาพ", 0);
            }
        });

        Button cacel = findViewById(R.id.Cacel_button);
        cacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> list, EasyImage.ImageSource imageSource, int i) {
                File logoFile = list.get(0);
                Log.i(TAG, logoFile.getAbsolutePath());
                Log.i(TAG, logoFile.getName());

                File privateDir = getFilesDir();
                File dstFile = new File(privateDir, logoFile.getName());
                try {
                    copy(logoFile, dstFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mLogoFilename = logoFile.getName();
                ImageView logoImageView = findViewById(R.id.logo_image_view);

                Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
                logoImageView.setImageBitmap(bitmap);
            }
        });
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    private void doInsertPhoneItem() {
        EditText titleEditText = findViewById(R.id.title_edit_food);
        EditText calEditText = findViewById(R.id.title_edit_cal);
        EditText timeEditText = findViewById(R.id.title_edit_time);

        String title = titleEditText.getText().toString();
        String number = calEditText.getText().toString();
        String time = timeEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_TITLE, title);
        cv.put(DatabaseHelper.COL_CAL, number);
        cv.put(DatabaseHelper.COL_TIME,time);
        cv.put(DatabaseHelper.COL_IMAGE, mLogoFilename);
        mDb.insert(DatabaseHelper.TABLE_NAME, null, cv);

        finish();
    }

}
