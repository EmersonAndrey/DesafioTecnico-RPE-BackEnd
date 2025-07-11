package com.rpe.desafioTecnico.mapper;

import org.mapstruct.Mapper;

import com.rpe.desafioTecnico.model.dto.ClienteRequestDTO;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;

@Mapper(componentModel = "spring", uses = FaturaMapper.class)
public interface ClienteMapper {
    
    ClienteResponseDTO toDto(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO dto);
    
}
