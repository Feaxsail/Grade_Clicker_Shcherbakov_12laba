package com.shcherbakov.grade_clicker_androidstudio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shcherbakov.grade_clicker_androidstudio.data.Datasource
import com.shcherbakov.grade_clicker_androidstudio.model.Grade
import com.shcherbakov.grade_clicker_androidstudio.ui.theme.GradeClickerTheme

class MainActivity : ComponentActivity() {


    private companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate Called")

        enableEdgeToEdge()
        setContent {
            GradeClickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    GradeClickerApp()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }
}

@Composable
fun GradeClickerApp(
    grades: List<Grade> = Datasource().gradeList
) {

    var points by rememberSaveable { mutableStateOf(0) }
    var clicks by rememberSaveable { mutableStateOf(0) }

    val currentGrade = getCurrentGrade(points, grades)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(currentGrade.imageId),
            contentDescription = stringResource(R.string.current_grade),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .clickable {
                    points += currentGrade.pointsPerClick
                    clicks++
                }
        )

        TransactionInfo(
            points = points,
            clicks = clicks
        )
    }
}

@Composable
fun TransactionInfo(
    points: Int,
    clicks: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "${stringResource(R.string.points_earned)}: $points",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "${stringResource(R.string.total_clicks)}: $clicks",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun getCurrentGrade(points: Int, grades: List<Grade>): Grade {
    var currentGrade = grades.first()
    for (grade in grades) {
        if (points >= grade.threshold) {
            currentGrade = grade
        }
    }
    return currentGrade
}

@Preview(showBackground = true)
@Composable
fun GradeClickerAppPreview() {
    GradeClickerTheme {
        GradeClickerApp()
    }
}