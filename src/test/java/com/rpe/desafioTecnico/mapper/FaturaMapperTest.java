package com.rpe.desafioTecnico.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import com.rpe.desafioTecnico.enums.StatusFatura;
import com.rpe.desafioTecnico.model.dto.FaturaResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.model.entities.Fatura;

@SpringBootTest
public class FaturaMapperTest {

    private FaturaMapper faturaMapper = Mappers.getMapper(FaturaMapper.class);

    private Cliente cliente;
    private Fatura fatura;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");

        fatura = new Fatura();
        fatura.setId(10L);
        fatura.setValor(500f);
        fatura.setDataVencimento(LocalDate.now());
        fatura.setStatusFatura(StatusFatura.A);
        fatura.setCliente(cliente);
    }

    @Test
    void faturaToDtoDeveMapearComNomeDoCliente() {
        FaturaResponseDTO dto = faturaMapper.toDto(fatura);

        assertEquals("João", dto.getNomeCliente());
        assertEquals(500f, dto.getValor());
    }

    @Test
    void faturaResponseDtoToEntity() {
        FaturaResponseDTO dto = new FaturaResponseDTO();
        dto.setId(10L);
        dto.setValor(300f);
        dto.setStatusFatura(StatusFatura.P);
        dto.setDataVencimento(LocalDate.now());

        Fatura entidade = faturaMapper.toEntity(dto);

        assertEquals(300f, entidade.getValor());
        assertEquals(StatusFatura.P, entidade.getStatusFatura());
    }

}
