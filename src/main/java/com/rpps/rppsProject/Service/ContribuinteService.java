package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.*;
import com.rpps.rppsProject.Repository.*;
import com.rpps.rppsProject.DTOS.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContribuinteService {
    @Autowired
    ContribuinteRepository contribuinteRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    ParenteRepository parenteRepository;
    @Autowired
    FiliacaoRepository filiacaoRepository;

    @Transactional
    public void cadastrarContribuinte(DadosCadastroDTO dadosCadastroDTO) {
        Contribuinte contribuinte = ContribuinteDTO.mapearParaContribuinte(dadosCadastroDTO.contribuinte());
        Long idContribuinte = contribuinteRepository.insert(contribuinte);

        List<Email> listaEmail = dadosCadastroDTO.listaEmails().stream()
                .map(emailDTO -> EmailDTO.mapearParaEmail(emailDTO))
                .toList();
        listaEmail.forEach(email -> email.setIdContribuinte(idContribuinte));
        listaEmail.forEach(email -> emailRepository.insert(email));

        List<Endereco> listaEndereco = dadosCadastroDTO.listaEnderecos().stream()
                .map(enderecoDTO -> EnderecoDTO.mapearParaEndereco(enderecoDTO))
                .toList();
        listaEndereco.forEach(endereco -> endereco.setIdContribuinte(idContribuinte));
        listaEndereco.forEach(endereco -> enderecoRepository.insert(endereco));

        List<Telefone> listaTelefone = dadosCadastroDTO.listaTelefones().stream()
                .map(telefoneDTO -> TelefoneDTO.mapearParaTelefone(telefoneDTO)).toList();
        listaTelefone.forEach(telefone -> telefone.setIdContribuinte(idContribuinte));
        listaTelefone.forEach(telefone -> telefoneRepository.insert(telefone));

        dadosCadastroDTO.listaParentes().forEach(parenteTipo -> {
            Parente parente = ParenteDTO.mapearParaParente(parenteTipo.parenteDTO());

            Filiacao filiacao = new Filiacao();
            filiacao.setIdParente(parenteRepository.insert(parente));
            filiacao.setIdContribuinte(idContribuinte);
            filiacao.setIdTipoParentesco(parenteTipo.idTipoParentesco());

            filiacaoRepository.insert(filiacao);
        });
    }

    public ContribuinteDTO buscarContribuintePorId(Long id) {
        return contribuinteRepository.findById(id)
                .map(contribuinte -> new ContribuinteDTO(
                        contribuinte.getIdContribuinte(),
                        contribuinte.getNomeCivil(), contribuinte.getNomeSocial(),
                        contribuinte.getCpf(), contribuinte.getIsAtivo(), contribuinte.getIdCategoria()))
                .orElseThrow(() -> new RuntimeException("Contribuinte não encontrado com ID: " + id));
    }

    public List<ContribuinteDTO> listarContribuintes() {
        return contribuinteRepository.findAll().stream()
                .map(contribuinte -> new ContribuinteDTO(
                        contribuinte.getIdContribuinte(),
                        contribuinte.getNomeCivil(), contribuinte.getNomeSocial(),
                        contribuinte.getCpf(), contribuinte.getIsAtivo(), contribuinte.getIdCategoria())).toList();
    }

    public void inativarContribuinte(Long id) {
        contribuinteRepository.delete(id);
    }

}
