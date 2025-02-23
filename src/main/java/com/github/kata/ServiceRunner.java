package com.github.kata;

import com.github.kata.service.ParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ServiceRunner implements CommandLineRunner {
  private final ParserService parserService;

  ServiceRunner(ParserService parserService) {
    this.parserService = parserService;
  }

  @Override
  public void run(String... args) throws Exception {
    Resource resource = new ClassPathResource("brignolles-gl.xml");
    File file = resource.getFile();
    parserService.parseFile(file);
  }
}
