package sda.spring.basicbankingsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBranchRequestDto {

    private String name;
    private String timezone;
    private String country;
}
