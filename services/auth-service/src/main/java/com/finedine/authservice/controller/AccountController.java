package com.finedine.authservice.controller;

import com.finedine.authservice.dto.AccountDetails;
import com.finedine.authservice.dto.GenericMessageResponse;
import com.finedine.authservice.dto.VerifiedUsers;
import com.finedine.authservice.entity.Account;
import com.finedine.authservice.security.SecurityUser;
import com.finedine.authservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService authService;

    @GetMapping("/account/{id}")
    public Account viewAccount(@PathVariable("id") Long id){
        return authService.getAccount(id);
    }

    @GetMapping("/me")
    public AccountDetails myAccount(@AuthenticationPrincipal SecurityUser securityUser){
        return authService.myAccount(securityUser);
    }

    @GetMapping("/verified")
    Page<VerifiedUsers> getAllVerifiedUsers(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return authService.getAllVerifiedUsers(pageable);
    }

    @DeleteMapping("/account/{id}/delete")
    public GenericMessageResponse deleteAccount(@PathVariable("id") Long id){
        return authService.deleteAccount(id);
    }
}
