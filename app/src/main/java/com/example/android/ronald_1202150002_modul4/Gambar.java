package com.example.android.ronald_1202150002_modul4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Gambar extends AppCompatActivity {

    ImageView gambar;
    EditText sumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);
        setTitle("AsyncTask"); //set title pada tampilan

        //memanggil variabel yang ada pada layout
        gambar = (ImageView)findViewById(R.id.picture);
        sumber = (EditText)findViewById(R.id.link);
    }

    public void cari(View view) {
        Picasso.with(Gambar.this).load(sumber.getText().toString())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(gambar);
    }
}
