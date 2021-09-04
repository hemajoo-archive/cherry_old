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
package com.hemajoo.commerce.cherry.persistence.test.unit.base;

import com.hemajoo.commerce.cherry.model.entity.base.Base;
import com.hemajoo.commerce.cherry.persistence.model.entity.base.BaseEntity;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.ressec.avocado.core.junit.BaseUnitTest;

/**
 * Base mapper unit test.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class BaseMapperTest extends BaseUnitTest
{
    /**
     * Checks equality of base fields.
     * @param persistent Base persistent entity.
     * @param client Base client entity.
     */
    protected void checkBaseFields(final @NonNull BaseEntity persistent, final @NonNull Base client)
    {
        Assertions.assertEquals(persistent.getId(), client.getId());
        Assertions.assertEquals(persistent.getCreatedBy(), client.getCreatedBy());
        Assertions.assertEquals(persistent.getCreatedDate(), client.getCreatedDate());
        Assertions.assertEquals(persistent.getModifiedBy(), client.getModifiedBy());
        Assertions.assertEquals(persistent.getModifiedDate(), client.getModifiedDate());
        Assertions.assertEquals(persistent.getStatusType(), client.getStatusType());
        Assertions.assertEquals(persistent.getSince(), client.getSince());
        Assertions.assertEquals(persistent.getEntityType(), client.getEntityType());
        Assertions.assertEquals(persistent.getDescription(), client.getDescription());
        Assertions.assertEquals(persistent.getReference(), client.getReference());
        Assertions.assertEquals(persistent.getName(), client.getName());
    }

    /**
     * Checks equality of base fields.
     * @param persistent Base persistent entity.
     * @param copy Base persistent entity.
     */
    protected void checkBaseFields(final @NonNull BaseEntity persistent, final @NonNull BaseEntity copy)
    {
        Assertions.assertEquals(persistent.getId(), copy.getId());
        Assertions.assertEquals(persistent.getCreatedBy(), copy.getCreatedBy());
        Assertions.assertEquals(persistent.getCreatedDate(), copy.getCreatedDate());
        Assertions.assertEquals(persistent.getModifiedBy(), copy.getModifiedBy());
        Assertions.assertEquals(persistent.getModifiedDate(), copy.getModifiedDate());
        Assertions.assertEquals(persistent.getStatusType(), copy.getStatusType());
        Assertions.assertEquals(persistent.getSince(), copy.getSince());
        Assertions.assertEquals(persistent.getEntityType(), copy.getEntityType());
        Assertions.assertEquals(persistent.getDescription(), copy.getDescription());
        Assertions.assertEquals(persistent.getReference(), copy.getReference());
        Assertions.assertEquals(persistent.getName(), copy.getName());
    }

    /**
     * Checks equality of base fields.
     * @param client Base client entity.
     * @param copy Base client entity.
     */
    protected void checkBaseFields(final @NonNull Base client, final @NonNull Base copy)
    {
        Assertions.assertEquals(client.getId(), copy.getId());
        Assertions.assertEquals(client.getCreatedBy(), copy.getCreatedBy());
        Assertions.assertEquals(client.getCreatedDate(), copy.getCreatedDate());
        Assertions.assertEquals(client.getModifiedBy(), copy.getModifiedBy());
        Assertions.assertEquals(client.getModifiedDate(), copy.getModifiedDate());
        Assertions.assertEquals(client.getStatusType(), copy.getStatusType());
        Assertions.assertEquals(client.getSince(), copy.getSince());
        Assertions.assertEquals(client.getEntityType(), copy.getEntityType());
        Assertions.assertEquals(client.getDescription(), copy.getDescription());
        Assertions.assertEquals(client.getReference(), copy.getReference());
        Assertions.assertEquals(client.getName(), copy.getName());
    }
}
