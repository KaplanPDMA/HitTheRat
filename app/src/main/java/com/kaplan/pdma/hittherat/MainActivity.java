package com.kaplan.pdma.hittherat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    ImageView[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        //gridLayout.setRowCount(3);
        gridLayout.setColumnCount(3);

        final TextView textViewScore = (TextView) findViewById(R.id.textView);
        final TextView textViewTime = (TextView) findViewById(R.id.textViewTime);

        final int timeRemaining = 10;
        final CountDownTimer countDownTimer = new CountDownTimer(timeRemaining * 1000, 1000) {
            @Override
            public void onTick(long l) {
                textViewTime.setText("" + (l / 1000));
            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time's up!");

                //https://developer.android.com/guide/topics/ui/dialogs.html
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("" + score)
                        .setTitle("Score");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        //score = 0;
                        textViewScore.setText("Game Over");
                    }
                });

                builder.setCancelable(false);

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();

                dialog.show();

            }
        };
        countDownTimer.start();


        array = new ImageView[9];
        array[0] = (ImageView) findViewById(R.id.imageView);
        array[1] = (ImageView) findViewById(R.id.imageView2);
        array[2] = (ImageView) findViewById(R.id.imageView3);
        array[3] = (ImageView) findViewById(R.id.imageView4);
        array[4] = (ImageView) findViewById(R.id.imageView5);
        array[5] = (ImageView) findViewById(R.id.imageView6);
        array[6] = (ImageView) findViewById(R.id.imageView7);
        array[7] = (ImageView) findViewById(R.id.imageView8);
        array[8] = (ImageView) findViewById(R.id.imageView9);


        for (int i = 0; i < 9; i++) {
            array[i].setTag(1);
            array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) ((ImageView) view).getTag();
                    if (tag == 2) {
                        ((ImageView) view).setImageResource(R.drawable.rathole);
                        ((ImageView) view).setTag(1);
                        score++;
                        setNextRat();
                    } else {
                        score--;
                    }
                    textViewScore.setText("" + score);
                }
            });
        }

        setNextRat();
    }

    public void setNextRat() {
        Random r = new Random();
        int i = r.nextInt(9);
        array[i].setImageResource(R.drawable.rathole2);
        array[i].setTag(2);
    }
}
