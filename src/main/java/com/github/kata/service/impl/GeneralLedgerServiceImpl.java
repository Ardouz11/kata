package com.github.kata.service.impl;

import com.github.kata.dao.dto.WsGeneralLedger;
import com.github.kata.dao.model.GeneralLedger;
import com.github.kata.dao.model.LetterInfo;
import com.github.kata.dao.model.PaymentInfo;
import com.github.kata.dao.repository.GeneralLedgerRepository;
import com.github.kata.service.GeneralLedgerService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneralLedgerServiceImpl implements GeneralLedgerService {
  private static final Logger log = LoggerFactory.getLogger(GeneralLedgerServiceImpl.class);
  private final GeneralLedgerRepository repository;

  public GeneralLedgerServiceImpl(GeneralLedgerRepository generalLedgerRepository) {
    this.repository = generalLedgerRepository;
  }

  private void parseDates(WsGeneralLedger ws, GeneralLedger entity) {
    try {
      entity.setDate(
          ws.getDate() != null && !ws.getDate().isBlank()
              ? LocalDate.parse(ws.getDate().trim())
              : null);
    } catch (DateTimeParseException e) {
      entity.setDate(null);
    }
    try {
      entity.setLastModifyDate(
          ws.getLastModifyDate() != null && !ws.getLastModifyDate().isBlank()
              ? LocalDate.parse(ws.getLastModifyDate().trim())
              : null);
    } catch (DateTimeParseException e) {
      entity.setLastModifyDate(null);
    }
  }

  /**
   * Processes a batch of {@link WsGeneralLedger} objects by converting them into {@link
   * GeneralLedger} entities and saving them to the repository.
   *
   * <p>This method performs the following steps:
   *
   * <ol>
   *   <li>Converts the list of {@link WsGeneralLedger} objects into {@link GeneralLedger} entities
   *       using the {@link #convertToEntity(WsGeneralLedger)} method.
   *   <li>Saves the list of entities to the repository using {@link
   *       JpaRepository#saveAll(Iterable)}.
   *   <li>Logs the number of records processed in the batch.
   * </ol>
   *
   * @param ledgers A list of {@link WsGeneralLedger} objects to be processed. Each object is
   *     converted into a corresponding {@link GeneralLedger} entity before being saved to the
   *     repository.
   */
  @Transactional
  @Override
  public void processBatch(List<WsGeneralLedger> ledgers) {
    List<GeneralLedger> entities =
        ledgers.stream().map(this::convertToEntity).collect(Collectors.toList());

    repository.saveAll(entities);
    log.info("Processed batch of {} records", ledgers.size());
  }

  @Override
  public Page<GeneralLedger> getLedgers(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  private GeneralLedger convertToEntity(WsGeneralLedger ws) {
    GeneralLedger entity = new GeneralLedger();
    entity.setInternalID(ws.getInternalID());
    entity.setBalance(ws.getBalance());
    entity.setCategory(ws.getCategory());
    entity.setCollectif(ws.getCollectif());
    entity.setComment(ws.getComment());
    entity.setCredit(ws.getCredit());
    entity.setCurrency(ws.getCurrency());
    entity.setCurrencyAmount(ws.getCurrencyAmount());
    parseDates(ws, entity);
    entity.setDebit(ws.getDebit());
    entity.setDescription(ws.getDescription());
    entity.setHeader(ws.getHeader());
    entity.setJournalIndex(ws.getJournalIndex());

    LetterInfo letterInfo = new LetterInfo();
    letterInfo.setLetter(ws.getLetter());
    letterInfo.setLetterID(ws.getLetterID());
    letterInfo.setLetteredInBetween(ws.getLetteredInBetween());
    entity.setLetterInfo(letterInfo);

    PaymentInfo paymentInfo = new PaymentInfo();
    paymentInfo.setPaymentMethod(ws.getPaymentMethod());
    paymentInfo.setPaymentMethodDescription(ws.getPaymentMethodDescription());
    entity.setPaymentInfo(paymentInfo);

    entity.setName(ws.getName());
    entity.setNumber(ws.getNumber());
    entity.setPeriodEnd(ws.getPeriodEnd());
    entity.setPeriodStart(ws.getPeriodStart());
    entity.setPiece(ws.getPiece());
    entity.setQtyUnit(ws.getQtyUnit());
    entity.setQuantity(ws.getQuantity());
    entity.setRef(ws.getRef());
    entity.setTerm(ws.getTerm());
    entity.setVoucherID(ws.getVoucherID());
    entity.setVoucherRef(ws.getVoucherRef());

    return entity;
  }
}
