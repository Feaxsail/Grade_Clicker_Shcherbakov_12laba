package com.shcherbakov.grade_clicker_androidstudio.data

import com.shcherbakov.grade_clicker_androidstudio.R
import com.shcherbakov.grade_clicker_androidstudio.model.Grade

class Datasource {
    val gradeList = listOf(
        Grade(R.drawable.grade_0, 5, 0),
        Grade(R.drawable.grade_50, 5, 50),
        Grade(R.drawable.grade_70, 5, 70),
        Grade(R.drawable.grade_90, 5, 90),
        Grade(R.drawable.grade_100, 5, 100)
    )
}

