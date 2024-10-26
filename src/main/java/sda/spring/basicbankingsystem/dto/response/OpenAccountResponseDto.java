package sda.spring.basicbankingsystem.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OpenAccountResponseDto {

    private String iban;
    private BigDecimal balance;
    private Long ownerId;
    private UserProfileDto owner;
    private CreateBranchResponseDto branch;

}
