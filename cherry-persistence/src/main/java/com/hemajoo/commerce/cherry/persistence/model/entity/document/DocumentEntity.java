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
package com.hemajoo.commerce.cherry.persistence.model.entity.document;

import com.hemajoo.commerce.cherry.commons.type.EntityType;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentType;
import com.hemajoo.commerce.cherry.persistence.model.entity.base.BaseEntity;
import lombok.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents a persistent document entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@Table(name = "DOCUMENT")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DocumentEntity extends BaseEntity
{
    /**
     * Document type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "DOCUMENT_TYPE", length = 50)
    @Getter
    @Setter
    private DocumentType documentType;

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
    @Column(name = "TAGS")
    private String tags;

    /**
     * Document file name.
     */
    @Getter
    @Column(name = "FILENAME")
    private String filename;

    /**
     * Multi part file.
     */
    @EqualsAndHashCode.Exclude
    @Getter
    @Transient
    private transient MultipartFile multiPartFile; // Only stored until the content of the file is loaded into the content store.

    /**
     * Base file name.
     */
    @EqualsAndHashCode.Exclude
    @Getter
    @Transient
    private transient String baseFilename; // Only stored until the content of the file is loaded into the content store.

    /**
     * File content identifier.
     * <br>
     * UUID of the file in the content store.
     */
    @Getter
    @Setter
    @ContentId
    private String contentId;

    /**
     * File content length.
     */
    @Getter
    @Setter
    @ContentLength
    private long contentLength;

    /**
     * File MIME type.
     */
    @Getter
    @Setter
    @MimeType
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
    @ToString.Exclude
    @Getter
    @Setter
    @ManyToOne(targetEntity = BaseEntity.class, fetch = FetchType.EAGER)
    private BaseEntity owner;

    /**
     * Creates a new document.
     * @param owner Document owner.
     * @param documentType Document type.
     */
    public DocumentEntity(final @NonNull BaseEntity owner, final @NonNull DocumentType documentType)
    {
        super(EntityType.DOCUMENT);

        setActive();
        this.documentType = documentType;
        this.owner = owner;
        owner.addDocument(this);
    }

    /**
     * Creates a new document given its path name.
     * @param owner Document owner.
     * @param documentType Document type.
     * @param filename File name.
     * @throws DocumentException Thrown in case an error occurred while accessing the document file.
     */
    public DocumentEntity(final @NonNull BaseEntity owner, final @NonNull DocumentType documentType, final @NonNull String filename) throws DocumentException
    {
        this(owner, documentType);

        this.filename = filename;
        setName(FilenameUtils.getName(FilenameUtils.removeExtension(filename)));
        setExtension(FilenameUtils.getExtension(this.filename));

        detectMimeType();
    }

    /**
     * Creates a new document given its path name.
     * @param owner Document owner.
     * @param documentType Document type.
     * @param multiPartFile Multi part file.
     * @throws DocumentException Thrown in case an error occurred while accessing the document file.
     */
    public DocumentEntity(final @NonNull BaseEntity owner, final @NonNull DocumentType documentType, final @NonNull MultipartFile multiPartFile) throws DocumentException
    {
        this(owner, documentType);

        this.filename = multiPartFile.getOriginalFilename();
        this.multiPartFile = multiPartFile;
        setName(FilenameUtils.getName(FilenameUtils.removeExtension(filename)));
        setExtension(FilenameUtils.getExtension(filename));
        detectMimeType();
    }

    /**
     * Detects the media file MIME type.
     * @throws DocumentException Thrown in case an error occurred while processing the media file.
     */
    private void detectMimeType() throws DocumentException
    {
        try
        {
            if (this.getMultiPartFile() != null)
            {
                mimeType = new Tika().detect(this.getMultiPartFile().getInputStream());
            }
            else
            {
                mimeType = new Tika().detect(new File(this.getFilename()));
            }
        }
        catch (IOException e)
        {
            throw new DocumentException(e.getMessage());
        }
    }

    /**
     * Returns the document output full path name (path + name).
     * @param outputPath Output path and file name.
     * @return Document full path and file name.
     */
    public final String getOutputFilename(final @NonNull String outputPath)
    {
        String end = getName() + "." + extension;

        return outputPath.endsWith(File.separator)
                ? outputPath + end
                : outputPath + File.separator + end;
    }
}