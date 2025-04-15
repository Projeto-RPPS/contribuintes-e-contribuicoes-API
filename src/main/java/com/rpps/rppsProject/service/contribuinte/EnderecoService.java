package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Endereco;
import com.rpps.rppsProject.repository.contribuinte.EnderecoRepository;
import com.rpps.rppsProject.dto.contribuinte.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrarEndereco(EnderecoDTO dto) {
        Endereco endereco = EnderecoDTO.mapearParaEndereco(dto);
        enderecoRepository.insert(endereco);
    }

    public void atualizarEndereco(Endereco endereco) {
        enderecoRepository.update(endereco);
    }

    public void deletarEndereco(Long idEndereco) {
        enderecoRepository.delete(idEndereco);
    }

    public Endereco buscarPorId(Long idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }
}
