package sda.spring.basicbankingsystem.service;


import org.springframework.stereotype.Service;
import sda.spring.basicbankingsystem.dto.request.CreateBranchRequestDto;
import sda.spring.basicbankingsystem.dto.response.CreateBranchResponseDto;
import sda.spring.basicbankingsystem.entity.Branch;
import sda.spring.basicbankingsystem.mapper.BranchMapper;
import sda.spring.basicbankingsystem.repository.BranchRepository;

import java.time.LocalDateTime;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    public CreateBranchResponseDto createBranch(CreateBranchRequestDto createdBranchRequestDto) {

        Branch branch = branchMapper.fromCreateBranchRequestToEntity(createdBranchRequestDto);
        branch.setCreatedAt(LocalDateTime.now());
        branch = branchRepository.save(branch);
        return branchMapper.fromEntityToCreateBranchResponseDto(branch);
    }
}
