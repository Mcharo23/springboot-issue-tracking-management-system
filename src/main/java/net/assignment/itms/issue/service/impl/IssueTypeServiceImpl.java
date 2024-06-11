package net.assignment.itms.issue.service.impl;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.IssueTypeDto;
import net.assignment.itms.issue.entity.IssueType;
import net.assignment.itms.issue.mapper.IssueMapper;
import net.assignment.itms.issue.repository.IssueTypeRepository;
import net.assignment.itms.issue.service.IssueTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueTypeServiceImpl implements IssueTypeService {
    private final IssueTypeRepository issueTypeRepository;

    public IssueTypeServiceImpl(IssueTypeRepository issueTypeRepository) {
        this.issueTypeRepository = issueTypeRepository;
    }

    @Override
    public List<IssueTypeDto> findAllIssueTypes() {
        List<IssueType> issues = issueTypeRepository.findAll();
        return issues
                .stream()
                .map(IssueMapper::mapToIssueTypeDto)
                .collect(Collectors.toList());
    }

    @Override
    public String createIssueType(IssueTypeDto issueTypeDto) throws BadRequestException {
        IssueType issueType = issueTypeRepository.save(IssueMapper.mapToissueType(issueTypeDto));

        if (issueType == null) {
            throw new BadRequestException("Failed to save issue type, please try again.");
        }

        return "Issue type saved successfully";
    }

    @Override
    public IssueType findById(Long issue_type_id) throws NotFoundException {
        return issueTypeRepository.findById(issue_type_id).orElseThrow(() -> new NotFoundException("Issue type not found"));
    }
}
