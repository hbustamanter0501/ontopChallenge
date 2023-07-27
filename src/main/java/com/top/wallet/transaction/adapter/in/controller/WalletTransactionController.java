package com.top.wallet.transaction.adapter.in.controller;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.application.port.in.CreateWalletTransactionCommand;
import com.top.wallet.transaction.domain.WalletTransaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/wallet-transaction")
public class WalletTransactionController {

    private final CreateWalletTransactionCommand saveWalletTransactionCommand;

    public WalletTransactionController(CreateWalletTransactionCommand saveWalletTransactionCommand){
        this.saveWalletTransactionCommand = saveWalletTransactionCommand;
    }

    @PostMapping()
    @ApiOperation(value = "Create a wallet transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = WalletTransaction.class, message = "OK"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Error creating a wallet transaction"),
            })
    public WalletTransaction createWalletTransaction(@RequestBody WalletTransactionDTO dto) throws ServiceException {
        return saveWalletTransactionCommand.execute(dto);
    }
}
