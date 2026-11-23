package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val padding = resources.getDimensionPixelSize(R.dimen.common_spacing)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + padding, systemBars.top + padding, systemBars.right + padding, systemBars.bottom + padding)
            insets
        }

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = countDelim(post.likes)
                repostsCount.text = countDelim(post.reposts)
                viewCount.text = countDelim(post.views)

                if (post.likedByMe) {
                    likeIcon.setImageResource(R.drawable.liked)
                } else {
                    likeIcon.setImageResource(R.drawable.likes)
                }
            }
        }

        binding.likeIcon.setOnClickListener {
            viewModel.like()
        }

        binding.repostsIcon.setOnClickListener {
            viewModel.repost()
        }
    }

    fun countDelim(count: Int): String {
        val countStr = count.toString()
        when (count) {
            in 0..999 -> return "$count"
            in 1000..1099 -> return "${countStr.dropLast(3)}K"
            in 1100..9999 -> return "${countStr[0]}.${countStr[1]}K"
            in 10000..999999 -> return "${countStr.dropLast(3)}K"
            else -> return "${countStr.dropLast(6)}.${countStr[countStr.length-5]}M"
        }
    }
}