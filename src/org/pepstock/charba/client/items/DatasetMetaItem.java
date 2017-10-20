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
import org.pepstock.charba.client.commons.Key;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected dataset. This object has been created and passed to event handler or 
 * callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetMetaItem  extends GenericJavaScriptObject {
 
    /** 
     * Needed for GWt injection
     */
    private enum Property implements Key{
    	_datasetIndex,
    	_index,
    	_view,
    	hidden
    }
    
    /** 
     * Needed for GWt injection
     */
    protected DatasetMetaItem() {
    	// do nothing
    }
    
    /**
     * Returns the dataset index of the chart
     * @return the dataset index of the chart
     * @see org.pepstock.charba.client.data.Data#getDatasets()
     */
	public final int getDatasetIndex() {
		return getInt(Property._datasetIndex.name());
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * @return the index of the data inside the dataset.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	public final int getIndex() {
		return getInt(Property._index.name());
	}

	/**
	 * Returns if the dataset is hidden.
	 * @return <code>true</code> if the dataset is hidden, otherwise <code>false</code>.
	 */
	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	/**
	 * Sets if the dataset must be hidden.
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise <code>false</code>.
	 */
	public final void setHidden(boolean hidden) {
		setBoolean(Property.hidden.name(), hidden);
	}

	/**
	 * Returns all view information about the dataset. 
	 * @return all view information about the dataset.
	 * @see org.pepstock.charba.client.items.DatasetMetaViewItem
	 */
	public final DatasetMetaViewItem getView(){
		return (DatasetMetaViewItem) getJavaScriptObject(Property._view.name());
	}
}