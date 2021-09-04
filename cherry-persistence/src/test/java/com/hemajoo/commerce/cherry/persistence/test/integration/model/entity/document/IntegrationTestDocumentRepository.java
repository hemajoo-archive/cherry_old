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
package com.hemajoo.commerce.cherry.persistence.test.integration.model.entity.document;

import com.hemajoo.commerce.cherry.model.entity.document.DocumentContentException;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.base.test.BaseDatabaseUnitTest;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentRandomizer;
import com.hemajoo.commerce.cherry.persistence.test.integration.SpringCherryForIntegrationTest;
import com.hemajoo.commerce.cherry.persistence.test.integration.configuration.PersistenceConfigurationForIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the document database table and associated content store.
 * <p>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SpringBootTest(classes = SpringCherryForIntegrationTest.class)
@ActiveProfiles("test") // Will search for: application-test.properties
@ExtendWith(SpringExtension.class)
@Import(value = {PersistenceConfigurationForIntegrationTest.class})
@Transactional
@Commit // Change default behavior for Spring Test which is normally to rollback transaction at the end of the test!
@DisplayName("Test handle document entity in database")
class IntegrationTestDocumentRepository extends BaseDatabaseUnitTest
{
    @Test
    @DisplayName("Create a document (without content) in the database") final void testCreateDocumentWithoutContentInDatabase() throws DocumentContentException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        documentService.save(entity);

        DocumentEntity document = documentService.findById(entity.getId());

        assertThat(document)
                .as("Document must not be null!")
                .isNotNull();
    }

    @Test
    @DisplayName("Create a document in the database with associated content in the content store")
    final void testCreateDocumentWithContentInDatabase() throws DocumentContentException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        entity.setContent("./media/java-8-streams-cheat-sheet.pdf");

        // Save it and store the associated media file.
        documentService.save(entity);

        DocumentEntity document = documentService.findById(entity.getId());
        assertThat(document)
                .as("Document must not be null!")
                .isNotNull();
        assertThat(document.getContent())
                .as("Document content must not be null!")
                .isNotNull();
    }

    @Test
    @DisplayName("Update a document (without content) in the database")
    final void testUpdateDocumentWithoutContentInDatabase() throws DocumentContentException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        documentService.save(entity);

        // Update the document 'tags'.
        entity = documentService.findById(entity.getId());
        entity.setTags("cherry");
        documentService.save(entity);

        // Assert the document has been updated in the database!
        DocumentEntity other = documentService.findById(entity.getId());

        assertThat(other)
                .as("Updated document should not be null!")
                .isNotNull();
        assertThat(other.getTags())
                .as("Document tags should be equal to: 'cherry'")
                .isEqualTo("cherry");
    }

    @Test
    @DisplayName("Update a document content in the database")
    final void testUpdateDocumentContentInDatabase() throws DocumentContentException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        entity.setContent("./media/java-8-streams-cheat-sheet.pdf");
        long length = entity.getContentLength();
        documentService.save(entity);

        // Update the document content.
        DocumentEntity other = documentService.findById(entity.getId());
        entity.setContent("./media/android-10.jpg");
        documentService.save(entity);

        // Assert the document has been updated in the database!
        DocumentEntity element = documentService.findById(entity.getId());
        assertThat(element)
                .as("Updated document should not be null!")
                .isNotNull();
        assertThat(other.getContentLength())
                .as(String.format("Document content length should be equal to: '%s'", element.getContentLength()))
                .isEqualTo(element.getContentLength());
    }

    @Test
    @DisplayName("Delete a document in the database") final void testDeleteDocumentInDatabase() throws DocumentContentException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        entity.setContent("./media/java-8-streams-cheat-sheet.pdf");
        documentService.save(entity);

        // Fetch the document back and delete it from the database.
        DocumentEntity other = documentService.findById(entity.getId());
        assertThat(other.getContent())
                .as("Document content must not be null!")
                .isNotNull();
        documentService.deleteById(other.getId());

        DocumentEntity element = documentService.findById(other.getId());
        assertThat(element)
                .as("Deleted document should be null!")
                .isNull();
    }

    @Test
    @DisplayName("Delete multiple documents in the database") final void testDeleteMultipleDocumentInDatabase() throws DocumentContentException, DocumentException
    {
        List<DocumentEntity> documents = new ArrayList<>();
        List<String> uuids = new ArrayList<>();
        DocumentEntity entity;

        for (int i = 0; i < 100; i++)
        {
            entity = DocumentRandomizer.generatePersistent(false);
            entity.setContent("./media/java-8-streams-cheat-sheet.pdf");
            documentService.save(entity);
            documents.add(entity);
            uuids.add(entity.getContentId());
        }

        for (DocumentEntity element : documents)
        {
            documentService.deleteById(element.getId());
        }

        for (String uuid : uuids)
        {
            assertThat(documentStore.getResource(uuid).exists())
                    .as(String.format("Resource with sid: '%s' should not exist anymore!", uuid))
                    .isFalse();
        }
    }
}
