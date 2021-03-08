package br.maua.agendadealunos.dao;

import br.maua.agendadealunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for ( Aluno alu : alunos ) {
            if ( alu.getId() == aluno.getId() ) {
                alunoEncontrado = alu;
            }
        }
        if ( alunoEncontrado != null ) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);

        }
    }

    public List<Aluno> getAll() {
        return new ArrayList<>(alunos);
    }
}
