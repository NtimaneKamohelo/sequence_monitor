package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.ParsedIdentity;
import org.example.dto.IdentityParserRequest;
import org.example.dto.IdentityParserResponse;
import org.example.mapper.IdentityMapper;
import org.example.service.IdentityService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identities")
@Validated
public class IdentityController {

    private final IdentityService identityService;

    private final IdentityMapper identityMapper;

    public IdentityController(
            IdentityService identityService,
            IdentityMapper identityMapper
    ){
        this.identityService = identityService;
        this.identityMapper = identityMapper;
    }

    @PostMapping("/parse")
    public ResponseEntity<IdentityParserResponse> parse(
            @Valid
            @RequestBody
            IdentityParserRequest request
    ) {
        ParsedIdentity parsedIdentity = identityService.parser(
                request.getIdNumber()
        );

        IdentityParserResponse response = identityMapper.toResponse(
                parsedIdentity
        );

        return ResponseEntity.ok(
                response
        );
    }
}
