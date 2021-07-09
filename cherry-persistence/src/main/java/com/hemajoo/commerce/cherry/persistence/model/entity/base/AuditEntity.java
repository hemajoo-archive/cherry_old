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
package com.hemajoo.commerce.cherry.persistence.model.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents the base audit part of a persistence entity of the {@code Cherry} data model.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class AuditEntity implements Serializable
{
    public static final String FIELD_CREATED_DATE   = "createdDate";
    public static final String FIELD_MODIFIED_DATE  = "modifiedDate";
    public static final String FIELD_CREATED_BY     = "createdBy";
    public static final String FIELD_MODIFIED_BY    = "modifiedBy";

    /**
     * Entity creation date.
     */
    @Column(name = "CREATED_DATE", length = 26)
    @CreatedDate
    private Date createdDate;

    /**
     * Entity modification date.
     */
    @Column(name = "MODIFIED_DATE", length = 26)
    @LastModifiedDate
    private Date modifiedDate;

    /**
     * Entity author.
     */
    @Column(name = "CREATED_BY", length = 50)
    @CreatedBy
    private String createdBy;

    /**
     * Entity last modification author.
     */
    @Column(name = "MODIFIED_BY", length = 50)
    @LastModifiedBy
    private String modifiedBy;
}
