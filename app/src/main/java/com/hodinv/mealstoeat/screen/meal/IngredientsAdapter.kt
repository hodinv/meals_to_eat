package com.hodinv.mealstoeat.screen.meal

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Ingredient

class IngredientsAdapter : RecyclerView.Adapter<IngredientsViewHolder>() {

    val list = ArrayList<Ingredient>()
    var text: String = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        (if (viewType == 0) R.layout.listitem_info else R.layout.listitem_ingredient)
                        , parent, false))

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        if (position == 0) {
            holder.setInfo(text)
        } else {
            holder.setIngredient(list[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    fun setInfo(strInstructions: String?, ingredients: List<Ingredient>) {
        text = strInstructions ?: ""
        list.clear()
        list.addAll(ingredients)
        notifyDataSetChanged()
    }

}