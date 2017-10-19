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

public class DatasetMetaViewItem extends BaseItem {

//	click { target: <canvas>, buttons: 0, clientX: 400, clientY: 101, layerX: 137, layerY: 91 }
	
	private enum Property implements Key{
		datasetLabel,
		label,
		borderSkipped,
		backgroundColor,
		borderColor,
		borderWidth,
		horizontal,
		base,
		x,
		y,
		width,
		height
	}
	
//  datasetLabel: "dataset 1", label: "January", borderSkipped: "bottom", backgroundColor: "rgba(11, 80, 218, 0.2)", borderColor: "rgb(11, 80, 218)", borderWidth: 1, horizontal: false, base: 305, x: 70.10714285714286, y: 156,
//	height, width   
	
    /** 
     * Needed for GWt injection
     */
	protected DatasetMetaViewItem() {
		
	}

	public final String getDatasetLabel(){
	    return getString(Property.datasetLabel.name());
	}
	public final String getLabel(){
	    return getString(Property.label.name());
	}
	public final String getBorderSkipped(){
	    return getString(Property.borderSkipped.name());
	}
	public final String getBackgroundColor(){
	    return getString(Property.backgroundColor.name());
	}
	public final String getBorderColor(){
	    return getString(Property.borderColor.name());
	}
	public final int getBorderWidth(){
	    return getInt(Property.borderWidth.name());
	}
	public final boolean isHorizontal(){
	    return getBoolean(Property.horizontal.name());
	}
	public final int getBase(){
	    return getInt(Property.base.name());
	}
	public final double getX(){
	    return getDouble(Property.x.name());
	}
	public final double getY(){
	    return getDouble(Property.y.name());
	}
	public final double getWidth(){
	    return getDouble(Property.width.name());
	}
	public final double getHeight(){
	    return getDouble(Property.height.name());
	}

	public final String toContentString() {
		return "EventViewItem [getDatasetLabel()=" + getDatasetLabel() + ", getLabel()=" + getLabel() + ", getBorderSkipped()=" + getBorderSkipped() + ", getBackgroundColor()=" + getBackgroundColor() + ", getBorderColor()=" + getBorderColor()
		+ ", getBorderWidth()=" + getBorderWidth() + ", isHorizontal()=" + isHorizontal() + ", getBase()=" + getBase() + ", getX()=" + getX() + ", getY()=" + getY() + ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + "]";
	}
}