/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.unomi.rest.validation.cookies;

import org.apache.unomi.api.utils.ValidationPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

public class CookieValidator implements ConstraintValidator<CookieValidation, List<String>> {

    @Override
    public void initialize(CookieValidation constraintAnnotation) {
        constraintAnnotation.payload();
    }

    @Override
    public boolean isValid(List<String> object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }

        return object.stream()
                .allMatch(cookie -> Pattern.compile(ValidationPattern.TEXT_VALID_CHARACTERS_PATTERN).matcher(cookie).matches());

    }
}
