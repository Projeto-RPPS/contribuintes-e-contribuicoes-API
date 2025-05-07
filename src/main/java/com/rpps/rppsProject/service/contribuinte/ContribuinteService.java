package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.ContribuinteDTO;
import com.rpps.rppsProject.dto.contribuinte.DadosCadastroDTO;
import com.rpps.rppsProject.dto.contribuinte.EmailDTO;
import com.rpps.rppsProject.dto.contribuinte.EnderecoDTO;
import com.rpps.rppsProject.dto.contribuinte.ParenteDTO;
import com.rpps.rppsProject.dto.contribuinte.TelefoneDTO;
import com.rpps.rppsProject.model.contribuinte.Contribuinte;
import com.rpps.rppsProject.model.contribuinte.Email;
import com.rpps.rppsProject.model.contribuinte.Endereco;
import com.rpps.rppsProject.model.contribuinte.Filiacao;
import com.rpps.rppsProject.model.contribuinte.Parente;
import com.rpps.rppsProject.model.contribuinte.Telefone;
import com.rpps.rppsProject.repository.contribuinte.ContribuinteRepository;
import com.rpps.rppsProject.repository.contribuinte.EmailRepository;
import com.rpps.rppsProject.repository.contribuinte.EnderecoRepository;
import com.rpps.rppsProject.repository.contribuinte.FiliacaoRepository;
import com.rpps.rppsProject.repository.contribuinte.ParenteRepository;
import com.rpps.rppsProject.repository.contribuinte.TelefoneRepository;
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

    public Contribuinte buscarContribuintePorId(Long id) {
        return contribuinteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribuinte não encontrado com ID: " + id));
    }

    public Contribuinte buscarContribuintePorCpf(String cpf) {
        return contribuinteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Contribuinte não encontrado com ID: " + cpf));
    }

    public List<Contribuinte> listarContribuintes() {
        return contribuinteRepository.findAll();
    }

    public void inativarContribuinte(Long id) {
        contribuinteRepository.delete(id);
    }

    public void inativarContribuintePorCpf(String cpf) {
        contribuinteRepository.deleteByCpf(cpf);
    }

}
