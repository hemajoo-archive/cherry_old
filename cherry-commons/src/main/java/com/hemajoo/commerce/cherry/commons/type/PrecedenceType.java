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
 * An enumeration representing the several possible precedence types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum PrecedenceType
{
    /**
     * The underlying entity is to be considered as the primary one among all entities of the same type.
     */
    PRIMARY,

    /**
     * The underlying entity is to be considered as a secondary one among all entities of the same type.
     */
    SECONDARY
}
