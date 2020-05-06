import React, { Component } from 'react'
import {Card} from 'react-bootstrap'
import '../Card-Style.css'


export default class AlarmItem extends Component{
    constructor(props){
        super(props)
        
        this.state={
            status:""
        }
    }

    alarmStatus=(props)=>{
        if(this.props.alarmData.isAlarmActive===true){
            this.state.status="Active"
            return <img src={ require('../images/true.png') } />    
        }
        else{
            this.state.status="Deactive"
            return <img src={ require('../images/false.png') } />
        }
    }

    render(){
        const {isAlarmActive} = this.props.alarmData;
        return(
            <Card className="card-item">
                <div className="card-title">{this.props.alarmData.name}</div>
                <div className="status">{this.alarmStatus()}</div>
                <div className="status" style={{color:isAlarmActive ? "rgb(8, 138, 8)":"black"}}>{this.state.status}</div>
                <div className="floor-text">Floor: {this.props.alarmData.floor}</div>
                <div className="room-text">Room: {this.props.alarmData.room}</div>
                <div className="co2-text">CO2 level: {this.props.alarmData.co2}</div>
                <div className="smoke-test">Smoke level: {this.props.alarmData.co2}</div>
            </Card>
        );
    }
}