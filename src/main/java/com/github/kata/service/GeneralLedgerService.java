package com.github.kata.service;

import com.github.kata.dao.dto.WsGeneralLedger;
import com.github.kata.dao.model.GeneralLedger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralLedgerService {

  void processBatch(List<WsGeneralLedger> ledgers);

  Page<GeneralLedger> getLedgers(Pageable pageable);
}
