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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the document persistence service.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Service
public class DocumentServiceCore implements DocumentService
{
    /**
     * Document repository.
     */
    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Content store repository.
     */
    @Autowired
    private ContentStoreRepository contentStoreRepository;

    @Override
    public Long count()
    {
        return documentRepository.count();
    }

    @Override
    public DocumentEntity findById(UUID id)
    {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    //@Transactional(rollbackOn = DocumentException.class) // TODO Should be removed safely!
    public DocumentEntity save(DocumentEntity document)
    {
        document = documentRepository.save(document);

        // Also save the associated content file if one.
        if (document.getContent() != null)
        {
            contentStoreRepository.setContent(document, document.getContent());
            document.setContent(null);
        }

        return document;
    }

    @Override
    public void deleteById(UUID id)
    {
        DocumentEntity document = findById(id);

        // If a content file is associated, then delete it!
        if (document != null && document.getContentId() != null)
        {
            contentStoreRepository.unsetContent(document);
        }

        documentRepository.deleteById(id);
    }

    @Override
    public List<DocumentEntity> findAll()
    {
        return documentRepository.findAll();
    }

    @Override
    public InputStream getContent(DocumentEntity document)
    {
        return contentStoreRepository.getContent(document);
    }

    @Override
    public InputStream getContent(UUID documentId) throws DocumentException
    {
        DocumentEntity document = findById(documentId);
        if (document != null)
        {
            return contentStoreRepository.getContent(document);
        }

        throw new DocumentException(String.format("Cannot find document id.: '%s'", documentId.toString()));
    }
}

