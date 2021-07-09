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
package com.hemajoo.commerce.cherry.persistence.test.base;

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
public abstract class BaseMapperUnitTest extends BaseUnitTest
{
    /**
     * Checks equality of base fields.
     * @param dbEntity Base persistent entity.
     * @param entity Base client entity.
     */
    protected void checkBaseFields(final @NonNull BaseEntity dbEntity, final @NonNull Base entity)
    {
        Assertions.assertEquals(dbEntity.getId(), entity.getId());
        Assertions.assertEquals(dbEntity.getCreatedBy(), entity.getCreatedBy());
        Assertions.assertEquals(dbEntity.getCreatedDate(), entity.getCreatedDate());
        Assertions.assertEquals(dbEntity.getModifiedBy(), entity.getModifiedBy());
        Assertions.assertEquals(dbEntity.getModifiedDate(), entity.getModifiedDate());
        Assertions.assertEquals(dbEntity.getStatusType(), entity.getStatusType());
        Assertions.assertEquals(dbEntity.getSince(), entity.getSince());
        Assertions.assertEquals(dbEntity.getEntityType(), entity.getEntityType());
        Assertions.assertEquals(dbEntity.getDescription(), entity.getDescription());
        Assertions.assertEquals(dbEntity.getReference(), entity.getReference());
        Assertions.assertEquals(dbEntity.getName(), entity.getName());
    }
}
