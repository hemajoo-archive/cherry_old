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
package com.hemajoo.commerce.cherry.persistence.base.validation;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Map;

/**
 * Hibernate validator customizer.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Component
public class ValidatorHibernateCustomizer implements HibernatePropertiesCustomizer
{
    // Necessary when using constraint validators with SpringBootTest
    // see: https://stackoverflow.com/questions/62985187/getting-nullpointerexception-for-repository-while-using-custom-javax-validator

    /**
     * Validator provider.
     */
    private final ObjectProvider<Validator> provider;

    @Autowired
    public ValidatorHibernateCustomizer(ObjectProvider<Validator> provider)
    {
        this.provider = provider;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties)
    {
        var validator = provider.getIfUnique();
        if (validator != null)
        {
            hibernateProperties.put("javax.persistence.validation.factory", validator);
        }
    }
}