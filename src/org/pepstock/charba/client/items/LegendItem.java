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
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;

public final class LegendItem  extends BaseItem {
    
    private enum Property implements Key{
    	datasetIndex,
    	text,
    	fillStyle,
    	hidden,
    	lineCap,
    	lineDash,
    	lineDashOffset,
    	lineJoin,
    	lineWidth,
    	strokeStyle,
    	pointStyle
    }

    /** 
     * Needed for GWt injection
     */
    protected LegendItem() {
	}

	public final int getDatasetIndex() {
        return getInt(Property.datasetIndex.name());
    }
    
    public final String getText() {
        return getString(Property.text.name());
    }

    public final String getFillStyle() {
    	return getString(Property.fillStyle.name());
    }

    public final boolean getHidden() {
        return getBoolean(Property.hidden.name());
    }

    public final CapStyle getLineCap() {
    	return getValue(Property.fillStyle, CapStyle.class, CapStyle.butt);
    }

    public final List<Integer> getLineDash() {
        return getIntegerArray(Property.lineDash.name());
    }

    public final int getLineDashOffset() {
        return getInt(Property.lineDashOffset.name());
    }

    public final JoinStyle getLineJoin() {
    	return getValue(Property.lineJoin, JoinStyle.class, JoinStyle.miter);
    }

    public final int getLineWidth() {
    	return getInt(Property.lineWidth.name());
    }

    public final String getStrokeStyle() {
    	return getString(Property.strokeStyle.name());
    }

    public final PointStyle getPointStyle() {
        return getValue(Property.lineJoin, PointStyle.class, PointStyle.circle);
    }

	public final String toContentString() {
		return "LegendItem [getDasetIndex()=" + getDatasetIndex() + ", getText()=" + getText() + ", getFillStyle()=" + getFillStyle() + ", getHidden()=" + getHidden() + ", getLineCap()=" + getLineCap() + ", getLineDash()=" + getLineDash()
				+ ", getLineDashOffset()=" + getLineDashOffset() + ", getLineJoin()=" + getLineJoin() + ", getLineWidth()=" + getLineWidth() + ", getStrokeStyle()=" + getStrokeStyle() + ", getPointStyle()=" + getPointStyle() + "]";
	}
    
    
    
}