package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.Telefone;
import com.rpps.rppsProject.Repository.TelefoneRepository;
import com.rpps.rppsProject.DTOS.TelefoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
