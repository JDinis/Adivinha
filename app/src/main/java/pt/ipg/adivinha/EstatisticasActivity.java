package pt.ipg.adivinha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class EstatisticasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mostraInformacao();
    }

    private void mostraInformacao() {
        Intent intent = getIntent();

        ((TextView)findViewById(R.id.textViewJogados)).setText(""+intent.getIntExtra(Global.JOGOS,0));
        ((TextView)findViewById(R.id.textViewVictorias)).setText(""+ intent.getIntExtra(Global.VICTORIAS,0));
        ((TextView)findViewById(R.id.textViewMinTentativas)).setText(""+ intent.getIntExtra(Global.MIN_TENT, 0));
        ((TextView)findViewById(R.id.textViewMaxTentativas)).setText(""+ intent.getIntExtra(Global.MAX_TENT, 0));
        ((TextView)findViewById(R.id.textViewMedia)).setText(""+ intent.getDoubleExtra(Global.MEDIA, 0));
    }

}
