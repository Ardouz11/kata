package com.github.kata.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.github.kata.service.GeneralLedgerService;
import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class XmlParserServiceTest {

  @Mock private GeneralLedgerService generalLedgerService;

  @InjectMocks private XmlParserService xmlParserService;

  @BeforeEach
  public void setUp() throws JAXBException {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testParseFile_Success() throws Exception {
    // Given
    File file = new File(getClass().getClassLoader().getResource("valid-ledgers.xml").getFile());

    // When
    xmlParserService.parseFile(file);

    // Then
    verify(generalLedgerService, times(1)).processBatch(anyList());
  }

  @Test
  public void testParseFile_EmptyFile() throws Exception {
    // Given
    File emptyFile = Files.createTempFile("empty-file", ".xml").toFile();

    // When
    try {
      xmlParserService.parseFile(emptyFile);
    } catch (RuntimeException e) {
      assertEquals("File is empty", e.getMessage());
    } finally {
      emptyFile.delete();
    }
    // Then
    verify(generalLedgerService, never()).processBatch(anyList());
  }

  @Test
  public void testParseFile_ExceptionHandling() throws Exception {
    // Given
    File invalidFile =
        new File(getClass().getClassLoader().getResource("invalid-ledgers.xml").getFile());

    // When
    try {
      xmlParserService.parseFile(invalidFile);
    } catch (RuntimeException e) {
      assertEquals("Error processing XML file: invalid-ledgers.xml", e.getMessage());
      assertTrue(e.getCause() instanceof JAXBException);
    }
    // Then
    verify(generalLedgerService, never()).processBatch(anyList());
  }
}
