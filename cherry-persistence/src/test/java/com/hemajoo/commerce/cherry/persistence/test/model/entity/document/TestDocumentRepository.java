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

import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.content.ContentStoreRepository;
import com.hemajoo.commerce.cherry.persistence.content.DocumentRepository;
import com.hemajoo.commerce.cherry.persistence.content.DocumentService;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentRandomizer;
import com.hemajoo.commerce.cherry.persistence.test.SpringTestCherry;
import com.hemajoo.commerce.cherry.persistence.test.base.BaseDatabaseUnitTest;
import com.hemajoo.commerce.cherry.persistence.test.configuration.TestPersistenceConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ressec.avocado.core.exception.checked.FileException;
import org.ressec.avocado.core.helper.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Test class for the document database table and associated content store.
 * <p>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SpringBootTest(classes = SpringTestCherry.class)
@ActiveProfiles("test") // Will search for: application-test.properties
@ExtendWith(SpringExtension.class)
@Import(value = { TestPersistenceConfiguration.class })
@Transactional
@Commit // Change default behavior for Spring Test which is normally to rollback transaction at the end of the test!
@DisplayName("Test handle document entity in database")
class TestDocumentRepository extends BaseDatabaseUnitTest
{
    /**
     * Document persistence service.
     */
    @Autowired
    private DocumentService documentService;

    /**
     * Document content store repository.
     */
    @Autowired
    private ContentStoreRepository contentRepository;

    @Test
    @DisplayName("Create a document (without content) in the database")
    final void shouldCreateDocumentWithoutContentInDatabase() throws DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        documentService.save(entity);

        // Assert the document exist in the database!
        Assertions.assertNotNull(documentService.findById(entity.getId()));
    }

    @Test
    @DisplayName("Create a document in the database with associated content in the content store")
    final void shouldCreateDocumentWithContentInDatabase() throws FileException, FileNotFoundException, DocumentException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        entity.setContent(new FileInputStream(FileHelper.getFile("./media/java-8-streams-cheat-sheet.pdf")));
        //FileInputStream fis = new FileInputStream(FileHelper.getFile("./media/java-8-streams-cheat-sheet.pdf"));

        // Save it and store the associated media file.
        documentService.save(entity);
        //contentRepository.setContent(entity, fis); // TODO Content should be saved in the document repository service directly!

        // Assert the document exist in the database and the content (media) can be retrieved!
        Assertions.assertNotNull(documentService.findById(entity.getId()));
        Assertions.assertNotNull(documentService.getContent(entity)); // TODO DocumentEntity should provide a getContent() service!
    }

    @Test
    @DisplayName("Update a document (without content) in the database")
    final void shouldUpdateDocumentWithoutContentInDatabase() throws DocumentException
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
        Assertions.assertNotNull(other);
        Assertions.assertEquals("cherry", other.getTags());
    }

    @Test
    @DisplayName("Update a document content in the database")
    final void shouldUpdateDocumentContentInDatabase() throws FileNotFoundException, DocumentException, FileException
    {
        // Generate a random document.
        DocumentEntity entity = DocumentRandomizer.generatePersistent(false);
        entity.setContent(new FileInputStream(FileHelper.getFile("./media/java-8-streams-cheat-sheet.pdf")));
        documentService.save(entity);

        // Update the document content.
        DocumentEntity other = documentService.findById(entity.getId());
        entity.setContent(new FileInputStream(FileHelper.getFile("./media/android-10.jpg")));
        documentService.save(entity);

        // Assert the document has been updated in the database!
        DocumentEntity element = documentService.findById(entity.getId());
        Assertions.assertNotNull(element);
        Assertions.assertNotNull(element.getContent());
    }
}
