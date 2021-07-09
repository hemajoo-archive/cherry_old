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
package com.hemajoo.commerce.cherry.model.domain.document.type;

import lombok.NonNull;

import java.util.Arrays;

/**
 * Enumeration representing the several possible document types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DocumentType
{
    /**
     * Unspecified document type.
     */
    UNSPECIFIED,

    /**
     * Generic document type.
     */
    DOCUMENT_GENERIC,

    /**
     * Photo document type.
     */
    DOCUMENT_PHOTO,

    /**
     * Invoice document type.
     */
    DOCUMENT_INVOICE,

    /**
     * Icon document type.
     */
    DOCUMENT_ICON;

    /**
     * Creates a new document type given its type as a string.
     * @param value Document type as a string.
     * @return Document type.
     * @throws IllegalArgumentException Thrown in case an error occurred while trying to create a document type enumerated value.
     */
    public static DocumentType from(final @NonNull String value)
    {
        for (DocumentType element : DocumentType.values())
        {
            if (element.name().equalsIgnoreCase(value))
            {
                return element;
            }
        }

        throw new IllegalArgumentException(String.format(
                "Cannot created a document type with value: '%s'. Possible values are: '%s'",
                value,
                Arrays.toString(DocumentType.values())));
    }
}
