package com.top.wallet.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "com.top.wallet.transaction.adapter.out.entity" })
public class WalletTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletTransactionApplication.class, args);
	}

}
