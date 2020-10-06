package com.fitriasari.latvsga_tugas8_latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class EksternalActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAMEEKS = "namafile.txt";
    private Button btnbuatfileeks,btnubahfileeks,btnbacafileeks,btnhapusfileeks;
    private TextView textbacafileeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eksternal);

        btnbuatfileeks = findViewById(R.id.btnbuatfileeks);
        btnubahfileeks = findViewById(R.id.btnubahfileeks);
        btnbacafileeks = findViewById(R.id.btnbacafileeks);
        btnhapusfileeks = findViewById(R.id.btnhapusfileeks);
        textbacafileeks = findViewById(R.id.textbacafileeks);


        btnbuatfileeks.setOnClickListener(this);
        btnubahfileeks.setOnClickListener(this);
        btnbacafileeks.setOnClickListener(this);
        btnhapusfileeks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //method yang dijalankan
        jalankaneksternalstorage(v.getId());
    }

    //yang dilakukan
    void buatfileeks(){
        String cashback = "ISI FILE EKSTERNAL ";
        String state = Environment.getExternalStorageState();
        //external storage availability check
        if (!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }
        //buat file yang disimpan pada eksternal direktori
        File file = new File(this.getExternalFilesDir(null),FILENAMEEKS);
        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            //argumen kedua konstruktor fileoutputstream menunjukan apakah akan menambhakan data
            //atau membuat file baru jika sudah ada
            outputStream = new FileOutputStream(file,true);
            outputStream.write(cashback.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void ubahfileeks(){
        String isiFile="Penambahan konten dari ubah file pada eksternal storage";
        File file = new File(this.getExternalFilesDir(null),FILENAMEEKS);

        FileOutputStream outstream = null;
        try {
            if(file.exists()){
                outstream = new FileOutputStream(file,true);
                outstream.write(isiFile.getBytes());
                outstream.flush();
                outstream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    void bacafileeks(){
        File sdcard2 = new File(Environment.getExternalStorageState());
        File file = new File(sdcard2,FILENAMEEKS);

        if (file.exists()){
            StringBuilder textbacafile = new StringBuilder();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();

                while (line != null) {
                    textbacafile.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textbacafileeks.setText(textbacafile.toString());
        }else {
            textbacafileeks.setText(R.string.filenotfound);
        }
    }
    void hapusfileeks(){
        File file = new File(Environment.getExternalStorageState(),FILENAMEEKS);
        if (file.exists()){
            file.delete();
        }
    }

    private void jalankaneksternalstorage(int id) {
        switch (id){
            case R.id.btnbuatfileeks: buatfileeks(); break;
            case R.id.btnubahfileeks: ubahfileeks(); break;
            case R.id.btnbacafileeks: bacafileeks(); break;
            case R.id.btnhapusfileeks: hapusfileeks(); break;
        }
    }
}