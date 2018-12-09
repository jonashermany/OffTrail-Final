package br.edu.unoesc.webmob.offtrail.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.edu.unoesc.webmob.offtrail.R;
import br.edu.unoesc.webmob.offtrail.helper.DatabaseHelper;
import br.edu.unoesc.webmob.offtrail.model.Trilheiro;

@EViewGroup(R.layout.lista_trilheiros)
public class TrilheiroItemView extends LinearLayout {

    @ViewById
    TextView txtNome;

    @ViewById
    TextView txtMoto;

    @ViewById
    ImageView imvFoto;

    // variável global
    Trilheiro trilheiro;

    @Bean
    DatabaseHelper dh;

    public TrilheiroItemView(Context context) {
        super(context);
    }

    @Click(R.id.imvEditar)
    public void editar(View view) {
        // criar uma intent para chamar a tela de cadastro
        // nesta intent passar o objeto Trilheiro
        Intent intent = new Intent(
                view.getContext(), CadastroTrilheiroActivity_.class
        );

        intent.putExtra("trilheiro", trilheiro);
        view.getContext().startActivity(intent);
    }

    @Click(R.id.imvExcluir)
    public void excluir() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
        dialogo.setTitle("Exclusão");
        dialogo.setMessage("Deseja realmente excluir o trilheiro " + trilheiro.getNome() + "?");
        dialogo.setCancelable(false);
        dialogo.setNegativeButton("Não", null);
        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dh.deleteTrilheiro(trilheiro)){
                    Toast.makeText(getContext(), "Trilheiro excluído com sucesso!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getContext(), "Não foi possível excluir o trilheiro! ",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialogo.show();
    }

    public void bind(Trilheiro t) {
        trilheiro = t;
        txtNome.setText(t.getNome());
        txtMoto.setText(t.getMoto().getModelo() + " - " + t.getMoto().getCilindradas());
        imvFoto.setImageBitmap(BitmapFactory.decodeByteArray(t.getFoto(), 0, t.getFoto().length));
    }
}