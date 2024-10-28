package sda.spring.basicbankingsystem.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import sda.spring.basicbankingsystem.enums.TransactionsStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TransactionResponseDto {

    private Long id;
    private OpenAccountResponseDto senderAccount;
    private String senderAccountIBAN;
    private OpenAccountResponseDto recipientAccount;
    private String recipientAccountIBAN;
    private BigDecimal amount;
    private TransactionsStatus status;
    private LocalDateTime createdAt;
}
