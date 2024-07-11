package com.gabrielcarvalhotp.meumercado.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcarvalhotp.meumercado.data.models.EstablishmentsModel
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gabrielcarvalhotp.meumercado.databinding.EstablishmentsItemBinding

class HomeAdapter(private val onClick: (EstablishmentsModel) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: EstablishmentsItemBinding, val onClick: (EstablishmentsModel) -> Unit) : ViewHolder(binding.root) {

        private var currentEstablishments: EstablishmentsModel? = null
        init {
            binding.root.setOnClickListener { currentEstablishments?.let { onClick(it) } }
        }

        @SuppressLint("SetTextI18n")
        fun bind(establishment: EstablishmentsModel) {
            currentEstablishments = establishment
            binding.textEstablishmentName.text = establishment.name
            binding.textAddress.text = establishment.address
            binding.textDeliveryTax.text = "Taxa de entrega: ${String.format("%.2f", establishment.deliveryTax)}"
            binding.textMinimumPurchase.text = "Compra mínima: ${String.format("%.2f", establishment.minimumPurchase)}"
        }

    }

    private var establishments: List<EstablishmentsModel> = listOf(
        EstablishmentsModel(1, "Pão de açucar", "Av. Interlagos 521 - Jardim Brasil", "Taxa de entrega: R$ 5,00", 5.0, 10.0),
        EstablishmentsModel(1, "Pão de açucar", "Av. Interlagos 521 - Jardim Brasil", "Taxa de entrega: R$ 5,00", 5.0, 10.0),
        EstablishmentsModel(1, "Pão de açucar", "Av. Interlagos 521 - Jardim Brasil", "Taxa de entrega: R$ 5,00", 5.0, 10.0),
        EstablishmentsModel(1, "Pão de açucar", "Av. Interlagos 521 - Jardim Brasil", "Taxa de entrega: R$ 5,00", 5.0, 10.0),
        EstablishmentsModel(1, "Pão de açucar", "Av. Interlagos 521 - Jardim Brasil", "Taxa de entrega: R$ 5,00", 5.0, 10.0)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = EstablishmentsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(item, onClick)
    }

    override fun getItemCount(): Int {
        return establishments.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val establishment = establishments[position]
        holder.bind(establishment)
    }

    fun setEstablishments(establishments: List<EstablishmentsModel>) {
        this.establishments = establishments
        notifyDataSetChanged()
    }


}