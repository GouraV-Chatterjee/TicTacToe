package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    //0 for red, 1 for yellow,2 means empty;

    int activePlayer=0;

    boolean gameActive=true;

    int [] gameState={2,2,2,2,2,2,2,2,2};

    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    String winner="";

    public void dropIn(View view){

        ImageView counter=(ImageView) view;

        int counterTapped=Integer.parseInt(counter.getTag().toString());

        if(gameState[counterTapped]==2 && gameActive) {

            gameState[counterTapped] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);

                activePlayer=1;
            }

            else {
                counter.setImageResource(R.drawable.yellow);

                activePlayer=0;
            }

            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    gameActive=false;

                    if (activePlayer == 1) {

                        winner="Red has won!";

                    }
                    else {

                        winner="Yellow has won!";

                    }

                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner);

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);

        activePlayer=0;

        gameActive=true;

        winner="";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}