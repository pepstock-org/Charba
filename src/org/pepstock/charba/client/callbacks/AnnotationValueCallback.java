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
package org.pepstock.charba.client.callbacks;

import java.util.Date;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.Annotation;

/**
 * This callback is the base interface for annotation callbacks to set the value of annotations at runtime.<br>
 * This callback is used ONLY by {@link Annotation} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AnnotationValueCallback {

	/**
	 * Returns the value that an annotation will uses to be drawn.<br>
	 * The result value can be a {@link Double}, {@link Date} or {@link String}, depending on the type of the scale.
	 * 
	 * @param chart chart instance
	 * @param annotation annotation options instance
	 * @return the value that an annotation will uses to be drawn.<br>
	 *         The result value can be a {@link Double}, {@link Date} or {@link String}, depending on the type of the scale
	 */
	Object compute(IsChart chart, AbstractAnnotation annotation);

}