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

import org.pepstock.charba.client.commons.Key;

public final class TooltipLabelColor  extends BaseItem {
	
    private enum Property implements Key{
    	backgroundColor,
    	borderColor
    }

    /** 
     * Needed for GWt injection
     */
    protected TooltipLabelColor() {
	}

	public final String getBackgroundColor() {
        return getString(Property.backgroundColor.name());
    }

	public final String getBorderColor() {
        return getString(Property.borderColor.name());
    }

	public final String toContentString() {
		return "TooltipLabelColor [getBackgroundColor()=" + getBackgroundColor() + ", getBorderColor()=" + getBorderColor() + "]";
	}
}