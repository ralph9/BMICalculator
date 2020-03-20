package com.example.aphextwin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView fadeInName = (TextView) findViewById(R.id.textNameAbout);
        Animation StoryAnimationQuick = AnimationUtils.loadAnimation(this, R.anim.fadeinquick);
        fadeInName.startAnimation(StoryAnimationQuick);

        ImageView fadeInImage = (ImageView) findViewById(R.id.imageProfile);
        Animation StoryAnimationMedium = AnimationUtils.loadAnimation(this, R.anim.fadeinmedium);
        fadeInImage.startAnimation(StoryAnimationMedium);

        TextView fadeInText = (TextView) findViewById(R.id.textDescriptionAbout);
        Animation StoryAnimationSlow = AnimationUtils.loadAnimation(this, R.anim.fadeinslowest);
        fadeInText.startAnimation(StoryAnimationSlow);
    }
}
