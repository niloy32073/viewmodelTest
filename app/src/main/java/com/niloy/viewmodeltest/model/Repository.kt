package com.niloy.viewmodeltest.model

class Repository {
    private var todos = mutableListOf<Todo>(
        Todo(1,"a",false),
        Todo(2,"b",true)
    )
    fun getTodos():List<Todo> = todos
    fun addTodo(todo:Todo){
        todos.add(todo)
    }
    fun updateTodo(todo: Todo){
        val index = todos.indexOfFirst { it.id==todo.id }
        if(index>-1){
            todos[index] = todo
        }
    }
}