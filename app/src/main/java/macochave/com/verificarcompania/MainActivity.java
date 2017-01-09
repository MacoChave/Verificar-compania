package macochave.com.verificarcompania;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import compania_telefonica.Compania_Telefonica;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ver;
    private EditText numero;
    private TextView compania;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ver = (Button) findViewById(R.id.btnVer);
        numero = (EditText) findViewById(R.id.edtNumero);
        compania = (TextView) findViewById(R.id.txtCompania);
        imagen = (ImageView) findViewById(R.id.imgvCompania);

        ver.setOnClickListener(this);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help)
        {
            Toast.makeText(getApplicationContext(), "Contactar con el desarrollador", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about)
        {
            Toast.makeText(getApplicationContext(), "Verificar compania v.1.1 BETA", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_programer)
        {
            Toast.makeText(getApplicationContext(), "https://macochave.github.io/", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("https://macochave.github.io/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void obtenerCompania(int n)
    {
        Compania_Telefonica telefonica = new Compania_Telefonica();
        switch (telefonica.obtenerCompania(n))
        {
            case 'T':
                compania.setText(n + " es TIGO");
                imagen.setBackgroundResource(R.drawable.tigo);
                Toast.makeText(getApplicationContext(), "Reconocí el número :)", Toast.LENGTH_SHORT).show();
                break;
            case 'C':
                compania.setText(n + " es CLARO");
                imagen.setBackgroundResource(R.drawable.claro);
                Toast.makeText(getApplicationContext(), "Reconocí el número :)", Toast.LENGTH_SHORT).show();
                break;
            case 'M':
                compania.setText(n + " es MOVISTAR");
                imagen.setBackgroundResource(R.drawable.movistar);
                Toast.makeText(getApplicationContext(), "Reconocí el número :)", Toast.LENGTH_SHORT).show();
                break;
            default:
                compania.setText(n + " no se pudo reconocer el número");
                Toast.makeText(getApplicationContext(), "No he reconocido el número :(", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnVer:
                if (numero.getText().length() > 0 && numero.getText().toString().equals(" "))
                {
                    obtenerCompania(Integer.parseInt(numero.getText().toString()));
                    numero.setText("");
                } else
                {
                    Toast.makeText(getApplicationContext(), "Pon un número telefónico :(", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
