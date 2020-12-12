package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText img;
    ImageView testimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.SITE);
        img = findViewById(R.id.editText);
        testimage=findViewById(R.id.imageView);

        MailWeather test = new MailWeather();
        String content = test.getSite("Новосибирск");
        String weather = test.getWeather(content);
        String src = test.getSrc(content);
        try {
           /* URL url = new URL("https://i.pinimg.com/originals/9a/8b/42/9a8b424974500d9269403ed5308e5be6.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            testimage.setImageBitmap(bmp);*/
            Picasso.get().load(src).into(testimage);
        } catch (Exception e) {
            img.setText("!!!"+e.getMessage());
        }
        textView.setText(weather);
    }
}