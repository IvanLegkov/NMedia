package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будушего",
            authorAvatar = R.drawable.post_avatar_drawable,
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likes = 9999,
            reposts = 991,
            views = 12637000
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = countDelim(post.likes)
            repostsCount.text = countDelim(post.reposts)
            viewCount.text = countDelim(post.views)

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.liked)
            }

            likeIcon.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    likeIcon.setImageResource(R.drawable.liked)
                    post.likes++
                } else {
                    likeIcon.setImageResource(R.drawable.likes)
                    post.likes--
                }
                likeCount.text = countDelim(post.likes)
            }

            repostsIcon.setOnClickListener {
                post.reposts++
                repostsCount.text = countDelim(post.reposts)
            }
        }

        val padding = resources.getDimensionPixelSize(R.dimen.common_spacing)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + padding, systemBars.top + padding, systemBars.right + padding, systemBars.bottom + padding)
            insets
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