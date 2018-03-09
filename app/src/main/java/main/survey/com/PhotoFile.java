package main.survey.com;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.survey.com.survey.R;
import main.survey.com.survey.main.survey.com.utility.GPSTracker;

public class PhotoFile extends AppCompatActivity {

    public static int REQUEST_DETAIL = 1;
    public static int CLOSE_CODE = 99;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;

    private int CAMERA_REQUEST = 100;
    private int Gallary_REQUEST = 101;


    Bitmap bitmap;
    Button btnGPS,btnPhoto;
    ImageView imgPhoto;
    public Uri fileUri;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_file);
        btnPhoto =(Button)findViewById(R.id.btnPhoto);
        imgPhoto = (ImageView)findViewById(R.id.imgPhoto);
        btnGPS =(Button) findViewById(R.id.btnGPS);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps=new GPSTracker(PhotoFile.this);
                if(gps.canGetLocation())
                {
                    double latitude=gps.getLatitude();
                    double longitude=gps.getLongitude();

                    Toast.makeText(getApplicationContext(),"Your location is in lat "+latitude+" and longitude "+longitude, Toast.LENGTH_LONG).show();

                }
                else
                {
                    gps.showSettingsAlert();
                }
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PhotoFile.REQUEST_DETAIL) {
            if (resultCode == PhotoFile.CLOSE_CODE) {
                setResult(PhotoFile.CLOSE_CODE);
                finish();
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            imgPhoto.setImageBitmap(bitmap);

        } else if (requestCode == Gallary_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmap = BitmapFactory.decodeFile(picturePath);
            ExifInterface ei = null;
            try {
                ei = new ExifInterface(picturePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotateImage(bitmap, 180);
                    break;
                // etc.
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);

            imgPhoto.setImageBitmap(bitmap);

        }

    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }

    void openCamera() {
        File directory = new File(
                Environment.getExternalStorageDirectory(), "iCollector" + "/" + getPackageName());
        try {
            SimpleDateFormat sdfPic = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String currentDateandTime = sdfPic.format(new Date()).replace(" ", "");
            File imagesFolder = new File(directory.getAbsolutePath());
            imagesFolder.mkdirs();

            String fname = "IMAGE_" + currentDateandTime + ".jpg";
            File file = new File(imagesFolder, fname);
            fileUri = Uri.fromFile(file);
            Intent cameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
