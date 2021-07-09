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
package com.hemajoo.commerce.cherry.persistence.base.randomizer;

import com.github.javafaker.Faker;
import com.hemajoo.commerce.cherry.commons.type.StatusType;
import com.hemajoo.commerce.cherry.model.entity.base.Base;
import com.hemajoo.commerce.cherry.persistence.model.entity.base.BaseEntity;
import lombok.NonNull;
import org.ressec.avocado.core.random.EnumRandomGenerator;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Base entity randomizer.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class BaseEntityRandomizer
{
    /**
     * Default dependency bound.
     */
    protected static final int DEFAULT_DEPENDENCY_BOUND = 10;

    /**
     * Faker generator.
     */
    protected static final Faker FAKER = new Faker();

    /**
     * Random number generator.
     */
    protected static final Random RANDOM = new Random();

    /**
     * Entity status type enumeration generator.
     */
    protected static final EnumRandomGenerator STATUS_TYPE_GENERATOR = new EnumRandomGenerator(StatusType.class).exclude(StatusType.UNSPECIFIED);

    /**
     * Populates the base persistent entity with random values.
     * @param parent Parent entity.
     */
    public static void populateBaseFields(final @NonNull BaseEntity parent)
    {
        if (parent.getId() == null)
        {
            parent.setId(UUID.randomUUID());
        }
        parent.setDescription(FAKER.hitchhikersGuideToTheGalaxy().marvinQuote());
        parent.setReference(FAKER.ancient().hero());
        parent.setStatusType((StatusType) STATUS_TYPE_GENERATOR.gen());
        parent.setCreatedBy(FAKER.name().name());
        parent.setCreatedDate(FAKER.date().past(100, TimeUnit.DAYS)); // Created in the previous 100 days
        parent.setModifiedBy(FAKER.name().name());
        parent.setCreatedDate(FAKER.date().past(1, TimeUnit.HOURS)); // Modified in the last hour
    }

    /**
     * Populates the base client entity with random values.
     * @param parent Parent entity.
     */
    public static void populateBaseFields(final @NonNull Base parent)
    {
        if (parent.getId() == null)
        {
            parent.setId(UUID.randomUUID());
        }
        parent.setDescription(FAKER.hitchhikersGuideToTheGalaxy().marvinQuote());
        parent.setReference(FAKER.ancient().hero());
        parent.setStatusType((StatusType) STATUS_TYPE_GENERATOR.gen());
        parent.setCreatedBy(FAKER.name().name());
        parent.setCreatedDate(FAKER.date().past(100, TimeUnit.DAYS)); // Created in the previous 100 days
        parent.setModifiedBy(FAKER.name().name());
        parent.setCreatedDate(FAKER.date().past(1, TimeUnit.HOURS)); // Modified in the last hour
    }

    /**
     * Returns a random element from a list.
     * @param list List.
     * @param <T> Element type.
     * @return Random element.
     */
    public static <T> T getRandomElement(final List<T> list)
    {
        return list.get(RANDOM.nextInt(list.size()));
    }
}
