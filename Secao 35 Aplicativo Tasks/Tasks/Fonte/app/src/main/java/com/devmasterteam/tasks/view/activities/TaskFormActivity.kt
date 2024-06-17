package com.devmasterteam.tasks.view.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.databinding.ActivityTaskFormBinding
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.entities.TaskModel
import com.devmasterteam.tasks.viewmodel.PriorityViewModel
import com.devmasterteam.tasks.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, OnDateSetListener {

    private lateinit var viewModel: TaskFormViewModel
    private lateinit var binding: ActivityTaskFormBinding

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var priorityList: List<PriorityModel> = listOf()
    private var taskId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)

        binding.buttonSave.setOnClickListener { handleSave() }
        binding.buttonDate.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            DatePickerDialog(this, this, year, month, day).show()
        }
        loadPriorities()
        observe()
        loadTaskData()
        setContentView(binding.root)
    }

    override fun onClick(v: View) {

    }

    override fun onDateSet(sender: DatePicker?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        binding.buttonDate.text = dateFormat.format(calendar.time)
    }

    private fun loadTaskData() {
        val bundle = intent.extras
        if (bundle != null) {
            taskId = bundle.getInt(TaskConstants.BUNDLE.TASKID)
            viewModel.findById(taskId)
        }
    }

    private fun handleSave() {
        val task = TaskModel().apply {
            this.id = taskId
            this.description = binding.editDescription.text.toString()
            this.complete = binding.checkComplete.isChecked
            this.dueDate = binding.buttonDate.text.toString()
            val index = binding.spinnerPriority.selectedItemPosition
            this.priorityId = priorityList[index].id
        }
        viewModel.save(task)
    }

    private fun loadPriorities() {
        val viewModel = ViewModelProvider(this).get(PriorityViewModel::class.java)
        viewModel.findAll()
        viewModel.priorityList.observe(this) { x ->
            val list = x.map { it.description }
            priorityList = x
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
            binding.spinnerPriority.adapter = adapter;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun observe() {
        viewModel.responseModel.observe(this) {
            if (it.status()) {
                Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.task.observe(this) {
            if (it != null) {
                binding.editDescription.setText(it.description)
                binding.checkComplete.isChecked = it.complete
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.dueDate)
                binding.buttonDate.text = SimpleDateFormat("dd/MM/yyyy").format(date)

                for (index in priorityList.indices) {
                    if (priorityList[index].id == it.priorityId) {
                        binding.spinnerPriority.setSelection(index)
                    }
                }
            }
        }
    }
}