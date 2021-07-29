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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-labels.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-labels.min.js
	private static final String[] CONTENT = {
		"/*",
		" Chen, Yi-Cyuan 2017-2018",
		" @license MIT",
		"*/",
		"(function(){function $Label$$(){this.renderToDataset=this.renderToDataset.bind(this)}if(\"undefined\"===typeof Chart)console.error(\"Can not find Chart object.\");else{var $SUPPORTED_TYPES$$={};[\"pie\",\"doughnut\",\"polarArea\",\"bar\"].forEach(function($t$$){$SUPPORTED_TYPES$$[$t$$]=!0});$Label$$.prototype.setup=function($chart$$,$options$$){this.chart=$chart$$;this.ctx=$chart$$.ctx;this.args={};this.barTotal={};this.font={};this.contexts=new Map;this.options=Chart.helpers.mergeIf($options$$,{position:\"default\",",
		"precision:0,color:Chart.defaults.color,font:{family:$chart$$.options.font.family,size:$chart$$.options.font.size,style:$chart$$.options.font.style,weight:$chart$$.options.font.weight,lineHeight:$chart$$.options.font.lineHeight},shadowOffsetX:3,shadowOffsetY:3,shadowColor:\"rgba(0,0,0,0.3)\",shadowBlur:6,images:[],outsidePadding:2,textMargin:2,overlap:!0});\"bar\"===$chart$$.config.type&&(this.options.position=\"default\",this.options.arc=!1,this.options.overlap=!0)};$Label$$.prototype.render=function(){this.labelBounds=",
		"[];this.chart.data.datasets.forEach(this.renderToDataset)};$Label$$.prototype.renderToDataset=function($dataset$$,$index$$){this.totalPercentage=0;this.total=null;var $arg$$=this.args[$index$$];$arg$$.meta.data.forEach(function($element$$,$forEachIndex$$){this.renderToElement($dataset$$,$arg$$,$element$$,$forEachIndex$$)}.bind(this))};$Label$$.prototype.getContext=function($context$jscomp$2_dataset$$,$element$$,$index$$){var $datasetIndex$$=this.chart.data.datasets.indexOf($context$jscomp$2_dataset$$),",
		"$key$$=1E3*$datasetIndex$$+$index$$;this.contexts.has($key$$)||($context$jscomp$2_dataset$$={type:\"labels\",label:this.chart.config.data.labels[$index$$],value:$context$jscomp$2_dataset$$.data[$index$$],percentage:this.getPercentage($context$jscomp$2_dataset$$,$element$$,$index$$),dataIndex:$index$$,datasetIndex:$datasetIndex$$,chart:this.chart},this.contexts.set($key$$,$context$jscomp$2_dataset$$));return this.contexts.get($key$$)};$Label$$.prototype.renderToElement=function($dataset$$,$arg$jscomp$9_label$$,",
		"$element$$,$index$$){if(this.shouldRenderToElement($arg$jscomp$9_label$$.meta,$element$$)&&(this.percentage=null,$arg$jscomp$9_label$$=this.getLabel($dataset$$,$element$$,$index$$))){var $ctx$$=this.ctx;$ctx$$.save();this.font=\"function\"===typeof this.options.font?this.getFont($dataset$$,$element$$,$index$$):this.options.font;$ctx$$.font=Chart.helpers.toFontString(this.font);var $renderInfo$$=this.getRenderInfo($element$$,$arg$jscomp$9_label$$);this.drawable($element$$,$arg$jscomp$9_label$$,$renderInfo$$)&&",
		"($ctx$$.beginPath(),this.color=\"function\"===typeof this.options.color?this.getFontColor($dataset$$,$element$$,$index$$):this.options.color,$ctx$$.fillStyle=this.color,this.renderLabel($arg$jscomp$9_label$$,$renderInfo$$));$ctx$$.restore()}};$Label$$.prototype.renderLabel=function($label$$,$renderInfo$$){return this.options.arc?this.renderArcLabel($label$$,$renderInfo$$):this.renderBaseLabel($label$$,$renderInfo$$)};$Label$$.prototype.renderBaseLabel=function($label$$,$position$$){var $ctx$$=this.ctx;",
		"if(\"object\"===typeof $label$$)$ctx$$.drawImage($label$$,$position$$.x-$label$$.width/2,$position$$.y-$label$$.height/2,$label$$.width,$label$$.height);else{$ctx$$.save();$ctx$$.textBaseline=\"top\";$ctx$$.textAlign=\"center\";this.options.textShadow&&($ctx$$.shadowOffsetX=this.options.shadowOffsetX,$ctx$$.shadowOffsetY=this.options.shadowOffsetY,$ctx$$.shadowColor=this.options.shadowColor,$ctx$$.shadowBlur=this.options.shadowBlur);$label$$=$label$$.split(\"\\n\");for(var $i$$=0;$i$$<$label$$.length;$i$$++)$ctx$$.fillText($label$$[$i$$],",
		"$position$$.x,$position$$.y-this.font.size/2*$label$$.length+this.font.size*$i$$);$ctx$$.restore()}};$Label$$.prototype.renderArcLabel=function($label$jscomp$12_lines$$,$max_renderInfo$$){var $ctx$$=this.ctx,$radius$$=$max_renderInfo$$.radius,$view$$=$max_renderInfo$$.view;$ctx$$.save();$ctx$$.translate($view$$.x,$view$$.y);if(\"string\"===typeof $label$jscomp$12_lines$$){$ctx$$.rotate($max_renderInfo$$.startAngle);$ctx$$.textBaseline=\"middle\";$ctx$$.textAlign=\"left\";$label$jscomp$12_lines$$=$label$jscomp$12_lines$$.split(\"\\n\");",
		"$max_renderInfo$$=0;$view$$=[];var $offset$$=0;\"border\"===this.options.position&&($offset$$=($label$jscomp$12_lines$$.length-1)*this.font.size/2);for(var $j_k$$=0;$j_k$$<$label$jscomp$12_lines$$.length;++$j_k$$){var $metrics$$=$ctx$$.measureText($label$jscomp$12_lines$$[$j_k$$]);$metrics$$.width>$max_renderInfo$$&&($max_renderInfo$$=$metrics$$.width);$view$$.push($metrics$$.width)}for($j_k$$=0;$j_k$$<$label$jscomp$12_lines$$.length;++$j_k$$){var $line$$=$label$jscomp$12_lines$$[$j_k$$],$y$$=($label$jscomp$12_lines$$.length-",
		"1-$j_k$$)*-this.font.size+$offset$$;$ctx$$.save();$ctx$$.rotate(($max_renderInfo$$-$view$$[$j_k$$])/2/$radius$$);for(var $i$$=0;$i$$<$line$$.length;$i$$++){var $char$$=$line$$.charAt($i$$);$metrics$$=$ctx$$.measureText($char$$);$ctx$$.save();$ctx$$.translate(0,-1*$radius$$);$ctx$$.fillText($char$$,0,$y$$);$ctx$$.restore();$ctx$$.rotate($metrics$$.width/$radius$$)}$ctx$$.restore()}}else $ctx$$.rotate(($view$$.startAngle+Math.PI/2+$max_renderInfo$$.endAngle)/2),$ctx$$.translate(0,-1*$radius$$),this.renderLabel($label$jscomp$12_lines$$,",
		"{x:0,y:0});$ctx$$.restore()};$Label$$.prototype.shouldRenderToElement=function($meta$$,$element$$){return!$meta$$.hidden&&!$element$$.hidden&&(this.options.showZero||\"polarArea\"===this.chart.config.type?0!==$element$$.outerRadius:0!==$element$$.circumference)};$Label$$.prototype.getLabel=function($dataset$jscomp$3_label$$,$element$$,$index$$){if(\"function\"===typeof this.options.render)$dataset$jscomp$3_label$$=this.options.render(this.getContext($dataset$jscomp$3_label$$,$element$$,$index$$));else switch(this.options.render){case \"value\":$dataset$jscomp$3_label$$=",
		"$dataset$jscomp$3_label$$.data[$index$$];break;case \"label\":$dataset$jscomp$3_label$$=this.chart.config.data.labels[$index$$];break;case \"image\":$dataset$jscomp$3_label$$=this.options.images[$index$$]?this.loadImage(this.options.images[$index$$]):\"\";break;default:$dataset$jscomp$3_label$$=this.getPercentage($dataset$jscomp$3_label$$,$element$$,$index$$)+\"%\"}\"object\"===typeof $dataset$jscomp$3_label$$?$dataset$jscomp$3_label$$=this.loadImage($dataset$jscomp$3_label$$):$dataset$jscomp$3_label$$&&($dataset$jscomp$3_label$$=",
		"$dataset$jscomp$3_label$$.toString());return $dataset$jscomp$3_label$$};$Label$$.prototype.getFont=function($dataset$jscomp$4_result$$,$element$$,$index$$){$dataset$jscomp$4_result$$=this.options.font(this.getContext($dataset$jscomp$4_result$$,$element$$,$index$$));return Chart.helpers.isObject($dataset$jscomp$4_result$$)?{family:\"undefined\"===typeof $dataset$jscomp$4_result$$.family?this.chart.options.font.family:$dataset$jscomp$4_result$$.family,size:\"undefined\"===typeof $dataset$jscomp$4_result$$.size?",
		"this.chart.options.font.size:$dataset$jscomp$4_result$$.size,style:\"undefined\"===typeof $dataset$jscomp$4_result$$.style?this.chart.options.font.style:$dataset$jscomp$4_result$$.style,weight:\"undefined\"===typeof $dataset$jscomp$4_result$$.weight?this.chart.options.font.weight:$dataset$jscomp$4_result$$.weight,lineHeight:\"undefined\"===typeof $dataset$jscomp$4_result$$.lineHeight?this.chart.options.font.lineHeight:$dataset$jscomp$4_result$$.lineHeight}:this.chart.options.font};$Label$$.prototype.getFontColor=",
		"function($dataset$jscomp$5_result$$,$element$$,$index$$){return($dataset$jscomp$5_result$$=this.options.color(this.getContext($dataset$jscomp$5_result$$,$element$$,$index$$)))?$dataset$jscomp$5_result$$:Chart.defaults.color};$Label$$.prototype.getPercentage=function($dataset$$,$element$jscomp$15_i$jscomp$5_k$$,$index$$){if(null!==this.percentage)return this.percentage;if(\"polarArea\"===this.chart.config.type){if(null===this.total)for($element$jscomp$15_i$jscomp$5_k$$=this.total=0;$element$jscomp$15_i$jscomp$5_k$$<",
		"$dataset$$.data.length;++$element$jscomp$15_i$jscomp$5_k$$)this.total+=$dataset$$.data[$element$jscomp$15_i$jscomp$5_k$$];$dataset$$=$dataset$$.data[$index$$]/this.total*100}else if(\"bar\"===this.chart.config.type){if(void 0===this.barTotal[$index$$])for($element$jscomp$15_i$jscomp$5_k$$=this.barTotal[$index$$]=0;$element$jscomp$15_i$jscomp$5_k$$<this.chart.data.datasets.length;++$element$jscomp$15_i$jscomp$5_k$$)this.barTotal[$index$$]+=this.chart.data.datasets[$element$jscomp$15_i$jscomp$5_k$$].data[$index$$];",
		"$dataset$$=$dataset$$.data[$index$$]/this.barTotal[$index$$]*100}else $dataset$$=180*$element$jscomp$15_i$jscomp$5_k$$.circumference/Math.PI/this.chart.options.circumference*100;$dataset$$=parseFloat($dataset$$.toFixed(this.options.precision));this.options.showActualPercentages||(\"bar\"===this.chart.config.type&&(this.totalPercentage=this.barTotalPercentage[$index$$]||0),this.totalPercentage+=$dataset$$,100<this.totalPercentage&&($dataset$$-=this.totalPercentage-100,$dataset$$=parseFloat($dataset$$.toFixed(this.options.precision))),",
		"\"bar\"===this.chart.config.type&&(this.barTotalPercentage[$index$$]=this.totalPercentage));return this.percentage=$dataset$$};$Label$$.prototype.getRenderInfo=function($element$$,$label$$){return\"bar\"===this.chart.config.type?this.getBarRenderInfo($element$$,$label$$):this.options.arc?this.getArcRenderInfo($element$$,$label$$):this.getBaseRenderInfo($element$$,$label$$)};$Label$$.prototype.getBaseRenderInfo=function($element$$,$label$jscomp$15_offset$$){if(\"outside\"===this.options.position||\"border\"===",
		"this.options.position){var $rangeFromCentre_renderInfo$$,$centreAngle$$=$element$$.startAngle+($element$$.endAngle-$element$$.startAngle)/2,$innerRadius$$=$element$$.outerRadius/2;\"border\"===this.options.position?$rangeFromCentre_renderInfo$$=($element$$.outerRadius-$innerRadius$$)/2+$innerRadius$$:\"outside\"===this.options.position&&($rangeFromCentre_renderInfo$$=$element$$.outerRadius-$innerRadius$$+$innerRadius$$+this.options.textMargin);$rangeFromCentre_renderInfo$$={x:$element$$.x+Math.cos($centreAngle$$)*",
		"$rangeFromCentre_renderInfo$$,y:$element$$.y+Math.sin($centreAngle$$)*$rangeFromCentre_renderInfo$$};\"outside\"===this.options.position&&($label$jscomp$15_offset$$=this.options.textMargin+this.measureLabel($label$jscomp$15_offset$$).width/2,$rangeFromCentre_renderInfo$$.x+=$rangeFromCentre_renderInfo$$.x<$element$$.x?-$label$jscomp$15_offset$$:$label$jscomp$15_offset$$);return $rangeFromCentre_renderInfo$$}return $element$$.tooltipPosition()};$Label$$.prototype.getArcRenderInfo=function($element$$,",
		"$label$jscomp$16_metrics$$){var $radius$$=\"outside\"===this.options.position?$element$$.outerRadius+this.font.size+this.options.textMargin:\"border\"===this.options.position?($element$$.outerRadius/2+$element$$.outerRadius)/2:($element$$.innerRadius+$element$$.outerRadius)/2;var $startAngle$$=$element$$.startAngle,$endAngle$$=$element$$.endAngle,$totalAngle$$=$endAngle$$-$startAngle$$;$startAngle$$+=Math.PI/2;$endAngle$$+=Math.PI/2;$label$jscomp$16_metrics$$=this.measureLabel($label$jscomp$16_metrics$$);",
		"$startAngle$$+=($endAngle$$-($label$jscomp$16_metrics$$.width/$radius$$+$startAngle$$))/2;return{radius:$radius$$,startAngle:$startAngle$$,endAngle:$endAngle$$,totalAngle:$totalAngle$$,view:$element$$}};$Label$$.prototype.getBarRenderInfo=function($element$jscomp$19_renderInfo$$,$label$$){$element$jscomp$19_renderInfo$$=$element$jscomp$19_renderInfo$$.tooltipPosition();$element$jscomp$19_renderInfo$$.y-=this.measureLabel($label$$).height/2+this.options.textMargin;return $element$jscomp$19_renderInfo$$};",
		"$Label$$.prototype.drawable=function($element$$,$label$jscomp$18_left$$,$bottom$jscomp$1_renderInfo$$){if(this.options.overlap)return!0;if(this.options.arc)return $bottom$jscomp$1_renderInfo$$.endAngle-$bottom$jscomp$1_renderInfo$$.startAngle<=$bottom$jscomp$1_renderInfo$$.totalAngle;var $metrics$$=this.measureLabel($label$jscomp$18_left$$);$label$jscomp$18_left$$=$bottom$jscomp$1_renderInfo$$.x-$metrics$$.width/2;var $right$$=$bottom$jscomp$1_renderInfo$$.x+$metrics$$.width/2,$top$$=$bottom$jscomp$1_renderInfo$$.y-",
		"$metrics$$.height/2;$bottom$jscomp$1_renderInfo$$=$bottom$jscomp$1_renderInfo$$.y+$metrics$$.height/2;return\"outside\"===this.options.position?this.outsideInRange($label$jscomp$18_left$$,$right$$,$top$$,$bottom$jscomp$1_renderInfo$$):$element$$.inRange($label$jscomp$18_left$$,$top$$)&&$element$$.inRange($label$jscomp$18_left$$,$bottom$jscomp$1_renderInfo$$)&&$element$$.inRange($right$$,$top$$)&&$element$$.inRange($right$$,$bottom$jscomp$1_renderInfo$$)};$Label$$.prototype.outsideInRange=function($left$$,",
		"$right$$,$top$$,$bottom$$){for(var $labelBounds$$=this.labelBounds,$i$$=0;$i$$<$labelBounds$$.length;++$i$$){for(var $bound$jscomp$2_k$$=$labelBounds$$[$i$$],$potins$$=[[$left$$,$top$$],[$left$$,$bottom$$],[$right$$,$top$$],[$right$$,$bottom$$]],$j$jscomp$1_x1$$=0;$j$jscomp$1_x1$$<$potins$$.length;++$j$jscomp$1_x1$$){var $x$jscomp$88_y1$$=$potins$$[$j$jscomp$1_x1$$][0],$y$$=$potins$$[$j$jscomp$1_x1$$][1];if($x$jscomp$88_y1$$>=$bound$jscomp$2_k$$.left&&$x$jscomp$88_y1$$<=$bound$jscomp$2_k$$.right&&",
		"$y$$>=$bound$jscomp$2_k$$.top&&$y$$<=$bound$jscomp$2_k$$.bottom)return!1}$potins$$=[[$bound$jscomp$2_k$$.left,$bound$jscomp$2_k$$.top],[$bound$jscomp$2_k$$.left,$bound$jscomp$2_k$$.bottom],[$bound$jscomp$2_k$$.right,$bound$jscomp$2_k$$.top],[$bound$jscomp$2_k$$.right,$bound$jscomp$2_k$$.bottom]];for($bound$jscomp$2_k$$=0;$bound$jscomp$2_k$$<$potins$$.length;++$bound$jscomp$2_k$$)if($j$jscomp$1_x1$$=$potins$$[$bound$jscomp$2_k$$][0],$x$jscomp$88_y1$$=$potins$$[$bound$jscomp$2_k$$][1],$j$jscomp$1_x1$$>=",
		"$left$$&&$j$jscomp$1_x1$$<=$right$$&&$x$jscomp$88_y1$$>=$top$$&&$x$jscomp$88_y1$$<=$bottom$$)return!1}$labelBounds$$.push({left:$left$$,right:$right$$,top:$top$$,bottom:$bottom$$});return!0};$Label$$.prototype.measureLabel=function($label$jscomp$19_lines$$){if(\"object\"===typeof $label$jscomp$19_lines$$)return{width:$label$jscomp$19_lines$$.width,height:$label$jscomp$19_lines$$.height};var $width$$=0;$label$jscomp$19_lines$$=$label$jscomp$19_lines$$.split(\"\\n\");for(var $i$$=0;$i$$<$label$jscomp$19_lines$$.length;++$i$$){var $result$$=",
		"this.ctx.measureText($label$jscomp$19_lines$$[$i$$]);$result$$.width>$width$$&&($width$$=$result$$.width)}return{width:$width$$,height:this.font.size*$label$jscomp$19_lines$$.length}};$Label$$.prototype.loadImage=function($obj$$){var $image$$=new Image;$image$$.src=$obj$$.src;$image$$.width=$obj$$.width;$image$$.height=$obj$$.height;return $image$$};Chart.register({id:\"labels\",beforeDatasetsUpdate:function($chart$$,$args_count$$,$optionsParam$$){if($SUPPORTED_TYPES$$[$chart$$.config.type]&&(\"bar\"!==",
		"$chart$$.config.type&&\"line\"!==$chart$$.config.type||\"y\"!==$chart$$.options.indexAxis)){var $options$$=[];Object.keys($optionsParam$$).forEach(function($key$jscomp$38_value$$){$key$jscomp$38_value$$=$optionsParam$$[$key$jscomp$38_value$$];\"object\"===typeof $key$jscomp$38_value$$&&$options$$.push($key$jscomp$38_value$$)});$args_count$$=$options$$.length;$chart$$._labels&&$args_count$$===$chart$$._labels.length||($chart$$._labels=$options$$.map(function(){return new $Label$$}));for(var $someOutside$$=",
		"!1,$maxPadding$$=0,$i$$=0;$i$$<$args_count$$;++$i$$){var $label$jscomp$20_padding$$=$chart$$._labels[$i$$];$label$jscomp$20_padding$$.setup($chart$$,$options$$[$i$$]);\"outside\"===$label$jscomp$20_padding$$.options.position&&($someOutside$$=!0,$label$jscomp$20_padding$$=(\"object\"===typeof $label$jscomp$20_padding$$.options.font?1.5*$label$jscomp$20_padding$$.options.font.size:$chart$$.options.font.size)+$label$jscomp$20_padding$$.options.outsidePadding,$label$jscomp$20_padding$$>$maxPadding$$&&($maxPadding$$=",
		"$label$jscomp$20_padding$$))}$someOutside$$&&($chart$$.chartArea.top+=$maxPadding$$,$chart$$.chartArea.bottom-=$maxPadding$$)}},afterDatasetUpdate:function($chart$$,$args$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&(\"bar\"!==$chart$$.config.type&&\"line\"!==$chart$$.config.type||\"y\"!==$chart$$.options.indexAxis)&&$chart$$._labels.forEach(function($label$$){$label$$.args[$args$$.index]=$args$$})},beforeDraw:function($chart$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&(\"bar\"!==$chart$$.config.type&&\"line\"!==",
		"$chart$$.config.type||\"y\"!==$chart$$.options.indexAxis)&&$chart$$._labels.forEach(function($label$$){$label$$.barTotalPercentage={}})},afterDatasetsDraw:function($chart$$){$SUPPORTED_TYPES$$[$chart$$.config.type]&&(\"bar\"!==$chart$$.config.type&&\"line\"!==$chart$$.config.type||\"y\"!==$chart$$.options.indexAxis)&&$chart$$._labels.forEach(function($label$$){$label$$.render()})}})}})();"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-labels.min.js</code> content.
	 */
	LabelsPluginResource() {
		super(ResourceName.LABELS_PLUGIN, CONTENT);
	}

}
