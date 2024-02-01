package com.niloy.viewmodeltest.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel(private  val repository: Repository):ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>(repository.getTodos())
    val todos: LiveData<List<Todo>> = _todos

    fun addTodoInList(tittle: String){
        repository.addTodo(Todo(todos.value!!.size+1,tittle,false))
        _todos.value = repository.getTodos()
    }
    fun updateTodoInList(todo: Todo){
        repository.updateTodo(todo)
        _todos.value = repository.getTodos()
    }
}