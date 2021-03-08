package br.maua.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.maua.agendadealunos.R;
import br.maua.agendadealunos.dao.AlunoDAO;
import br.maua.agendadealunos.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

        configuraFabNovoAluno();


    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFormularioModoInserirAluno();
            }
        });
    }

    private void abrirFormularioModoInserirAluno() {
        // De onde --> Para onde
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = alunoDAO.getAll();
        // this representa essa MainActivity
        configurarAdapter(listaDeAlunos);
        configurarListenerDeCliquePorItem(listaDeAlunos);
    }

    private void configurarListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                abrirFormularioModoEditarAluno(alunoEscolhido);
            }
        });
    }

    private void abrirFormularioModoEditarAluno(final Aluno aluno) {
        Intent irParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        // Transferência de Dados entre Activities
        irParaFormularioActivity.putExtra("aluno", aluno);
        startActivity(irParaFormularioActivity);
    }

    private void configurarAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                alunoDAO.getAll()));
    }
}
