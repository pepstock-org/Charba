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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-datalabels.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-datalabels.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-plugin-datalabels v0.7.0",
		" * https://chartjs-plugin-datalabels.netlify.com",
		" * (c) 2020 Chart.js Contributors",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=e(require(\"chart.js\")):\"function\"==typeof define&&define.amd?define([\"chart.js\"],e):(t=t||self).ChartDataLabels=e(t.Chart)}(this,(function(t){\"use strict\";var e=(t=t&&Object.prototype.hasOwnProperty.call(t,\"default\")?t.default:t).helpers,r=function(){if(\"undefined\"!=typeof window){if(window.devicePixelRatio)return window.devicePixelRatio;var t=window.screen;if(t)return(t.deviceXDPI||1)/(t.logicalXDPI||1)}return 1}(),n={toTextLines:function(t){var r,n=[];for(t=[].concat(t);t.length;)\"string\"==typeof(r=t.pop())?n.unshift.apply(n,r.split(\"\\n\")):Array.isArray(r)?t.push.apply(t,r):e.isNullOrUndef(t)||n.unshift(\"\"+r);return n},toFontString:function(t){return!t||e.isNullOrUndef(t.size)||e.isNullOrUndef(t.family)?null:(t.style?t.style+\" \":\"\")+(t.weight?t.weight+\" \":\"\")+t.size+\"px \"+t.family},textSize:function(t,e,r){var n,i=[].concat(e),a=i.length,o=t.font,l=0;for(t.font=r.string,",
		"n=0;n<a;++n)l=Math.max(t.measureText(i[n]).width,l);return t.font=o,{height:a*r.lineHeight,width:l}},parseFont:function(r){var i=t.defaults,a=e.valueOrDefault(r.size,i.font.size),o={family:e.valueOrDefault(r.family,i.font.family),lineHeight:e.toLineHeight(r.lineHeight,a),size:a,style:e.valueOrDefault(r.style,i.font.style),weight:e.valueOrDefault(r.weight,null),string:\"\"};return o.string=n.toFontString(o),o},bound:function(t,e,r){return Math.max(t,Math.min(e,r))},arrayDiff:function(t,e){var r,n,i,a,o=t.slice(),l=[];for(r=0,i=e.length;r<i;++r)a=e[r],-1===(n=o.indexOf(a))?l.push([a,1]):o.splice(n,1);for(r=0,i=o.length;r<i;++r)l.push([o[r],-1]);return l},rasterize:function(t){return Math.round(t*r)/r}};function i(t,e){var r=e.x,n=e.y;if(null===r)return{x:0,y:-1};if(null===n)return{x:1,y:0};var i=t.x-r,a=t.y-n,o=Math.sqrt(i*i+a*a);return{x:o?i/o:0,y:o?a/o:-1}}function a(t,e,r){var n=0;return t<r.left?n|=1:t>r.right&&(n|=2),e<r.top?n|=8:e>r.bottom&&(n|=4),n}function o(t,e){var r,n,i=e.ancho",
		"r,o=t;return e.clamp&&(o=function(t,e){for(var r,n,i,o=t.x0,l=t.y0,s=t.x1,u=t.y1,f=a(o,l,e),c=a(s,u,e);f|c&&!(f&c);)8&(r=f||c)?(n=o+(s-o)*(e.top-l)/(u-l),i=e.top):4&r?(n=o+(s-o)*(e.bottom-l)/(u-l),i=e.bottom):2&r?(i=l+(u-l)*(e.right-o)/(s-o),n=e.right):1&r&&(i=l+(u-l)*(e.left-o)/(s-o),n=e.left),r===f?f=a(o=n,l=i,e):c=a(s=n,u=i,e);return{x0:o,x1:s,y0:l,y1:u}}(o,e.area)),\"start\"===i?(r=o.x0,n=o.y0):\"end\"===i?(r=o.x1,n=o.y1):(r=(o.x0+o.x1)/2,n=(o.y0+o.y1)/2),function(t,e,r,n,i){switch(i){case\"center\":r=n=0;break;case\"bottom\":r=0,n=1;break;case\"right\":r=1,n=0;break;case\"left\":r=-1,n=0;break;case\"top\":r=0,n=-1;break;case\"start\":r=-r,n=-n;break;case\"end\":break;default:i*=Math.PI/180,r=Math.cos(i),n=Math.sin(i)}return{x:t,y:e,vx:r,vy:n}}(r,n,t.vx,t.vy,e.align)}var l=function(t,e){var r=(t.startAngle+t.endAngle)/2,n=Math.cos(r),i=Math.sin(r),a=t.innerRadius,l=t.outerRadius;return o({x0:t.x+n*a,y0:t.y+i*a,x1:t.x+n*l,y1:t.y+i*l,vx:n,vy:i},e)},s=function(t,e){var r=i(t,e.origin),",
		"n=r.x*t.options.radius,a=r.y*t.options.radius;return o({x0:t.x-n,y0:t.y-a,x1:t.x+n,y1:t.y+a,vx:r.x,vy:r.y},e)},u=function(t,e){var r=i(t,e.origin),n=t.x,a=t.y,l=0,s=0;return t.horizontal?(n=Math.min(t.x,t.base),l=Math.abs(t.base-t.x)):(a=Math.min(t.y,t.base),s=Math.abs(t.base-t.y)),o({x0:n,y0:a+s,x1:n+l,y1:a,vx:r.x,vy:r.y},e)},f=function(t,e){var r=i(t,e.origin);return o({x0:t.x,y0:t.y,x1:t.x,y1:t.y,vx:r.x,vy:r.y},e)},c=t.helpers,h=n.rasterize;function d(t,e){var r=t.horizontal,n=e.chart.getDatasetMeta(e.datasetIndex).vScale;if(!n)return null;if(void 0!==n.xCenter&&void 0!==n.yCenter)return{x:n.xCenter,y:n.yCenter};var i=n.getBasePixel();return r?{x:i,y:null}:{x:null,y:i}}function x(t,e,r){var n=r.backgroundColor,i=r.borderColor,a=r.borderWidth;(n||i&&a)&&(t.beginPath(),function(t,e,r,n,i,a){const o=Math.PI/2;if(a){var l=Math.min(a,i/2,n/2),s=e+l,u=r+l,f=e+n-l,c=r+i-l;t.moveTo(e,u),s<f&&u<c?(t.arc(s,u,l,-Math.PI,-o),t.arc(f,u,l,-o,0),t.arc(f,c,l,0,o),t.arc(s,c,l,o,Math.PI)):s<f?(t.moveT",
		"o(s,r),t.arc(f,u,l,-o,o),t.arc(s,u,l,o,Math.PI+o)):u<c?(t.arc(s,u,l,-Math.PI,0),t.arc(s,c,l,0,Math.PI)):t.arc(s,u,l,-Math.PI,Math.PI),t.closePath(),t.moveTo(e,r)}else t.rect(e,r,n,i)}(t,h(e.x)+a/2,h(e.y)+a/2,h(e.w)-a,h(e.h)-a,r.borderRadius),t.closePath(),n&&(t.fillStyle=n,t.fill()),i&&a&&(t.strokeStyle=i,t.lineWidth=a,t.lineJoin=\"miter\",t.stroke()))}function y(t,e,r){var n=t.shadowBlur,i=r.stroked,a=h(r.x),o=h(r.y),l=h(r.w);i&&t.strokeText(e,a,o,l),r.filled&&(n&&i&&(t.shadowBlur=0),t.fillText(e,a,o,l),n&&i&&(t.shadowBlur=n))}var v=function(t,e,r,n){var i=this;i._config=t,i._index=n,i._model=null,i._rects=null,i._ctx=e,i._el=r};Object.assign(v.prototype,{_modelize:function(e,r,i,a){var o,h=this._index,x=c.resolve,y=n.parseFont(x([i.font,{}],a,h)),v=x([i.color,t.defaults.font.color],a,h);return{align:x([i.align,\"center\"],a,h),anchor:x([i.anchor,\"center\"],a,h),area:a.chart.chartArea,backgroundColor:x([i.backgroundColor,null],a,h),borderColor:x([i.borderColor,null],a,h),borderRadius:",
		"x([i.borderRadius,0],a,h),borderWidth:x([i.borderWidth,0],a,h),clamp:x([i.clamp,!1],a,h),clip:x([i.clip,!1],a,h),color:v,display:e,font:y,lines:r,offset:x([i.offset,0],a,h),opacity:x([i.opacity,1],a,h),origin:d(this._el,a),padding:c.toPadding(x([i.padding,0],a,h)),positioner:(o=this._el,o instanceof t.elements.ArcElement?l:o instanceof t.elements.PointElement?s:o instanceof t.elements.BarElement?u:f),rotation:x([i.rotation,0],a,h)*(Math.PI/180),size:n.textSize(this._ctx,r,y),textAlign:x([i.textAlign,\"start\"],a,h),textShadowBlur:x([i.textShadowBlur,0],a,h),textShadowColor:x([i.textShadowColor,v],a,h),textStrokeColor:x([i.textStrokeColor,v],a,h),textStrokeWidth:x([i.textStrokeWidth,0],a,h)}},update:function(t){var e,r,i,a=this,o=null,l=null,s=a._index,u=a._config,f=c.resolve([u.display,!0],t,s);f&&(e=t.dataset.data[s],r=c.valueOrDefault(c.callback(u.formatter,[e,t]),e),(i=c.isNullOrUndef(r)?[]:n.toTextLines(r)).length&&(l=function(t){var e=t.borderWidth||0,r=t.padding,n=t.size.height,i=",
		"t.size.width,a=-i/2,o=-n/2;return{frame:{x:a-r.left-e,y:o-r.top-e,w:i+r.width+2*e,h:n+r.height+2*e},text:{x:a,y:o,w:i,h:n}}}(o=a._modelize(f,i,u,t)))),a._model=o,a._rects=l},geometry:function(){return this._rects?this._rects.frame:{}},rotation:function(){return this._model?this._model.rotation:0},visible:function(){return this._model&&this._model.opacity},model:function(){return this._model},draw:function(t,e){var r,i=t.ctx,a=this._model,o=this._rects;this.visible()&&(i.save(),a.clip&&(r=a.area,i.beginPath(),i.rect(r.left,r.top,r.right-r.left,r.bottom-r.top),i.clip()),i.globalAlpha=n.bound(0,a.opacity,1),i.translate(h(e.x),h(e.y)),i.rotate(a.rotation),x(i,o.frame,a),function(t,e,r,n){var i,a=n.textAlign,o=n.color,l=!!o,s=n.font,u=e.length,f=n.textStrokeColor,c=n.textStrokeWidth,h=f&&c;if(u&&(l||h))for(r=function(t,e,r){var n=r.lineHeight,i=t.w,a=t.x;return\"center\"===e?a+=i/2:\"end\"!==e&&\"right\"!==e||(a+=i),{h:n,w:i,x:a,y:t.y+n/2}}(r,a,s),t.font=s.string,t.textAlign=a,t.textBaseline",
		"=\"middle\",t.shadowBlur=n.textShadowBlur,t.shadowColor=n.textShadowColor,l&&(t.fillStyle=o),h&&(t.lineJoin=\"round\",t.lineWidth=c,t.strokeStyle=f),i=0,u=e.length;i<u;++i)y(t,e[i],{stroked:h,filled:l,w:r.w,x:r.x,y:r.y+r.h*i})}(i,a.lines,o.text,a),i.restore())}});t.helpers;var b=Number.MIN_SAFE_INTEGER||-9007199254740991,_=Number.MAX_SAFE_INTEGER||9007199254740991;function p(t,e,r){var n=Math.cos(r),i=Math.sin(r),a=e.x,o=e.y;return{x:a+n*(t.x-a)-i*(t.y-o),y:o+i*(t.x-a)+n*(t.y-o)}}function g(t,e){var r,n,i,a,o,l=_,s=b,u=e.origin;for(r=0;r<t.length;++r)i=(n=t[r]).x-u.x,a=n.y-u.y,o=e.vx*i+e.vy*a,l=Math.min(l,o),s=Math.max(s,o);return{min:l,max:s}}function m(t,e){var r=e.x-t.x,n=e.y-t.y,i=Math.sqrt(r*r+n*n);return{vx:(e.x-t.x)/i,vy:(e.y-t.y)/i,origin:t,ln:i}}var w=function(){this._rotation=0,this._rect={x:0,y:0,w:0,h:0}};function M(t,e,r){var n=e.positioner(t,e),i=n.vx,a=n.vy;if(!i&&!a)return{x:n.x,y:n.y};var o=r.w,l=r.h,s=e.rotation,u=Math.abs(o/2*Math.cos(s))+Math.abs(l/2*Math.sin(s)),f=M",
		"ath.abs(o/2*Math.sin(s))+Math.abs(l/2*Math.cos(s)),c=1/Math.max(Math.abs(i),Math.abs(a));return u*=i*c,f*=a*c,u+=e.offset*i,f+=e.offset*a,{x:n.x+u,y:n.y+f}}Object.assign(w.prototype,{center:function(){var t=this._rect;return{x:t.x+t.w/2,y:t.y+t.h/2}},update:function(t,e,r){this._rotation=r,this._rect={x:e.x+t.x,y:e.y+t.y,w:e.w,h:e.h}},contains:function(t){var e=this._rect;return!((t=p(t,this.center(),-this._rotation)).x<e.x-1||t.y<e.y-1||t.x>e.x+e.w+2||t.y>e.y+e.h+2)},intersects:function(t){var e,r,n,i=this._points(),a=t._points(),o=[m(i[0],i[1]),m(i[0],i[3])];for(this._rotation!==t._rotation&&o.push(m(a[0],a[1]),m(a[0],a[3])),e=0;e<o.length;++e)if(r=g(i,o[e]),n=g(a,o[e]),r.max<n.min||n.max<r.min)return!1;return!0},_points:function(){var t=this._rect,e=this._rotation,r=this.center();return[p({x:t.x,y:t.y},r,e),p({x:t.x+t.w,y:t.y},r,e),p({x:t.x+t.w,y:t.y+t.h},r,e),p({x:t.x,y:t.y+t.h},r,e)]}});var k={prepare:function(t){var e,r,n,i,a,o=[];for(e=0,n=t.length;e<n;++e)for(r=0,i=t[e].length;r",
		"<i;++r)a=t[e][r],o.push(a),a.$layout={_box:new w,_hidable:!1,_visible:!0,_set:e,_idx:r};return o.sort((function(t,e){var r=t.$layout,n=e.$layout;return r._idx===n._idx?n._set-r._set:n._idx-r._idx})),this.update(o),o},update:function(t){var e,r,n,i,a,o=!1;for(e=0,r=t.length;e<r;++e)i=(n=t[e]).model(),(a=n.$layout)._hidable=i&&\"auto\"===i.display,a._visible=n.visible(),o|=a._hidable;o&&function(t){var e,r,n,i,a,o;for(e=0,r=t.length;e<r;++e)(i=(n=t[e]).$layout)._visible&&(a=n.geometry(),o=M(n._el,n.model(),a),i._box.update(o,a,n.rotation()));(function(t,e){var r,n,i,a;for(r=t.length-1;r>=0;--r)for(i=t[r].$layout,n=r-1;n>=0&&i._visible;--n)(a=t[n].$layout)._visible&&i._box.intersects(a._box)&&e(i,a)})(t,(function(t,e){var r=t._hidable,n=e._hidable;r&&n||n?e._visible=!1:r&&(t._visible=!1)}))}(t)},lookup:function(t,e){var r,n;for(r=t.length-1;r>=0;--r)if((n=t[r].$layout)&&n._visible&&n._box.contains(e))return t[r];return null},draw:function(t,e){var r,n,i,a,o,l;for(this.update(e),r=0,n=e.len",
		"gth;r<n;++r)(a=(i=e[r]).$layout)._visible&&(o=i.geometry(),l=M(i._el,i.model(),o),a._box.update(l,o,i.rotation()),i.draw(t,l))}},S=t.helpers,C={align:\"center\",anchor:\"center\",backgroundColor:null,borderColor:null,borderRadius:0,borderWidth:0,clamp:!1,clip:!1,color:void 0,display:!0,font:{family:void 0,lineHeight:1.2,size:void 0,style:void 0,weight:null},formatter:function(t){if(S.isNullOrUndef(t))return null;var e,r,n,i=t;if(S.isObject(t))if(S.isNullOrUndef(t.label))if(S.isNullOrUndef(t.r))for(i=\"\",n=0,r=(e=Object.keys(t)).length;n<r;++n)i+=(0!==n?\", \":\"\")+e[n]+\": \"+t[e[n]];else i=t.r;else i=t.label;return\"\"+i},labels:void 0,listeners:{},offset:4,opacity:1,padding:{top:4,right:4,bottom:4,left:4},rotation:0,textAlign:\"start\",textStrokeColor:void 0,textStrokeWidth:0,textShadowBlur:0,textShadowColor:void 0},P=t.helpers,O=\"$datalabels\";function I(t,e,r){if(e){var n,i=r.$context,a=r.$groups;e[a._set]&&(n=e[a._set][a._key])&&!0===P.callback(n,[i])&&(t[O]._dirty=!0,r.update(i",
		"))}}function $(t,e){var r,n,i=t[O],a=i._listeners;if(a.enter||a.leave){if(\"mousemove\"===e.type)n=k.lookup(i._labels,e);else if(\"mouseout\"!==e.type)return;r=i._hovered,i._hovered=n,function(t,e,r,n){var i,a;(r||n)&&(r?n?r!==n&&(a=i=!0):a=!0:i=!0,a&&I(t,e.leave,r),i&&I(t,e.enter,n))}(t,a,r,n)}}var z={id:\"datalabels\",beforeInit:function(t){t[O]={_actives:[]}},beforeUpdate:function(t){var e=t[O];e._listened=!1,e._listeners={},e._datasets=[],e._labels=[]},afterDatasetUpdate:function(t,e,r){var n,i,a,o,l,s,u,f,c=e.index,h=t[O],d=h._datasets[c]=[],x=t.isDatasetVisible(c),y=t.data.datasets[c],b=function(t,e){var r,n,i,a=t.datalabels,o=[];return!1===a?null:(!0===a&&(a={}),e=P.merge({},[e,a]),n=e.labels||{},i=Object.keys(n),delete e.labels,i.length?i.forEach((function(t){n[t]&&o.push(P.merge({},[e,n[t],{_key:t}]))})):o.push(e),r=o.reduce((function(t,e){return P.each(e.listeners||{},(function(r,n){t[n]=t[n]||{},t[n][e._key||\"$default\"]=r})),delete e.listeners,t}),{}),{labels:o,listeners:r}",
		")}(y,r),_=e.meta.data||[],p=t.ctx;for(p.save(),n=0,a=_.length;n<a;++n)if((u=_[n])[O]=[],x&&u&&t.getDataVisibility(n)&&!u.skip)for(i=0,o=b.labels.length;i<o;++i)s=(l=b.labels[i])._key,(f=new v(l,p,u,n)).$groups={_set:c,_key:s||\"$default\"},f.$context={active:!1,chart:t,dataIndex:n,dataset:y,datasetIndex:c},f.update(f.$context),u[O].push(f),d.push(f);p.restore(),P.merge(h._listeners,b.listeners,{merger:function(t,r,n){r[t]=r[t]||{},r[t][e.index]=n[t],h._listened=!0}})},afterUpdate:function(t,e){t[O]._labels=k.prepare(t[O]._datasets,e)},afterDatasetsDraw:function(t){k.draw(t,t[O]._labels)},beforeEvent:function(t,e){if(t[O]._listened)switch(e.type){case\"mousemove\":case\"mouseout\":$(t,e);break;case\"click\":!function(t,e){var r=t[O],n=r._listeners.click,i=n&&k.lookup(r._labels,e);i&&I(t,n,i)}(t,e)}},afterEvent:function(t){var e,r,i,a,o,l,s,u=t[O],f=u._actives,c=u._actives=t._active||[],h=n.arrayDiff(f,c);for(e=0,r=h.length;e<r;++e)if((o=h[e])[1])for(i=0,a=(s=o[0].element[O]||[]).length;i",
		"<a;++i)(l=s[i]).$context.active=1===o[1],l.update(l.$context);(u._dirty||h.length)&&k.update(u._labels),delete u._dirty}};return t.register(z),t.defaults.plugins.datalabels=C,z}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-datalabels.min.js</code> content.
	 */
	DataLabelsPluginResource() {
		super(ResourceName.DATALABELS_PLUGIN, CONTENT);
	}

}
