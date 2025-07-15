package com.tops.androidfundamentals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.androidfundamentals.databinding.EmployeeRowItemBinding
import com.tops.androidfundamentals.model.EmployeeModel

class MyAdapter(private val emplist: List<EmployeeModel>, private val onDeletClick:(Int) -> Unit): RecyclerView.Adapter<MyAdapter.EmpViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpViewHolder {
       val binding = EmployeeRowItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return EmpViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EmpViewHolder,
        position: Int
    ) {
       val emp = emplist[position]
        holder.binding.tvName.text = emp.name
        holder.binding.tvrole.text = emp.role
        holder.binding.tvempID.text = emp.id.toString()

        holder.binding.btnDelete.setOnClickListener {
            onDeletClick(emp.id)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = emplist.size

    class EmpViewHolder (val binding: EmployeeRowItemBinding) : RecyclerView.ViewHolder(binding.root)
}