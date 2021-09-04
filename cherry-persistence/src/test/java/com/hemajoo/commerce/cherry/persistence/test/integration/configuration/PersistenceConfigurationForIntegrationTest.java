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
package com.hemajoo.commerce.cherry.persistence.test.integration.configuration;

import com.hemajoo.commerce.cherry.commons.exception.ContentStoreException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * A {@code Spring} configuration containing definitions for the persistence layer for the {@code test} environment.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Configuration
@Profile("test")
@ActiveProfiles("test")
@PropertySource("classpath:cherry-test.properties")
@ComponentScan(basePackages = "com.hemajoo.commerce.cherry.persistence.*")
@EnableJpaRepositories(basePackages = "com.hemajoo.commerce.cherry.persistence.*")
@EntityScan(basePackages = "com.hemajoo.commerce.cherry.persistence.*")
@EnableFilesystemStores(basePackages = "com.hemajoo.commerce.cherry.persistence.*")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfigurationForIntegrationTest
{
    @Getter
    @Value("${hemajoo.commerce.cherry.store.location}")
    private String baseContentStoreLocation;

    @Bean
    public AuditorAware<String> auditorProvider()
    {
        return new JpaAuditorForIntegrationTest();
    }

    /**
     * Base file base path for the content store.
     * @return File base path.
     * @throws ContentStoreException Raised if required content store properties are not defined!
     */
    @Bean
    public File fileSystemRoot() throws ContentStoreException
    {
        if (baseContentStoreLocation == null || baseContentStoreLocation.isEmpty())
        {
            throw new ContentStoreException("Property: 'hemajoo.commerce.cherry.store.location' cannot be null or empty!");
        }

        // Clear the content store for a test environment
        try
        {
            Arrays.stream(
                    Objects.requireNonNull(
                            new File(baseContentStoreLocation).listFiles())).forEach(File::delete);
        }
        catch (Exception e)
        {
            // Directory does not exist, do nothing!
        }

        return new File(baseContentStoreLocation);
    }

    /**
     * Returns the content store file system resource loader.
     * @return File system resource loader.
     * @throws ContentStoreException Raised if required content store properties are not defined!
     */
    @Bean
    FileSystemResourceLoader fileSystemResourceLoader() throws ContentStoreException
    {
        return new FileSystemResourceLoader(fileSystemRoot().getAbsolutePath());
    }
}
