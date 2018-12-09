package br.edu.unoesc.webmob.offtrail.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.Map;

import br.edu.unoesc.webmob.offtrail.R;

@EActivity(R.layout.activity_preferencias)
@Fullscreen
public class PreferenciasActivity extends AppCompatActivity {
    @ViewById
    EditText edtCor;

    private String PREFERENCIA = "preferencias";

    @AfterViews
    public void inicializar() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            sb.append(entry.getValue().toString() + "\n");
            edtCor.setText(sb.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

    }

    public void salvar(View v) {

        SharedPreferences preferences = getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cor", edtCor.getText().toString());
        editor.commit();

        Toast.makeText(this, "Preferencias salvas com sucesso! ", Toast.LENGTH_LONG).show();

        StringBuilder sb = new StringBuilder();

        finish();
    }
}
