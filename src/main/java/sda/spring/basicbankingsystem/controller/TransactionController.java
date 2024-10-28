package sda.spring.basicbankingsystem.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.spring.basicbankingsystem.dto.request.IntraBankTransferRequestDto;
import sda.spring.basicbankingsystem.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {

        this.transactionService = transactionService;

    }
    @PostMapping("/intra-bank")
    public void intraBankPayment(@RequestBody IntraBankTransferRequestDto intraBankTransferRequestDto) {

    }

}
