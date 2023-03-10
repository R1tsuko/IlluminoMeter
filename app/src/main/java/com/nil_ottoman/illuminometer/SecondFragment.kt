package com.nil_ottoman.illuminometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nil_ottoman.illuminometer.databinding.FragmentSecondBinding

class SecondFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var sensorManager: SensorManager
    private var illuminanceSensor: Sensor? = null

    private var currentLux: String = "1"
    private val optionsViewModel: OptionsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonStart.setOnClickListener {
            it.isClickable = false
            sensorManager.registerListener(
                this, illuminanceSensor, SensorManager.SENSOR_DELAY_NORMAL
            )

            Handler(Looper.getMainLooper()).postDelayed({
                sensorManager.unregisterListener(this)
                binding.luxExp.text = currentLux
                it.isClickable = true
            },3000)
        }
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        illuminanceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        optionsViewModel.options.observe(viewLifecycleOwner) {
            var workSubcategory: String = "??"
            var resNorm: String = "?????? ??????????"
            val lightSystem = it[3]
            when (it[1]) {
                "backgroundDark" -> when (it[2]) {
                    "contrastLow" -> workSubcategory = "a"
                    "contrastMedium" -> workSubcategory = "??"
                    "contrastHigh" -> workSubcategory = "??"
                }
                "backgroundMedium" -> when (it[2]) {
                    "contrastLow" -> workSubcategory = "??"
                    "contrastMedium" -> workSubcategory = "??"
                    "contrastHigh" -> workSubcategory = "??"
                }
                "backgroundLight" -> when (it[2]) {
                    "contrastLow" -> workSubcategory = "??"
                    "contrastMedium" -> workSubcategory = "??"
                    "contrastHigh" -> workSubcategory = "??"
                }
            }
            when (it[0]) {
                "<0,15????" -> when (workSubcategory) {
                    "a" -> resNorm = if (lightSystem == "systemCommon") {
                        "?????? ??????????"
                    } else {
                        "5000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "1250"
                    } else {
                        "4000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "750"
                    } else {
                        "2500"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "500"
                    } else {
                        "1500"
                    }
                }
                "0,15<0,30????" -> when (workSubcategory) {
                    "a" -> resNorm = if (lightSystem == "systemCommon") {
                        "?????? ??????????"
                    } else {
                        "4000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "750"
                    } else {
                        "7000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "500"
                    } else {
                        "2000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "400"
                    } else {
                        "1000"
                    }
                }
                "0,30<0,50????" -> when (workSubcategory) {
                    "a" -> resNorm = if (lightSystem == "systemCommon") {
                        "500"
                    } else {
                        "2000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "400"
                    } else {
                        "1000"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "300"
                    } else {
                        "750"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "400"
                    }
                }
                "0,50<1,00????" -> when (workSubcategory) {
                    "a" -> resNorm = if (lightSystem == "systemCommon") {
                        "400"
                    } else {
                        "750"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "300"
                    } else {
                        "500"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "400"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "?????? ??????????"
                    }
                }
                "1,00<5,00????" -> when (workSubcategory) {
                    "a" -> resNorm = if (lightSystem == "systemCommon") {
                        "300"
                    } else {
                        "400"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "?????? ??????????"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "?????? ??????????"
                    }
                    "??" -> resNorm = if (lightSystem == "systemCommon") {
                        "200"
                    } else {
                        "?????? ??????????"
                    }
                }
                ">5,00????" -> resNorm = if (lightSystem == "systemCommon") {
                    "200"
                } else {
                    "?????? ??????????"
                }
            }
            binding.luxNorm.text = resNorm
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // ???????? ?????????????????? ???????????????? ??????????????
    }

    override fun onSensorChanged(event: SensorEvent) {
        val luxes = event.values[0]
        currentLux = luxes.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}