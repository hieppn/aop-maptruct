package com.example.springboot.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document()
public class Logger {
        private Date timestamp;
        private String level;
        private String className;
        private String methodName;
        private String operation;
        private String returned;
        private String exceptionMessage;
        private String arguments;
        private Long executionTime;
        private Long count;
        private Long elapsedTime;
        private Long maxTime;
        private Long avgTime;
        private Long runningAvg;
    public static class Builder {
        private Logger logger;

        public Builder(Level level, String className, String methodName) {
            logger = new Logger();
            logger.timestamp = new Date();
            logger.level = level.name();
            logger.className = className;
            logger.methodName = methodName;
        }

        public Builder setExecutionTime(Long executionTime) {
            logger.executionTime = executionTime;
            return this;
        }

        public Builder setArguments(Object... arguments) {
            logger.arguments = "Arguments: ".concat(Arrays.stream(arguments).map(Object::toString).collect(Collectors.joining(",")));
            return this;
        }

        public Builder setElapsedTime(Long elapsedTime) {
            logger.elapsedTime = elapsedTime;
            return this;
        }

        public Builder setMaxTime(Long maxTime) {
            logger.maxTime = maxTime;
            return this;
        }

        public Builder setAvgTime(Long avgTime) {
            logger.avgTime = avgTime;
            return this;
        }

        public Builder setRunningAvg(Long runningAvg) {
            logger.runningAvg = runningAvg;
            return this;
        }

        public Builder setOperation(Operation operation) {
            logger.operation = operation.getDescription();
            return this;
        }

        public Builder setReturn(Object returned) {
            logger.returned = returned.toString();
            return this;
        }

        public Builder setExceptionMessage(String exceptionMessage) {
            logger.exceptionMessage = exceptionMessage;
            return this;
        }

        public Builder setCount(Long count) {
            logger.count = count;
            return this;
        }

        public Logger build() {
            return logger;
        }
    }
    }