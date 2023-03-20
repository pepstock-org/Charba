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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;

/**
 * This is the {@link AnnotationPlugin#ID} plugin DEFAULTS for main options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultOptions implements IsDefaultsAnnotationOptions {

	// defaults options instance
	static final DefaultOptions INSTANCE = new DefaultOptions();
	// list of animation collection properties
	private static final List<String> DEFAULT_PROPERTIES = Arrays.asList("x", "y", "x2", "y2", "width", "height", "centerX", "centerY", "pointX", "pointY", "radius");
	// animation collection default properties
	private static final List<Key> DEFAULT_ANIMATIONS_PROPERTIES = new LinkedList<>();
	// default animations
	private static final DefaultAnnotationAnimations DEFAULT_ANIMATIONS = new DefaultAnnotationAnimations();

	/**
	 * To avoid any instantiation
	 */
	private DefaultOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotationOptions#getInteraction()
	 */
	@Override
	public IsDefaultsAnnotationInteraction getInteraction() {
		return DefaultInteraction.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotationOptions#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return DEFAULT_ANIMATIONS;
	}

	/**
	 * This class is implementing the default of Annotation plugin animations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultAnnotationAnimations implements IsDefaultAnimations {

		/**
		 * To avoid any instantiation
		 */
		private DefaultAnnotationAnimations() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#contains(org.pepstock.charba.client.commons.Key)
		 */
		@Override
		public boolean contains(Key collection) {
			return Key.isValid(collection) && DefaultAnimationCollectionKey.NUMBERS.value().equals(collection.value());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#get(org.pepstock.charba.client.commons.Key)
		 */
		@Override
		public IsDefaultAnimationCollection get(Key collection) {
			// checks if contains numbers
			if (contains(collection)) {
				return new DefaultAnnotationAnimationCollection();
			}
			// if here, the key is not numbers
			// therefore is not a default of annotation plugin
			// then it returns the default animation collection
			return DefaultsBuilder.get().getOptions().getAnimations().get(collection);
		}

	}

	/**
	 * This class is implementing the default of Annotation plugin animations collection.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultAnnotationAnimationCollection extends DefaultAnimationCollection {

		/**
		 * To avoid any instantiation
		 */
		private DefaultAnnotationAnimationCollection() {
			// loads properties id not loaded
			if (DEFAULT_ANIMATIONS_PROPERTIES.isEmpty()) {
				// scans and creates key
				for (String prop : DEFAULT_PROPERTIES) {
					// creates and add prop
					DEFAULT_ANIMATIONS_PROPERTIES.add(Key.create(prop));
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getProperties()
		 */
		@Override
		public List<Key> getProperties() {
			return Collections.unmodifiableList(DEFAULT_ANIMATIONS_PROPERTIES);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getType()
		 */
		@Override
		public AnimationType getType() {
			return AnimationType.NUMBER;
		}

	}

}