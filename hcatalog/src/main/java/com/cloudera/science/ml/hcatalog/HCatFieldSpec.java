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
package com.cloudera.science.ml.hcatalog;

import org.apache.hcatalog.data.schema.HCatFieldSchema;

import com.cloudera.science.ml.core.records.BasicSpec;
import com.cloudera.science.ml.core.records.DataType;
import com.cloudera.science.ml.core.records.FieldSpec;
import com.cloudera.science.ml.core.records.Spec;

/**
 *
 */
public class HCatFieldSpec implements FieldSpec {

  private final HCatFieldSchema schema;
  private final int position;
  
  public HCatFieldSpec(HCatFieldSchema schema, int position) {
    this.schema = schema;
    this.position = position;
  }
  
  @Override
  public String name() {
    return schema.getName();
  }

  @Override
  public int position() {
    return position;
  }

  @Override
  public Spec spec() {
    switch (schema.getType()) {
    case BOOLEAN:
      return new BasicSpec(DataType.BOOLEAN, 0);
    case INT:
    case SMALLINT:
    case TINYINT:
      return new BasicSpec(DataType.INT, 0);
    case BIGINT:
      return new BasicSpec(DataType.LONG, 0);
    case FLOAT:
    case DOUBLE:
      return new BasicSpec(DataType.DOUBLE, 0);
    case STRING:
      return new BasicSpec(DataType.STRING, 0);
      default:
        throw new IllegalArgumentException(
            "Unsupported field type: " + schema.getType());
    }
  }

}
