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
 * Interface to map the annotation which contains the label options.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of the label
 */
interface IsLabelContainer<T extends InnerLabel> {

	/**
	 * Returns the inner label of annotation.
	 * 
	 * @return the inner label of annotation
	 */
	T getLabel();

}