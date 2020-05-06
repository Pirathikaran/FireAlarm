import React, { Component } from 'react'
import AlarmItem from './alarm-item.components'

export default class AlarmList extends Component{
    constructor(props){
        super(props);

        this.state={
            alarms:[]
        }
    }

    componentDidMount=()=>{
        this.interval=setInterval(()=>fetch("http://localhost:5001/alarm/")
        .then(res=> res.json())
        .then(data=> {
            this.setState({
                alarms:data
            })
        }),10000);
    }

    renderAlarm=()=>{
        return this.state.alarms.map(Currentalarm=>{
            console.log(Currentalarm.isAlarmActive);
            return <AlarmItem alarmData={Currentalarm} key={Currentalarm._id}/>
        })
    }

    render(){
        return(
            <div className="flex-container">
                {this.renderAlarm()}
            </div>
        );
    }

    componentWillUnmount(){
        clearInterval(this.interval);
    }

}

