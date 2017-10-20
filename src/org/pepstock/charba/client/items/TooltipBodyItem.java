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

import java.util.List;

import org.pepstock.charba.client.commons.Key;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip body.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.items.TooltipModel
 */
public final class TooltipBodyItem  extends BaseItem {
    
	/**
	 * Name of fields of JavaScript object. 
	 */
    private enum Property implements Key{
    	before,
    	lines,
    	after
    }

    /** 
     * Needed for GWt injection
     */
    protected TooltipBodyItem() {
    	// do nothing
	}
    
    /**
     * Returns text to render before the body section.
     * @return text to render before the body section.
     */
	public final List<String> getBefore() {
        return getStringArray(Property.before.name());
    }

	/**
	 * Returns all lines of body section.
	 * @return all lines of body section.
	 */
	public final List<String> getLines() {
        return getStringArray(Property.lines.name());
    }

	/**
	 * Returns text to render after the body section
	 * @return text to render after the body section
	 */
	public final List<String> getAfter() {
        return getStringArray(Property.after.name());
    }

}