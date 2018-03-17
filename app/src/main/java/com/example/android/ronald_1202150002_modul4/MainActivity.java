package com.example.android.ronald_1202150002_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("AsyncTask");
        listMahasiswa = (ListView) findViewById(R.id.listMahasiswa);
    }



    public void start(View view) { new getData(listMahasiswa).execute() ;}

    class getData extends AsyncTask<String, Integer, String> {
    ListView listMahasiswa;
    ArrayAdapter adapter;
    ArrayList<String> listNama;
    ProgressDialog dialog;

    public getData(ListView listMahasiswa){
        this.listMahasiswa = listMahasiswa;
        dialog = new ProgressDialog(MainActivity.this);
        listNama = new ArrayList<>();
    }
        @Override
        protected String doInBackground(String... strings) {
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listNama); //membuat adapter

            //menyimpan array pada sebuah variabel
            String[] mhs = getResources().getStringArray(R.array.namaMhs);
            //perulangan untuk menyimpan array
            for (int a = 0; a < mhs.length; a++) {
                final long persen = 100L * a / mhs.length;
                final String nama = mhs[a];
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage((int) persen+"% - Adding "+nama);
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    listNama.add(mhs[a]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Loading Data");
            dialog.setIndeterminate(true);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(true);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });

            dialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listMahasiswa.setAdapter(adapter);
            dialog.dismiss();
        }
    }

}
