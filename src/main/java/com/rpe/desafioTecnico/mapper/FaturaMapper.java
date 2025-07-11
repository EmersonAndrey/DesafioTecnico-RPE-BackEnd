package com.rpe.desafioTecnico.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rpe.desafioTecnico.model.dto.FaturaResponseDTO;
import com.rpe.desafioTecnico.model.entities.Fatura;

@Mapper(componentModel = "spring")
public interface FaturaMapper {

    @Mapping(source = "cliente.nome", target = "nomeCliente")
    FaturaResponseDTO toDto(Fatura fatura);

    Fatura toEntity(FaturaResponseDTO dto);

}
