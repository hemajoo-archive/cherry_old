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
 * An enumeration representing the several possible divulgation types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DivulgationType
{
    /**
     * The underlying entity is to be considered as private and should not be published publicly.
     */
    PRIVATE,

    /**
     * The underlying entity is to be considered as protected .
     */
    PROTECTED,

    /**
     * The underlying entity is to be considered as .
     */
    PUBLIC,

    /**
     * The underlying entity is to be considered as .
     */
    ORGANIZATION,

    /**
     * The underlying entity is to be considered as .
     */
    COMPANY,
}
