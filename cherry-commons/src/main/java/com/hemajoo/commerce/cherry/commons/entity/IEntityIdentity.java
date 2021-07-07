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

/**
 * Provides the behavior for an entity to know its identity (its type and its unique identifier).
 * <p>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IEntityIdentity
{
    /**
     * Returns the identity of the entity.
     * @return Entity identity that can be used to retrieve any kind of entity in the {@code Cherry} system.
     */
    EntityIdentity getIdentity();

    /**
     * Returns the entity type.
     * @return Entity type.
     */
    EntityType getEntityType();
}
