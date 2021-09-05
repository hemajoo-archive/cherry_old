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

import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * {@code JPA} repository for the document entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID>
{
    /**
     * Returns a list of documents belonging to the given owner identifier.
     * @param id Owner identifier.
     * @return List of documents.
     */
    List<DocumentEntity> findByOwnerId(UUID id);
}