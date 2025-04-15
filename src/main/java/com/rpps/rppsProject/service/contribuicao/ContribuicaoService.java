package com.rpps.rppsProject.service.contribuicao;

import com.rpps.rppsProject.model.contribuicao.Contribuicao;
import com.rpps.rppsProject.repository.contribuicao.ContribuicaoRepository;
import com.rpps.rppsProject.dto.contribuicao.ContribuicaoDTO;
import com.rpps.rppsProject.dto.contribuicao.DadosFinanceirosDTO;
import com.rpps.rppsProject.repository.contribuinte.ContribuinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContribuicaoService {
    @Autowired
    ContribuicaoRepository contribuicaoRepository;
    @Autowired
    ContribuinteRepository contribuinteRepository;

    private BigDecimal calcularValorContribuicao(BigDecimal percentualSalario, BigDecimal valorSalario){
        return valorSalario.multiply(percentualSalario);
    }

    @Transactional
    public void fazerContribuicao(ContribuicaoDTO contribuicaoDTO) {

        Boolean jaExiste = contribuicaoRepository.existeContribuicaoNoMesmoMes(contribuicaoDTO.idContribuinte(), contribuicaoDTO.dataReferencia());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe uma contribuição para este contribuinte neste mês.");
        } else {
            if (contribuinteRepository.isAtivo(contribuicaoDTO.idContribuinte())) {
                Contribuicao contribuicao = new Contribuicao();

                DadosFinanceirosDTO dados = contribuicaoRepository.buscarPercentualESalario(contribuicaoDTO.dataReferencia(), contribuicaoDTO.idContribuinte());
                BigDecimal valorContribuicao = calcularValorContribuicao(dados.percentualContribuicao(), dados.valorSalario());

                contribuicao.setIdContribuinte(contribuicaoDTO.idContribuinte());
                contribuicao.setDataReferencia(contribuicaoDTO.dataReferencia());
                contribuicao.setIdSalarioMinimo(dados.idSalario());
                contribuicao.setValorContribuicao(valorContribuicao);

                contribuicaoRepository.insert(contribuicao);
            } else {
                throw new IllegalArgumentException("Contribuinte não está ativo, portanto não pode fazer contribuição");
            }
        }
    }

    public void deletarContribuicao(Long idContribuicao) {
        contribuicaoRepository.delete(idContribuicao);
    }

    public Contribuicao buscarPorId(Long idContribuicao) {
        Contribuicao contribuicao = contribuicaoRepository.findById(idContribuicao)
                .orElseThrow(() -> new IllegalArgumentException("Contribuição não encontrada para o ID: " + idContribuicao));

        return contribuicao;
    }


    public List<Contribuicao> listarContribuicoes() {

        return contribuicaoRepository.findAll();

    }

    public List<Contribuicao> listarContribuicoesPorCPF(String cpfContribuinte) {
        List<Contribuicao> listaContribuicoes = contribuicaoRepository.findAllContribuicaoPorCPF(cpfContribuinte);
        if (listaContribuicoes.isEmpty()){
            throw new IllegalArgumentException("Não encontrado contribuições feitas com esse CPF");
        }
        return listaContribuicoes;
    }
}
