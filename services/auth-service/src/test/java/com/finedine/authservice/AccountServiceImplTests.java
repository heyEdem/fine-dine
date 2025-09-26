package com.finedine.authservice;


import com.finedine.authservice.entity.Account;
import com.finedine.authservice.enums.AccountStatus;
import com.finedine.authservice.repository.AccountRepository;
import com.finedine.authservice.service.AccountServiceImpl;
import com.finedine.authservice.util.AccountMapper;
import com.finedine.authservice.util.Common;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AccountServiceImplTests {
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Common common;
    @Mock
    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountRepository, common, null);
    }

    @Test
    void testGetAccountById() {
        Account testAccount = createTestAccount();
        when(common.loadAccountById(1L)).thenReturn(Optional.of(testAccount).get());
        Account account = accountService.getAccount(1L);
        verify(accountRepository).findById(1L);
        assert(account.getId().equals(1L));
    }

    private Account createTestAccount() {
                            return Account.builder()
                                          .id(1L)
                                          .email("test@example.com")
                                          .accountStatus(AccountStatus.ACTIVE)
                                          .firstName("Test")
                                          .lastName("User")
                                          .build();
                        }

}
