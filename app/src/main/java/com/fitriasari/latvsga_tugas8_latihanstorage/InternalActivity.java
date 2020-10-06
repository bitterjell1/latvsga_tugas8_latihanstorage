package com.fitriasari.latvsga_tugas8_latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    private Button btnbuatfile,btnubahfile,btnbacafile,btnhapusfile;
    private TextView textbacafile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        btnbuatfile = findViewById(R.id.btnbuatfile);
        btnubahfile = findViewById(R.id.btnubahfile);
        btnbacafile = findViewById(R.id.btnbacafile);
        btnhapusfile = findViewById(R.id.btnhapusfile);
        textbacafile = findViewById(R.id.textbacafile);

        btnbuatfile.setOnClickListener(this);
        btnubahfile.setOnClickListener(this);
        btnbacafile.setOnClickListener(this);
        btnhapusfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        jalankan(v.getId());
    }

    void buatFile(){
        String isiFile = "ISI DATA FILE Text";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void ubahFile(){
        String isiFile="Penambahan konten dari ubah file";
        File file = new File(getFilesDir(),FILENAME);

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
    void bacaFile(){
        File sdcard = new File(getFilesDir(),FILENAME);
        File file = new File(sdcard,FILENAME);

        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();

                while (line != null){
                    textbacafile.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textbacafile.setText(textbacafile.toString());
        }else {
            textbacafile.setText(R.string.file_not_found);
        }
    }
    void hapusFile(){
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
            Toast.makeText(this,"File Was Delete!",Toast.LENGTH_LONG).show();
        }
    }
    private void jalankan(int id) {
        switch (id){
            case R.id.btnbuatfile: buatFile(); break;
            case R.id.btnubahfile: ubahFile(); break;
            case R.id.btnbacafile: bacaFile(); break;
            case R.id.btnhapusfile: hapusFile(); break;
        }
    }
}