package sda.spring.basicbankingsystem.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.spring.basicbankingsystem.dto.request.CreateBranchRequestDto;
import sda.spring.basicbankingsystem.dto.response.CreateBranchResponseDto;
import sda.spring.basicbankingsystem.service.BranchService;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService){
        this.branchService = branchService;
    }

    @PostMapping
    public CreateBranchResponseDto createBranch(@RequestBody CreateBranchRequestDto createdBranchRequestDto){
        return this.branchService.createBranch(createdBranchRequestDto);
    }
}
