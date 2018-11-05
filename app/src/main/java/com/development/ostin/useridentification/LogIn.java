package com.development.ostin.useridentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    Button btnRegistro,btnIniciarSesion;
    View view;
    String matricula,contrasenia;
    TextInputLayout txtMatricula,txtContrasenia;
    Boolean errorStatus;
    static UserData usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnRegistro=(Button)findViewById(R.id.btnRegistrarse);
        btnRegistro.setOnClickListener(this);
        btnIniciarSesion=(Button)findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(this);
        txtMatricula=(TextInputLayout)findViewById(R.id.txtMatricula);
        txtContrasenia=(TextInputLayout)findViewById(R.id.txtContrasenia);
        view= this.getWindow().getDecorView().findViewById(android.R.id.content);


    }

    @Override
    public void onClick(View view) {

        errorStatus=validarDatos();
        if (!errorStatus)
        {

            formatoInformacion();


            //Intent intent =new Intent(LogIn.this,QRScanner.class);
            //startActivity(intent);

        }
    }

    public boolean validarDatos()
    {
        Boolean error =false;
        matricula=txtMatricula.getEditText().getText().toString();
        contrasenia=txtContrasenia.getEditText().getText().toString();
        //Usuario
        if (matricula.equals("")||matricula.equals(null))
        {
            txtMatricula.setError("El campo no puede estar vacio");
            error=true;
        }
        else
        {
            txtMatricula.setError(null);
        }
        //Contraseña1
        if (contrasenia.equals("")||contrasenia.equals(null))
        {
            txtContrasenia.setError("El campo no puede estar vacio");
            error=true;
        }
        else{
            txtContrasenia.setError(null);
        }
        return error;

    }

    public void formatoInformacion() {
        JSONObject json = new JSONObject();
        try {
            json.put("matricula", matricula);
            json.put("contrasenia", contrasenia);
        } catch (JSONException e) {
            Snackbar.make(view, "Error al crear la estructura de datos "+ e, Snackbar.LENGTH_LONG).show();
        }
        EnvioJSON envio = new EnvioJSON(getApplicationContext(),StaticData.serverIP+"login.php", json, view);


        envio.enviar(new EnvioJSON.VolleyCallback(){
            @Override
            public void onSuccess(String result) {

                try {
                    JSONArray jsonarray = new JSONArray(result);
                    JSONObject jsonRespuesta= jsonarray.getJSONObject(0);
                    String respuesta=jsonRespuesta.getString("Mensaje");
                    if (respuesta.contains("El usuario existe"))
                    {
                        txtContrasenia.getEditText().setText("");
                        txtMatricula.getEditText().setText("");

                        Intent intent =new Intent(LogIn.this,QRScanner.class);
                        //intent.putExtra("Datos",result);
                        jsonRespuesta= jsonarray.getJSONObject(0);
                        usuarioActual= new UserData(jsonRespuesta.getString("matricula"),jsonRespuesta.getString("nombre"),jsonRespuesta.getString("apellidoP"),jsonRespuesta.getString("apellidoM"));
                        startActivity(intent);
                        Toast.makeText(LogIn.this,usuarioActual.matricula,Toast.LENGTH_LONG).show();


                    }
                    else {
                        Snackbar.make(view, respuesta, Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }


            }




        });
    }

}
