const router = require('express').Router();
const nodemailer = require('nodemailer');

const transporter = nodemailer.createTransport({
    service:'outlook',
    auth:{
 //       user:'enter email address',
  //      pass:'enter password'
    }
});

let Alarm = require('../models/alarm.module')

router.route('/').get((req,res)=>{
    Alarm.find()
        .then(alarms=>res.json(alarms))
        .catch(err=>err.status(400).json('Error:'+err))
        
})

router.route('/:id').get((req,res)=>{
    Alarm.findById(req.params.id)
        .then(alarm=>res.json(alarm))
        .catch(err=>res.status(400).json("Err:"+err));
})

router.route('/add').post((req,res)=>{
    const name = req.body.name;
    const floor = req.body.floor;
    const room =  req.body.room;
    const co2 = req.body.co2;
    const smoke = req.body.smoke;
    const isAlarmActive = req.body.isAlarmActive;

    const newAlarm= new Alarm({
        name,
        floor,
        room,
        co2,
        smoke,
        isAlarmActive
    })

    newAlarm.save()
        .then(()=>res.json("Alarm Added"))
        .catch(err=>res.status(400).json('Err:'+err))
})


router.route('/update/:id').put((req,res)=>{
    Alarm.findById(req.params.id)
        .then(alarm=>{
            alarm.name=req.body.name;
            alarm.floor=req.body.floor;
            alarm.room=req.body.room;
            alarm.co2=req.body.co2;
            alarm.smoke=req.body.smoke;
            alarm.isAlarmActive=req.body.isAlarmActive;
            if(alarm.co2 >5 || alarm.smoke > 5){
                alarm.isAlarmActive = true;
                let mailOptions={
                    from:'it18144772@my.sliit.lk',
                    to: 'niroshantrinity@gmail.com',
                    subject:'Fire Alarm Active',
                    text:`Fire ${alarm.name} at floor ${alarm.floor} in the room ${alarm.room} is Activated,Please take necessary steps to control the fire.`
                }
                
                transporter.sendMail(mailOptions, function(error, info){
                    if (error) {
                      console.log(error);
                    } else {
                      console.log('Email sent: ' + info.response);
                    }
                  });
            }
            else{
                alarm.isAlarmActive = false;
            }

            

            alarm.save()
                .then(()=>res.json("Alarm updated!"))
                .catch(err=>res.status(400).json("Err:"+ err));
        })
        .catch(err=>res.status(400).json("Err:"+err))
})

module.exports = router;

