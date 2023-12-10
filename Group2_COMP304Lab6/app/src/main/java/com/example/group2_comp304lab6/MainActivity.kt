package com.example.group2_comp304lab6


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.group2_comp304lab6.ui.theme.Group2_COMP304Lab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Group2_COMP304Lab6Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController)

                    // handle back press event
                    this.onBackPressedDispatcher.addCallback(this) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

}


@Composable
// handle navigation
fun Navigation(navController: NavHostController) {

    // by default Program screen is the default screen
    NavHost(navController = navController, startDestination = "program") {
        composable("program") {
            ShowPrograms(navController)
        }
        composable(
            route = "courses/{program}",
            arguments = listOf(
                navArgument("program") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            // read the program argument
            val program = entry.arguments?.getString("program")
            ShowCourse(program.toString())
        }
    }
}


@Composable
// Program Screen for showing all the software programs
fun ShowPrograms(navController: NavController) {
    // load program from string array
    val programs: Array<String> = stringArrayResource(R.array.programs)

    Group2_COMP304Lab6Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = { topBar("Programs") },
            ) {
                LazyColumn {
                    itemsIndexed(
                        programs
                    ) { _, program ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("courses/${program}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp, horizontal = 10.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                            ) {
                                Text(
                                    text = program,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
            }

        }
    }
}

@Composable
// the title bar
fun topBar(title: String) {
    TopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    })
}

@Composable
// Course Screen for listing the courses of selected program
fun ShowCourse(program: String) {
    val allCourses = getCourse(program)
    if (allCourses != null) {
        Group2_COMP304Lab6Theme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(
                    topBar = { topBar("Courses") },
                ) {
                    LazyColumn {
                        itemsIndexed(allCourses) { _, course ->
                            CourseItem(course = course)
                        }
                    }
                }
            }
        }
    }

}

@Composable
// UI for each course row in the ShowCourse Screen
fun CourseItem(course: String) {
    var descriptionDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                descriptionDialog.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text(
                text = course,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }

    // show dialog if course button was clicked
    if (descriptionDialog.value) {
        CourseDescriptionDialog(
            course = course,
            onDismiss = { descriptionDialog.value = false }
        )
    }
}


@Composable
// function for showing the dialog
fun CourseDescriptionDialog(course: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = course)
        },
        text = {
            Text(text = getCourseDescription(course))
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        }
    )

}

@Composable
// function for getting course description from the StringArray
fun getCourseDescription(course: String): String {
    val courses: Array<String> = stringArrayResource(R.array.courses)
    val coursesDescription: Array<String> = stringArrayResource(R.array.courseDescription)
    val idx = courses.indexOf(course)

    return coursesDescription[idx]
}

@Composable
// function for getting courses of a given program from the StringArray
fun getCourse(program: String): Array<String>? {
    val programs: Array<String> = stringArrayResource(R.array.programs)
    val idx = programs.indexOf(program)
    var courses: Array<String>? = null
    when (idx) {
        0 -> courses = stringArrayResource(R.array.programs0)
        1 -> courses = stringArrayResource(R.array.programs1)
        2 -> courses = stringArrayResource(R.array.programs2)
        3 -> courses = stringArrayResource(R.array.programs3)
        4 -> courses = stringArrayResource(R.array.programs4)
        5 -> courses = stringArrayResource(R.array.programs5)
    }
    return courses
}
