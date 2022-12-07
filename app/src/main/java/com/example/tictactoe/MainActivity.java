package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] board = new String[9];
    String turn = "X";

    public void setLineImage(int imageSRC, int imageRotation) {
        ImageView lineImage = findViewById(R.id.main_image_line);
        lineImage.setBackgroundResource(imageSRC);
        lineImage.setRotation(imageRotation);
    }

    public String checkWinner()
    {
        for (int a = 0; a < 8; a++) {
            String line = null;
            int image = R.drawable.empty;
            int rotation = 0;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    image = R.drawable.mark3;
                    rotation = 90;
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    image = R.drawable.mark4;
                    rotation = 90;
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    image = R.drawable.mark5;
                    rotation = 90;
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    image = R.drawable.mark3;
                    rotation = 0;
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    image = R.drawable.mark4;
                    rotation = 0;
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    image = R.drawable.mark5;
                    rotation = 0;
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    image = R.drawable.mark1;
                    rotation = 0;
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    image = R.drawable.mark2;
                    rotation = 0;
                    break;
            }

            //For X winner
            if (line.equals("XXX")) {
                setLineImage(image, rotation);
                return "X";
            }

            // For O winner
            else if (line.equals("OOO")) {
                setLineImage(image, rotation);
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (board[a].equals("")) {
                break;
            }
            else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

        View.OnClickListener cellListener = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View view) {
            int index = Integer.parseInt(view.getTag().toString());
            Log.d("TAG", board[index]);
            if(board[index].length() == 0) {
                board[index] = turn;
                ImageView turnImage = findViewById(R.id.main_image_turn);
                if(turn.equals("X")) {
                    view.setBackgroundResource(R.drawable.x);
                } else {
                    view.setBackgroundResource(R.drawable.o);
                }
                String winner = checkWinner();
                if(winner != null) {
                    for(int i = 0; i < 9; i++) {
                        String buttonID = "main_btn_" + i;
                        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                        ImageView cell = findViewById(resID);
                        cell.setClickable(false);
                    }

                    Log.d("TAG", winner);
                    TextView playAgainTextView = findViewById(R.id.main_text_playAgain);
                    playAgainTextView.setVisibility(View.VISIBLE);
                    if(winner.equals("X")) {
                        turnImage.setBackgroundResource(R.drawable.xwin);
                    } else if(winner.equals("O")) {
                        turnImage.setBackgroundResource(R.drawable.owin);
                    } else {
                        turnImage.setBackgroundResource(R.drawable.nowin);
                    }
                } else {
                    if(turn.equals("X")) {
                        turn = "O";
                        turnImage.setBackgroundResource(R.drawable.oplay);
                    } else {
                        turn = "X";
                        turnImage.setBackgroundResource(R.drawable.xplay);
                    }
                }
                view.setClickable(false);
            }
        }
    };

    View.OnClickListener playAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View view) {
            for(int i = 0; i < 9; i++) {
                board[i] = "";
                String buttonID = "main_btn_" + i;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                ImageView cell = findViewById(resID);
                cell.setBackgroundResource(R.drawable.empty);
                cell.setClickable(true);
            }
            turn = "X";
            ImageView turnImage = findViewById(R.id.main_image_turn);
            turnImage.setBackgroundResource(R.drawable.xplay);
            view.setVisibility(View.INVISIBLE);
            setLineImage(R.drawable.empty, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView turnImage = findViewById(R.id.main_image_turn);
        turnImage.setBackgroundResource(R.drawable.xplay);

        for(int i = 0; i < 9; i++) {
            board[i] = "";
            String buttonID = "main_btn_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            ImageView cell = findViewById(resID);
            cell.setTag(i);
            cell.setOnClickListener(cellListener);
        }

        TextView playAgain = findViewById(R.id.main_text_playAgain);
        playAgain.setOnClickListener(playAgainListener);
    }
}