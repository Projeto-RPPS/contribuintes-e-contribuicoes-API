package com.rpps.rppsProject.service.contribuicao;

import com.rpps.rppsProject.model.contribuicao.HistoricoSalarioMinimo;
import com.rpps.rppsProject.repository.contribuicao.HistoricoSalarioMinimoRepository;
import com.rpps.rppsProject.dto.contribuicao.HistoricoSalarioMinimoDTO;
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

    public Long atualizarHistorico(HistoricoSalarioMinimo historicoSalarioMinimo) {
        return repository.update(historicoSalarioMinimo);
    }

    public void excluirHistorico(Long id) {
        repository.delete(id);
    }

    public HistoricoSalarioMinimo buscarPorId(Long id) {
        Optional<HistoricoSalarioMinimo> historico = repository.findById(id);
        return historico.orElseThrow(() -> new RuntimeException("Salário mínimo não encontrado"));
    }

    public List<HistoricoSalarioMinimo> listarTodos() {
        return repository.findAll();
    }
}
