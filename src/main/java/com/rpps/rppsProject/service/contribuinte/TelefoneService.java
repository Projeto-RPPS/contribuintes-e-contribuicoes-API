package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Telefone;
import com.rpps.rppsProject.repository.contribuinte.TelefoneRepository;
import com.rpps.rppsProject.dto.contribuinte.TelefoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public void cadastrarTelefone(TelefoneDTO dto) {
        Telefone telefone = TelefoneDTO.mapearParaTelefone(dto);
        telefoneRepository.insert(telefone);
    }

    public void atualizarTelefone(TelefoneDTO dto) {
        Telefone telefone = TelefoneDTO.mapearParaTelefone(dto);
        telefoneRepository.update(telefone);
    }

    public void deletarTelefone(Long idTelefone) {
        telefoneRepository.delete(idTelefone);
    }

    public TelefoneDTO buscarPorId(Long idTelefone) {
        return telefoneRepository.findById(idTelefone)
                .map(telefone -> new TelefoneDTO(
                telefone.getIdTelefone(),
                telefone.getIdContribuinte(),
                telefone.getNumero(),
                telefone.getTipoTelefone()))
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
    }

    public List<TelefoneDTO> listarTodos() {
        return telefoneRepository.findAll()
                .stream()
                .map(telefone -> new TelefoneDTO(
                        telefone.getIdTelefone(),
                        telefone.getIdContribuinte(),
                        telefone.getNumero(),
                        telefone.getTipoTelefone()))
                .toList();
    }

}
