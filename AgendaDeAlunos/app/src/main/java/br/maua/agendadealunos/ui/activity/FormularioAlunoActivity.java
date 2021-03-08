package br.maua.agendadealunos.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.maua.agendadealunos.R;
import br.maua.agendadealunos.dao.AlunoDAO;
import br.maua.agendadealunos.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        // Ctrl B para verficar o método ou constante
        // Crtl Alt M para extrair
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if ( dados.hasExtra("aluno") ) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencherAluno();
                if ( aluno.validarId() ) {      // Se o id já existir
                    alunoDAO.editar(aluno);
                } else {
                    alunoDAO.salvar(aluno);
                }
                finish();
            }
        });
    }


    private void preencherAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }
}