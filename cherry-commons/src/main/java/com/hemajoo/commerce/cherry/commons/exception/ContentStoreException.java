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
package com.hemajoo.commerce.cherry.commons.exception;

import com.hemajoo.commerce.cherry.commons.type.EntityType;
import org.springframework.http.HttpStatus;

/**
 * Checked exception thrown to indicate an error occurred with the content store.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class ContentStoreException extends EntityException
{
    /**
     * Default serialization identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param exception Parent exception.
     */
    public ContentStoreException(final Exception exception)
    {
        super(EntityType.DOCUMENT, exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param exception Parent exception.
     * @param status {@link HttpStatus}.
     */
    public ContentStoreException(final Exception exception, final HttpStatus status)
    {
        super(EntityType.DOCUMENT, exception, status);
    }

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param message Message describing the error being the cause of the raised exception.
     */
    public ContentStoreException(final String message)
    {
        super(EntityType.DOCUMENT, message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param message Message describing the error being the cause of the raised exception.
     * @param status {@link HttpStatus}.
     */
    public ContentStoreException(final String message, final HttpStatus status)
    {
        super(EntityType.DOCUMENT, message, status);
    }

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param message Message describing the error being the cause of the raised exception.
     * @param exception Parent exception.
     */
    public ContentStoreException(final String message, final Exception exception)
    {
        super(EntityType.DOCUMENT, message, exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown to indicate that an error occurred with the content store.
     * @param message Message describing the error being the cause of the raised exception.
     * @param exception Parent exception.
     * @param status {@link HttpStatus}.
     */
    public ContentStoreException(final String message, final Exception exception, final HttpStatus status)
    {
        super(EntityType.DOCUMENT, message, exception, status);
    }
}
