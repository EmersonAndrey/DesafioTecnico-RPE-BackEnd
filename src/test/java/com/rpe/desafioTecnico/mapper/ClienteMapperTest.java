package com.rpe.desafioTecnico.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import com.rpe.desafioTecnico.enums.StatusBloqueio;
import com.rpe.desafioTecnico.model.dto.ClienteRequestDTO;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;

@SpringBootTest
public class ClienteMapperTest {

    private ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setStatusBloqueio(StatusBloqueio.A);
        cliente.setLimiteCredito(1000f);
    }

    @Test
    void clienteToDtoDeveMapearCorretamente() {
        ClienteResponseDTO dto = clienteMapper.toDto(cliente);

        assertEquals("João", dto.getNome());
        assertEquals("12345678900", dto.getCpf());
    }

    @Test
    void clienteRequestDtoToEntityDeveMapearCorretamente() {
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setNome("Maria");
        request.setCpf("99988877766");

        Cliente entity = clienteMapper.toEntity(request);

        assertEquals("Maria", entity.getNome());
        assertEquals("99988877766", entity.getCpf());
    }

}
