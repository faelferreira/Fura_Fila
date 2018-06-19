package com.furafila.felipearaujo.projetofurafila.modelo;

public class Agendamento {

   private String nome;
   private String cpf;
   private String dt_agendamento;
   private String hr_agendamento;
   private String es_agendamento;
   private String email;
   private String telefone;
   private String celular;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDt_agendamento() {
        return dt_agendamento;
    }

    public void setDt_agendamento(String dt_agendamento) {
        this.dt_agendamento = dt_agendamento;
    }

    public String getHr_agendamento() {
        return hr_agendamento;
    }

    public void setHr_agendamento(String hr_agendamento) {
        this.hr_agendamento = hr_agendamento;
    }

    public String getEs_agendamento() {
        return es_agendamento;
    }

    public void setEs_agendamento(String es_agendamento) {
        this.es_agendamento = es_agendamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Agendamento(){

 }
}
