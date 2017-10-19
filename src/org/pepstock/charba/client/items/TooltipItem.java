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

public final class TooltipItem  extends BaseItem {
    
    private enum Property implements Key{
    	xLabel,
    	yLabel,
    	datasetIndex,
    	index,
    	x,
    	y
    }

    /** 
     * Needed for GWt injection
     */
    protected TooltipItem() {
	}

	public final String getXLabel() {
        return getString(Property.xLabel.name());
    }

	public final String getYLabel() {
        return getString(Property.yLabel.name());
    }

	public final int getDatasetIndex() {
        return getInt(Property.datasetIndex.name());
    }

	public final int getIndex() {
        return getInt(Property.index.name());
    }

	public final int getX() {
        return getInt(Property.x.name());
    }

	public final int getY() {
        return getInt(Property.y.name());
    }

	public String toContentString()  {
		return "TooltipItem [getXLabel()=" + getXLabel() + ", getYLabel()=" + getYLabel() + ", getDatasetIndex()=" + getDatasetIndex() + ", getIndex()=" + getIndex() + ", getX()=" + getX() + ", getY()=" + getY() + "]";
	}

}