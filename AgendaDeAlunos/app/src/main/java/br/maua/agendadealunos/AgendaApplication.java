package br.maua.agendadealunos;

import android.app.Application;
import br.maua.agendadealunos.dao.AlunoDAO;
import br.maua.agendadealunos.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunosDeTeste();
    }

    private void criarAlunosDeTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.salvar(new Aluno("Matheus", "123456789", "matheus@gmail.com"));
        alunoDAO.salvar(new Aluno("Alex", "987654321", "alex@gmail.com"));
    }
}
