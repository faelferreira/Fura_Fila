package com.furafila.felipearaujo.projetofurafila.modelo;

public class Pessoa {

    private String uid;
    private String nome;
    private String SobreNome;
    private String email;
    private String pass;
    private String pass2;

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    private String cpf;

    public String getPass() {
        return pass;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Pessoa(){

    }

    public String getUid() {
        return uid;
    }

    public String getSobreNome() {
        return SobreNome;
    }

    public void setSobreNome(String sobreNome) {
        SobreNome = sobreNome;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome;
    }
}
