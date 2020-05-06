import React from 'react';
import './App.css';
import AlarmList from './components/alarm-list.component'

function App() {
  return (
    <div className="App">
      <h1 className="banner">Fire Alarm List</h1>
      <AlarmList/>
    </div>
  );
}

export default App;
