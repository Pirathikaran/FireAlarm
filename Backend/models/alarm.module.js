const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const alarmSchema = new Schema({
    name:{type:String, required:true},
    floor:{type:Number, required:true},
    room:{type:Number, required:true},
    co2:{type:Number, required:true},
    smoke:{type:Number, required:true},
    isAlarmActive:{type:Boolean, required:true}
})

const Alarm = mongoose.model('Alarm',alarmSchema)

module.exports = Alarm;