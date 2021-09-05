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
package com.hemajoo.commerce.cherry.persistence.model.entity.base;

import com.hemajoo.commerce.cherry.commons.entity.EntityIdentity;
import com.hemajoo.commerce.cherry.commons.entity.IEntityIdentity;
import com.hemajoo.commerce.cherry.commons.type.EntityType;
import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

/**
 * Represents the base part of a persistence entity of the {@code Cherry} data model.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ENTITY")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity extends StatusEntity implements IEntityIdentity
{
    public static final String FIELD_ID             = "id";
    public static final String FIELD_ENTITY_TYPE    = "entityType";
    public static final String FIELD_NAME           = "name";
    public static final String FIELD_DESCRIPTION    = "description";
    public static final String FIELD_REFERENCE      = "reference";

    /**
     * Entity identifier.
     */
    @Getter
    @Setter
    @Id
    @Type(type = "uuid-char") // Allow to display in the DB the UUID as a string instead of a binary field!
    @GeneratedValue
    private UUID id;

    /**
     * Entity type.
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "ENTITY_TYPE", length = 50)
    private EntityType entityType;

    /**
     * Entity name.
     */
    @Getter
    @Setter
    @Column(name = "NAME")
    private String name;

    /**
     * Entity description.
     */
    @Getter
    @Setter
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Entity internal reference.
     */
    @Getter
    @Setter
    @Column(name = "REFERENCE", length = 100)
    private String reference;

    /**
     * Documents associated with this entity.
     */
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<DocumentEntity> documents = new ArrayList<>();

    /**
     * Creates a new base entity.
     * @param type Entity type.
     */
    protected BaseEntity(final EntityType type)
    {
        this.entityType = type;
    }

    @Override
    public EntityIdentity getIdentity()
    {
        return new EntityIdentity(id, entityType);
    }

    /**
     * Adds a document to this entityDocumentEntity.
     * @param document Document.
     */
    public final void addDocument(final @NonNull DocumentEntity document)
    {
        documents.add(document);
    }

    /**
     * Returns the documents associated to this entity.
     * @return List of documents.
     */
    public List<DocumentEntity> getDocuments()
    {
        if (entityType == EntityType.MEDIA)
        {
            return new ArrayList<>();
        }

        //return Collections.unmodifiableList(documents);
        return documents;
    }
}
