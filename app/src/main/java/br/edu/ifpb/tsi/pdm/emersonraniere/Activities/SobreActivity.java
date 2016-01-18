package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;

public class SobreActivity extends AppCompatActivity {
    private Button btn_obrigado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loading();
    }

    private void loading() {
        this.btn_obrigado = (Button) findViewById(R.id.btn_sobre_obrigado);
        this.btn_obrigado.setOnClickListener(new onClickBotao());
    }

    private class onClickBotao implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
