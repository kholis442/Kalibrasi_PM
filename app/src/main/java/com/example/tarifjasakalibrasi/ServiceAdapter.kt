package com.example.tarifjasakalibrasi


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

class ServiceAdapter(
    private val context: Context,
    private val serviceList: List<ServiceModel>
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int = serviceList.size

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivService: ImageView = itemView.findViewById(R.id.iv_service)
        private val tvServiceName: TextView = itemView.findViewById(R.id.tv_service_name)
        private val tvServicePrice: TextView = itemView.findViewById(R.id.tv_service_price)
        private val tvServiceDescription: TextView = itemView.findViewById(R.id.tv_service_description)
        private val btnOrder: Button = itemView.findViewById(R.id.btn_order)

        fun bind(service: ServiceModel) {
            ivService.setImageResource(service.imageResource)
            tvServiceName.text = service.name
            tvServicePrice.text = formatPrice(service.price)
            tvServiceDescription.text = service.description

            btnOrder.setOnClickListener {
                openWhatsApp(service)
            }
        }

        private fun formatPrice(price: Long): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            return formatter.format(price).replace("IDR", "Rp")
        }

        private fun openWhatsApp(service: ServiceModel) {
            val phoneNumber ="6282233303285" // Ganti dengan nomor WhatsApp yang benar
            val message = """
                Halo, saya ingin menggunakan jasa kalibrasi:
                
                Layanan: ${service.name}
                Harga: ${formatPrice(service.price)}
                
                Mohon informasi lebih lanjut. Terima kasih.
            """.trimIndent()

            val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"

            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
            } catch (e: Exception) {
                // Fallback jika WhatsApp tidak terinstall
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://web.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}")
                context.startActivity(intent)
            }
        }
    }
}