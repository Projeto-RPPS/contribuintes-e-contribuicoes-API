package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.Filiacao;
import com.rpps.rppsProject.Repository.FiliacaoRepository;
import com.rpps.rppsProject.DTOS.DadosGenealogicosDTO;
import com.rpps.rppsProject.DTOS.FiliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliacaoService {

    @Autowired
    private FiliacaoRepository filiacaoRepository;

    public Long cadastrarFiliacao(FiliacaoDTO dto) {
        Filiacao filiacao = new Filiacao();
        filiacao.setIdContribuinte(dto.idContribuinte());
        filiacao.setIdTipoParentesco(dto.idTipoParentesco());
        filiacao.setIdParente(dto.idParente());

        return filiacaoRepository.insert(filiacao);
    }

    public Long atualizarFiliacao(FiliacaoDTO dto) {
        Filiacao filiacao = new Filiacao();
        filiacao.setIdFiliacao(dto.idFiliacao());
        filiacao.setIdContribuinte(dto.idContribuinte());
        filiacao.setIdTipoParentesco(dto.idTipoParentesco());
        filiacao.setIdParente(dto.idParente());

        return filiacaoRepository.update(filiacao);
    }

    public void deletarFiliacao(Long idFiliacao) {
        filiacaoRepository.delete(idFiliacao);
    }

    public FiliacaoDTO buscarPorId(Long idFiliacao) {
        return filiacaoRepository.findById(idFiliacao)
                .map(filiacao -> new FiliacaoDTO(filiacao.getIdFiliacao(), filiacao.getIdContribuinte()
                ,filiacao.getIdTipoParentesco(), filiacao.getIdParente()))
                .orElseThrow(() -> new RuntimeException("Filiação não encontrada com ID: " + idFiliacao));
    }

    public List<FiliacaoDTO> listarTodas() {
        return filiacaoRepository.findAll().stream().map(filiacao -> new FiliacaoDTO(filiacao.getIdFiliacao(), filiacao.getIdContribuinte()
                ,filiacao.getIdTipoParentesco(), filiacao.getIdParente())).toList();
    }

    public List<DadosGenealogicosDTO> obterArvoreGenealogica(Long idContribuinte) {
        return filiacaoRepository.obterDadosArvoreGenealogica(idContribuinte);
    }
}
