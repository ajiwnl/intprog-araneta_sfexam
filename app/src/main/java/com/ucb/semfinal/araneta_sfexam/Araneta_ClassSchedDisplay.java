package com.ucb.semfinal.araneta_sfexam;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Araneta_ClassSchedDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araneta_class_sched_display);

        TextView DAYView = findViewById(R.id.idDay);
        TextView TIMEView = findViewById(R.id.idTime);
        TextView CODEView = findViewById(R.id.idCode);
        TextView DESCView = findViewById(R.id.idDesc);
        TextView ROOMView = findViewById(R.id.idRoom);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String day = extras.getString("day");
            String time = extras.getString("time");
            String code = extras.getString("code");
            String desc = extras.getString("desc");
            String room = extras.getString("room");

            DAYView.setText(day);
            TIMEView.setText(time);
            CODEView.setText(code);
            DESCView.setText(desc);
            ROOMView.setText(room);
        }


    }
}