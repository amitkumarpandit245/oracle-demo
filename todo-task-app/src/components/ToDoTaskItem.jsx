import PropTypes from 'prop-types'
import { useContext } from 'react'
import { FaTimes, FaEdit } from 'react-icons/fa'
import TaskContext from './context/TaskContext'
function ToDoTaskItem({ task }) {

  const { deleteTask, editTask } = useContext(TaskContext)

  return (<>
    <div>
      <table className="table table-bordered">
        <tbody>
          <tr>
            <td>{task.name}  <br></br> {task.description}</td>
            <td width="10px">
              <button action="submit" onClick={() => deleteTask(task.id)}><FaTimes color='purple' /></button>
              <button action="submit" onClick={() => editTask(task)}><FaEdit color='purple' /></button>
            </td>
            {/* <td><button action="submit"  onClick={()=>deleteFeedback(feedback.id)}><FaTimes color='purple' /></button></td>
            <td><button action="submit"  onClick={()=>editFeedback(feedback)}><FaEdit color='purple' /></button></td> */}
          </tr>
        </tbody>
      </table>
    </div>
  </>
  )
}

ToDoTaskItem.propTypes = {
  feedback: PropTypes.object.isRequired,
}

export default ToDoTaskItem