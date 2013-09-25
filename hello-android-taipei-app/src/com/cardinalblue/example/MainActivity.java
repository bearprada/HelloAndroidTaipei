package com.cardinalblue.example;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    private final Calculator calculator = new Calculator();
    private EditText mStartText;
    private EditText mEndText;
    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartText = ((EditText)findViewById(R.id.editTextStart));
        mEndText = ((EditText)findViewById(R.id.editTextEnd));
        mResultText = ((TextView)findViewById(R.id.textViewResult));
        
        findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0) {
                try {
                    long start = Long.parseLong(mStartText.getText().toString());
                    long end = Long.parseLong(mEndText.getText().toString());
                    mResultText.setText("Result : " + calculator.multiple((int)start, (int)end));
                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "please enter the numbers", Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(MainActivity.this, "please enter the correct range .. the start should be small then end",
                            Toast.LENGTH_SHORT).show();
                } catch (OverNumberException e) {
                    Toast.makeText(MainActivity.this, "the result is Over then Long.MAX_VALUE", Toast.LENGTH_SHORT).show();
                }                        
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
