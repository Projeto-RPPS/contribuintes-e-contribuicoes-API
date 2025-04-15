package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Filiacao;
import com.rpps.rppsProject.repository.contribuinte.FiliacaoRepository;
import com.rpps.rppsProject.dto.contribuinte.DadosGenealogicosDTO;
import com.rpps.rppsProject.dto.contribuinte.FiliacaoDTO;
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

    public Long atualizarFiliacao(Filiacao filiacao) {
        return filiacaoRepository.update(filiacao);
    }

    public void deletarFiliacao(Long idFiliacao) {
        filiacaoRepository.delete(idFiliacao);
    }

    public Filiacao buscarPorId(Long idFiliacao) {
        return filiacaoRepository.findById(idFiliacao)
                .orElseThrow(() -> new RuntimeException("Filiação não encontrada com ID: " + idFiliacao));
    }

    public List<Filiacao> listarTodas() {
        return filiacaoRepository.findAll();
    }

    public List<DadosGenealogicosDTO> obterArvoreGenealogica(Long idContribuinte) {
        return filiacaoRepository.obterDadosArvoreGenealogica(idContribuinte);
    }
}
