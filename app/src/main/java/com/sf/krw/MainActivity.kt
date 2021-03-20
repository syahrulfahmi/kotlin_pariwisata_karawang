package com.sf.krw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.GridLayoutManager
import com.sf.krw.adapter.CategoryAdapter
import com.sf.krw.adapter.PariwisataAdapter
import com.sf.krw.databinding.ActivityMainBinding
import com.sf.krw.utils.Extra
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var pariwisataAdapter: PariwisataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerview()
        setUpListener()
    }

    private fun setUpRecyclerview() {
        val d = ArrayList<DummyData>()
        var i = 0
        while (i < 4) {
            d.add(DummyData("Judul ${i + 1}", "https://picsum.photos/id/${i}/200/300"))
            i++
        }
        pariwisataAdapter = PariwisataAdapter(d)
        binding.recyclerView.adapter = pariwisataAdapter

        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewCategory.layoutManager = layoutManager
        binding.recyclerViewCategory.adapter = CategoryAdapter(d)
    }

    private fun setUpListener() {
        pariwisataAdapter.onItemClick = { item ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(Extra.DATA, item)
            }
            startActivity(intent)
        }
    }

    @Parcelize
    data class DummyData(
        val name: String,
        val imageUrl: String
    ) : Parcelable
}