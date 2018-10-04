package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;

class DataPointListFactory  implements Factory<DataPoint, NativeDataPoint> {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
	 */
	@Override
	public DataPoint create(NativeDataPoint nativeObject) {
		return new DataPoint(nativeObject);
	}

}
