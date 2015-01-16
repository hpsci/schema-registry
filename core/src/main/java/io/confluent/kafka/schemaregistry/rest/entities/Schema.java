/**
 * Copyright 2014 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.confluent.kafka.schemaregistry.rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

import io.confluent.kafka.schemaregistry.storage.SchemaRegistryValue;

public class Schema implements Comparable<Schema>, SchemaRegistryValue {

  @NotEmpty
  private String name;
  @Min(1)
  private Integer version;
  @Min(0)
  private Long id;
  @NotEmpty
  private String schema;

  public Schema(@JsonProperty("name") String name,
                @JsonProperty("version") Integer version,
                @JsonProperty("id") Long id,
                @JsonProperty("schema") String schema) {
    this.name = name;
    this.version = version;
    this.id = id;
    this.schema = schema;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("version")
  public Integer getVersion() {
    return this.version;
  }

  @JsonProperty("version")
  public void setVersion(Integer version) {
    this.version = version;
  }

  @JsonProperty("id")
  public Long getId() {
    return this.id;
  }

  @JsonProperty("id")
  public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("schema")
  public String getSchema() {
    return this.schema;
  }

  @JsonProperty("schema")
  public void setSchema(String schema) {
    this.schema = schema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Schema that = (Schema) o;

    if (!this.name.equals(that.name)) {
      return false;
    }
    if (!this.version.equals(that.version)) {
      return false;
    }
    if (!this.id.equals(that.getId())) {
      return false;
    }
    if (!this.schema.equals(that.schema)) {
      return false;
    }


    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + version;
    result = 31 * result + id.intValue();
    result = 31 * result + schema.hashCode();
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{name=" + this.name + ",");
    sb.append("version=" + this.version + ",");
    sb.append("id=" + this.id + ",");
    sb.append("schema=" + this.schema + "}");
    return sb.toString();
  }

  @Override
  public int compareTo(Schema that) {
    int result = this.name.compareTo(that.name);
    if (result != 0) {
      return result;
    }
    result = this.version - that.version;
    return result;
  }
}
