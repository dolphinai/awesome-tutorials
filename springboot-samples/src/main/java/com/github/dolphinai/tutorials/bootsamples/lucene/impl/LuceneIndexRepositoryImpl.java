package com.github.dolphinai.tutorials.bootsamples.lucene.impl;

import com.github.dolphinai.tutorials.bootsamples.lucene.DocumentEntry;
import com.github.dolphinai.tutorials.bootsamples.lucene.DocumentIndexRepository;
import com.github.dolphinai.tutorials.bootsamples.lucene.LuceneIndexService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class LuceneIndexRepositoryImpl implements DocumentIndexRepository {

  private LuceneIndexService luceneIndexService;

  public void setLuceneIndexService(LuceneIndexService luceneIndexService) {
    this.luceneIndexService = luceneIndexService;
  }

  @Override
  public List<DocumentEntry> findAll(String indexName, String keywords, int start, int hitsPerPage) {
    return null;
  }

  @Override
  public boolean save(DocumentEntry document) {
    return saveAll(Collections.singleton(document));
  }

  @Override
  public boolean saveAll(Iterable<DocumentEntry> documents) {
    return false;
  }

  @Override
  public boolean deleteById(String indexName, String id) {
    try {
      return luceneIndexService.delete("id", id, true);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public boolean deleteAll(String indexName) {
    try {
      return luceneIndexService.delete("indexName", indexName, true);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
