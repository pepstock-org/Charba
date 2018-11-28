package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

class DataPointListFactory implements Factory<DataPoint> {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
	 */
	@Override
	public DataPoint create(NativeObject nativeObject) {
		return new DataPoint(nativeObject);
	}

}
