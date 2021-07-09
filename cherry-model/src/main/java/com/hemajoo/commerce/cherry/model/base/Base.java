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
package com.hemajoo.commerce.cherry.model.base;

import com.hemajoo.commerce.cherry.commons.entity.EntityIdentity;
import com.hemajoo.commerce.cherry.commons.entity.IEntityIdentity;
import com.hemajoo.commerce.cherry.commons.type.EntityType;
import com.hemajoo.commerce.cherry.model.domain.document.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the (abstract) base part of a (client) entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Base extends Status implements IEntityIdentity
{
    /**
     * Entity identifier.
     */
    @ApiModelProperty(name = "id", notes = "Identifier")
    private UUID id;

    /**
     * Entity type.
     */
    @ApiModelProperty(name = "entityType", notes = "Entity type", value = "PERSON")
    private EntityType entityType;

    /**
     * Entity name.
     */
    @ApiModelProperty(name = "name", notes = "Name")
    private String name;

    /**
     * Entity description.
     */
    @ApiModelProperty(name = "description", notes = "Description")
    private String description;

    /**
     * Entity reference.
     */
    @ApiModelProperty(name = "reference", notes = "Reference")
    private String reference;

    /**
     * Entity documents.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(hidden = true)
    private final List<Document> documents = new ArrayList<>();

    /**
     * Creates a new (client) base entity type
     * @param type Entity type.
     */
    protected Base(final EntityType type)
    {
        this.entityType = type;
    }

    @Override
    public final EntityIdentity getIdentity()
    {
        return new EntityIdentity(id, entityType);
    }

    @Override
    public final EntityType getEntityType()
    {
        return entityType;
    }
    /**
     * Returns the documents associated with this entity.
     * @return List of documents.
     */
    public final List<Document> getDocuments()
    {
        if (entityType == EntityType.DOCUMENT)
        {
            return new ArrayList<>();
        }

        return documents;
    }


}
