package com.softserve.edu.resources.entity;

import java.net.URL;
import java.util.Set;

/**
 * Represents legal document issued by authorities
 */
public class Document {

  private int id;
  private String name;
  private String series;
  private String number;
  private Set<URL> copies;


  public Document() {
  }

  public int getId() {
    return id;
  }

  public Document setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Document setName(String name) {
    this.name = name;
    return this;
  }

  public String getSeries() {
    return series;
  }

  public Document setSeries(String series) {
    this.series = series;
    return this;
  }

  public String getNumber() {
    return number;
  }

  public Document setNumber(String number) {
    this.number = number;
    return this;
  }

  public Set<URL> getCopies() {
    return copies;
  }

  public Document setCopies(Set<URL> copies) {
    this.copies = copies;
    return this;
  }
}
