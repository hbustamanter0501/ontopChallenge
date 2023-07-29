package com.top.wallet.transaction.adapter.in.controller;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionRestModel;
import com.top.wallet.transaction.application.port.in.CreateWalletTransactionCommand;
import com.top.wallet.transaction.application.port.in.GetWalletTransactionsCommand;
import com.top.wallet.transaction.domain.WalletTransaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(path = "/wallet-transaction")
public class WalletTransactionController {
    private final CreateWalletTransactionCommand saveWalletTransactionCommand;
    private final GetWalletTransactionsCommand getWalletTransactionsCommand;

    public WalletTransactionController(
            CreateWalletTransactionCommand saveWalletTransactionCommand,
            GetWalletTransactionsCommand getWalletTransactionsCommand){
        this.saveWalletTransactionCommand = saveWalletTransactionCommand;
        this.getWalletTransactionsCommand = getWalletTransactionsCommand;
    }

    @PostMapping()
    @ApiOperation(value = "Create a wallet transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = WalletTransactionRestModel.class, message = "OK"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Error creating a wallet transaction"),
            })
    public WalletTransactionRestModel createWalletTransaction(@RequestBody WalletTransactionDTO dto) throws ServiceException {
        WalletTransaction walletTransaction = saveWalletTransactionCommand.execute(dto);
        return WalletTransactionRestModel.fromDomain(walletTransaction);
    }

    @GetMapping(value = "/find-all-by-filters")
    @ApiOperation(value = "Get all wallet transactions paginated by filters and by userId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = WalletTransactionRestModel.class, message = "OK"),
            @ApiResponse(code = 404, message = "Transaction not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    public Page<WalletTransactionRestModel> findAllByFilter(
            @PageableDefault(size=20, page=0, direction = Sort.Direction.DESC, sort = "creationDate") Pageable pageable,
            @RequestParam("amount") Optional<Double> amount,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> date,
            @RequestParam("userId") Integer userId) throws ServiceException {

        return getWalletTransactionsCommand.execute(userId, amount, date, pageable);
    }
}
