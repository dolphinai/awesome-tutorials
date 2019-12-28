package com.github.dolphinai.tutorials.bootsamples.lucene;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class LuceneIndexService {

  private Directory indexDirectory;
  private Analyzer analyzer;

  public LuceneIndexService(String indexPath)  {
    try {
      this.indexDirectory = FSDirectory.open(Paths.get(indexPath, new String[0]));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    System.out.println("indexes: " + indexDirectory);
    this.analyzer = new StandardAnalyzer();
  }

  public Directory getIndexDirectory() {
    return indexDirectory;
  }

  public Analyzer getAnalyzer() {
    return analyzer;
  }

  public void setAnalyzer(Analyzer analyzer) {
    this.analyzer = analyzer;
  }

  public void write(DocumentWriter handler) throws IOException {
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
    indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
    IndexWriter writer = new IndexWriter(this.indexDirectory, indexWriterConfig);
    try {
      handler.call(writer);
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new IOException(e);
    } finally {
      writer.close();
    }
  }

  protected IndexWriter getIndexWriter() throws IOException {
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
    indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
    return new IndexWriter(this.indexDirectory, indexWriterConfig);
  }

  public void create(Document document) throws IOException {
    IndexWriter writer = getIndexWriter();
    try {
      writer.addDocument(document);
    } catch (Exception e) {
      throw new IOException(e);
    } finally {
      writer.close();
    }
  }

  public <T> boolean save(Iterable<T> documents, String primaryField, DocumentConverter<T, Document> converter) throws IOException {
    if (documents == null) {
      return false;
    }
    IndexWriter writer = getIndexWriter();
    try {
      Iterator<T> iterator = documents.iterator();
      while (iterator.hasNext()) {
        Document doc = converter.to(iterator.next());
        String id = doc.get(primaryField);
        if (id == null || id.length() == 0) {
          System.out.println("adding " + id);
          writer.addDocument(doc);
        } else {
          System.out.println("updating " + id);
          writer.updateDocument(new Term(primaryField, id), doc);
        }
      }
      log.debug("document indexed");
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new IOException(e);
    } finally {
      writer.close();
    }
    return true;
  }

  public boolean delete(String fieldName, String fieldValue, boolean forceDelete) throws IOException {
    IndexWriter writer = getIndexWriter();
    Term term = new Term(fieldName, fieldValue);
    try {
      writer.deleteDocuments(term);
      if (forceDelete) {
        writer.forceMergeDeletes();
      }
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new IOException(e);
    } finally {
      writer.close();
    }
    return true;
  }

  public List<Document> findAll(String[] fields, String keywords, int start, int hitsPerPage) throws IOException {
    IndexReader reader = DirectoryReader.open(getIndexDirectory());
    List<Document> result = new ArrayList<>();
    try {
      IndexSearcher indexSearcher = new IndexSearcher(reader);
      QueryParser queryParser = new MultiFieldQueryParser(fields, getAnalyzer());
      Query query = queryParser.parse(keywords);
      TopDocs topDocs = indexSearcher.search(query, 5 * hitsPerPage);
      // total hits
      int numTotalHits = Math.toIntExact(topDocs.totalHits.value);

      ScoreDoc[] scoreDocs = topDocs.scoreDocs;
      int end = Math.min(numTotalHits, start + hitsPerPage);
      for (int i = start; i < end; i++) {
        int doc = scoreDocs[i].doc;
        result.add(indexSearcher.doc(doc));
      }
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new IOException(e);
    } finally {
      reader.close();
    }
    return result;
  }

  @FunctionalInterface
  public static interface DocumentWriter {
    void call(IndexWriter writer) throws IOException;
  }

  @FunctionalInterface
  public static interface DocumentConverter<From, To> {
    To to(From entity);
  }
}
