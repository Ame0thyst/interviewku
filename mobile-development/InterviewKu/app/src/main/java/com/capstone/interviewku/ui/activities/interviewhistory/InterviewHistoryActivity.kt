package com.capstone.interviewku.ui.activities.interviewhistory

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.interviewku.R
import com.capstone.interviewku.databinding.ActivityInterviewHistoryBinding
import com.capstone.interviewku.ui.activities.interviewresult.InterviewResultActivity
import com.capstone.interviewku.ui.adapters.ItemInterviewHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterviewHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInterviewHistoryBinding

    private val viewModel by viewModels<InterviewHistoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInterviewHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.interview_history)
        }

        val interviewHistoryAdapter = ItemInterviewHistoryAdapter {
            startActivity(
                Intent(this, InterviewResultActivity::class.java).apply {
                    putExtra(InterviewResultActivity.EXTRA_INTERVIEW_ID_KEY, it.interviewId)
                }
            )
        }.also { adapter ->
            adapter.addLoadStateListener { combinedLoadStates ->
                binding.progressBar.isVisible =
                    combinedLoadStates.refresh == LoadState.Loading
                            || combinedLoadStates.append == LoadState.Loading

                binding.tvNoItem.isVisible =
                    combinedLoadStates.refresh != LoadState.Loading
                            && adapter.itemCount == 0
            }
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@InterviewHistoryActivity)
            adapter = interviewHistoryAdapter
        }

        viewModel.interviewResults.observe(this) {
            interviewHistoryAdapter.submitData(lifecycle, it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}