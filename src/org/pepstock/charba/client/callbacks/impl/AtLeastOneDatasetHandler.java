package org.pepstock.charba.client.callbacks.impl;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendClickEventHandler;
import org.pepstock.charba.client.items.DatasetMetaItem;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public class AtLeastOneDatasetHandler implements LegendClickEventHandler {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.events.LegendClickEventHandler#onClick(org.pepstock.charba.client.events.LegendClickEvent)
	 */
	@Override
	public void onClick(LegendClickEvent event) {
		AbstractChart<?, ?> chart = event.getChart();
		DatasetMetaItem metadata = chart.getDatasetMeta(event.getItem().getDatasetIndex());
		if (metadata.isHidden()) {
			metadata.setHidden(false);
		} else {
			int count = 0;
			List<Dataset> dss = chart.getData().getDatasets();
			for (int i=0; i<dss.size(); i++){
				DatasetMetaItem metadataItem = chart.getDatasetMeta(i);
				if (!metadataItem.isHidden()) {
					count++;
				}
			}
			if (count > 1) {
				metadata.setHidden(true);
			} else {
				return;
			}
		}
		chart.update();
	}

}
