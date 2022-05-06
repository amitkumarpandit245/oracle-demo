import { createContext, useState, useEffect } from 'react'

const TaskContext = createContext()

export const TaskProvider = ({ children }) => {
  const [isLoading, setIsLoading] = useState(true)
  const [task, setTask] = useState([])
  const [taskEdit, setTaskEdit] = useState({
    task: {},
    edit: false,
  })

  useEffect(() => {
    fetchTask()
  }, [])

  // Fetch feedback
  const fetchTask = async () => {
    const response = await fetch(`http://localhost:8080/api/v1/tasks`)
    const data = await response.json()

    setTask(data)
    setIsLoading(false)
  }

  const addTask = async (newTask) => {
    const response = await fetch('http://localhost:8080/api/v1/task', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newTask),
    })

    const data = await response.json()

    setTask([data, ...task])
  }
  const deleteTask = async (id) => {
    await fetch(`http://localhost:8080/api/v1/task/${id}`, {
      method: 'DELETE',
    })
    alert("Task Deleted Successfully")
    fetchTask();
  }

  const updateTask = async (id, updatedTask) => {
    const response = await fetch(`http://localhost:8080/api/v1/task/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedTask),
    })

    const data = await response.json()

    setTask([data, ...task])

    setTaskEdit({
      task: {},
      edit: false,
    })
  }

    // Set item to be updated
    const editTask = (task) => {
      setTaskEdit({
        task,
        edit: true,
      })
    }

  return (
    <TaskContext.Provider
      value={{
        task,
        isLoading,
        taskEdit,
        fetchTask,
        addTask,
        deleteTask,
        updateTask,
        editTask
      }}
    >
      {children}
    </TaskContext.Provider>
  )
}

export default TaskContext