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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-zoom.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-zoom.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * @license",
		" * chartjs-plugin-zoom",
		" * http://chartjs.org/",
		" * Version: 0.7.7",
		" *",
		" * Copyright 2020 Chart.js Contributors",
		" * Released under the MIT license",
		" * https://github.com/chartjs/chartjs-plugin-zoom/blob/master/LICENSE.md",
		" */",
		"!function(e,o){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=o(require(\"chart.js\"),require(\"hammerjs\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"hammerjs\"],o):(e=e||self).ChartZoom=o(e.Chart,e.Hammer)}(this,(function(e,o){\"use strict\";e=e&&Object.prototype.hasOwnProperty.call(e,\"default\")?e.default:e,o=o&&Object.prototype.hasOwnProperty.call(o,\"default\")?o.default:o;var n=e.helpers,t=e.Zoom=e.Zoom||{},a=t.zoomFunctions=t.zoomFunctions||{},i=t.panFunctions=t.panFunctions||{};function r(e,o){var t={};void 0!==e.options.pan&&(t.pan=e.options.pan),void 0!==e.options.zoom&&(t.zoom=e.options.zoom);var a=e.$zoom;o=a._options=n.merge({},[o,t]);var i=a._node,r=o.zoom&&o.zoom.enabled,l=o.zoom.drag;r&&!l?i.addEventListener(\"wheel\",a._wheelHandler):i.removeEventListener(\"wheel\",a._wheelHandler),r&&l?(i.addEventListener(\"mousedown\",a._mouseDownHandler),i.ownerDocument.addEventListener(\"mouseup\",a._mouseUpHandler)):(i.removeEventListe",
		"ner(\"mousedown\",a._mouseDownHandler),i.removeEventListener(\"mousemove\",a._mouseMoveHandler),i.ownerDocument.removeEventListener(\"mouseup\",a._mouseUpHandler))}function l(e){var o=e.$zoom._originalOptions;n.each(e.scales,(function(e){o[e.id]||(o[e.id]=n.clone(e.options))})),n.each(o,(function(n,t){e.scales[t]||delete o[t]}))}function m(e,o,n){return void 0===e||(\"string\"==typeof e?-1!==e.indexOf(o):\"function\"==typeof e&&-1!==e({chart:n}).indexOf(o))}function s(e,o){if(e.scaleAxes&&e.rangeMax&&!n.isNullOrUndef(e.rangeMax[e.scaleAxes])){var t=e.rangeMax[e.scaleAxes];o>t&&(o=t)}return o}function u(e,o){if(e.scaleAxes&&e.rangeMin&&!n.isNullOrUndef(e.rangeMin[e.scaleAxes])){var t=e.rangeMin[e.scaleAxes];o<t&&(o=t)}return o}function c(e,o,n,t){var a=e.max-e.min,i=a*(o-1),r=e.isHorizontal()?n.x:n.y,l=(e.getValueForPixel(r)-e.min)/a,m=i*l,c=i*(1-l);e.options.min=u(t,e.min+m),e.options.max=s(t,e.max-c)}function d(e,o,n,t){var i=a[e.type];i&&i(e,o,n,t)}function p(e,o,t,a,i,r){var s=e.char",
		"tArea;a||(a={x:(s.left+s.right)/2,y:(s.top+s.bottom)/2});var u=e.$zoom._options.zoom;if(u.enabled){l(e);var c,p=\"function\"==typeof u.mode?u.mode({chart:e}):u.mode;c=\"xy\"===p&&void 0!==i?i:\"xy\",n.each(e.scales,(function(n){n.isHorizontal()&&m(p,\"x\",e)&&m(c,\"x\",e)?(u.scaleAxes=\"x\",d(n,o,a,u)):!n.isHorizontal()&&m(p,\"y\",e)&&m(c,\"y\",e)&&(u.scaleAxes=\"y\",d(n,t,a,u))})),r?(e.options.animation.zoom||(e.options.animation.zoom={duration:r,easing:\"easeOutQuad\"}),e.update(\"zoom\")):e.update(\"none\"),\"function\"==typeof u.onZoom&&u.onZoom({chart:e})}}function f(e,o,t){var a,i=e.options,r=e.min,l=e.max,m=e.getValueForPixel(e.getPixelForValue(r)-o),s=e.getValueForPixel(e.getPixelForValue(l)-o),u=m=m.valueOf?m.valueOf():m,c=s=s.valueOf?s.valueOf():s;t.scaleAxes&&t.rangeMin&&!n.isNullOrUndef(t.rangeMin[t.scaleAxes])&&(u=t.rangeMin[t.scaleAxes]),t.scaleAxes&&t.rangeMax&&!n.isNullOrUndef(t.rangeMax[t.scaleAxes])&&(c=t.rangeMax[t.scaleAxes]),m>=u&&s<=c?(i.min=m,i.max=s):m<u?(a=r-u,i",
		".min=u,i.max=l-a):s>c&&(a=c-l,i.max=c,i.min=r+a)}function v(e,o,n){var t=i[e.type];t&&t(e,o,n)}t.zoomFunctions.category=function(e,o,n,a){var i=e.chart.data.labels,r=e.min,l=i.length-1,m=e.max,c=a.sensitivity,d=e.isHorizontal()?e.left+e.width/2:e.top+e.height/2,p=e.isHorizontal()?n.x:n.y;t.zoomCumulativeDelta=o>1?t.zoomCumulativeDelta+1:t.zoomCumulativeDelta-1,Math.abs(t.zoomCumulativeDelta)>c&&(t.zoomCumulativeDelta<0?(p>=d?r<=0?m=Math.min(l,m+1):r=Math.max(0,r-1):p<d&&(m>=l?r=Math.max(0,r-1):m=Math.min(l,m+1)),t.zoomCumulativeDelta=0):t.zoomCumulativeDelta>0&&(p>=d?r=r<m?r=Math.min(m,r+1):r:p<d&&(m=m>r?m=Math.max(r,m-1):m),t.zoomCumulativeDelta=0),e.options.min=u(a,i[r]),e.options.max=s(a,i[m]))},t.zoomFunctions.time=function(e,o,n,t){c(e,o,n,t)},t.zoomFunctions.linear=c,t.zoomFunctions.logarithmic=c,t.panFunctions.category=function(e,o,n){var a,i=e.chart.data.labels,r=i.length-1,l=Math.max(e.ticks.length,1),m=n.speed,c=e.min,d=Math.round(e.width/(l*m));t.panCumulativeDelta+=o,c=t.pan",
		"CumulativeDelta>d?Math.max(0,c-1):t.panCumulativeDelta<-d?Math.min(r-l+1,c+1):c,t.panCumulativeDelta=c!==e.min?0:t.panCumulativeDelta,a=Math.min(r,c+l-1),e.options.min=u(n,i[c]),e.options.max=s(n,i[a])},t.panFunctions.time=function(e,o,n){f(e,o,n)},t.panFunctions.linear=f,t.panFunctions.logarithmic=f,t.panCumulativeDelta=0,t.zoomCumulativeDelta=0;var h={id:\"zoom\",afterInit:function(e){e.resetZoom=function(){l(e);var o=e.$zoom._originalOptions;n.each(e.scales,(function(e){var n=e.options;o[e.id]?(n.min=o[e.id].min,n.max=o[e.id].max):(delete n.min,delete n.max)})),e.update()}},beforeUpdate:function(e,o){r(e,o)},beforeInit:function(e,a){e.$zoom={_originalOptions:{}};var i=e.$zoom._node=e.ctx.canvas;r(e,a);var s=e.$zoom._options,u=s.pan&&s.pan.threshold;e.$zoom._mouseDownHandler=function(o){i.addEventListener(\"mousemove\",e.$zoom._mouseMoveHandler),e.$zoom._dragZoomStart=o},e.$zoom._mouseMoveHandler=function(o){e.$zoom._dragZoomStart&&(e.$zoom._dragZoomEnd=o,e.update(\"none\"))},e.$zoom.",
		"_mouseUpHandler=function(o){if(e.$zoom._dragZoomStart){i.removeEventListener(\"mousemove\",e.$zoom._mouseMoveHandler);var n=e.$zoom._dragZoomStart,t=n.target.getBoundingClientRect().left,a=Math.min(n.clientX,o.clientX)-t,r=Math.max(n.clientX,o.clientX)-t,l=n.target.getBoundingClientRect().top,u=Math.min(n.clientY,o.clientY)-l,c=r-a,d=Math.max(n.clientY,o.clientY)-l-u;e.$zoom._dragZoomStart=null,e.$zoom._dragZoomEnd=null;var f=s.zoom&&s.zoom.threshold||0;if(!(c<=f&&d<=f)){var v=e.chartArea,h=e.$zoom._options.zoom,z=v.right-v.left,g=m(h.mode,\"x\",e)&&c?1+(z-c)/z:1,x=v.bottom-v.top,y=m(h.mode,\"y\",e);p(e,g,y&&d?1+(x-d)/x:1,{x:(a-v.left)/(1-c/z)+v.left,y:(u-v.top)/(1-d/x)+v.top},void 0,h.drag.animationDuration),\"function\"==typeof h.onZoomComplete&&h.onZoomComplete({chart:e})}}};var c=null;if(e.$zoom._wheelHandler=function(o){if(o.cancelable&&o.preventDefault(),void 0!==o.deltaY){var n=o.target.getBoundingClientRect(),t={x:o.clientX-n.left,y:o.clientY-n.top},a=e.$zoom._options.zoom,i=a.s",
		"peed;o.deltaY>=0&&(i=-i),p(e,1+i,1+i,t),clearTimeout(c),c=setTimeout((function(){\"function\"==typeof a.onZoomComplete&&a.onZoomComplete({chart:e})}),250)}},o){var d,f=new o.Manager(i);f.add(new o.Pinch),f.add(new o.Pan({threshold:u}));var h=function(o){var n=1/d*o.scale,t=o.target.getBoundingClientRect(),a={x:o.center.x-t.left,y:o.center.y-t.top},i=Math.abs(o.pointers[0].clientX-o.pointers[1].clientX),r=Math.abs(o.pointers[0].clientY-o.pointers[1].clientY),l=i/r;p(e,n,n,a,l>.3&&l<1.7?\"xy\":i>r?\"x\":\"y\");var m=e.$zoom._options.zoom;\"function\"==typeof m.onZoomComplete&&m.onZoomComplete({chart:e}),d=o.scale};f.on(\"pinchstart\",(function(){d=1})),f.on(\"pinch\",h),f.on(\"pinchend\",(function(e){h(e),d=null,t.zoomCumulativeDelta=0}));var z=null,g=null,x=!1,y=function(o){if(null!==z&&null!==g){x=!0;var t=o.deltaX-z,a=o.deltaY-g;z=o.deltaX,g=o.deltaY,function(e,o,t){l(e);var a=e.$zoom._options.pan;if(a.enabled){var i=\"function\"==typeof a.mode?a.mode({chart:e}):a.mode;n.each(e.scales,",
		"(function(n){n.isHorizontal()&&m(i,\"x\",e)&&0!==o?(a.scaleAxes=\"x\",v(n,o,a)):!n.isHorizontal()&&m(i,\"y\",e)&&0!==t&&(a.scaleAxes=\"y\",v(n,t,a))})),e.update(\"none\"),\"function\"==typeof a.onPan&&a.onPan({chart:e})}}(e,t,a)}};f.on(\"panstart\",(function(e){z=0,g=0,y(e)})),f.on(\"panmove\",y),f.on(\"panend\",(function(){z=null,g=null,t.panCumulativeDelta=0,setTimeout((function(){x=!1}),500);var o=e.$zoom._options.pan;\"function\"==typeof o.onPanComplete&&o.onPanComplete({chart:e})})),e.$zoom._ghostClickHandler=function(e){x&&e.cancelable&&(e.stopImmediatePropagation(),e.preventDefault())},i.addEventListener(\"click\",e.$zoom._ghostClickHandler),e._mc=f}},beforeDatasetsDraw:function(e){var o=e.ctx;if(e.$zoom._dragZoomEnd){var n=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(a.isHorizontal())return a}}(e),t=function(e){for(var o=e.scales,n=Object.keys(o),t=0;t<n.length;t++){var a=o[n[t]];if(!a.isHorizontal())return a}}(e),a=e.$zoom._dragZoomStart,",
		"i=e.$zoom._dragZoomEnd,r=n.left,l=n.right,s=t.top,u=t.bottom;if(m(e.$zoom._options.zoom.mode,\"x\",e)){var c=a.target.getBoundingClientRect().left;r=Math.min(a.clientX,i.clientX)-c,l=Math.max(a.clientX,i.clientX)-c}if(m(e.$zoom._options.zoom.mode,\"y\",e)){var d=a.target.getBoundingClientRect().top;s=Math.min(a.clientY,i.clientY)-d,u=Math.max(a.clientY,i.clientY)-d}var p=l-r,f=u-s,v=e.$zoom._options.zoom.drag;o.save(),o.beginPath(),o.fillStyle=v.backgroundColor||\"rgba(225,225,225,0.3)\",o.fillRect(r,s,p,f),v.borderWidth>0&&(o.lineWidth=v.borderWidth,o.strokeStyle=v.borderColor||\"rgba(225,225,225)\",o.strokeRect(r,s,p,f)),o.restore()}},destroy:function(e){if(e.$zoom){var o=e.$zoom,n=o._node;n.removeEventListener(\"mousedown\",o._mouseDownHandler),n.removeEventListener(\"mousemove\",o._mouseMoveHandler),n.ownerDocument.removeEventListener(\"mouseup\",o._mouseUpHandler),n.removeEventListener(\"wheel\",o._wheelHandler),n.removeEventListener(\"click\",o._ghostClickHandler),delete e.$zoom;v",
		"ar t=e._mc;t&&(t.remove(\"pinchstart\"),t.remove(\"pinch\"),t.remove(\"pinchend\"),t.remove(\"panstart\"),t.remove(\"pan\"),t.remove(\"panend\"),t.destroy())}}};return e.register(h),e.Zoom.defaults=e.defaults.plugins.zoom={pan:{enabled:!1,mode:\"xy\",speed:20,threshold:10},zoom:{enabled:!1,mode:\"xy\",sensitivity:3,speed:.1}},h}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-zoom.min.js</code> content.
	 */
	ZoomPluginResource() {
		super(ResourceName.ZOOM_PLUGIN, CONTENT);
	}

}
