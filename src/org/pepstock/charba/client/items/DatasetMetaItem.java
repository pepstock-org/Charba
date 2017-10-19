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

public final class DatasetMetaItem  extends GenericJavaScriptObject {
 
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
	}
    
	public final int getDatasetIndex() {
		return getInt(Property._datasetIndex.name());
	}

	public final int getIndex() {
		return getInt(Property._index.name());
	}

	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	public final void setHidden(boolean hidden) {
		setBoolean(Property.hidden.name(), hidden);
	}

	public final DatasetMetaViewItem getView(){
		return (DatasetMetaViewItem) getJavaScriptObject(Property._view.name());
	}

    public String toContentString()  {
		return "DatasetMetaItem [getDatasetIndex()=" + getDatasetIndex() + ", getIndex()=" + getIndex() + ", isHidden()=" + isHidden() + ", getView()=" + getView().toContentString() + "]";
    }
}