package org.pepstock.charba.client.plugins.impl;

import java.util.logging.Logger;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HandlerRegistration;

public final class DatasetsSelector extends AbstractPlugin {
	
	Logger LOG = Logger.getLogger("selection");
	
	private SelectionHandler handler = null;

	private HandlerRegistration mouseDown = null;
	
	private HandlerRegistration mouseUp = null;
	
	private HandlerRegistration mouseMove = null;
	
	/**
	 * Plugin ID 
	 */
	public static final String ID = "datasetsselector";
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart, JavaScriptObject options) {
		if (chart.getType().equals(ChartType.line)) {
			handler = new SelectionHandler(chart);
			mouseDown = chart.getCanvas().addMouseDownHandler(handler);
			mouseUp = chart.getCanvas().addMouseUpHandler(handler);		
			mouseMove = chart.getCanvas().addMouseMoveHandler(handler);
			chart.getOptions().getTooltips().setEnabled(false);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDraw(org.pepstock.charba.client.AbstractChart, double, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
		LOG.info("onAfterDraw");
		if (chart.getType().equals(ChartType.line)) {
			if (easing == 1D) {
				handler.setSnapshot(chart.getCanvas().toDataUrl());
				//ChartAreaItem chartArea = getChartNode().getChartArea();
				handler.getVisibleDatasetItems().clear();
				int datasetsCount = chart.getData().getDatasets().size();
				int limitRight = chart.getCanvas().getOffsetWidth(); 
				for (int i=0 ; i < datasetsCount ; i++ ) {
					if (chart.isDatasetVisible(i) && handler.getVisibleDatasetItems().isEmpty()) {
						DatasetMetaItem items = chart.getDatasetMeta(i);
						for (DatasetItem item : items.getDatasets()) {
							if (item.getView().getX() <= limitRight ) {
								handler.getVisibleDatasetItems().add(item);
							}
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDatasetsUpdate(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onAfterDatasetsUpdate(AbstractChart<?, ?> chart, JavaScriptObject options) {
		LOG.info("onAfterDatasetsUpdate");
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.AbstractChart, org.pepstock.charba.client.items.SizeItem, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size, JavaScriptObject options) {
		LOG.info("onResize");
		if (handler.isSelected()) {
			// draw into new image
			handler.setSnapshot(chart.getCanvas().toDataUrl());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onDestroy(AbstractChart<?, ?> chart, JavaScriptObject options) {
		if (mouseDown != null) { 
			mouseDown.removeHandler();
		}
		if (mouseUp != null) { 
			mouseUp.removeHandler();
		}
		if (mouseMove != null) { 
			mouseMove.removeHandler();
		}
	}
	
}
