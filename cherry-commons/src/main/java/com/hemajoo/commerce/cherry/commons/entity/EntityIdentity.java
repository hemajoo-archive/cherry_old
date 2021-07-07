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
package com.hemajoo.commerce.cherry.commons.entity;

import com.hemajoo.commerce.cherry.commons.type.EntityType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * An entity identity contains the entity type and its unique identifier.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class EntityIdentity
{
    /**
     * Entity type.
     */
    @ApiModelProperty(hidden = true)
    @Getter
    private final EntityType entityType;

    /**
     * Entity unique identifier.
     */
    @Getter
    private final UUID id;

    /**
     * Creates a new entity identity.
     * @param id Entity identifier.
     * @param type Entity type.
     */
    public EntityIdentity(final UUID id, final EntityType type)
    {
        this.id = id;
        this.entityType = type;
    }
}
