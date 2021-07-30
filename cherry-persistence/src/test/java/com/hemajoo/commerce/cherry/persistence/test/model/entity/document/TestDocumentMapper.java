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
import com.hemajoo.commerce.cherry.model.entity.document.DocumentContentException;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.base.mapper.CycleAvoidingMappingContext;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentMapper;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentRandomizer;
import com.hemajoo.commerce.cherry.persistence.test.base.BaseMapperUnitTest;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("Map a persistent document to a client document")
    final void testMapPersistentDocumentToClientDocument() throws DocumentContentException
    {
        DocumentEntity persistent = DocumentRandomizer.generatePersistent(true);
        Document client = DocumentMapper.INSTANCE.fromPersistent(persistent, new CycleAvoidingMappingContext());
        checkFields(persistent, client);
    }

    @Test
    @DisplayName("Map a list of persistent documents to a list of client documents")
    final void testMapListPersistentDocumentToListClientDocument() throws DocumentContentException, DocumentException
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
    @DisplayName("Map a client document to a persistent document")
    final void testMapClientDocumentToPersistentDocument() throws DocumentContentException
    {
        Document client = DocumentRandomizer.generateClient(true);
        DocumentEntity persistent = DocumentMapper.INSTANCE.fromClient(client, new CycleAvoidingMappingContext());
        checkFields(persistent, client);
    }

    @Test
    @DisplayName("Map a list of client documents to a list of persistent documents")
    final void testMapListClientDocumentToListPersistentDocument() throws DocumentContentException, DocumentException
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
    @DisplayName("Create a deep copy of a client document")
    final void testDeepCopyClientDocument() throws DocumentContentException
    {
        Document client = DocumentRandomizer.generateClient(true);
        Document copy = DocumentMapper.INSTANCE.copy(client, new CycleAvoidingMappingContext());
        checkFields(client, copy);
    }

    @Test
    @DisplayName("Create a deep copy of a persistent document")
    final void testDeepCopyPersistentDocument() throws DocumentContentException, DocumentException
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
        assertThat(persistent.getFilename())
                .as("Filename should be equal!")
                .isEqualTo(client.getFilename());
        assertThat(persistent.getDocumentType())
                .as("Document type should be equal!")
                .isEqualTo(client.getDocumentType());
        assertThat(persistent.getTags())
                .as("Tags type should be equal!")
                .isEqualTo(client.getTags());
        assertThat(persistent.getExtension())
                .as("Extension type should be equal!")
                .isEqualTo(client.getExtension());
        assertThat(persistent.getContentId())
                .as("Content id type should be equal!")
                .isEqualTo(client.getContentId());
        assertThat(persistent.getContentPath())
                .as("Content path type should be equal!")
                .isEqualTo(client.getContentPath());
        assertThat(persistent.getContentLength())
                .as("Content length type should be equal!")
                .isEqualTo(client.getContentLength());
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
        assertThat(client.getFilename())
                .as("Filename should be equal!")
                .isEqualTo(copy.getFilename());
        assertThat(client.getDocumentType())
                .as("Document type should be equal!")
                .isEqualTo(copy.getDocumentType());
        assertThat(client.getTags())
                .as("Tags type should be equal!")
                .isEqualTo(copy.getTags());
        assertThat(client.getExtension())
                .as("Extension type should be equal!")
                .isEqualTo(copy.getExtension());
        assertThat(client.getContentId())
                .as("Content id type should be equal!")
                .isEqualTo(copy.getContentId());
        assertThat(client.getContentPath())
                .as("Content path type should be equal!")
                .isEqualTo(copy.getContentPath());
        assertThat(client.getContentLength())
                .as("Content length type should be equal!")
                .isEqualTo(copy.getContentLength());
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
        assertThat(persistent.getFilename())
                .as("Filename should be equal!")
                .isEqualTo(copy.getFilename());
        assertThat(persistent.getDocumentType())
                .as("Document type should be equal!")
                .isEqualTo(copy.getDocumentType());
        assertThat(persistent.getTags())
                .as("Tags type should be equal!")
                .isEqualTo(copy.getTags());
        assertThat(persistent.getExtension())
                .as("Extension type should be equal!")
                .isEqualTo(copy.getExtension());
        assertThat(persistent.getContentId())
                .as("Content id type should be equal!")
                .isEqualTo(copy.getContentId());
        assertThat(persistent.getContentPath())
                .as("Content path type should be equal!")
                .isEqualTo(copy.getContentPath());
        assertThat(persistent.getContentLength())
                .as("Content length type should be equal!")
                .isEqualTo(copy.getContentLength());
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
                throw new DocumentException(String.format("Cannot find client document with id: '%s'!", persistent.getId()));
            }
        }
    }
}
