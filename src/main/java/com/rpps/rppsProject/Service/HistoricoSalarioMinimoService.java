package com.rpps.rppsProject.service;

import com.rpps.rppsProject.Model.HistoricoSalarioMinimo;
import com.rpps.rppsProject.Repository.HistoricoSalarioMinimoRepository;
import com.rpps.rppsProject.DTOS.HistoricoSalarioMinimoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricoSalarioMinimoService {

    @Autowired
    private HistoricoSalarioMinimoRepository repository;

    public Long cadastrarHistorico(HistoricoSalarioMinimoDTO dto) {
        HistoricoSalarioMinimo salario = new HistoricoSalarioMinimo();
        salario.setValor(dto.valorSalarioMinimo());
        salario.setDataInicioVigencia(dto.dataInicioVigencia());
        salario.setDataFimVigencia(dto.dataFinalVigencia());
        return repository.insert(salario);
    }

    public Long atualizarHistorico(HistoricoSalarioMinimoDTO dto) {
        HistoricoSalarioMinimo salario = new HistoricoSalarioMinimo();
        salario.setIdSalarioMinimo(dto.idSalarioMinimo());
        salario.setValor(dto.valorSalarioMinimo());
        salario.setDataInicioVigencia(dto.dataInicioVigencia());
        salario.setDataFimVigencia(dto.dataFinalVigencia());
        return repository.update(salario);
    }

    public void excluirHistorico(Long id) {
        repository.delete(id);
    }

    public HistoricoSalarioMinimoDTO buscarPorId(Long id) {
        Optional<HistoricoSalarioMinimo> historico = repository.findById(id);
        return historico.map(h -> new HistoricoSalarioMinimoDTO(
                h.getIdSalarioMinimo(),
                h.getValor(),
                h.getDataInicioVigencia(),
                h.getDataFimVigencia()
        )).orElseThrow(() -> new RuntimeException("Salário mínimo não encontrado"));
    }

    public List<HistoricoSalarioMinimoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(h -> new HistoricoSalarioMinimoDTO(
                        h.getIdSalarioMinimo(),
                        h.getValor(),
                        h.getDataInicioVigencia(),
                        h.getDataFimVigencia()
                ))
                .collect(Collectors.toList());
    }
}
