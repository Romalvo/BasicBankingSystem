package sda.spring.basicbankingsystem.dto.response;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CreateBranchResponseDto {
    private Long id;
    private String name;
    private String timezone;
    private String country;
    private LocalDateTime created_at;
}
