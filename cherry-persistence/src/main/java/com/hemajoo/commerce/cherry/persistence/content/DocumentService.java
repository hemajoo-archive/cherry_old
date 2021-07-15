/*
 * Copyright(c) 2021 Hemajoo Digital Systems Inc.
 * --------------------------------------------------------------------------------------
 * This file is part of Hemajoo Systems Inc. projects which is licensed
 * under the Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * --------------------------------------------------------------------------------------
 */
package com.hemajoo.commerce.cherry.persistence.content;

import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Document persistence service.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface DocumentService
{
    /**
     * Returns the number of documents.
     * @return Number of documents.
     */
    Long count();

    /**
     * Finds a document given its identifier.
     * @param id Document identifier.
     * @return Document if found, null otherwise.
     */
    DocumentEntity findById(UUID id);

    /**
     * Saves a document.
     * @param document Document to save.
     * @return Saved document.
     * @throws DocumentException Raised if an error occurred while trying to save the document.
     */
    DocumentEntity save(DocumentEntity document) throws DocumentException;

    /**
     * Deletes a document given its identifier.
     * @param id Document identifier.
     */
    void deleteById(UUID id);

    /**
     * Returns all the documents.
     * @return List of documents.
     */
    List<DocumentEntity> findAll();

    InputStream getContent(DocumentEntity document);

    InputStream getContent(UUID documentId) throws DocumentException;
}
