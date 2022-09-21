package com.nhs.skill.exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;

}