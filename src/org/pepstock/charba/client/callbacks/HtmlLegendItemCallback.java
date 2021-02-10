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

import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.items.LegendLabelItem;

/**
 * This callback can be implemented to change the text of legend for a specific item, as HTML.<br>
 * This callback is used ONLY by {@link HtmlLegend} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface HtmlLegendItemCallback extends HtmlLegendCallback<LegendLabelItem> {

}