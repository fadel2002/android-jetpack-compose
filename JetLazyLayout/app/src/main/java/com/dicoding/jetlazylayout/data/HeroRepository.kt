package com.dicoding.jetlazylayout.data

import com.dicoding.jetlazylayout.model.Hero
import com.dicoding.jetlazylayout.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
    fun searchHeroes(query: String): List<Hero>{
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}