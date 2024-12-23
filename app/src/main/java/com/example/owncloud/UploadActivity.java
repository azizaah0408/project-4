package com.example.owncloud;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity{
    EditText edtName, edtFormat;
    Button btnChoose, btnAdd;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        init();

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "CloudDb.sqlite", null, 1);

        /*----------------- BUTTON CHOOSE IMAGE TO STORAGE --------------------*/
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UploadActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        /*----------------- BUTTON CHOOSE IMAGE TO STORAGE --------------------*/

        /*----------------- BUTTON ADD IMAGE TO GRIDVIEW LIST --------------------*/
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            edtFormat.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtFormat.setText("");
                    imageView.setImageResource(R.drawable.ic_insert_photo);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        /*----------------- BUTTON ADD IMAGE TO GRIDVIEW LIST --------------------*/

        /*----------------- BUTTON INTENT TO THE GRIDVIEW LIST --------------------*/

        /*----------------- BUTTON INTENT TO THE GRIDVIEW LIST --------------------*/
    }


        public static byte[] imageViewToByte(ImageView image) {
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }

    /*----------------- REQUEST PERMISSION TO STORAGE --------------------*/
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == REQUEST_CODE_GALLERY){
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_GALLERY);
                }
                else {
                    Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    /*----------------- REQUEST PERMISSION TO STORAGE --------------------*/


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        private void init(){
            edtName = (EditText) findViewById(R.id.edtName);
            edtFormat = (EditText) findViewById(R.id.edtFormat);
            btnChoose = (Button) findViewById(R.id.btnChoose);
            btnAdd = (Button) findViewById(R.id.btnAdd);

            imageView = (ImageView) findViewById(R.id.imageView);
        }

}