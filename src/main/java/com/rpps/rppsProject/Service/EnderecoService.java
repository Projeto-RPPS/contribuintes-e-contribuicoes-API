package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.Endereco;
import com.rpps.rppsProject.Repository.EnderecoRepository;
import com.rpps.rppsProject.DTOS.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrarEndereco(EnderecoDTO dto) {
        Endereco endereco = EnderecoDTO.mapearParaEndereco(dto);
        enderecoRepository.insert(endereco);
    }

    public void atualizarEndereco(EnderecoDTO dto) {
        Endereco endereco = EnderecoDTO.mapearParaEndereco(dto);
        enderecoRepository.update(endereco);
    }

    public void deletarEndereco(Long idEndereco) {
        enderecoRepository.delete(idEndereco);
    }

    public EnderecoDTO buscarPorId(Long idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .map(endereco -> new EnderecoDTO(
                        endereco.getIdEndereco(),
                        endereco.getIdContribuinte(),
                        endereco.getCep(),
                        endereco.getNumero(),
                        endereco.getEstado()
                )).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public List<EnderecoDTO> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(endereco -> new EnderecoDTO(
                        endereco.getIdEndereco(),
                        endereco.getIdContribuinte(),
                        endereco.getCep(),
                        endereco.getNumero(),
                        endereco.getEstado()
                ))
                .toList();
    }
}
