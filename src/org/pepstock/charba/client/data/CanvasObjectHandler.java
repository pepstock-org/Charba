package org.pepstock.charba.client.data;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

final class CanvasObjectHandler extends AbstractPlugin {
	
	static final String ID = "canvasobjecthandler";

	/**
	 * To avoid any instantiation
	 */
	CanvasObjectHandler() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
		for (Dataset dataset : chart.getData().getDatasets()){
			dataset.applyPatterns(chart);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.AbstractChart, org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size) {
	}

}
