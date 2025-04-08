package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.Contribuicao;
import com.rpps.rppsProject.Repository.ContribuicaoRepository;
import com.rpps.rppsProject.DTOS.ContribuicaoDTO;
import com.rpps.rppsProject.DTOS.DadosFinanceirosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContribuicaoService {
    @Autowired
    ContribuicaoRepository contribuicaoRepository;

    private BigDecimal calcularValorContribuicao(BigDecimal percentualSalario, BigDecimal valorSalario){
        return valorSalario.multiply(percentualSalario);
    }

    @Transactional
    public void fazerContribuicao(ContribuicaoDTO contribuicaoDTO) {

        Boolean jaExiste = contribuicaoRepository.existeContribuicaoNoMesmoMes(contribuicaoDTO.idContribuinte(), contribuicaoDTO.dataReferencia());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe uma contribuição para este contribuinte neste mês.");
        } else {
            Contribuicao contribuicao = new Contribuicao();

            DadosFinanceirosDTO dados = contribuicaoRepository.buscarPercentualESalario(contribuicaoDTO.dataReferencia(), contribuicaoDTO.idContribuinte());
            BigDecimal valorContribuicao = calcularValorContribuicao(dados.percentualContribuicao(), dados.valorSalario());

            contribuicao.setIdContribuinte(contribuicaoDTO.idContribuinte());
            contribuicao.setDataReferente(contribuicaoDTO.dataReferencia());
            contribuicao.setIdSalarioMinimo(dados.idSalario());
            contribuicao.setValorContribuicao(valorContribuicao);

            contribuicaoRepository.insert(contribuicao);
        }
    }

    public void deletarContribuicao(Long idContribuicao) {
        contribuicaoRepository.delete(idContribuicao);
    }

    public ContribuicaoDTO buscarPorId(Long idContribuicao) {
        Contribuicao contribuicao = contribuicaoRepository.findById(idContribuicao)
                .orElseThrow(() -> new IllegalArgumentException("Contribuição não encontrada para o ID: " + idContribuicao));

        return new ContribuicaoDTO(contribuicao.getIdContribuicao(), contribuicao.getIdContribuinte(),
                contribuicao.getDataContribuicao(), contribuicao.getValorContribuicao(),
                contribuicao.getDataReferente(), contribuicao.getIdSalarioMinimo());
    }


    public List<ContribuicaoDTO> listarContribuicoes() {

        return contribuicaoRepository.findAll().stream()
                .map(contribuicao -> new ContribuicaoDTO(contribuicao.getIdContribuicao(), contribuicao.getIdContribuinte(),
                        contribuicao.getDataContribuicao(), contribuicao.getValorContribuicao(),
                        contribuicao.getDataReferente(), contribuicao.getIdSalarioMinimo())).toList();

    }

    public List<ContribuicaoDTO> listarContribuicoesPorCPF(String cpfContribuinte) {
        return contribuicaoRepository.findAllContribuicaoPorCPF(cpfContribuinte).stream()
                .map(contribuicao -> new ContribuicaoDTO(contribuicao.getIdContribuicao(), contribuicao.getIdContribuinte(),
                        contribuicao.getDataContribuicao(), contribuicao.getValorContribuicao(),
                        contribuicao.getDataReferente(), contribuicao.getIdSalarioMinimo())).toList();
    }
}
