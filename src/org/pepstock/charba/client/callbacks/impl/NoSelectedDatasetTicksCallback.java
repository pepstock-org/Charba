package org.pepstock.charba.client.callbacks.impl;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.TickItem;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
public class NoSelectedDatasetTicksCallback implements TickCallback {

	private final static NumberFormat FORMAT = NumberFormat.getFormat("0.0");
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.callbacks.TickCallback#onCallback(org.pepstock.charba.client.AbstractChart, org.pepstock.charba.client.items.TickItem)
	 */
	@Override
	public String onCallback(AbstractChart<?, ?> chart, TickItem item) {
		boolean allHidden = false;
		List<Dataset> dss = chart.getData().getDatasets();
		for (int i=0; i<dss.size(); i++){
			DatasetMetaItem metadata = chart.getDatasetMeta(i);
			if (metadata != null) {
				allHidden = allHidden || metadata.isHidden();
			}
		}
		if (allHidden) {
			return FORMAT.format(item.getValue());
		}
		return String.valueOf(item.getValue());
	}

}
