package com.example.setting.ui.device_detail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentStatisticDeviceBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class StatisticDeviceFragment : BaseFragment<FragmentStatisticDeviceBinding, StatisticsViewModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation

    override val layoutId = R.layout.fragment_statistic_device

    private val viewModel: StatisticsViewModel by viewModels()
    override fun getVM() = viewModel
    lateinit var tfLight: Typeface

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initChart()
        setData(27, 90f)
    }


    private fun setData(count: Int, range: Float) {
        val values = ArrayList<Entry>()
        for (i in 0 until count) {
            val data = (Math.random() * (range + 1)).toFloat() + 20
            values.add(Entry(i.toFloat(), data))
        }
        val set1: LineDataSet
        if (binding.lineChart.getData() != null &&
            binding.lineChart.getData().getDataSetCount() > 0
        ) {
            set1 = binding.lineChart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            binding.lineChart.getData().notifyDataChanged()
            binding.lineChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
//            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.BLACK)
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.color = Color.BLUE
            set1.fillColor = Color.BLUE
            set1.fillAlpha = 100
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider ->
                    binding.lineChart.getAxisLeft().getAxisMinimum()
                }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTypeface(tfLight)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            binding.lineChart.setData(data)
        }
    }

    private fun initChart() {
        tfLight = Typeface.createFromAsset(resources.getAssets(), "OpenSans-Light.ttf");
//        lineChart.setViewPortOffsets(10f, 10f, 10f, 10f);
        binding.lineChart.setBackgroundColor(Color.WHITE)
        binding.lineChart.setGridBackgroundColor(Color.WHITE)
        // Description text: false
        binding.lineChart.getDescription().setEnabled(false)
        // enable touch gestures
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setDrawGridBackground(true)

//        // Set maker view
//        // create marker to display box when values are selected
//        MyMarkerView mv = new MyMarkerView(this, R.layout.view_custom_marker);
//
//        // Set the marker to the chart
//        mv.setChartView(lineChart);
//        lineChart.setMarker(mv);

        // enable scaling and dragging
        binding.lineChart.setDragEnabled(true)
        binding.lineChart.setScaleXEnabled(true)

        // force pinch zoom along both axis
        binding.lineChart.setPinchZoom(false)
        // X axis
        var x: XAxis
        run {
            x = binding.lineChart.getXAxis()
            x.disableGridDashedLine()
            x.gridColor = resources.getColor(R.color.black)
            x.position = XAxis.XAxisPosition.BOTTOM
//            x.valueFormatter = DateTimeXAxisValueFormatter(lineChart.getViewPortHandler())
            x.axisMinimum = 0f
            x.setLabelCount(7, true)
        }

        // Y axis
        var y: YAxis
        run {
            y = binding.lineChart.getAxisLeft()
            binding.lineChart.getAxisRight().setEnabled(false)
            y.gridColor = resources.getColor(R.color.black)
            y.disableGridDashedLine()
            //            y.enableGridDashedLine(10f, 10f, 0f);
            y.axisMaximum = 200f
            y.axisMinimum = 20f
            y.labelCount = 7
        }
        x.axisLineWidth = 3.0f

        // Set formatter for x
        binding.lineChart.animateXY(1500, 1500)
        val legend: Legend = binding.lineChart.getLegend()
        legend.isEnabled = false
        legend.form = Legend.LegendForm.LINE
    }
}