package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.GlobalOptions;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;

public final class ChartOptionsBuilder {

	/**
	 * 
	 */
	private ChartOptionsBuilder() {
		// do nothing
	}

	public static NativeOptions get(Type type) {
		ChartOptions base = Chart.defaults().chart(type);
		Scale scale = Chart.defaults().scale();
		GlobalOptions global = Chart.defaults().global();

		NativeOptions chartOptions = Chart.helpers().clone(base.getDelegated());
		NativeScale scaleOptions = Chart.helpers().clone(scale.getDelegated());
		NativeOptions globalOptions = Chart.helpers().clone(global.getDelegated());
		
		if (chartOptions.getScale() != null) {
			Chart.helpers().mergeIf(chartOptions.getScale(), scaleOptions);
		} else if (chartOptions.getScales() != null) {
			if (chartOptions.getScales().getXAxes() != null) {
				ArrayObject<NativeScale> xAxes = chartOptions.getScales().getXAxes();
				for (int i=0; i<xAxes.length(); i++){
					Chart.helpers().mergeIf(xAxes.get(i), scaleOptions);
				}
			}
			if (chartOptions.getScales().getYAxes() != null) {
				ArrayObject<NativeScale> yAxes = chartOptions.getScales().getYAxes();
				for (int i=0; i<yAxes.length(); i++){
					Chart.helpers().mergeIf(yAxes.get(i), scaleOptions);
				}
			}
		}
		return Chart.helpers().mergeIf(chartOptions, globalOptions);
	}
	
//	var chartOptions = new Object();
//	if ($wnd.Chart.defaults.hasOwnProperty(chartType)){
//		chartOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults[chartType]);
//	}
//	var scaleOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.scale);
//    var globalOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.global);
//	
//	if (chartOptions.hasOwnProperty("scale")){
//		var scaleChartOptions = $wnd.Chart.helpers.mergeIf(chartOptions.scale, scaleOptions);
//	} else if (chartOptions.hasOwnProperty("scales")){
//		var xAxes = chartOptions.scales.xAxes;
//		for (var i=0; i<xAxes.length; i++){
//			$wnd.Chart.helpers.mergeIf(xAxes[i], scaleOptions, xAxes[i]);
//		}
//		var yAxes = chartOptions.scales.yAxes;
//		for (var i=0; i<yAxes.length; i++){
//			$wnd.Chart.helpers.mergeIf(yAxes[i], scaleOptions);
//		}
//	}
//    $wnd.Chart.helpers.mergeIf(chartOptions, globalOptions);
//    return chartOptions;

}
