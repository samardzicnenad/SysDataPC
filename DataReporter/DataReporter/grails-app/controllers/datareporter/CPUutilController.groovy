/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/15/2013
 * Description: The class represents controller for CPU utilization data
 * 				The class was originally created automatically
 * 				Later, all of the functionalities that provided any kind of control except read-only
 * 				actions were canceled
 * 				After that, methods 
 * 					- for creating dataset from the DB
 * 					- for creating a chart
 * 					- for taking a snapshot and
 * 					- for putting all of this together were included in the class
 **********************************************************************/
package datareporter

import org.springframework.dao.DataIntegrityViolationException

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.DateAxis
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.encoders.SunPNGEncoderAdapter
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.StandardXYItemRenderer
import org.jfree.chart.renderer.xy.XYItemRenderer
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.general.DefaultValueDataset
import org.jfree.data.general.ValueDataset
import org.jfree.data.time.Minute
import org.jfree.data.time.TimeSeries
import org.jfree.data.time.TimeSeriesCollection
import org.jfree.data.xy.XYDataset
import org.jfree.ui.ApplicationFrame
import org.jfree.ui.RefineryUtilities

import java.awt.image.BufferedImage
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import java.text.SimpleDateFormat

import groovy.sql.Sql

class CPUutilController {

	static allowedMethods = []

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 50, 100)
		[CPUutilInstanceList: CPUutil.list(params), CPUutilInstanceTotal: CPUutil.count()]
	}

	def show(Long id) {
		def CPUutilInstance = CPUutil.get(id)
		if (!CPUutilInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'CPUutil.label', default: 'CPUutil'), id])
			redirect(action: "list")
			return
		}

		[CPUutilInstance: CPUutilInstance]
	}
	
	public static byte[] getChartAsImage(JFreeChart chart, int nWidth, int nHeight)
	{
		if(nWidth < 0 || nHeight < 0) //validate dimensions
			throw new IllegalArgumentException("Chart dimension < 0")
		// create PNG encoder and return byte array representation
		SunPNGEncoderAdapter encoder = new SunPNGEncoderAdapter()
		encoder.setEncodingAlpha(true)
		def cb = encoder.encode(chart.createBufferedImage(nWidth, nHeight, BufferedImage.BITMASK, null))
		return cb;
	}
	//creates chart with the provided dataset
	private JFreeChart createChart(final XYDataset dataset) {
		// create the chart...
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"CPU utilization chart", "Time", "Projected on a scale of 1", dataset, true, true, false)
		//customize it
		chart.setBackgroundPaint(Color.white)
		final XYPlot plot = chart.getXYPlot()
		plot.setBackgroundPaint(Color.lightGray)
		plot.setDomainGridlinePaint(Color.white)
		plot.setRangeGridlinePaint(Color.white)
		// customize the plot
		plot.setDomainCrosshairVisible(true)
		plot.setRangeCrosshairVisible(false)
		// customize the renderer...
		final XYItemRenderer renderer = plot.getRenderer()
		if (renderer instanceof StandardXYItemRenderer) {
			final StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer
			rr.setPlotLines(true)
			for (i in 0..8) renderer.setSeriesStroke(i, new BasicStroke(2.0f))
		}
		// customize date axis
		final DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("YYY-MM-dd hh:mm"));
		return chart;
	}
	//creates dataset (series) from the DB, accordingly to user-selected time interval
	private XYDataset createDataset(String sInterval) {
		int prevM = 0, prevH = 0, prevD = 0

		def sql = Sql.newInstance("jdbc:mysql://localhost:3306/datagenerator",
				"username", "password", "com.mysql.jdbc.Driver")
		def cData = sql.dataSet("cpudata")
		cData.sort { it.Time }

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		final TimeSeries s1 = new TimeSeries("User", Minute.class)
		final TimeSeries s2 = new TimeSeries("System", Minute.class)
		final TimeSeries s3 = new TimeSeries("Nice", Minute.class)
		final TimeSeries s4 = new TimeSeries("Idle", Minute.class)
		final TimeSeries s5 = new TimeSeries("Wait", Minute.class)
		final TimeSeries s6 = new TimeSeries("Irq", Minute.class)
		final TimeSeries s7 = new TimeSeries("SoftIrq", Minute.class)
		final TimeSeries s8 = new TimeSeries("Stolen", Minute.class)
		final TimeSeries s9 = new TimeSeries("Combined", Minute.class)
		cData.each{ cpudata -> //Graph takes only the first element of the interval
			if ((sInterval == "Minute" && //Dataset is sorted, so the one-way check is sufficient
			(prevM != cpudata.Time.minutes ||
			(prevM == cpudata.Time.minutes &&
			(prevH != cpudata.Time.hours || prevD != cpudata.Time.date)))) ||
			(sInterval == "Hour" &&
			(prevH != cpudata.Time.hours ||
			(prevH == cpudata.Time.hours && prevD != cpudata.Time.date))) ||
			(sInterval == "Day" && prevD != cpudata.Time.date)) {
				prevM = cpudata.Time.minutes
				prevH = cpudata.Time.hours
				prevD = cpudata.Time.date
				s1.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.User);
				s2.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.System);
				s3.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Nice);
				s4.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Idle);
				s5.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Wait);
				s6.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Irq);
				s7.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.SoftIrq);
				s8.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Stolen);
				s9.add(new Minute(cpudata.Time.minutes, cpudata.Time.hours,
						cpudata.Time.date, cpudata.Time.month + 1, cpudata.Time.year +1900), cpudata.Combined);
			}
		}
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		dataset.addSeries(s3);
		dataset.addSeries(s4);
		dataset.addSeries(s5);
		dataset.addSeries(s6);
		dataset.addSeries(s7);
		dataset.addSeries(s8);
		dataset.addSeries(s9);
		return dataset;
	}

	def drawChartImage(String sInterval) {
		def chart = createChart(createDataset(sInterval))
		return getChartAsImage(chart, 800, 600)
	}
	//initially set the graph to minutes
	def returnCPUData = {
		def sInterval = params.interval ? params.interval : "Minute"
		def imgChartImage = drawChartImage(sInterval)
		response.setContentType("image/png")
		response.setContentLength(imgChartImage.length)
		response.outputStream.write(imgChartImage)
	}
}
