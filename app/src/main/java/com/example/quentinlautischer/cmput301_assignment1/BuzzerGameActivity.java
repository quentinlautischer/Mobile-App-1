package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by quentinlautischer on 2015-09-13.
 */
public class BuzzerGameActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzer_button_layout);

        final StatsController statsController = MainActivity.statsController;

        final Intent intent = getIntent();
        String numPl = intent.getStringExtra(BuzzerGameFragment.NUM_OF_PLAYERS);
        Integer numPlayers = Integer.valueOf(numPl);

        LinearLayout linear = (LinearLayout)findViewById(R.id.buzzer_button_layout);
        for(int i=0; i < numPlayers; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    (float) 1/numPlayers);
            Button btn = new Button(this);
            btn.setId(i + 1);
            final int id_ = btn.getId();
            btn.setText("Player " + id_);
            btn.setBackgroundColor(Color.rgb(20 * (i + 1), 90 * (i + 1), 50 * (i + 1)));
            btn.setTextColor(Color.rgb(200, 200, 200));
            linear.addView(btn, params);
            Button btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                    statsController.addBuzzerClick("P2", "p1");
                }
            });

        }

    }
}
