package com.top.wallet.transaction.adapter.in.controller;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.application.port.in.CreateAccountCommand;
import com.top.wallet.transaction.application.port.in.CreateWalletTransactionCommand;
import com.top.wallet.transaction.domain.Account;
import com.top.wallet.transaction.domain.WalletTransaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private final CreateAccountCommand createAccountCommand;

    public AccountController(CreateAccountCommand createAccountCommand){
        this.createAccountCommand = createAccountCommand;
    }

    @PostMapping()
    @ApiOperation(value = "Create an account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Account.class, message = "OK"),
            @ApiResponse(code = 500, message = "Error creating an account"),
    })
    public Account createAccount(@RequestBody AccountDTO dto) throws ServiceException {
        return createAccountCommand.execute(dto);
    }
}
