package com.github.dolphinai.tutorials.bootsamples.lucene;

import java.util.List;

public interface DocumentIndexRepository {

  List<DocumentEntry> findAll(String indexName, String keywords, int start, int hitsPerPage);

  boolean save(DocumentEntry document);

  boolean saveAll(Iterable<DocumentEntry> documents);

  boolean deleteById(String indexName, String id);

  boolean deleteAll(String indexName);

}
