package com.example.stylish2

import android.content.Context
import java.util.*

object ThemeSetter {

    private var preferredSeason: Season? = null
    private var isDark = false

    private val winterMonths = listOf(12, 1, 2)
    private val springMonths = listOf(3, 4, 5)
    private val summerMonths = listOf(6, 7, 8)
    private val autumnMonths = listOf(9, 10, 11)

    private fun getSeason(): Season {
        return preferredSeason?.let { it } ?: run {
            val month = Calendar.getInstance().get(Calendar.MONTH)
            when {
                winterMonths.contains(month) -> Season.Winter
                springMonths.contains(month) -> Season.Spring
                summerMonths.contains(month) -> Season.Summer
                autumnMonths.contains(month) -> Season.Autumn
                else -> Season.Spring
            }
        }
    }

    fun setTheme(context: Context, isDark: Boolean = false, season: Season? = null) {
        this.isDark = isDark
        this.preferredSeason = season
        context.setTheme(getTheme().themeID)
    }

    private fun getTheme(): Theme {
        return when (getSeason()) {
            Season.Winter -> if (!isDark) Theme.Winter else Theme.WinterDark
            Season.Spring -> if (!isDark) Theme.Spring else Theme.SpringDark
            Season.Summer -> if (!isDark) Theme.Summer else Theme.SummerDark
            Season.Autumn -> if (!isDark) Theme.Autumn else Theme.AutumnDark
        }
    }

    enum class Season {
        Winter,
        Spring,
        Summer,
        Autumn
    }

    private enum class Theme(val themeID: Int) {
        Winter(R.style.winter),
        WinterDark(R.style.dark_winter),
        Summer(R.style.summer),
        SummerDark(R.style.dark_summer),
        Spring(R.style.spring),
        SpringDark(R.style.dark_spring),
        Autumn(R.style.autumn),
        AutumnDark(R.style.dark_autumn),
    }
}