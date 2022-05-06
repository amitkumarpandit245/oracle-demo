import { useContext } from 'react'
import ToDoTaskItem from './ToDoTaskItem'
import Spinner from './shared/Spinner'
import TaskContext from './context/TaskContext'

function TaskList() {
  const { task, isLoading } = useContext(TaskContext)

  if (!isLoading && (!task || task.length === 0)) {
    return <p>No To Do Task Yet</p>
  }

  return isLoading ? (
    <Spinner />
  ) : (
    <div className='task-list'>
      <br></br>
      <h5 align="center">To Do List</h5>
      {task.map((task, i) => (
        <ToDoTaskItem key={i} task={task} />
      ))}
    </div>
  )

}

export default TaskList