/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.annotation;

/**
 * This is the {@link Annotation#ID} plugin BOX annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BoxAnnotationDefaultsOptions implements IsDefaultsBoxAnnotation {

	// defaults options instance
	static final BoxAnnotationDefaultsOptions INSTANCE = new BoxAnnotationDefaultsOptions();

	/**
	 * To avoid any instantiation
	 */
	private BoxAnnotationDefaultsOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	public AnnotationType getType() {
		return AnnotationType.BOX;
	}

}
