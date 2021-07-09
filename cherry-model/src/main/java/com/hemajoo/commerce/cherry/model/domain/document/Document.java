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
package com.hemajoo.commerce.cherry.model.domain.document;

import com.hemajoo.commerce.cherry.commons.type.EntityType;
import com.hemajoo.commerce.cherry.model.base.Base;
import com.hemajoo.commerce.cherry.model.domain.document.type.DocumentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a document on the client side.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class Document extends Base
{
    /**
     * Document type.
     */
    @Getter
    @Setter
    private transient DocumentType documentType;

    /**
     * Document file extension.
     */
    @Getter
    @Setter
    private String extension;

    /**
     * Document tags.
     */
    @Getter
    @Setter
    private String tags;

    /**
     * Document file name.
     */
    @Getter
    @Setter
    private String filename;

    /**
     * File content identifier.
     * <br>
     * UUID of the file in the content store.
     */
    @Getter
    @Setter
    private String contentId;

    /**
     * File content length.
     */
    @Getter
    @Setter
    private long contentLength;

    /**
     * File MIME type.
     */
    @Getter
    @Setter
    private String mimeType = "text/plain";

    /**
     * File path (in the content store).
     */
    @Getter
    @Setter
    private String contentPath;

    /**
     * Document owner.
     */
    @Getter
    @Setter
    private Base owner;

    /**
     * Creates a new document.
     */
    public Document()
    {
        super(EntityType.DOCUMENT);

        setActive();
    }
}