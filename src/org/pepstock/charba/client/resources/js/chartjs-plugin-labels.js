/**
 * Starting poit is [chartjs-plugin-labels]{@link https://github.com/emn178/chartjs-plugin-labels}
 *
 * @version 1.1.0
 * @author Chen, Yi-Cyuan [emn178@gmail.com]
 * @copyright Chen, Yi-Cyuan 2017-2018
 * @license MIT
 */
(function () {
  'use strict';

  if (typeof Chart === 'undefined') {
    console.error('Can not find Chart object.');
    return;
  }

  const SUPPORTED_TYPES = {};
  ['pie', 'doughnut', 'polarArea', 'bar'].forEach(function (t) {
    SUPPORTED_TYPES[t] = true;
  });
  
  function Label() {
    this.renderToDataset = this.renderToDataset.bind(this);
  }

  Label.prototype.setup = function (chart, options) {
    this.chart = chart;
    this.ctx = chart.ctx;
    this.args = {};
    this.barTotal = {};
    this.font = {};
    
    this.options = Chart.helpers.mergeIf(options, {
      position: 'default',
      precision: 0,
      color: Chart.defaults.color,
      font: Chart.helpers.clone(chart.options.font),
      shadowOffsetX: 3,
      shadowOffsetY: 3,
      shadowColor: 'rgba(0,0,0,0.3)',
      shadowBlur: 6,
      images: [],
      outsidePadding: 2,
      textMargin: 2,
      overlap: true
    });

    if (chart.config.type === 'bar') {
      this.options.position = 'default';
      this.options.arc = false;
      this.options.overlap = true;
    }
  };

  Label.prototype.render = function () {
    this.labelBounds = [];
    this.chart.data.datasets.forEach(this.renderToDataset);
  };

  Label.prototype.renderToDataset = function (dataset, index) {
    this.totalPercentage = 0;
    this.total = null;
    var arg = this.args[index];
    arg.meta.data.forEach(function (element, forEachIndex) {
      this.renderToElement(dataset, arg, element, forEachIndex);
    }.bind(this));
  };

  Label.prototype.renderToElement = function (dataset, arg, element, index) {
    if (!this.shouldRenderToElement(arg.meta, element)) {
      return;
    }
    this.percentage = null;
    var label = this.getLabel(dataset, element, index);
    if (!label) {
      return;
    }
    var ctx = this.ctx;
    ctx.save();

	this.font = typeof this.options.font === 'function' ? this.getFont(dataset, element, index) : this.options.font;
    ctx.font = Chart.helpers.toFontString(this.font);
    var renderInfo = this.getRenderInfo(element, label);
    if (!this.drawable(element, label, renderInfo)) {
      ctx.restore();
      return;
    }

    ctx.beginPath();
	this.color = typeof this.options.color === 'function' ? this.getFontColor(dataset, element, index) : this.options.color;
    ctx.fillStyle = this.color;
    this.renderLabel(label, renderInfo);
    ctx.restore();
  };

  Label.prototype.renderLabel = function (label, renderInfo) {
    return this.options.arc ? this.renderArcLabel(label, renderInfo) : this.renderBaseLabel(label, renderInfo);
  };

  Label.prototype.renderBaseLabel = function (label, position) {
    var ctx = this.ctx;
    if (typeof label === 'object') {
      ctx.drawImage(label, position.x - label.width / 2, position.y - label.height / 2, label.width, label.height);
    } else {
      ctx.save();
      ctx.textBaseline = 'top';
      ctx.textAlign = 'center';

      if (this.options.textShadow) {
        ctx.shadowOffsetX = this.options.shadowOffsetX;
        ctx.shadowOffsetY = this.options.shadowOffsetY;
        ctx.shadowColor = this.options.shadowColor;
        ctx.shadowBlur = this.options.shadowBlur;
      }

      var lines = label.split('\n');
      for (var i = 0; i < lines.length; i++) {
        var y = position.y - this.font.size / 2 * lines.length + this.font.size * i;
        ctx.fillText(lines[i], position.x, y);
      }
      ctx.restore();
    }
  };

  Label.prototype.renderArcLabel = function (label, renderInfo) {
    var ctx = this.ctx, radius = renderInfo.radius, view = renderInfo.view;
    ctx.save();
    ctx.translate(view.x, view.y);
    if (typeof label === 'string') {
      ctx.rotate(renderInfo.startAngle);
      ctx.textBaseline = 'middle';
      ctx.textAlign = 'left';
      var lines = label.split('\n'), max = 0, widths = [], offset = 0;
      if (this.options.position === 'border') {
        offset = (lines.length - 1) * this.font.size / 2;
      }
      for (var j = 0; j < lines.length; ++j) {
        var metrics = ctx.measureText(lines[j]);
        if (metrics.width > max) {
          max = metrics.width;
        }
        widths.push(metrics.width);
      }
      for (var k = 0; k < lines.length; ++k) {
        var line = lines[k];
        var y = (lines.length - 1 - k) * -this.font.size + offset;
        ctx.save();
        var padding = (max - widths[k]) / 2;
        ctx.rotate(padding / radius);
        for (var i = 0; i < line.length; i++) {
          var char = line.charAt(i);
          metrics = ctx.measureText(char);
          ctx.save();
          ctx.translate(0, -1 * radius);
          ctx.fillText(char, 0, y);
          ctx.restore();
          ctx.rotate(metrics.width / radius);
        }
        ctx.restore();
      }
    } else {
      ctx.rotate((view.startAngle + Math.PI / 2 + renderInfo.endAngle) / 2);
      ctx.translate(0, -1 * radius);
      this.renderLabel(label, { x: 0, y: 0 });
    }
    ctx.restore();
  };

  Label.prototype.shouldRenderToElement = function (meta, element) {
    return !meta.hidden && !element.hidden && (
      this.options.showZero ||
      this.chart.config.type === 'polarArea' ? element.outerRadius !== 0 : element.circumference !== 0
    );
  };

  Label.prototype.getLabel = function (dataset, element, index) {
    var label;
    if (typeof this.options.render === 'function') {
      label = this.options.render({
        type: 'labels',
        label: this.chart.config.data.labels[index],
        value: dataset.data[index],
        percentage: this.getPercentage(dataset, element, index),
        dataset: dataset,
        dataIndex: index,
        datasetIndex: this.chart.data.datasets.indexOf(dataset),
		chart: this.chart
      });
    } else {
      switch (this.options.render) {
        case 'value':
          label = dataset.data[index];
          break;
        case 'label':
          label = this.chart.config.data.labels[index];
          break;
        case 'image':
          label = this.options.images[index] ? this.loadImage(this.options.images[index]) : '';
          break;
        case 'percentage':
        default:
          label = this.getPercentage(dataset, element, index) + '%';
          break;
      }
    }
    if (typeof label === 'object') {
      label = this.loadImage(label);
    } else if (label) {
      label = label.toString();
    }
    return label;
  };

  Label.prototype.getFont = function (dataset, element, index) {
  	const result = this.options.font({
        type: 'labels',
        label: this.chart.config.data.labels[index],
        value: dataset.data[index],
        percentage: this.getPercentage(dataset, element, index),
        dataset: dataset,
        dataIndex: index,
        datasetIndex: this.chart.data.datasets.indexOf(dataset),
		chart: this.chart
      });
    return Chart.helpers.mergeIf(!result ? {} : result, this.chart.options.font);
  };
  
  Label.prototype.getFontColor = function (dataset, element, index) {
  	const result = this.options.color({
        type: 'labels',
        label: this.chart.config.data.labels[index],
        value: dataset.data[index],
        percentage: this.getPercentage(dataset, element, index),
        dataset: dataset,
        dataIndex: index,
        datasetIndex: this.chart.data.datasets.indexOf(dataset),
		chart: this.chart
      });
    return !result ? Chart.defaults.color : result;
  };

  Label.prototype.getPercentage = function (dataset, element, index) {
    if (this.percentage !== null) {
      return this.percentage;
    }
    var percentage;
    if (this.chart.config.type === 'polarArea') {
      if (this.total === null) {
        this.total = 0;
        for (var i = 0;i < dataset.data.length; ++i) {
          this.total += dataset.data[i];
        }
      }
      percentage = dataset.data[index] / this.total * 100;
    } else if (this.chart.config.type === 'bar') {
      if (this.barTotal[index] === undefined) {
        this.barTotal[index] = 0;
        for (var k = 0;k < this.chart.data.datasets.length; ++k) {
          this.barTotal[index] += this.chart.data.datasets[k].data[index];
        }
      }
      percentage = dataset.data[index] / this.barTotal[index] * 100;
    } else {
      var degree = element.circumference * 180 / Math.PI;	
      percentage = degree / this.chart.options.circumference * 100;
    }
    percentage = parseFloat(percentage.toFixed(this.options.precision));
    
    if (!this.options.showActualPercentages) {
      if (this.chart.config.type === 'bar') {
        this.totalPercentage = this.barTotalPercentage[index] || 0;
      }
      this.totalPercentage += percentage;
      if (this.totalPercentage > 100) {
        percentage -= this.totalPercentage - 100;
        percentage = parseFloat(percentage.toFixed(this.options.precision));
      }
      if (this.chart.config.type === 'bar') {
        this.barTotalPercentage[index] = this.totalPercentage
      }
    }
    this.percentage = percentage;
    return percentage;
  };

  Label.prototype.getRenderInfo = function (element, label) {
    if (this.chart.config.type === 'bar') {
      return this.getBarRenderInfo(element, label);
    } else {
      return this.options.arc ? this.getArcRenderInfo(element, label) : this.getBaseRenderInfo(element, label);
    }
  };

  Label.prototype.getBaseRenderInfo = function (element, label) {
    if (this.options.position === 'outside' || this.options.position === 'border') {
      var renderInfo, rangeFromCentre,
        centreAngle = element.startAngle + (element.endAngle - element.startAngle) / 2,
        innerRadius = element.outerRadius / 2;
      if (this.options.position === 'border') {
        rangeFromCentre = (element.outerRadius - innerRadius) / 2 + innerRadius;
      } else if (this.options.position === 'outside') {
        rangeFromCentre = (element.outerRadius - innerRadius) + innerRadius + this.options.textMargin;
      }
      renderInfo = {
        x: element.x + (Math.cos(centreAngle) * rangeFromCentre),
        y: element.y + (Math.sin(centreAngle) * rangeFromCentre)
      };
      if (this.options.position === 'outside') {
        var offset = this.options.textMargin + this.measureLabel(label).width / 2;
        renderInfo.x += renderInfo.x < element.x ? -offset : offset;
      }
      return renderInfo;
    } else {
      return element.tooltipPosition();
    }
  };

  Label.prototype.getArcRenderInfo = function (element, label) {
    var radius;
    if (this.options.position === 'outside') {
      radius = element.outerRadius + this.font.size + this.options.textMargin;
    } else if (this.options.position === 'border') {
      radius = (element.outerRadius / 2 + element.outerRadius) / 2;
    } else {
      radius = (element.innerRadius + element.outerRadius) / 2;
    }
    var startAngle = element.startAngle, endAngle = element.endAngle;
    var totalAngle = endAngle - startAngle;
    startAngle += Math.PI / 2;
    endAngle += Math.PI / 2;
    var metrics = this.measureLabel(label);
    startAngle += (endAngle - (metrics.width / radius + startAngle)) / 2;
    return {
      radius: radius,
      startAngle: startAngle,
      endAngle: endAngle,
      totalAngle: totalAngle,
      view: element
    }
  };

  Label.prototype.getBarRenderInfo = function (element, label) {
    var renderInfo = element.tooltipPosition();
    renderInfo.y -= this.measureLabel(label).height / 2 + this.options.textMargin;
    return renderInfo;
  };

  Label.prototype.drawable = function (element, label, renderInfo) {
    if (this.options.overlap) {
      return true;
    } else if (this.options.arc) {
      return renderInfo.endAngle - renderInfo.startAngle <= renderInfo.totalAngle;
    } else {
      var metrics = this.measureLabel(label),
        left = renderInfo.x - metrics.width / 2,
        right = renderInfo.x + metrics.width / 2,
        top = renderInfo.y - metrics.height / 2,
        bottom = renderInfo.y + metrics.height / 2;
      if (this.options.position === 'outside') {
        return this.outsideInRange(left, right, top, bottom);
      } else {
		return element.inRange(left, top) && element.inRange(left, bottom) &&
          element.inRange(right, top) && element.inRange(right, bottom);
      }
    }
  };

  Label.prototype.outsideInRange = function (left, right, top, bottom) {
    var labelBounds = this.labelBounds;
    for (var i = 0;i < labelBounds.length;++i) {
      var bound = labelBounds[i];
      var potins = [
        [left, top],
        [left, bottom],
        [right, top],
        [right, bottom]
      ];
      for (var j = 0;j < potins.length;++j) {
        var x = potins[j][0];
        var y = potins[j][1];
        if (x >= bound.left && x <= bound.right && y >= bound.top && y <= bound.bottom) {
          return false;
        }
      }
      potins = [
        [bound.left, bound.top],
        [bound.left, bound.bottom],
        [bound.right, bound.top],
        [bound.right, bound.bottom]
      ];
      for (var k = 0;k < potins.length;++k) {
        var x1 = potins[k][0];
        var y1 = potins[k][1];
        if (x1 >= left && x1 <= right && y1 >= top && y1 <= bottom) {
          return false;
        }
      }
    }
    labelBounds.push({
      left: left,
      right: right,
      top: top,
      bottom: bottom
    });
    return true;
  };

  Label.prototype.measureLabel = function (label) {
    if (typeof label === 'object') {
      return { width: label.width, height: label.height };
    } else {
      var width = 0;
      var lines = label.split('\n');
      for (var i = 0; i < lines.length; ++i) {
        var result = this.ctx.measureText(lines[i]);
        if (result.width > width) {
          width = result.width;
        }
      }
      return { width: width, height: this.font.size * lines.length };
    }
  };

  Label.prototype.loadImage = function (obj) {
    var image = new Image();
    image.src = obj.src;
    image.width = obj.width;
    image.height = obj.height;
    return image;
  };

  const plugin = {
    id: 'labels',
    beforeDatasetsUpdate: function (chart, args, optionsParam) {
      if (!SUPPORTED_TYPES[chart.config.type] || ((chart.config.type === 'bar' || chart.config.type === 'line') && chart.options.indexAxis === 'y')) {
        return;
      }
	  const options = new Array();
	  Object.keys(optionsParam).forEach(key => {
		const value = optionsParam[key];
		if (typeof value === 'object') {
			options.push(value);
		}
	  });
      var count = options.length;
      if (!chart._labels || count !== chart._labels.length) {
        chart._labels = options.map(function () {
          return new Label();
        });
      }
      var someOutside = false, maxPadding = 0;
      for (var i = 0; i < count; ++i) {
        var label = chart._labels[i];
        label.setup(chart, options[i]);
        if (label.options.position === 'outside') {
          someOutside = true;
          var padding = (typeof label.options.font === 'object' ? label.options.font.size * 1.5 : chart.options.font.size) + label.options.outsidePadding;
          if (padding > maxPadding) {
            maxPadding = padding;
          }
        }
      }
      if (someOutside) {
        chart.chartArea.top += maxPadding;
        chart.chartArea.bottom -= maxPadding;
      }
    },
    afterDatasetUpdate: function (chart, args) {
      if (!SUPPORTED_TYPES[chart.config.type] || ((chart.config.type === 'bar' || chart.config.type === 'line') && chart.options.indexAxis === 'y')) {
        return;
      }
      chart._labels.forEach(function (label) {
        label.args[args.index] = args;
      });
    },
    beforeDraw: function (chart) {
      if (!SUPPORTED_TYPES[chart.config.type] || ((chart.config.type === 'bar' || chart.config.type === 'line') && chart.options.indexAxis === 'y')) {
        return;
      }
      chart._labels.forEach(function (label) {
        label.barTotalPercentage = {};
      });
    },
    afterDatasetsDraw: function (chart) {
      if (!SUPPORTED_TYPES[chart.config.type] || ((chart.config.type === 'bar' || chart.config.type === 'line') && chart.options.indexAxis === 'y')) {
        return;
      }
      chart._labels.forEach(function (label) {
        label.render();
      });
    }
  };

  Chart.register(plugin);
})();
