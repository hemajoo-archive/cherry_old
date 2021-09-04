/*
 * Copyright(c) 2021 Hemajoo Systems Inc.
 * --------------------------------------------------------------------------------------
 * This file is part of Hemajoo Systems Inc. projects which is licensed
 * under the Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * --------------------------------------------------------------------------------------
 */
package com.hemajoo.commerce.cherry.model.entity.document;

import com.hemajoo.commerce.cherry.commons.exception.EntityException;
import com.hemajoo.commerce.cherry.commons.type.EntityType;
import org.springframework.http.HttpStatus;

/**
 * Checked exception thrown to indicate an error occurred with a document content.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class DocumentContentException extends EntityException
{
    /**
     * Default serialization identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param exception Parent exception.
     */
    public DocumentContentException(final Exception exception)
    {
        super(EntityType.DOCUMENT_CONTENT, exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param exception Parent exception.
     * @param status {@link HttpStatus}.
     */
    public DocumentContentException(final Exception exception, final HttpStatus status)
    {
        super(EntityType.DOCUMENT_CONTENT, exception, status);
    }

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param message Message describing the error being the cause of the raised exception.
     */
    public DocumentContentException(final String message)
    {
        super(EntityType.DOCUMENT_CONTENT, message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param message Message describing the error being the cause of the raised exception.
     * @param status {@link HttpStatus}.
     */
    public DocumentContentException(final String message, final HttpStatus status)
    {
        super(EntityType.DOCUMENT_CONTENT, message, status);
    }

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param message Message describing the error being the cause of the raised exception.
     * @param exception Parent exception.
     */
    public DocumentContentException(final String message, final Exception exception)
    {
        super(EntityType.DOCUMENT_CONTENT, message, exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with a document content.
     * @param message Message describing the error being the cause of the raised exception.
     * @param exception Parent exception.
     * @param status {@link HttpStatus}.
     */
    public DocumentContentException(final String message, final Exception exception, final HttpStatus status)
    {
        super(EntityType.DOCUMENT_CONTENT, message, exception, status);
    }
}
