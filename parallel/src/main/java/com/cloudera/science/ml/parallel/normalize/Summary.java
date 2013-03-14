/**
 * Copyright (c) 2013, Cloudera, Inc. All Rights Reserved.
 *
 * Cloudera, Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"). You may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the
 * License.
 */
package com.cloudera.science.ml.parallel.normalize;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 *
 */
public class Summary implements Serializable {

  private Map<Integer, SummaryStats> stats = Maps.newTreeMap();
  private Header header;
  private long recordCount;
  private int fieldCount;
  
  public Summary() {}
  
  public Summary(Header header, long recordCount, int fieldCount, Map<Integer, SummaryStats> stats) {
    this.header = header;
    this.recordCount = recordCount;
    this.fieldCount = fieldCount;
    for (Map.Entry<Integer, SummaryStats> e : stats.entrySet()) {
      String fieldName = header.getFieldName(e.getKey());
      if (fieldName != null) {
        e.getValue().setFieldName(fieldName);
      }
      this.stats.put(e.getKey(), e.getValue());
    }
  }
  
  public Header getHeader() {
    return header;
  }
  
  public Map<Integer, SummaryStats> getAllStats() {
    return stats;
  }
  
  public long getRecordCount() {
    return recordCount;
  }
  
  public int getFieldCount() {
    return fieldCount;
  }
  
  public SummaryStats getStats(int field) {
    return stats.get(field);
  }
  
  public int getNetLevels() {
    int netLevels = 0;
    for (SummaryStats ss : stats.values()) {
      netLevels += ss.numLevels() - 1;
    }
    return netLevels;
  }
}
