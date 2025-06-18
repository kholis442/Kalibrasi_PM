package com.example.tarifjasakalibrasi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ServiceListActivity : AppCompatActivity() {

    private lateinit var rvServices: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var btnLogout: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_list)

        initViews()
        setupRecyclerView()
        setupLogoutButton()
    }

    private fun initViews() {
        rvServices = findViewById(R.id.rv_services)
        btnLogout = findViewById(R.id.btn_logout)
    }



    private fun setupRecyclerView() {
        val serviceList = getServiceList()
        serviceAdapter = ServiceAdapter(this, serviceList)

        rvServices.apply {
            layoutManager = LinearLayoutManager(this@ServiceListActivity)
            adapter = serviceAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupLogoutButton() {
        btnLogout.setOnClickListener {
            // Clear any stored credentials or session data here if needed
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun getServiceList(): List<ServiceModel> {
        return listOf(
            ServiceModel(
                1,
                "Kalibrasi Tangki Tanam",
                2500000,
                R.drawable.kalibrasi_tangki_tanam,
                "Kalibrasi untuk tangki tanam dengan standar yang tepat"
            ),
            ServiceModel(
                2,
                "Kalibrasi Tangki BBM",
                2500000,
                R.drawable.kalibrasi_tangki_bbm,
                "Kalibrasi tangki bahan bakar minyak sesuai standar industri"
            ),
            ServiceModel(
                3,
                "Kalibrasi Timbangan Digital",
                350000,
                R.drawable.kalibrasi_timbangan_digital,
                "Kalibrasi timbangan digital untuk akurasi pengukuran"
            ),
            ServiceModel(
                4,
                "Kalibrasi Suhu Ruangan",
                300000,
                R.drawable.kalibrasi_suhu_ruangan,
                "Kalibrasi alat pengukur suhu ruangan"
            ),
            ServiceModel(
                5,
                "Kalibrasi Volume",
                300000,
                R.drawable.kalibrasi_volume,
                "Kalibrasi alat pengukur volume berbagai jenis"
            ),
            ServiceModel(
                6,
                "Kalibrasi Timbangan Jembatan",
                1500000,
                R.drawable.kalibrasi_timbangan_jembatan,
                "Kalibrasi timbangan jembatan untuk kendaraan berat"
            ),
            ServiceModel(
                7,
                "Kalibrasi Thermometer",
                300000,
                R.drawable.kalibrasi_thermometer,
                "Kalibrasi thermometer untuk pengukuran suhu akurat"
            ),
            ServiceModel(
                8,
                "Kalibrasi Timbangan Sembako",
                300000,
                R.drawable.kalibrasi_timbangan_sembako,
                "Kalibrasi timbangan untuk keperluan sembako"
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}