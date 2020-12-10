package edu.csustan.budgetbuddy;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

// written by: Natasha Garcia
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Natasha

public class BuildingCredit2 extends AppCompatActivity {

    ImageView img;
    TextView tv1, tv2, tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_credit2);

        img = (ImageView) findViewById(R.id.desc_img);
        tv1 = (TextView) findViewById(R.id.desc_header);
        tv2 = (TextView) findViewById(R.id.desc_desc);
        tv3 = (TextView) findViewById(R.id.t3);

        img.setImageResource(getIntent().getIntExtra("imagename", 0));
        tv1.setText(getIntent().getStringExtra("header"));
        tv2.setText(getIntent().getStringExtra("desc"));
        tv3.setText(getIntent().getStringExtra("info"));


    }
}