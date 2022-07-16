package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class RepositoryPair extends Pair {

  private final String prefix;
  private final CrudRepository repository;

  @Autowired
  public RepositoryPair(String prefix, CrudRepository repository) {
    this.prefix = prefix;
    this.repository = repository;
  }
  @Override
  public String getLeft() {
    return prefix;
  }

  @Override
  public CrudRepository getRight() {
    return repository;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  @Override
  public Object setValue(Object value) {
    return null;
  }
}
