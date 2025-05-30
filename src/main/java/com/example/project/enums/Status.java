package com.example.project.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
    public enum Status {
        ACTIVE,
        INACTIVE;

        @JsonCreator
        public static Status fromString(String value) {
            return value == null ? null : Status.valueOf(value.toUpperCase());
        }

        @JsonValue
        public String getValue() {
            return name();
        }
    }


