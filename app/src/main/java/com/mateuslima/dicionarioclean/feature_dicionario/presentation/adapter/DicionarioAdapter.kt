package com.mateuslima.dicionarioclean.feature_dicionario.presentation.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mateuslima.dicionarioclean.databinding.ContainerPalavraBinding
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo

class DicionarioAdapter : ListAdapter<PalavraInfo, DicionarioAdapter.MyViewHolder>(Companion) {

    companion object: DiffUtil.ItemCallback<PalavraInfo>(){
        override fun areItemsTheSame(oldItem: PalavraInfo, newItem: PalavraInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PalavraInfo, newItem: PalavraInfo): Boolean {
            return oldItem.palavra == newItem.palavra
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContainerPalavraBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val palavraInfo = getItem(position)
        holder.binding.apply { //
            textPalavra.text = palavraInfo.palavra ?: ""
            textFonetica.text = palavraInfo.fonetica ?: ""
            textOrigem.text = palavraInfo.origem ?: ""

            palavraInfo.significados.forEach { significado ->
                val textClasse = TextView(root.context)
                textClasse.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                textClasse.setTextColor(Color.BLACK)
                textClasse.setTypeface(null, Typeface.BOLD)
                textClasse.setTextSize(TypedValue.COMPLEX_UNIT_SP,18F)
                textClasse.text = significado.classeGramatical ?: ""
                layout.addView(textClasse)

                significado.listaDefinicao.forEach { definicao ->
                    val textDefinicao = TextView(root.context)
                    val textExemplo = TextView(root.context)
                    textDefinicao.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    textExemplo.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    textExemplo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                    textDefinicao.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)

                    textDefinicao.text = definicao.definicao ?: ""
                    textExemplo.text = definicao.exemplo ?: ""
                    layout.addView(textDefinicao)
                    layout.addView(textExemplo)
                }
            }
        }
    }

    inner class MyViewHolder(val binding: ContainerPalavraBinding)
        : RecyclerView.ViewHolder(binding.root)
}