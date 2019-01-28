package org.pepstock.charba.client.data;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
final class CanvasObjectHandler extends AbstractPlugin {
	
	static final String ID = "canvasobjecthandler";
	
	private boolean checked = false;
	
	private boolean patternsChecked = false;

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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDatasetsDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		if (!checked) {
			for (Dataset dataset : chart.getData().getDatasets()){
				if (!patternsChecked) {
					dataset.applyPatterns(chart);
				}
				dataset.applyGradients(chart);
			}
			patternsChecked = true;
			checked = true;
			chart.update();
			return false;
		}
    	return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.AbstractChart, org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size) {
		checked = false;
	}

}
