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
package org.pepstock.charba.client.jsinterop.impl.callbacks;

import java.util.List;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.data.Dataset;
import org.pepstock.charba.client.jsinterop.events.LegendClickEvent;
import org.pepstock.charba.client.jsinterop.events.LegendClickEventHandler;
import org.pepstock.charba.client.jsinterop.items.DatasetMetaItem;


/**
 * Implementation of legend click handler which disable the capability to hide the last visible dataset.<br>
 * In this way, always a dataset is showed.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public class AtLeastOneDatasetHandler implements LegendClickEventHandler {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.events.LegendClickEventHandler#onClick(org.pepstock.charba.client.jsinterop.events.LegendClickEvent)
	 */
	@Override
	public void onClick(LegendClickEvent event) {
		// getx teh chart instance form event
		AbstractChart<?, ?> chart = event.getChart();
		// gets the dataset by the legend item and its relation with dataset
		DatasetMetaItem metadata = chart.getDatasetMeta(event.getItem().getDatasetIndex());
		// if the dataset is already hidden
		if (metadata.isHidden()) {
			// it will show the dataset again
			metadata.setHidden(false);
		} else {
			// creates a counter about how many datasets are visible
			int count = 0;
			// gets all datasets
			List<Dataset> dss = chart.getData().getDatasets();
			// scans them by for cycle to have the index for retrieving 
			// the dataset metadata
			for (int i=0; i<dss.size(); i++){
				// gets metadata to know if is hidden
				DatasetMetaItem metadataItem = chart.getDatasetMeta(i);
				// if visible increments counter
				if (!metadataItem.isHidden()) {
					count++;
				}
			}
			// checks if there is ONLY 1 visible dataset 
			if (count > 1) {
				// if not, it can hide it
				metadata.setHidden(true);
			} else {
				// otherwise do nothing
				return;
			}
		}
		// updates chart to apply the changed datasets visibility
		chart.update();
	}

}
