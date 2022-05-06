import { useState, useContext ,useEffect} from 'react'
import TaskContext from './context/TaskContext'

function TaskForm() {
  const [name, setName] = useState('')
  const [description, setDescription] = useState('')
  const { addTask, fetchTask, taskEdit , updateTask} = useContext(TaskContext)
  useEffect(() => {
    if (taskEdit.edit === true) {
     // setBtnDisabled(false)
     setDescription(taskEdit.task.description)
      setName(taskEdit.task.name)
    }
  }, [taskEdit])
  const handleName = ({ target: { value } }) => {
    setName(value)
  }
  const handleDescription = ({ target: { value } }) => {
    setDescription(value)
  }
  const handleSubmit = (e) => {
    e.preventDefault()
    console.log("Inside submit")
    const newTask = {
      name,
      description
    }
    if (taskEdit.edit === true) {
      updateTask(taskEdit.task.id, newTask)
      alert("Task Updated Successfully")
    } else {
      addTask(newTask)
      alert("Task Added Successfully")
    }

    //addTask(newTask);
    
    setName('')
    setDescription('')
    fetchTask()

  }
  return (
    <>
      <form onSubmit={handleSubmit}>
        <br></br><br></br>
        <h5 align="center">Task board</h5>
        <div className="form-group">
          <label htmlFor="usr">Task Name</label>
          <input type="text" className="form-control" id="usr" placeholder='Meeting' value={name} onChange={handleName} required />
          <br></br>
        </div>

        <div className="form-group">
          <label htmlFor="comment">Description</label>
          <textarea className="form-control" rows="5" maxLength="500" id="comment" value={description} onChange={handleDescription} required></textarea>
        </div>
        <div align="right">
          <br></br>
          <button type="submit" className="btn btn-primary">POST</button>
        </div>
      </form>
    </>
  )
}

export default TaskForm

