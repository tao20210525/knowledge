package com.knowledge.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnowledgeException extends Exception {

    private Logger logger = LoggerFactory.getLogger(KnowledgeException.class);

    public KnowledgeException(String s) {
        logger.error("知识库系统功能模块异常:"+s);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
