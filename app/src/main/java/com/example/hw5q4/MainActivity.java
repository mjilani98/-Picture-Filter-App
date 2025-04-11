package com.example.hw5q4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private int PICTURE_REQUEST = 1;      //code used for camera activity
    private Bitmap bitmap;                //bit map used to store image
    private Bitmap greyBitmap;            //bit map used to store grey image
    private Bitmap blackWhiteBitmap;      //bit map used to store black and white image
    private Bitmap greenBitmap;           //bit map used to store green image


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //package manager
        PackageManager manager = getPackageManager();

        //if camera feature is available
        if (manager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            //create camera activity
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //start camera activity
            startActivityForResult(pictureIntent, PICTURE_REQUEST);
        }
        //otherwise display error message
        else
            Toast.makeText(this, "No camera", Toast.LENGTH_LONG).show();

    }

    //Method displays result of camera activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //if the result is from camera activity
        if (requestCode == PICTURE_REQUEST)
        {
            //get result of camera activity
            Bundle extras = data.getExtras();

            //get bitmap (picture) from result
            bitmap = (Bitmap)extras.get("data");

            //access image view to display bitmap
            ImageView imageView = findViewById(R.id.picture);

            //display bitmap on image view
            imageView.setImageBitmap(bitmap);
        }
    }

    //Method displays grey scale image of camera image
    public void grey(View view)
    {
        //create a mutable bitmap to store grey scale image
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config configuration = bitmap.getConfig();
        greyBitmap = Bitmap.createBitmap(width, height, configuration);

        //go thru each pixel of camera image
        for (int i = 0; i < bitmap.getWidth(); i++)
            for (int j = 0; j < bitmap.getHeight(); j++)
            {
                //get color value of pixel
                int color = bitmap.getPixel(i, j);

                //find r, g, b values of color
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                //find average of r, g, b values
                int grey = (int)((red + green + blue)/3.0);

                //create grey color using average
                color = Color.rgb(grey, grey, grey);

                //set grey pixel on grey bit map
                greyBitmap.setPixel(i, j, color);
            }

        //access image view to display grey image
        ImageView imageView = findViewById(R.id.picture);

        //display grey image on image view
        imageView.setImageBitmap(greyBitmap);
    }

    //method displays black and white scale image of camera image
    public void blackWhite(View view)
    {
        //create a mutable bitmap to store black and white  scale image
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config configuration = bitmap.getConfig();
        blackWhiteBitmap = Bitmap.createBitmap(width,height,configuration);

        //go through each pixel of camera image
        for (int i = 0; i < bitmap.getWidth(); i++)
        {
            for (int j = 0; j < bitmap.getHeight(); j++)
            {
                //get color value of pixel
                int color = bitmap.getPixel(i, j);

                //find r, g, b values of color
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                //find average of r, g, b values
                int average = (int)((red + green + blue)/3.0);

                //if the average of rgb values is less thant 127
                if(average < 127)
                {
                    color = Color.rgb(0,0,0);
                }
                //if greater
                else
                {
                    color = Color.rgb(255,255,255);
                }

                //set black and white pixel on blackWhite Bitmap
                blackWhiteBitmap.setPixel(i,j,color);
            }

            //access image view to display black and white image
            ImageView imageView = findViewById(R.id.picture);

            //display black and white image on image view
            imageView.setImageBitmap(blackWhiteBitmap);
        }
    }


    // //method displays green scale image of camera image
    public void green(View view)
    {
        //create a mutable bitmap to store grey scale image
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config configuration = bitmap.getConfig();
        greenBitmap = Bitmap.createBitmap(width, height, configuration);

        //go through each pixel of camera image
        for (int i = 0; i < bitmap.getWidth(); i++)
        {
            for (int j = 0; j < bitmap.getHeight(); j++)
            {
                //get color value of pixel
                int color = bitmap.getPixel(i, j);

                //find r, g, b values of color
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                //find average of r, g, b values
                int average = (int)((red + green + blue)/3.0);

                //create grey color using average
                color = Color.rgb(0, average,0);

                //set green pixel on green bit map
                greenBitmap.setPixel(i,j,color);
            }
        }

        //access image view to display grey image
        ImageView imageView = findViewById(R.id.picture);

        //display grey image on image view
        imageView.setImageBitmap(greenBitmap);
    }

    //method display the original image to image view
    public void original(View view)
    {
        //access image view to display grey image
        ImageView imageView = findViewById(R.id.picture);

        //display grey image on image view
        imageView.setImageBitmap(bitmap);
    }


}