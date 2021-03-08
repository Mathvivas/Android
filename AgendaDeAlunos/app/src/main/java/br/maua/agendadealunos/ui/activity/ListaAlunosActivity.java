package br.maua.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.maua.agendadealunos.R;
import br.maua.agendadealunos.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO alunoDAO = new AlunoDAO();

        setTitle("Lista de Alunos");
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        // this representa essa MainActivity
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                alunoDAO.getAll()));
    }
}
