package edu.csustan.budgetbuddy;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// written by: Natasha Garcia
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Natasha


public class CreditViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView t1,t2,t3;

    public CreditViewHolder(@NonNull View itemView) {
        super(itemView);
        img=(ImageView)itemView.findViewById(R.id.img1);
        t1=(TextView)itemView.findViewById(R.id.t1);
        t2=(TextView)itemView.findViewById(R.id.t2);
        t3=(TextView)itemView.findViewById(R.id.t3);
    }
}
