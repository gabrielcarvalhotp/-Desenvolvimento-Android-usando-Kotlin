package com.devmasterteam.tasks.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmasterteam.tasks.constants.TaskConstants
import com.devmasterteam.tasks.databinding.FragmentAllTasksBinding
import com.devmasterteam.tasks.listeners.TaskListener
import com.devmasterteam.tasks.view.activities.TaskFormActivity
import com.devmasterteam.tasks.view.adapter.TaskAdapter
import com.devmasterteam.tasks.viewmodel.TaskListViewModel

class AllTasksFragment : Fragment() {

    private lateinit var viewModel: TaskListViewModel
    private val adapter = TaskAdapter()
    private var _binding: FragmentAllTasksBinding? = null
    private val binding get() = _binding!!
    private var searchFilter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        _binding = FragmentAllTasksBinding.inflate(inflater, container, false)

        searchFilter = arguments?.getInt(TaskConstants.BUNDLE.TASKFILTER, 0) ?: 0

        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllTasks.adapter = adapter
        adapter.attachListener(object : TaskListener {
            override fun onListClick(id: Int) {
                val intent = Intent(context, TaskFormActivity::class.java)
                intent.putExtras(bundleOf(Pair(TaskConstants.BUNDLE.TASKID, id)));
                startActivity(intent)
            }

            override fun onDeleteClick(id: Int) {
                viewModel.delete(id)
            }

            override fun onCompleteClick(id: Int) {
                viewModel.complete(id)
            }

            override fun onUndoClick(id: Int) {
                viewModel.undo(id)
            }

        })

        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.find(searchFilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.taskList.observe(viewLifecycleOwner) {
            adapter.updateTasks(it)
        }
    }
}