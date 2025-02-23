package com.github.kata.controller;

import com.github.kata.dao.model.GeneralLedger;
import com.github.kata.service.GeneralLedgerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/general-ledger")
public class GeneralLedgerController {

  private final GeneralLedgerService generalLedgerService;

  public GeneralLedgerController(GeneralLedgerService generalLedgerService) {
    this.generalLedgerService = generalLedgerService;
  }

  @GetMapping
  public ResponseEntity<Page<GeneralLedger>> getLedgers(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(generalLedgerService.getLedgers(pageable));
  }
}
