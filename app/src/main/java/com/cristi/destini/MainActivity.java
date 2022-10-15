package com.cristi.destini;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView mStoryTextView;
    public Button mButtonTop;
    public Button mButtonBottom;
    public int mStoryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);

        if (savedInstanceState != null) {
            mStoryIndex = savedInstanceState.getInt("currentState");
            setStoryContext(mStoryIndex);
        }
        else {
            mStoryIndex = 1;
        }

        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Top", "Pressed");
                if (mStoryTextView.getText().equals(getString(R.string.T1_Story))) {
                    mStoryIndex = 3;
                }
                else if (mStoryTextView.getText().equals(getString(R.string.T2_Story))) {
                    mStoryIndex = 3;
                }
                else if (mStoryTextView.getText().equals(getString(R.string.T3_Story))){
                    mStoryIndex = 6;
                }
                setStoryContext(mStoryIndex);
            }
        });

        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStoryTextView.getText().equals(getString(R.string.T1_Story))) {
                    mStoryIndex = 2;
                }
                else if (mStoryTextView.getText().equals(getString(R.string.T2_Story))) {
                    mStoryIndex = 4;
                }
                else if (mStoryTextView.getText().equals(getString(R.string.T3_Story))){
                    mStoryIndex = 5;
                }
                setStoryContext(mStoryIndex);
            }
        });
    }

    void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.alert_title);
        alert.setCancelable(false);
        alert.setMessage("Replay the story?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGame();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    public void setStoryContext(int currentState) {
        if (currentState == 1) {
            mStoryTextView.setText(R.string.T1_Story);
            mButtonTop.setText(R.string.T1_Ans1);
            mButtonBottom.setText(R.string.T1_Ans2);
        }
        else if (currentState == 2) {
            mStoryTextView.setText(R.string.T2_Story);
            mButtonTop.setText(R.string.T2_Ans1);
            mButtonBottom.setText(R.string.T2_Ans2);
        }
        else if (currentState == 3) {
            mStoryTextView.setText(R.string.T3_Story);
            mButtonTop.setText(R.string.T3_Ans1);
            mButtonBottom.setText(R.string.T3_Ans2);
        }
        else if (currentState == 4) {
            mStoryTextView.setText(R.string.T4_End);
            mButtonTop.setVisibility(View.INVISIBLE);
            mButtonBottom.setVisibility(View.INVISIBLE);
            showAlertDialog();
        }
        else if (currentState == 5) {
            mStoryTextView.setText(R.string.T5_End);
            mButtonTop.setVisibility(View.INVISIBLE);
            mButtonBottom.setVisibility(View.INVISIBLE);
            showAlertDialog();
        }
        else if (currentState == 6) {
            mStoryTextView.setText(R.string.T6_End);
            mButtonTop.setVisibility(View.INVISIBLE);
            mButtonBottom.setVisibility(View.INVISIBLE);
            showAlertDialog();
        }
    }

    public void resetGame() {
        mStoryIndex = 1;
        mStoryTextView.setText(R.string.T1_Story);
        mButtonTop.setText(R.string.T1_Ans1);
        mButtonTop.setVisibility(View.VISIBLE);
        mButtonBottom.setText(R.string.T1_Ans2);
        mButtonBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentState", mStoryIndex);
    }
}
