package com.rpps.rppsProject.Model;

public class Email {
    private Long idEmail;
    private Long idContribuinte;
    private String email;

    public Email() {
    }

    public Email(Long idEmail, Long idContribuinte, String email) {
        this.idEmail = idEmail;
        this.idContribuinte = idContribuinte;
        this.email = email;
    }

    public Long getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Long idEmail) {
        this.idEmail = idEmail;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
