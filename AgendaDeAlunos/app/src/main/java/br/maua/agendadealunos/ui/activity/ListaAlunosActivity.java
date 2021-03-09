package br.maua.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.maua.agendadealunos.R;
import br.maua.agendadealunos.dao.AlunoDAO;
import br.maua.agendadealunos.model.Aluno;
import br.maua.agendadealunos.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static br.maua.agendadealunos.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        CharSequence tituloDoMenu = item.getTitle();
        if ( itemId == R.id.activity_lista_alunos_menu_remover ) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remover(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
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
        atualizarAlunos();
    }

    private void atualizarAlunos() {
        adapter.clear();
        adapter.addAll(alunoDAO.getAll());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        // this representa essa MainActivity
        configurarAdapter(listaDeAlunos);
        configurarListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void remover(final Aluno aluno) {
        alunoDAO.remover(aluno);
        adapter.remove(aluno);
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
        irParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(irParaFormularioActivity);
    }

    private void configurarAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
