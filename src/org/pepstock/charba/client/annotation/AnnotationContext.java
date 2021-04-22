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

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ContextType;

/**
 * The callback or handler context wrapper, created and passed by {@link AnnotationPlugin#ID} which contains the link to the native chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationContext extends ChartContext {

	private final AbstractAnnotation annotation;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param annotation annotation instance which is engaged as caller of callback.
	 * @param nativeObject native object instance to be wrapped.
	 */
	AnnotationContext(AbstractAnnotation annotation, NativeObject nativeObject) {
		super(nativeObject);
		// checks if context is consistent
		// and stores annotation
		this.annotation = Checker.checkAndGetIfValid(annotation, "Annotation argument");
	}

	/**
	 * Returns the annotation of plugin.
	 * 
	 * @return the annotation of plugin
	 */
	public AbstractAnnotation getAnnotation() {
		return annotation;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the context types are chart or annotatiob
		return ContextType.CHART.equals(getType()) || ContextType.ANNOTATION.equals(getType());
	}

}