package com.github.kata.service;

import com.github.kata.dao.model.GeneralLedger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneralLedgerService {

  Page<GeneralLedger> getLedgers(Pageable pageable);
}
