package com.zohaib.starzplay.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zohaib.starzplay.databinding.ActivityMainBinding
import com.zohaib.starzplay.ui.home.adapter.CarouselItemAdapter
import com.zohaib.starzplayllib.data.api.RetrofitInstance
import com.zohaib.starzplayllib.data.model.CarouselItem
import com.zohaib.starzplayllib.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: Repository
    private lateinit var carouselItemAdapter: CarouselItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        initData()

    }

    private fun setupUi() {
        carouselItemAdapter = CarouselItemAdapter(emptyList())
        binding.carouselRecyclerView.adapter = carouselItemAdapter
        binding.buttonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                performMoviesSearch(query)
            }
        }
    }

    private fun initData() {
        repository = Repository(RetrofitInstance.apiService)
        performMoviesSearch("fun")
    }

    private fun performMoviesSearch(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.search(query)
            withContext(Dispatchers.Main) {
                updateCarouselItemAdapterData(data)
            }
        }
    }

    private fun updateCarouselItemAdapterData(items: List<CarouselItem>) {
        carouselItemAdapter.updateData(items)
    }
}