/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.Set;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.AbstractElementFactory;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Specific base element factory for Annotation plugin elements, related to the annotation types.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AnnotationElementFactory<T extends AbstractAnnotation> extends AbstractElementFactory<T> {

	/**
	 * Creates the factory by the key of object, as string.
	 * 
	 * @param elementKeyAsString the key of object, as string.
	 */
	AnnotationElementFactory(String elementKeyAsString) {
		super(elementKeyAsString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public final T create(NativeObject nativeObject) {
		// checks if annotation plugin is enabled
		Set<String> plugins = Defaults.get().getPlugins().getIds();
		// checks if annotation is registers
		if (plugins.contains(ResourceName.ANNOTATION_PLUGIN.value())) {
			return createOptions(nativeObject);
		}
		// if here, plugin is not registered
		// then returns null
		return null;
	}

	/**
	 * Creates a native object container instance by a native object.
	 * 
	 * @param nativeObject native object
	 * @return native object container element instance
	 */
	abstract T createOptions(NativeObject nativeObject);

}