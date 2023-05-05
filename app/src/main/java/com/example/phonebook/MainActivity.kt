package com.example.phonebook

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.phonebook.routing.MyNotesRouter
import com.example.phonebook.routing.Screen
import com.example.phonebook.screen.NotesScreen
import com.example.phonebook.screen.SaveNoteScreen
import com.example.phonebook.screen.TrashScreen
import com.example.phonebook.ui.theme.MyNotesThemeSettings
import com.example.phonebook.ui.theme.PhoneBookTheme
import com.example.phonebook.viewmodel.MainViewModel
import com.example.phonebook.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneBookTheme(darkTheme = MyNotesThemeSettings.isDarkThemeEnabled) {
                // A surface container using the 'background' color from the theme
                val viewModel: MainViewModel = viewModel(
                        factory = MainViewModelFactory(LocalContext.current.applicationContext as Application)
                        )
                MainActivityScreen(viewModel)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainActivityScreen(viewModel: MainViewModel){
    Surface {
        when (MyNotesRouter.currentScreen){ //ใช้when มันคล้ายๆ swith
            is Screen.Notes -> NotesScreen(viewModel)
            is Screen.SaveNote -> SaveNoteScreen(viewModel)
            is Screen.Trash -> TrashScreen(viewModel)
        }

    }
}