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
package com.hemajoo.commerce.cherry.persistence.test.model.entity.document;

import com.hemajoo.commerce.cherry.model.entity.document.Document;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.base.mapper.CycleAvoidingMappingContext;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentMapper;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentRandomizer;
import com.hemajoo.commerce.cherry.persistence.test.base.BaseMapperUnitTest;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test the {@link DocumentMapper} class.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
class TestDocumentMapper extends BaseMapperUnitTest
{
    /**
     * List element count.
     */
    private final int LIST_COUNT = 10;

    @Test
    @DisplayName("Map a persistent document to a client document and check they are equal")
    final void shouldMapPersistentDocumentToClientDocument()
    {
        DocumentEntity persistent = DocumentRandomizer.generatePersistent(true);
        Document client = DocumentMapper.INSTANCE.fromPersistent(persistent, new CycleAvoidingMappingContext());
        checkFields(persistent, client);
    }

    @Test
    @DisplayName("Map a list of persistent documents to a list of client documents and check they are equal")
    final void shouldMapListPersistentDocumentToListClientDocument() throws DocumentException
    {
        List<DocumentEntity> persistentList = new ArrayList<>();
        for (int i = 0; i < LIST_COUNT; i++)
        {
            persistentList.add(DocumentRandomizer.generatePersistent(true));
        }

        List<Document> clientList = DocumentMapper.INSTANCE.fromPersistentList(persistentList, new CycleAvoidingMappingContext());
        checkFields(persistentList, clientList);
    }

    @Test
    @DisplayName("Map a client document to a persistent document and check they are equal")
    final void shouldMapClientDocumentToPersistentDocument()
    {
        Document client = DocumentRandomizer.generateClient(true);
        DocumentEntity persistent = DocumentMapper.INSTANCE.fromClient(client, new CycleAvoidingMappingContext());
        checkFields(persistent, client);
    }

    @Test
    @DisplayName("Map a list of client documents to a list of persistent documents and check they are equal")
    final void shouldMapListClientDocumentToListPersistentDocument() throws DocumentException
    {
        List<Document> clientList = new ArrayList<>();
        for (int i = 0; i < LIST_COUNT; i++)
        {
            clientList.add(DocumentRandomizer.generateClient(true));
        }

        List<DocumentEntity> persistentList = DocumentMapper.INSTANCE.fromClientList(clientList, new CycleAvoidingMappingContext());
        checkFields(persistentList, clientList);
    }

    @Test
    @DisplayName("Create a deep copy of a client document and check they are equal")
    final void shouldDeepCopyClientDocument()
    {
        Document client = DocumentRandomizer.generateClient(true);
        Document copy = DocumentMapper.INSTANCE.copy(client, new CycleAvoidingMappingContext());
        checkFields(client, copy);
    }

    @Test
    @DisplayName("Create a deep copy of a persistent document and check they are equal")
    final void shouldDeepCopyPersistentDocument()
    {
        DocumentEntity persistent = DocumentRandomizer.generatePersistent(true);
        DocumentEntity copy = DocumentMapper.INSTANCE.copy(persistent, new CycleAvoidingMappingContext());
        checkFields(persistent, copy);
    }

    /**
     * Checks equality of fields between a persistent and a client entities.
     * @param persistent Persistent entity.
     * @param client Client entity.
     */
    private void checkFields(final @NonNull DocumentEntity persistent, final @NonNull Document client)
    {
        checkBaseFields(persistent, client);

        // Do not test equality for transient fields and for owner!
        Assertions.assertEquals(persistent.getFilename(), client.getFilename());
        Assertions.assertEquals(persistent.getDocumentType(), client.getDocumentType());
        Assertions.assertEquals(persistent.getExtension(), client.getExtension());
        Assertions.assertEquals(persistent.getTags(), client.getTags());
        Assertions.assertEquals(persistent.getContentId(), client.getContentId());
        Assertions.assertEquals(persistent.getContentPath(), client.getContentPath());
        Assertions.assertEquals(persistent.getContentLength(), client.getContentLength());
    }

    /**
     * Checks equality of fields between two client entities.
     * @param client Client entity.
     * @param copy Client entity.
     */
    private void checkFields(final @NonNull Document client, final @NonNull Document copy)
    {
        checkBaseFields(client, copy);

        // Do not test equality for transient fields and for owner!
        Assertions.assertEquals(client.getFilename(), copy.getFilename());
        Assertions.assertEquals(client.getDocumentType(), copy.getDocumentType());
        Assertions.assertEquals(client.getExtension(), copy.getExtension());
        Assertions.assertEquals(client.getTags(), copy.getTags());
        Assertions.assertEquals(client.getContentId(), copy.getContentId());
        Assertions.assertEquals(client.getContentPath(), copy.getContentPath());
        Assertions.assertEquals(client.getContentLength(), copy.getContentLength());
    }

    /**
     * Checks equality of fields between two persistent entities.
     * @param persistent Persistent entity.
     * @param copy Persistent entity.
     */
    private void checkFields(final @NonNull DocumentEntity persistent, final @NonNull DocumentEntity copy)
    {
        checkBaseFields(persistent, copy);

        // Do not test equality for transient fields and for owner!
        Assertions.assertEquals(persistent.getFilename(), copy.getFilename());
        Assertions.assertEquals(persistent.getDocumentType(), copy.getDocumentType());
        Assertions.assertEquals(persistent.getExtension(), copy.getExtension());
        Assertions.assertEquals(persistent.getTags(), copy.getTags());
        Assertions.assertEquals(persistent.getContentId(), copy.getContentId());
        Assertions.assertEquals(persistent.getContentPath(), copy.getContentPath());
        Assertions.assertEquals(persistent.getContentLength(), copy.getContentLength());
    }

    /**
     * Checks equality of fields between document entities.
     * @param persistentList List of persistent documents.
     * @param clientList List of client documents.
     * @throws DocumentException Thrown in case an error occurred finding a client document!
     */
    private void checkFields(final @NonNull List<DocumentEntity> persistentList, final @NonNull List<Document> clientList) throws DocumentException
    {
        Optional<Document> client;
        for (DocumentEntity persistent : persistentList)
        {
            client = clientList.stream().filter(element -> element.getId().equals(persistent.getId())).findFirst();
            if (client.isPresent())
            {
                checkBaseFields(persistent, client.get());
            }
            else
            {
                throw new DocumentException(String.format("Cannot find client document with id: '%s'", persistent.getId()));
            }
        }
    }
}
