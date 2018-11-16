package pt.ipg.adivinha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private int minTentativas = 10;
    private int maxTentativas = 0;
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
        final Activity thisActivity = this;

        butaoAdivinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int valor = Integer.parseInt(textInputEditText.getText().toString());

                    if(valor<1 || valor>10){
                        textInputEditText.setError(getString(R.string.ErroPalpite));
                        textInputEditText.requestFocus();
                        return;
                    }
                    Helpers.EscondeTeclado(thisActivity);

                    String mensagem = "";

                    if(valor == numeroAdivinhar){
                        acertou();
                        return;
                    }else if(valor < numeroAdivinhar){
                        mensagem = getString(R.string.numero_maior);
                    }else{ // Valor > numeroAdivinhar
                        mensagem = getString(R.string.numero_menor);
                    }

                    mensagem += valor + getString(R.string.tente_novamente);

                    Snackbar.make(v, mensagem, Snackbar.LENGTH_INDEFINITE)
                            .setAction( getString(R.string.snackBar_advinha), null).show();

                } catch (NumberFormatException e) {
                    textInputEditText.setError(getString(R.string.ErroPalpite));
                    textInputEditText.requestFocus();
                    return;
                }
            }
        });
    }


    private void acertou() {
        //todo: perguntar ao utilizador se quer voltar a jogar
        totalTentativas+=tentativas;
        victorias++;
        jogar = false;

        if(tentativas < minTentativas){
            minTentativas = tentativas;
        }

        if(tentativas > maxTentativas){
            maxTentativas = tentativas;
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle(getString(R.string.parabens));
        dialogBuilder.setTitle(getString(R.string.novo_jogo));
        dialogBuilder.setMessage(getString(R.string.jogar_novamente));
        dialogBuilder.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                novoJogo();
            }
        });

        dialogBuilder.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // sair da app
            }
        });

        dialogBuilder.show();
    }

    private void novoJogo() {
        if(jogar == true){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(getString(R.string.novo_jogo));
            dialogBuilder.setMessage(getString(R.string.Certeza));
            dialogBuilder.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    iniciaNovoJogo();
                }
            });

            dialogBuilder.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish(); // sair da app
                }
            });

            dialogBuilder.show();
        }else{
            iniciaNovoJogo();
        }

    }

    private void iniciaNovoJogo() {
        numeroAdivinhar = random.nextInt(10)+1;
        tentativas=0;
        jogos++;
        jogar=true;
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
