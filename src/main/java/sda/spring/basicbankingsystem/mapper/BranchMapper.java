package sda.spring.basicbankingsystem.mapper;


import org.springframework.stereotype.Component;
import sda.spring.basicbankingsystem.dto.request.CreateBranchRequestDto;
import sda.spring.basicbankingsystem.dto.response.CreateBranchResponseDto;
import sda.spring.basicbankingsystem.entity.Branch;

@Component
public class BranchMapper {

    public Branch fromCreateBranchRequestToEntity(CreateBranchRequestDto createBranchRequestDto) {
        return new Branch()
                .setName(createBranchRequestDto.getName())
                .setCountry(createBranchRequestDto.getCountry())
                .setTimezone(createBranchRequestDto.getTimezone());
    }

    public CreateBranchResponseDto fromEntityToCreateBranchResponseDto(Branch branch) {
        return new CreateBranchResponseDto()
                .setId(branch.getId())
                .setCountry(branch.getCountry())
                .setName(branch.getName())
                .setTimezone(branch.getTimezone())
                .setCreatedAt(branch.getCreatedAt());
    }
}
