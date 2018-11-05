package com.development.ostin.useridentification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static com.development.ostin.useridentification.LogIn.usuarioActual;


public class QRScanner extends AppCompatActivity {
    public String jsonData;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    TextView lblNumeroPc,lblSala,lblIp,lblAlumno,lblMatricula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lblNumeroPc =(TextView)findViewById(R.id.lblNumeroPC);
        lblSala=(TextView)findViewById(R.id.lblSala);
        lblIp=(TextView)findViewById(R.id.lblIp);
        lblAlumno=(TextView)findViewById(R.id.lblAlumno);
        lblMatricula=(TextView)findViewById(R.id.lblMatricula);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QRScanner.this, QrCodeActivity.class);
                startActivityForResult(i, REQUEST_CODE_QR_SCAN);
            }
        });
        lblAlumno.setText("Nombre: "+usuarioActual.nombre+" "+usuarioActual.apellidoP+" "+usuarioActual.apellidoM);
        lblMatricula.setText("Matricula: "+usuarioActual.matricula);
        Intent i = new Intent(QRScanner.this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);
    }

    public void onClick(View v) {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
                String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
                if (resultado != null) {
                    Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (requestCode == REQUEST_CODE_QR_SCAN) {
                if (data != null) {
                    jsonData = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                    Gson gson = new GsonBuilder().create();
                    PC pc =gson.fromJson(jsonData,PC.class);
                    lblNumeroPc.setText("Equipo numero: "+pc.computernumber);
                    lblSala.setText("Sala numero: "+pc.sala);
                    lblIp.setText("Dirección IP: "+pc.ipaddress);
                }
                else{
                    lblNumeroPc.setText("Equipo numero: Sin datos");
                    lblSala.setText("Sala numero: Sin datos");
                    lblIp.setText("Dirección IP: Sin datos");
                }
            }
        }catch (Exception E)
        {
            Toast.makeText(getApplicationContext(), "El usuario ha cncelado la operación", Toast.LENGTH_SHORT).show();
            lblNumeroPc.setText("Equipo numero: Sin datos");
            lblSala.setText("Sala numero: Sin datos");
            lblIp.setText("Dirección IP: Sin datos");
        }
    }


}
