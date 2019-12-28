package com.github.dolphinai.tutorials.bootsamples.lucene;

import java.io.Serializable;
import java.util.Date;

public interface DocumentEntry extends Serializable {

  String getIndexName();

  String getId();

  String getTitle();

  String getContent();

  String getAuthor();

  Date getLastModified();

}
