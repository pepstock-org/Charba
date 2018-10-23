package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisBuildTicksHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisCalculateTickRotationHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDataLimitsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDimensionsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisFitHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisTickToLabelConversionHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisUpdateHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.LegendFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.LegendHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.LegendLabelsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipBodyHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipCustomHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipFooterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipItemSortHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipLabelHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipTitleHandler;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.events.handlers.ChartClickCallbackHandler;
import org.pepstock.charba.client.jsinterop.events.handlers.ChartHoverCallbackHandler;
import org.pepstock.charba.client.jsinterop.events.handlers.ChartResizeCallbackHandler;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;

import jsinterop.annotations.JsFunction;

public final class EventableOptions extends BaseOptions<EventableAnimation,EventableLegend>{
	
	// legend error
	private static final String LEGEND_CALLBACK_ERROR = "Unable to execute LegendCallback";
	
	/**
	 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an array
	 * of active elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartClickCallback {
		/**
		 * Called if the event is of type 'mouseup' or 'click'. Called in the context of the chart and passed the event and an array
		 * of active elements.
		 * 
		 * @param event event generated by chart.
		 * @param metadata dataset meta data.
		 */
		void call(Chart chart, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	/**
	 * Called when any of the events fire. Called in the context of the chart and passed the event and an array of active
	 * elements (bars, points, etc).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartHoverCallback {
		
		/**
		 * Called when any of the events fire. Called in the context of the chart and passed the event and an array of active
		 * elements (bars, points, etc).
		 * FIXME
		 * @param event event generated by chart.
		 * @param metadata dataset meta data.
		 */
		void call(Chart chart, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	/**
	 * Called when a resize occurs. Gets passed the new size.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartResizeCallback {
		
		/**
		 * Called when a resize occurs. Gets passed the new size.
		 * 
		 * @param item the new size item.
		 */
		void call(NativeOptions context, Chart chart, SizeItem size); 
	}

	private final EventableAnimation animation;
	
	private final EventableLegend legend;
	
	private final CallbackProxy<ProxyChartResizeCallback> resizeCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyChartClickCallback> clickCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyChartHoverCallback> hoverCallbackProxy = JsHelper.newCallbackProxy();
	
	private final CallbackProxy<ProxyGenerateLegendCallback> legendCallbackProxy = JsHelper.newCallbackProxy();
	
	private ChartClickCallbackHandler clickCallbackHandler = null;
	
	private ChartHoverCallbackHandler hoverCallbackHandler = null;
	
	private ChartResizeCallbackHandler resizeCallbackHandler = null;
	
	private LegendHandler legendHandler = null;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		legendCallback,
		onResize,
		onClick,
		onHover
	}

	public EventableOptions(IsDefaultOptions defaultValues) {
		super(defaultValues);
		animation = new EventableAnimation(this, getDefaultValues().getAnimation(), getDelegated().getAnimation());
		legend = new EventableLegend(this, getDefaultValues().getLegend(),getDelegated().getLegend());
		// events
		clickCallbackProxy.setCallback(new ProxyChartClickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ChartClickCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				if (clickCallbackHandler != null) {
					clickCallbackHandler.onClick(chart, event, items);
				}
			}
		});
		
		hoverCallbackProxy.setCallback(new ProxyChartHoverCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ProxyChartHoverCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, org.pepstock.charba.client.jsinterop.commons.ArrayObject)
			 */
			@Override
			public void call(Chart chart, ChartNativeEvent event, ArrayObject<DatasetItem> items) {
				if (hoverCallbackHandler != null) {
					hoverCallbackHandler.onHover(chart, event, items);
				}
			}

		});
		
		resizeCallbackProxy.setCallback(new ProxyChartResizeCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.EventableOptions.ProxyChartResizeCallback#call(java.lang.Object, java.lang.Object, org.pepstock.charba.client.jsinterop.items.SizeItem)
			 */
			@Override
			public void call(NativeOptions context, Chart chart, SizeItem size) {
				if (resizeCallbackHandler != null) {
					resizeCallbackHandler.onResize(context, chart, size);
				}
			}
			
		});
		
		legendCallbackProxy.setCallback(new ProxyGenerateLegendCallback() {
			
			@Override
			public String call(Object context) {
				return legendHandler != null ? legendHandler.generateLegend(context) : LEGEND_CALLBACK_ERROR;
			}
		});
		

	}
	
	public void setCharbaId(String id) {
		getDelegated().setCharbaId(id);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getAnimation()
	 */
	@Override
	public EventableAnimation getAnimation() {
		return animation;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getLegend()
	 */
	@Override
	public EventableLegend getLegend() {
		return legend;
	}	

	/**
	 * @param hoverCallbackHandler the hoverCallbackHandler to set
	 */
	public void setHoverCallbackHandler(ChartHoverCallbackHandler hoverCallbackHandler) {
		if (hoverCallbackHandler != null) {
			getDelegated().setOnHover(hoverCallbackProxy.getProxy());	
		} else {
			remove(Property.onHover);
		}
		this.hoverCallbackHandler = hoverCallbackHandler;
	}

	/**
	 * @param resizeCallbackHandler the resizeCallbackHandler to set
	 */
	public void setResizeCallbackHandler(ChartResizeCallbackHandler resizeCallbackHandler) {
		if (resizeCallbackHandler != null) {
			getDelegated().setOnResize(resizeCallbackProxy.getProxy());	
		} else {
			remove(Property.onResize);
		}
		this.resizeCallbackHandler = resizeCallbackHandler;
	}

	/**
	 * @param clickCallbackHandler the clickCallbackHandler to set
	 */
	public void setClickCallbackHandler(ChartClickCallbackHandler clickCallbackHandler) {
		if (clickCallbackHandler != null) {
			getDelegated().setOnClick(clickCallbackProxy.getProxy());
		} else {
			remove(Property.onClick);
		}
		this.clickCallbackHandler = clickCallbackHandler;
	}
	
	/**
	 * @param legendHandler the legendHandler to set
	 */
	public void setLegendHandler(LegendHandler legendHandler) {
		if (legendHandler == null) {
			getDelegated().setLegendCallback(legendCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.legendCallback);
		}
		this.legendHandler = legendHandler;
	}
	
	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		getLegend().getLabels().setFilterHandler(filterHandler);
	}
	
	/**
	 * @param labelsHandler the labelsHandler to set
	 */
	public void setLegendLabelsHandler(LegendLabelsHandler labelsHandler) {
		getLegend().getLabels().setLabelsHandler(labelsHandler);
	}
	
	/**
	 * @param customHandler the customHandler to set
	 */
	public void setTooltipCustomHandler(TooltipCustomHandler customHandler) {
		getTooltips().setCustomHandler(customHandler);
	}
	
	/**
	 * @param itemSortHandler the itemSortHandler to set
	 */
	public void setTooltipItemSortHandler(TooltipItemSortHandler itemSortHandler) {
		getTooltips().setItemSortHandler(itemSortHandler);
	}
	
	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setTooltipFilterHandler(TooltipFilterHandler filterHandler) {
		getTooltips().setFilterHandler(filterHandler);
	}
	
	/**
	 * @param titleHandler the titleHandler to set
	 */
	public void setTooltipTitleHandler(TooltipTitleHandler titleHandler) {
		getTooltips().getCallbacks().setTitleHandler(titleHandler);
	}

	/**
	 * @param bodyHandler the bodyHandler to set
	 */
	public void setTooltipBodyHandler(TooltipBodyHandler bodyHandler) {
		getTooltips().getCallbacks().setBodyHandler(bodyHandler);
	}

	/**
	 * @param labelHandler the labelHandler to set
	 */
	public void setTooltipLabelHandler(TooltipLabelHandler labelHandler) {
		getTooltips().getCallbacks().setLabelHandler(labelHandler);

	}
	
	/**
	 * @param footerHandler the footerHandler to set
	 */
	public void setTooltipFooterHandler(TooltipFooterHandler footerHandler) {
		getTooltips().getCallbacks().setFooterHandler(footerHandler);
	}

	/**
	 * @param axisBuildTicksHandler the axisBuildTicksHandler to set
	 */
	public void setAxisBuildTicksHandler(Scale scale, AxisBuildTicksHandler axisBuildTicksHandler) {
		scale.setAxisBuildTicksHandler(axisBuildTicksHandler);
	}

	/**
	 * @param axisCalculateTickRotationHandler the axisCalculateTickRotationHandler to set
	 */
	public void setAxisCalculateTickRotationHandler(Scale scale, AxisCalculateTickRotationHandler axisCalculateTickRotationHandler) {
		scale.setAxisCalculateTickRotationHandler(axisCalculateTickRotationHandler);
	}

	/**
	 * @param axisDataLimitsHandler the axisDataLimitsHandler to set
	 */
	public void setAxisDataLimitsHandler(Scale scale, AxisDataLimitsHandler axisDataLimitsHandler) {
		scale.setAxisDataLimitsHandler(axisDataLimitsHandler);
	}

	/**
	 * @param axisDimensionsHandler the axisDimensionsHandler to set
	 */
	public void setAxisDimensionsHandler(Scale scale, AxisDimensionsHandler axisDimensionsHandler) {
		scale.setAxisDimensionsHandler(axisDimensionsHandler);
	}

	/**
	 * @param axisFitHandler the axisFitHandler to set
	 */
	public void setAxisFitHandler(Scale scale, AxisFitHandler axisFitHandler) {
		scale.setAxisFitHandler(axisFitHandler);
	}

	/**
	 * @param axisTickToLabelConversionHandler the axisTickToLabelConversionHandler to set
	 */
	public void setAxisTickToLabelConversionHandler(Scale scale, AxisTickToLabelConversionHandler axisTickToLabelConversionHandler) {
		scale.setAxisTickToLabelConversionHandler(axisTickToLabelConversionHandler);
	}
	
	/**
	 * @param axisUpdateHandler the axisUpdateHandler to set
	 */
	public void setAxisUpdateHandler(Scale scale, AxisUpdateHandler axisUpdateHandler) {
		scale.setAxisUpdateHandler(axisUpdateHandler);
	}
	
	// FIXME to be removed
	public NativeOptions getObject() {
		return getDelegated();
	}
}
