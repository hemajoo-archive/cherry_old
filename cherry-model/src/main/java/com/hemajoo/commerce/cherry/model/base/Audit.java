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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the (abstract) audit part of a base (client) entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Audit implements Serializable
{
    /**
     * Entity creation date.
     */
    @ApiModelProperty(hidden = true)
    private Date createdDate;

    /**
     * Entity modification date.
     */
    @ApiModelProperty(hidden = true)
    private Date modifiedDate;

    /**
     * Entity creation author.
     */
    @ApiModelProperty(name = "createdBy", notes = "Person being the author of this entity.", required = false, example = "Henry.Jacobson@gmail.com")
    private String createdBy;

    /**
     * Entity modification author.
     */
    @ApiModelProperty(name = "modifiedBy", notes = "Last person having modified this entity.", required = false, example = "Henry.Jacobson@gmail.com")
    private String modifiedBy;
}
