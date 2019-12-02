package com.dormiroty.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class RestResult<T> {

  public static final String STATUS_SUCCESS = "success".intern();
  public static final String STATUS_ERROR = "error".intern();

  @JsonView(Object.class)
  private String status;

  @JsonView(Object.class)
  private List<String> messages;

  private String message;

  @JsonView(Object.class)
  private T data;

  private HashMap<String,String> metaData;

  public RestResult() {
    messages = new ArrayList<String>();
    metaData = new HashMap<>();
    status = STATUS_SUCCESS;
  }

  public static <T> RestResult<T> create(Class<T> genericType) {
    return new RestResult<T>();
  }

  public static <T> RestResult<T> ok(Class<T> type) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_SUCCESS;
    return result;
  }

  public static <T> RestResult<T> ok(Class<T> type, String message, T data) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_SUCCESS;
    result.addMessage(message);
    result.data = data;
    return result;
  }

  public void ok(String message) {
    this.status = STATUS_SUCCESS;
    addMessage(message);
  }

  public void ok(String message, T data) {
    this.status = STATUS_SUCCESS;
    addMessage(message);
    this.data = data;
  }

  public static <T> RestResult<T> fail(Class<T> type) {
    RestResult<T> result = new RestResult<T>();
    result.status = STATUS_ERROR;
    return result;
  }

  public void fail(String message) {
    this.status = STATUS_ERROR;
    addMessage(message);
  }

  /**
   * Getter & Setter
   */
  public String getStatus() {
    return status;
  }

  public RestResult<T> setStatus(String status) {
    this.status = status;
    return this;
  }

  public List<String> getMessages() {
    return messages;
  }

  public RestResult<T> setMessages(List<String> messages) {
    for (String message : messages) {
      this.messages.add(message);
    }
    return this;
  }

  public RestResult<T> addMessage(String message) {
    messages.add(message);
    return this;
  }

  public T getData() {
    return data;
  }

  public static <T> T getRestResultData(Object object, Class<T> clazz) {
    return new GsonBuilder().create().fromJson(new GsonBuilder().create().toJson(object), clazz);
  }

  public static <T> List<T> getRestResultListData(Object object, Class<T[]> clazz) {
    return Arrays.asList(new Gson().fromJson(new GsonBuilder().create().toJson(object), clazz));
  }

  public RestResult<T> setData(T data) {
    this.data = data;
    return this;
  }

  public RestResult<T> addError(String error) {
    this.status = STATUS_ERROR;
    this.addMessage(error);
    return this;
  }

  @JsonIgnore
  public boolean isError() {
    return !StringUtils.equals(STATUS_SUCCESS, status);
  }

  public String getMessage() {
    if(StringUtils.isEmpty(message) && CollectionUtils.isNotEmpty(messages)){
      message = String.join(",", this.messages);
    }
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public HashMap<String, String> getMetaData() {
    return metaData;
  }

  public void setMetaData(HashMap<String, String> metaData) {
    this.metaData = metaData;
  }

  public void addMetaData(String key, String value){
    this.metaData.put(key,value);
  }

}
