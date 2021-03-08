package br.maua.agendadealunos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.maua.agendadealunos.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastro de Alunos");
        setContentView(R.layout.activity_formulario_aluno);
    }
}