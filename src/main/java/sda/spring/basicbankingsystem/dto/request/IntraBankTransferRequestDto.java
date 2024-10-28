package sda.spring.basicbankingsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntraBankTransferRequestDto {

    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
}
