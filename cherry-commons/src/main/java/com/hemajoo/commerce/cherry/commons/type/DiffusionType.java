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
package com.hemajoo.commerce.cherry.commons.type;

/**
 * An enumeration representing the several possible diffusion types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DiffusionType
{
    /**
     * The underlying information is public and can be diffused publicly.
     */
    PUBLIC,

    /**
     * The underlying information can be publicly diffused is a consent is provided.
     */
    PUBLIC_WITH_CONSENT,

    /**
     * The underlying information is private and must not be diffused publicly.
     */
    PRIVATE,

    /**
     * The underlying information can be diffused only to persons of the organization the underlying person belongs to.
     */
    ORGANIZATION
}
