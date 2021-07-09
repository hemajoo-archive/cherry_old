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

import com.hemajoo.commerce.cherry.commons.type.StatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Represents the (abstract) status part of a base (client) entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Status extends Audit
{
    /**
     * Email address status.
     */
    @ApiModelProperty(name = "statusType", notes = "Email address status type", value = "ACTIVE")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    /**
     * Inactivity time stamp information (server time) that must be filled when the email address becomes inactive.
     */
    @ApiModelProperty(hidden = true) // As it is set automatically when status changes.
    private Date since;

    /**
     * Returns if the entity is active?
     * @return {@code True} if the entity is active, {@code false} otherwise.
     */
    @ApiModelProperty(hidden = true) // Status type is used instead!
    public final boolean isActive()
    {
        return statusType == StatusType.ACTIVE;
    }

    /**
     * Sets the underlying entity to {@link StatusType#ACTIVE}.
     */
    public final void setActive()
    {
        statusType = StatusType.ACTIVE;
        since = null;
    }

    /**
     * Sets the underlying entity to {@link StatusType#INACTIVE}.
     */
    public final void setInactive()
    {
        statusType = StatusType.INACTIVE;
        since = new Date(System.currentTimeMillis());
    }

    /**
     * Sets the status of the entity.
     * @param status Status.
     * @see StatusType
     */
    public void setStatusType(StatusType status)
    {
        if (status != this.statusType)
        {
            if (status == StatusType.INACTIVE)
            {
                setInactive();
            }
            else
            {
                setActive();
            }
        }
    }
}
