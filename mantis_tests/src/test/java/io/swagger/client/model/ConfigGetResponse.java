/*
 * MantisBT REST API
 * For the sandbox to work, MantisBT must be hosted at the root folder of the host. For example: http://mantishost/ rather http://host/mantisbt.  If that is not the case, then create a host alias to map it as such or edit swagger.json to change basePath to include the mantisbt folder name.
 *
 * OpenAPI spec version: 1.1.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ConfigAnyTypeOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConfigGetResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2025-07-29T13:56:12.702+05:30")
public class ConfigGetResponse {
  @SerializedName("configs")
  private List<ConfigAnyTypeOption> configs = null;

  public ConfigGetResponse configs(List<ConfigAnyTypeOption> configs) {
    this.configs = configs;
    return this;
  }

  public ConfigGetResponse addConfigsItem(ConfigAnyTypeOption configsItem) {
    if (this.configs == null) {
      this.configs = new ArrayList<ConfigAnyTypeOption>();
    }
    this.configs.add(configsItem);
    return this;
  }

   /**
   * Get configs
   * @return configs
  **/
  @ApiModelProperty(value = "")
  public List<ConfigAnyTypeOption> getConfigs() {
    return configs;
  }

  public void setConfigs(List<ConfigAnyTypeOption> configs) {
    this.configs = configs;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigGetResponse configGetResponse = (ConfigGetResponse) o;
    return Objects.equals(this.configs, configGetResponse.configs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(configs);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigGetResponse {\n");
    
    sb.append("    configs: ").append(toIndentedString(configs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

