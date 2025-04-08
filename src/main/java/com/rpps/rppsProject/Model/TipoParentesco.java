package com.rpps.rppsProject.Model;

public class TipoParentesco {
    private Long idTipoParentesco;
    private String descricaoParentesco;

    public TipoParentesco() {
    }

    public TipoParentesco(Long idTipoParentesco, String descricaoParentesco) {
        this.idTipoParentesco = idTipoParentesco;
        this.descricaoParentesco = descricaoParentesco;
    }

    public Long getIdTipoParentesco() {
        return idTipoParentesco;
    }

    public void setIdTipoParentesco(Long idTipoParentesco) {
        this.idTipoParentesco = idTipoParentesco;
    }

    public String getDescricaoParentesco() {
        return descricaoParentesco;
    }

    public void setDescricaoParentesco(String descricaoParentesco) {
        this.descricaoParentesco = descricaoParentesco;
    }
}
