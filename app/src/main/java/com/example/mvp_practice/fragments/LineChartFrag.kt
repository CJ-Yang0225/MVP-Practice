package com.example.mvp_practice.fragments


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvp_practice.main.BMI_value

import com.example.mvp_practice.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("UNREACHABLE_CODE")
class LineChartFrag : Fragment() {
    private var chart1: LineChart? = null
    private var lsBMI: MutableList<Entry>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment 有誤
//        return inflater.inflate(R.layout.fragment_line_chart, container, false)
        val view = inflater.inflate(R.layout.fragment_line_chart, container, false)

        chart1 = view.findViewById<LineChart>(R.id.lineChart)

        initChart(chart1!!)

        return view
    }



    // 初始化圖表屬性
    private fun initChart(chart: LineChart) {
        chart.onTouchListener = null
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)
        chart.setExtraOffsets(10f, 10f, 10f, 0f)
        chart.animateX(1000)

        //圖示
        val l = chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)

        chart.setDrawGridBackground(false)
        chart.description.isEnabled = false
        chart.description.text = "紅線: 體重過重(> 24) 藍線: 體重過輕(< 18.5)"
        chart.description.textSize = 12f
        chart.setDrawBorders(true)
        chart.setBorderColor(Color.parseColor("#b3b3b3"))  // 黑色

        // enable touch gestures
        chart.setTouchEnabled(true)

        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)

        val xAxis: XAxis
        run {
            // X-Axis Style
            xAxis = chart.xAxis
//            xAxis.axisMinimum = 0f

            xAxis.isEnabled = false

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f)
        }

        val yAxis: YAxis
        run {
            // Y-Axis Style
            yAxis = chart.axisLeft
            yAxis.axisMinimum = 10f
//            yAxis.axisMaximum = 36f
            yAxis.isEnabled = true
            yAxis.labelCount = 5

            var yLimitLine = LimitLine(18.5f,"過輕(< 18.5)")
            yLimitLine.textSize = 10f
            yLimitLine.lineWidth = .5f
            yLimitLine.lineColor = Color.BLUE
            yLimitLine.textColor = Color.BLUE

            var yLimitLine2 = LimitLine(24f,"過重(> 24)")
            yLimitLine2.textSize = 10f
            yLimitLine2.lineWidth = .5f
            yLimitLine2.lineColor = Color.RED
            yLimitLine2.textColor = Color.RED

            yAxis.addLimitLine(yLimitLine)
            yAxis.addLimitLine(yLimitLine2)

            // disable dual axis (only use LEFT axis)
            chart1?.axisRight!!.isEnabled = false

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f)
        }

        // 設定圖表X軸為 BMI可變集合MutableList 的長度，Y軸為 50
        setLineData(BMI_value.size, 40)

        Log.d("initChart", "val- $BMI_value, size- ${BMI_value.size}")
    }

    // 隨機產生顏色
    private fun randomColor(): Int {
        val random = Random()
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        return Color.rgb(r, g, b)
    }

    // 更改部分圖表屬性，將 BMI_value 資料繪製為圖表
    private fun setLineData(count: Int, range: Int) {
        val dataSets = ArrayList<ILineDataSet>()
        lsBMI = ArrayList()
        for (i in 0 until count) {
//            val value = Math.random() * range
            val value = BMI_value[i]
            lsBMI?.add(Entry(i.toFloat(), value))
        }
        val dataSet1 = LineDataSet(lsBMI, "BMI")
        dataSet1.lineWidth = 3f
        dataSet1.circleSize = 5f
        dataSet1.valueTextSize = 11f
        dataSet1.setDrawCircleHole(false)
        dataSet1.color = randomColor()
        dataSets.add(dataSet1)
        val d = LineData(dataSets)
        chart1?.data = d
        chart1?.invalidate()
    }
}
