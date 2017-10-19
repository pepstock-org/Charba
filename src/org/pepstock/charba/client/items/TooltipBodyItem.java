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

public final class TooltipBodyItem  extends BaseItem {
    
	// before: String[] // lines of text before the line with the color square
    // lines: String[], // lines of text to render as the main item with color square
    // after: String[], // lines of text to render after the main lines
	
    private enum Property implements Key{
    	before,
    	lines,
    	after
    }

    /** 
     * Needed for GWt injection
     */
    protected TooltipBodyItem() {
	}
    
	public final List<String> getBefore() {
        return getStringArray(Property.before.name());
    }

	public final List<String> getLines() {
        return getStringArray(Property.lines.name());
    }

	public final List<String> getAfter() {
        return getStringArray(Property.after.name());
    }

	public String toContentString()  {
		return "TooltipBodyItem [getBefore()=" + getBefore() + ", getLines()=" + getLines() + ", getAfter()=" + getAfter() + "]";
	}

}