/*
 * (C) Copyright Hemajoo Systems Inc. 2021 - All Rights Reserved
 * -----------------------------------------------------------------------------------------------
 * All information contained herein is, and remains the property of
 * Hemajoo Inc. and its suppliers, if any. The intellectual and technical
 * concepts contained herein are proprietary to Hemajoo Inc. and its
 * suppliers and may be covered by U.S. and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from
 * Hemajoo Systems Inc.
 * -----------------------------------------------------------------------------------------------
 */
package com.hemajoo.commerce.cherry.persistence.model.entity.document;

import com.hemajoo.commerce.cherry.model.entity.document.Document;
import com.hemajoo.commerce.cherry.model.entity.document.DocumentException;
import com.hemajoo.commerce.cherry.persistence.base.mapper.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A mapper to convert between {@link Document} and {@link DocumentEntity} and vice-versa.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DocumentMapper
{
    /**
     * Instance to this bean mapper.
     */
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    /**
     * Converts from a persistent entity to a client entity.
     * @param entity Persistent entity to convert.
     * @param context Context object.
     * @return Converted client entity.
     */
    Document fromPersistent(DocumentEntity entity, @Context CycleAvoidingMappingContext context);

    /**
     * Converts a list of persistent entities to a list of client entities.
     * @param list List of persistent entities.
     * @param context Context object.
     * @return Converted list of client entities.
     */
    List<Document> fromPersistentList(List<DocumentEntity> list, @Context CycleAvoidingMappingContext context);

    /**
     * Converts from a client entity to a persistent entity.
     * @param entity Client entity to convert.
     * @param context Context object.
     * @return Converted persistent entity.
     */
    @Mapping(target = "content", ignore = true)
    DocumentEntity fromClient(Document entity, @Context CycleAvoidingMappingContext context);

    /**
     * Converts a list of client entities to a list of persistent entities.
     * @param list List of client entities.
     * @param context Context object.
     * @return Converted list of persistent entities.
     */
    List<DocumentEntity> fromClientList(List<Document> list, @Context CycleAvoidingMappingContext context);

    /**
     * Returns a deep copy of a persistent entity.
     * @param entity Persistent entity to copy.
     * @param context Context object.
     * @return Copy of the persistent entity.
     */
    DocumentEntity copy(DocumentEntity entity, @Context CycleAvoidingMappingContext context) throws DocumentException;

    /**
     * Returns a deep copy of a client entity.
     * @param entity Client entity to copy.
     * @param context Context object.
     * @return Copy of the client entity.
     */
    Document copy(Document entity, @Context CycleAvoidingMappingContext context);
}
