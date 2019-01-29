package org.pepstock.charba.client.data;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CanvasObjectHandler extends AbstractPlugin {

	static final String ID = "canvasobjecthandler";

	private boolean updated = false;

	/**
	 * To avoid any instantiation
	 */
	CanvasObjectHandler() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDatasetsDraw(org.pepstock.charba.client.AbstractChart,
	 * double)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.getPatternsContainer().isChanged()) {
				dataset.applyPatterns(chart);
				dataset.getPatternsContainer().setChanged(false);
				updated = true;
			}
			if (dataset.getGradientsContainer().isChanged()) {
				dataset.applyGradients(chart);
				dataset.getGradientsContainer().setChanged(false);
				updated = true;
			}
		}
		if (updated) {
			updated = false;
			chart.update();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size) {
		for (Dataset dataset : chart.getData().getDatasets()) {
			dataset.getGradientsContainer().setChanged(true);
		}
		CanvasObjectFactory.resetGradients(chart);
	}

}
