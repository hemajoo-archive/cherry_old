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
package com.hemajoo.commerce.cherry.persistence.content;

import com.hemajoo.commerce.cherry.persistence.model.entity.document.DocumentEntity;
import lombok.NonNull;
import org.springframework.content.fs.config.FilesystemStoreConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import java.io.File;

/**
 * Configurer for the content store.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Configuration
public class DocumentStoreConfigurer
{
    @Bean
    public FilesystemStoreConfigurer configurer()
    {
        return new FilesystemStoreConfigurer()
        {
            @Override
            public void configureFilesystemStoreConverters(ConverterRegistry registry)
            {
                registry.addConverter(new Converter<DocumentEntity, String>()
                {
                    @Override
                    public String convert(final @NonNull DocumentEntity document)
                    {
//                        return File.separator + document.getOwner().getId().toString() + File.separator + document.getContentId();
                        return File.separator + document.getContentId();
                    }
                });
            }
        };
    }
}
