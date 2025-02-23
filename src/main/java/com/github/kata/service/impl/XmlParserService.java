package com.github.kata.service.impl;

import com.github.kata.dao.dto.Response;
import com.github.kata.dao.dto.WsGeneralLedger;
import com.github.kata.dao.dto.WsGeneralLedgerListWrapper;
import com.github.kata.service.GeneralLedgerService;
import com.github.kata.service.ParserService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class XmlParserService implements ParserService {
  private static final Logger log = LoggerFactory.getLogger(XmlParserService.class);
  private static final int BATCH_SIZE = 1000;
  private static final JAXBContext jaxbContext;
  private static final XMLInputFactory xmlInputFactory;

  static {
    try {
      jaxbContext = JAXBContext.newInstance(WsGeneralLedgerListWrapper.class);
      xmlInputFactory = XMLInputFactory.newInstance();
      // Security settings
      xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
      xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    } catch (JAXBException e) {
      throw new ExceptionInInitializerError("Failed to initialize JAXBContext: " + e.getMessage());
    }
  }

  private final GeneralLedgerService generalLedgerService;

  XmlParserService(GeneralLedgerService generalLedgerService) {
    this.generalLedgerService = generalLedgerService;
  }

  /**
   * Parses the given XML file, processes the data in batches, and interacts with the
   * generalLedgerService to process the parsed ledgers.
   *
   * <p>This method performs the following steps:
   *
   * <ol>
   *   <li>Reads the XML file using {@link XMLStreamReader}.
   *   <li>Unmarshals the XML data into a {@link WsGeneralLedgerListWrapper} object using JAXB.
   *   <li>Processes the response data (logs the result and datetime).
   *   <li>Iterates over the ledgers in the wrapper, grouping them into batches of a specified size.
   *   <li>Calls {@link GeneralLedgerService#processBatch(List)} to process each batch of ledgers.
   *   <li>Handles any exceptions that occur during XML parsing or processing.
   * </ol>
   *
   * @param file The XML file to parse. The file should be in a valid XML format and contain the
   *     necessary structure to be unmarshalled into a {@link WsGeneralLedgerListWrapper} object.
   * @throws RuntimeException If an error occurs while processing the file, such as issues with XML
   *     unmarshalling, file not found, or errors during the batch processing.
   */
  public void parseFile(File file) {
    validateFile(file);

    XMLStreamReader reader = null;
    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
      reader = xmlInputFactory.createXMLStreamReader(bis);

      WsGeneralLedgerListWrapper wrapper = unmarshalXml(reader);
      processResponse(wrapper.getResponse());
      processLedgersInBatches(wrapper.getData().getLedgers());

      log.info("File processing completed successfully");

    } catch (Exception e) {
      log.error("Error processing XML file: {}", file.getName(), e);
      throw new RuntimeException("Error processing XML file: " + file.getName(), e);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (XMLStreamException e) {
          log.warn("Error closing XMLStreamReader", e);
        }
      }
    }
  }

  private void validateFile(File file) {
    if (file == null || !file.exists()) {
      throw new IllegalArgumentException("File does not exist");
    }
    if (!file.canRead()) {
      throw new IllegalArgumentException("File is not readable");
    }
    if (file.length() == 0) {
      throw new IllegalArgumentException("File is empty");
    }
  }

  private WsGeneralLedgerListWrapper unmarshalXml(XMLStreamReader reader) throws JAXBException {
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    return (WsGeneralLedgerListWrapper) unmarshaller.unmarshal(reader);
  }

  private void processResponse(Response response) {
    if (response == null) {
      log.warn("Null response received");
      return;
    }
    log.info("Processing file with result: {}", response.getResult());
    log.info("Processing datetime: {}", response.getDatetime());
  }

  private void processLedgersInBatches(List<WsGeneralLedger> ledgers) {
    if (ledgers == null || ledgers.isEmpty()) {
      log.warn("No ledgers to process");
      return;
    }

    int totalBatches = (ledgers.size() + BATCH_SIZE - 1) / BATCH_SIZE;
    int processedLedgers = 0;

    List<WsGeneralLedger> batch = new ArrayList<>(BATCH_SIZE);

    for (WsGeneralLedger ledger : ledgers) {
      batch.add(ledger);

      if (batch.size() >= BATCH_SIZE) {
        generalLedgerService.processBatch(batch);
        processedLedgers += batch.size();
        log.debug(
            "Processed batch {}/{} ({} ledgers)",
            processedLedgers / BATCH_SIZE,
            totalBatches,
            processedLedgers);
        batch.clear();
      }
    }

    if (!batch.isEmpty()) {
      generalLedgerService.processBatch(batch);
      processedLedgers += batch.size();
      log.debug(
          "Processed final batch {}/{} (total {} ledgers)",
          totalBatches,
          totalBatches,
          processedLedgers);
    }
  }
}
