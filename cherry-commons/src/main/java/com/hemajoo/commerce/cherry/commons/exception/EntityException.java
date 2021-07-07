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
import lombok.Getter;
import org.ressec.avocado.core.exception.checked.AbstractCheckedException;
import org.springframework.http.HttpStatus;

/**
 * Checked exception thrown to indicate an error occurred with an entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class EntityException extends AbstractCheckedException
{
    /**
     * Http status.
     */
    @Getter
    private final HttpStatus status;

    /**
     * Underlying entity type.
     */
    @Getter
    private final EntityType entityType;

    /**
     * Default serialization identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Thrown to indicate that an error occurred with an entity.
     * @param type Underlying entity type.
     * @param exception Parent {@link Exception}.
     * @param status Http status type.
     * @see EntityType
     * @see HttpStatus
     */
    public EntityException(final EntityType type, final Exception exception, final HttpStatus status)
    {
        super(exception);
        this.status = status;
        this.entityType = type;
    }

    /**
     * Thrown to indicate that an error occurred with an entity.
     * @param type Underlying entity type.
     * @param message Message describing the error being the cause of the raised exception.
     * @param status Http status type.
     * @see EntityType
     * @see HttpStatus
     */
    public EntityException(final EntityType type, final String message, final HttpStatus status)
    {
        super(message);
        this.status = status;
        this.entityType = type;
    }

    /**
     * Thrown to indicate that an error occurred with an entity.
     * @param type Underlying entity type.
     * @param message Message describing the error being the cause of the raised exception.
     * @param exception Parent {@link Exception}.
     * @param status Http status type.
     * @see EntityType
     * @see HttpStatus
     */
    public EntityException(final EntityType type, final String message, final Exception exception, final HttpStatus status)
    {
        super(message, exception);
        this.status = status;
        this.entityType = type;
    }
}
