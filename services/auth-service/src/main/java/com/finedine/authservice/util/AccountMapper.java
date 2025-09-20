package com.finedine.authservice.util;

import com.finedine.authservice.dto.AccountDetails;
import com.finedine.authservice.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDetails toDto(Account account);
}
