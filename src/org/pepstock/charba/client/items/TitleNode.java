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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;

/**
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TitleNode extends BaseBoxNodeItem {

	TitleNode(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}
}