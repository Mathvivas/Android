package br.maua.agendadealunos.model;

public class Aluno {
    private final String nome;
    private final String telefone;
    private final String email;

    public Aluno(String nome, String telefone, String email, String nome1, String telefone1, String email1) {
        this.nome = nome1;
        this.telefone = telefone1;
        this.email = email1;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
