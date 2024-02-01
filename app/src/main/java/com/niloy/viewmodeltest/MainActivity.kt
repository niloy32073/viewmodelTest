package com.niloy.viewmodeltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.niloy.viewmodeltest.model.Repository
import com.niloy.viewmodeltest.model.Todo
import com.niloy.viewmodeltest.model.TodoViewModel
import com.niloy.viewmodeltest.ui.theme.ViewModelTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoView(viewModel = TodoViewModel(repository = Repository()))
                }
            }
        }
    }
}

@Composable
fun TodoView(viewModel: TodoViewModel){
    val todos by viewModel.todos.observeAsState(emptyList())
    var tittle by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(){
            items(todos){todo->
                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(text = todo.tittle)
                    if(!todo.isComplete)
                        Button(onClick = { viewModel.updateTodoInList(Todo(todo.id,todo.tittle,true)) }) {
                            Text(text = "Done")
                        }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(value = tittle, onValueChange ={tittle=it} , label = { Text(text = "Tittle")})
            Button(onClick = { viewModel.addTodoInList(tittle)
            tittle = ""}) {
                Text(text = "Add")
            }
        }
    }
}