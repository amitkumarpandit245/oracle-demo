import logo from './logo.svg';
import './App.css';
import { TaskProvider } from './components/context/TaskContext';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';

function App() {
  return (
    <TaskProvider>
        <div className="container">
            <TaskForm />
            <TaskList/>
        </div>
    </TaskProvider>
)
}

export default App;
