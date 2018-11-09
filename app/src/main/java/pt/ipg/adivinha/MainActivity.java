package pt.ipg.adivinha;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int numeroAdivinhar;
    private int tentativas;
    private int totalTentativas = 0; // de todos os Jogos
    private int jogos = 0;
    private int victorias = 0;
    private boolean jogar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        novoJogo();
        adivinha();
    }

    private void adivinha() {
        FloatingActionButton butaoAdivinhar = (FloatingActionButton) findViewById(R.id.butaoAdivinhar);
        final TextInputEditText textInputEditText = (TextInputEditText)findViewById(R.id.textInputEditTextGuess);
        butaoAdivinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int valor = Integer.parseInt(textInputEditText.getText().toString());

                    if(valor<1 || valor>10){
                        textInputEditText.setError(getString(R.string.ErroPalpite));
                        textInputEditText.requestFocus();
                        return;
                    }else{

                    }
                } catch (NumberFormatException e) {
                    textInputEditText.setError(getString(R.string.ErroPalpite));
                    textInputEditText.requestFocus();
                    return;
                }
            }
        });
    }

    private void novoJogo() {
        numeroAdivinhar = random.nextInt(10)+1;
        tentativas=0;
        jogos++;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
