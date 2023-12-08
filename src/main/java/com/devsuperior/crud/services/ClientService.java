package com.devsuperior.crud.services;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;
import com.devsuperior.crud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> page = clientRepository.findAll(pageable);
        return page.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O cliente não foi encontrado"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        copyDtoToEntity(client, clientDTO);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.getReferenceById(id);
        copyDtoToEntity(client, clientDTO);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    private void copyDtoToEntity(Client client, ClientDTO clientDTO) {
        client.setName(clientDTO.name());
        client.setCpf(clientDTO.cpf());
        client.setIncome(clientDTO.income());
        client.setBirthDate(clientDTO.birthDate());
        client.setChildren(clientDTO.children());
    }
}
