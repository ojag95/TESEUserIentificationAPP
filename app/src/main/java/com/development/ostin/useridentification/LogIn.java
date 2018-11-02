package com.development.ostin.useridentification;

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
            usuarioActual= new UserData(txtMatricula.getEditText().getText().toString(),txtContrasenia.getEditText().getText().toString());

            Toast.makeText(this,usuarioActual.getMatricula()+" "+usuarioActual.getContrasenia(),Toast.LENGTH_LONG).show();


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
}
