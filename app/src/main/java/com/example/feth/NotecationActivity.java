package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotecationActivity extends AppCompatActivity {
    TextView lblNotication;
    Button btnNoticationOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecation);
        lblNotication=findViewById(R.id.lblNotication);
        btnNoticationOK=findViewById(R.id.btnNoticationOK);
        Intent intent=getIntent();
        String noti =intent.getStringExtra("notication");
        lblNotication.setText(noti);
        btnNoticationOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(NotecationActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
