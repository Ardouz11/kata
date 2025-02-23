package com.github.kata.service.impl;

import com.github.kata.dao.dto.Response;
import com.github.kata.dao.dto.WsGeneralLedger;
import com.github.kata.dao.dto.WsGeneralLedgerListWrapper;
import com.github.kata.service.GeneralLedgerService;
import com.github.kata.service.ParserService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  @Override
  public void parseFile(File file) {
    try {
      if (file.length() == 0) {
        log.warn("The file is empty.");
        return;
      }
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));

      JAXBContext context = JAXBContext.newInstance(WsGeneralLedgerListWrapper.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();

      WsGeneralLedgerListWrapper wrapper =
          (WsGeneralLedgerListWrapper) unmarshaller.unmarshal(reader);

      // Process response data
      Response response = wrapper.getResponse();
      log.info("Processing file with result: {}", response.getResult());
      log.info("Processing datetime: {}", response.getDatetime());

      // Process ledgers in batches
      List<WsGeneralLedger> ledgers = wrapper.getData().getLedgers();
      List<WsGeneralLedger> batch = new ArrayList<>(BATCH_SIZE);

      for (WsGeneralLedger ledger : ledgers) {
        batch.add(ledger);

        if (batch.size() >= BATCH_SIZE) {
          generalLedgerService.processBatch(batch);
          batch.clear();
        }
      }

      if (!batch.isEmpty()) {
        generalLedgerService.processBatch(batch);
      }

      reader.close();
      log.info("File processing completed successfully");

    } catch (XMLStreamException | JAXBException | FileNotFoundException e) {
      log.error("Error processing XML file", e);
      throw new RuntimeException("Error processing XML file", e);
    }
  }
}
