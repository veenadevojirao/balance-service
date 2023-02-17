package com.maveric.balanceservice.mapper;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    Balance dtoToEntity(BalanceDto balanceDto);

    BalanceDto entityToDto(Balance balance);

    List<Balance> dtoToEntity (List<BalanceDto> accounts);

    List<BalanceDto> entityToDto (List<Balance> accounts);
}
